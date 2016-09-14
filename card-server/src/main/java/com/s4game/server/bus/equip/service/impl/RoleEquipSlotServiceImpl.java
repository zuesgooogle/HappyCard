package com.s4game.server.bus.equip.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.s4game.core.event.IEventService;
import com.s4game.server.bus.bag.service.IBagService;
import com.s4game.server.bus.equip.dao.IRoleEquipSlotDao;
import com.s4game.server.bus.equip.dao.filter.EquipSlotNumFilter;
import com.s4game.server.bus.equip.entity.RoleEquipSlot;
import com.s4game.server.bus.equip.event.publish.EquipChangeEvent;
import com.s4game.server.bus.equip.output.EquipErrorCode;
import com.s4game.server.bus.equip.output.EquipSlotOutput;
import com.s4game.server.bus.equip.service.IRoleEquipSlotService;
import com.s4game.server.bus.equip.util.EquipUtils;
import com.s4game.server.bus.role.export.IRoleExportService;
import com.s4game.server.bus.role.export.RoleWrapper;
import com.s4game.server.bus.share.service.IRoleStateService;
import com.s4game.server.gamerule.goods.configure.export.IGoodsConfigService;
import com.s4game.server.gamerule.goods.configure.export.impl.GoodsConfig;
import com.s4game.server.gamerule.goods.creator.ItemModel;
import com.s4game.server.gamerule.goods.util.GoodsCategoryUtil;
import com.s4game.server.gamerule.goods.util.GoodsConvertUtil;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月14日 下午6:54:42
 *
 */
@Component
public class RoleEquipSlotServiceImpl implements IRoleEquipSlotService {

	private Logger LOG = LoggerFactory.getLogger(getClass());
	
    @Resource
    private IRoleEquipSlotDao roleEquipSlotDao;
    
    @Resource
    private IBagService bagService;
    
    @Resource
    private IRoleExportService roleExportService;
    
    @Resource
    private IRoleStateService roleStateService;
    
    @Resource
    private IGoodsConfigService goodsConfigService;
    
    @Resource
    private IEventService eventService;
    
    @Override
    public Object[] dressEquip(String roleId, String guid, int bagSlot, int equipSlot) {
    	if( EquipUtils.isBagSlot(bagSlot) && EquipUtils.isEquipSlot(equipSlot) ) {
    		return loadEquip(roleId, guid, bagSlot, equipSlot);
    	}
    	
    	if(EquipUtils.isEquipSlot(bagSlot) && EquipUtils.isBagSlot(equipSlot)) {
    		return unloadEquip(roleId, guid, bagSlot, equipSlot);
    	}
    	
    	return null;
    }

	@Override
	public Object[] loadEquip(String roleId, String guid, int bagSlot, int equipSlot) {
		ItemModel itemModel = GoodsConvertUtil.entity2Model(bagService.loadGoodsBySlot(roleId, bagSlot));
		if( null == itemModel ) {
			LOG.error("goods not exist. guid: {}", guid);
			return EquipSlotOutput.getErrorCode(EquipErrorCode.EQUIP_ERROE_31001, null);
		}
		
		// database id not same
		if( !guid.equals(itemModel.getId()) ) {
			return EquipSlotOutput.getErrorCode(EquipErrorCode.EQUIP_ERROE_31002, null);
		}
		
		//装备配置合法性验证
		Object[] checkResult = checkEquip(roleId, itemModel.getGoodsId()); 
		if( null !=  checkResult) {
			return checkResult;
		}
		
		/**
		 * 装备位已经存在装备，进行卸载
		 */
		if( existEquip(roleId, equipSlot) ) {
			RoleEquipSlot oldEqup = getRoleEquipSlot(roleId, equipSlot);
			
			//移除现有装备
			this.roleEquipSlotDao.cacheDelete(oldEqup.getId(), roleId);
			
			//装载新装备
			RoleEquipSlot newEquip = addEquip(roleId, itemModel, equipSlot);
			
			//背包删除物品
			this.bagService.removeGoodsById(roleId, guid);
			
			//背包新增替换下来的装备
			ItemModel oldEquipModel = GoodsConvertUtil.entity2Model(oldEqup);
			oldEquipModel.setSlotNum(bagSlot);
			this.bagService.putInBag1(roleId, oldEquipModel);
			
			//装备佩戴成功
			eventService.publish(new EquipChangeEvent(roleId, getEquipIds(roleId)));
			
			//TODO 返回消息
		}
		
		//装载新装备
		RoleEquipSlot newEquip = addEquip(roleId, itemModel, equipSlot);
		//背包删除物品
		this.bagService.removeGoodsById(roleId, guid);

		eventService.publish(new EquipChangeEvent(roleId, getEquipIds(roleId)));
		
		return null;
	}

	@Override
	public Object[] unloadEquip(String roleId, String guid, int equipSlot, int bagSlot) {
		if( !existEquip(roleId, equipSlot) ) {
			return EquipSlotOutput.getErrorCode(EquipErrorCode.EQUIP_ERROE_31002, null);
		}
		
		RoleEquipSlot oldEqup = getRoleEquipSlot(roleId, equipSlot);
		ItemModel itemModel = GoodsConvertUtil.entity2Model(oldEqup);
		
		//没有格子
		if (!this.bagService.checkBagSlots1(roleId, itemModel)) {
			return EquipSlotOutput.getErrorCode(EquipErrorCode.EQUIP_ERROE_31003, null);
		}
		
		//放入背包
		this.bagService.putInBag1(roleId, itemModel);
		
		//删除装备
		this.removeEquip(roleId, equipSlot);
		
		eventService.publish(new EquipChangeEvent(roleId, getEquipIds(roleId)));
		
		return null;
	}

	private boolean removeEquip(String roleId, int slotNum) {
	    List<RoleEquipSlot> list = this.roleEquipSlotDao.cacheLoadAll(roleId, new EquipSlotNumFilter(slotNum));
	    if( list != null && !list.isEmpty() ) {
	        RoleEquipSlot roleRequipSlot = list.get(0);
	        this.roleEquipSlotDao.cacheDelete(roleRequipSlot.getId(), roleId);
	        
	        return true;
	    }
	    return false;
	}
	
	private Object[] getEquipIds(String roleId) {
		List<RoleEquipSlot> list = this.roleEquipSlotDao.cacheLoadAll(roleId);
		if (list == null) {
			return null;
		}
		
		ArrayList<String> ids = new ArrayList<String>();
		for (RoleEquipSlot equip : list) {
			ids.add( equip.getGoodsId() );
		}
		
		return ids.toArray();
	}
	
	private RoleEquipSlot addEquip(String roleId, ItemModel itemModel, int soltNum) {
		//佩戴直接绑定
		itemModel.setBind(1);
		
		RoleEquipSlot roleEquipSlot = copyRoleEquipSlot(itemModel, roleId, soltNum);
		this.roleEquipSlotDao.cacheInsert(roleEquipSlot, roleId);
		return roleEquipSlot;
	}

	private RoleEquipSlot copyRoleEquipSlot(ItemModel itemModel, String roleId, int soltNum) {
		RoleEquipSlot roleEquipSlot = new RoleEquipSlot();
		roleEquipSlot.setId(itemModel.getId());
		roleEquipSlot.setGoodsId(itemModel.getGoodsId());
		roleEquipSlot.setBind(itemModel.getBind());
		roleEquipSlot.setCount(itemModel.getGoodsCount());
		roleEquipSlot.setItemLevel(itemModel.getItemLevel());
		roleEquipSlot.setRareLevel(itemModel.getRareLevel());
		roleEquipSlot.setUserRoleId(roleId);
		roleEquipSlot.setSlotNum(soltNum);
		roleEquipSlot.setExpireTime(itemModel.getExpireTime());
		roleEquipSlot.setAttributes(itemModel.getAttributes());
		return roleEquipSlot;
	}

	
	
	private Object[] checkEquip(String roleId, String goodsId) {
		RoleWrapper role = roleExportService.getRole(roleId);
		GoodsConfig goodsConfig = goodsConfigService.loadById(goodsId);
		
		if( null == goodsConfig ) {
			return EquipSlotOutput.getErrorCode(EquipErrorCode.ERROR_31004, null);
		}
		
		//equp category
		if( !GoodsCategoryUtil.isEquip(goodsConfig.getCategory()) ) {
			return EquipSlotOutput.getErrorCode(EquipErrorCode.ERROR_31005, null);
		}
		
		//job limit
		if( !role.getJob().equals(goodsConfig.getJob()) ) {
			return EquipSlotOutput.getErrorCode(EquipErrorCode.ERROR_31004, null);
		}
		
		//level limit
		if( role.getLevel().intValue() < goodsConfig.getLevelReq() ) {
			return EquipSlotOutput.getErrorCode(EquipErrorCode.ERROR_31007, null);
		}
		
		return null;
	}
	
    private boolean existEquip(String roleId, int equipSlot) {
        return this.roleEquipSlotDao.cacheLoadAll(roleId, new EquipSlotNumFilter(equipSlot)) != null;
    }

	@Override
	public RoleEquipSlot getRoleEquipSlot(String roleId, int slotNum) {
		if( roleStateService.isOnline(roleId) ) {
			List<RoleEquipSlot> list = this.roleEquipSlotDao.cacheLoadAll(roleId, new EquipSlotNumFilter(slotNum));
			if( null == list || list.isEmpty() ) {
				return null;
			}
			return list.get(0);
		}
		
		return this.roleEquipSlotDao.getRoleEquipSlotFromDb(roleId, slotNum);
	}
	
}
