package com.s4game.server.bus.bag.service.impl;

import static com.s4game.server.gs.sycn.GsSyncComponent.COMPONENT_NAME;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.data.IQueryFilter;
import com.s4game.core.sync.annotation.Sync;
import com.s4game.server.bus.bag.BagModuleInfo;
import com.s4game.server.bus.bag.command.BagCommands;
import com.s4game.server.bus.bag.comparator.BagSlotNumComparator;
import com.s4game.server.bus.bag.comparator.GoodsConsumeComparator;
import com.s4game.server.bus.bag.constants.BagConstants;
import com.s4game.server.bus.bag.dao.IRoleBagSlotDao;
import com.s4game.server.bus.bag.dao.filter.GoodsAmountFilter;
import com.s4game.server.bus.bag.dao.filter.GoodsIdFilter;
import com.s4game.server.bus.bag.dao.filter.IQueryCountFilter;
import com.s4game.server.bus.bag.dao.filter.SlotNumFilter;
import com.s4game.server.bus.bag.dao.filter.StoreSlotFilter;
import com.s4game.server.bus.bag.entity.RoleBagSlot;
import com.s4game.server.bus.bag.export.response.BagResponse;
import com.s4game.server.bus.bag.export.response.IBagDecrSingleResponse;
import com.s4game.server.bus.bag.export.response.IBagIncrSingleResponse;
import com.s4game.server.bus.bag.export.response.IBagRemoveResponse;
import com.s4game.server.bus.bag.output.BagErrorCode;
import com.s4game.server.bus.bag.service.IBagService;
import com.s4game.server.bus.bag.util.BagClearupUtil;
import com.s4game.server.bus.bag.util.ClearBagGoodsCheck;
import com.s4game.server.bus.id.export.IdGenerator;
import com.s4game.server.bus.swap.BusMsgSender;
import com.s4game.server.gamerule.goods.configure.export.IGoodsConfigService;
import com.s4game.server.gamerule.goods.configure.export.impl.GoodsConfig;
import com.s4game.server.gamerule.goods.creator.ItemModel;
import com.s4game.server.gamerule.goods.entity.StoreGoods;
import com.s4game.server.gamerule.goods.output.StoreGoodsOutput;
import com.s4game.server.gamerule.goods.util.GoodsConvertUtil;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月11日 下午3:26:37
 *
 */
@Component
public class BagServiceImpl implements IBagService {

    @Resource
    private IRoleBagSlotDao roleBagSlotDao;
    
    @Resource
    private IdGenerator idGenerator;
    
    @Resource
    private IGoodsConfigService goodsConfigService;
    
    @Resource
    private BusMsgSender busMsgSender;
    
    @Override
    public void createRoleBagSlot(String roleId) {
        //TODO 初始化，默认物品
        ItemModel itemModel = new ItemModel("goodsId", 1);
        
        StoreGoods storeGoods = GoodsConvertUtil.model2Entity(itemModel);
        
        RoleBagSlot bagSlot = new RoleBagSlot(storeGoods);
        bagSlot.setId( idGenerator.getId4Module(BagModuleInfo.MODULE_NAME) );
        bagSlot.setUserRoleId(roleId);
        bagSlot.setSlotNum(BagConstants.BAG_MIN);
        
        this.roleBagSlotDao.insertDb(bagSlot);
    }
    
    @Sync(component = COMPONENT_NAME, indexes = {0})
    @Override
    public IBagIncrSingleResponse putInBag1(String roleId, ItemModel itemModel) {
        BagResponse response = new BagResponse();
        if( !checkBagSlots1(roleId, itemModel) ) {
            response.setErrorCode(BagErrorCode.BAG_SLOT_LESS);
            return response;
        }
        
        putOneGoodsInBag(roleId, itemModel, response);
        return response;
    }
    
    @Sync(component = COMPONENT_NAME, indexes = {0})
    @Override
    public BagResponse putInBag1(String roleId, List<ItemModel> items) {
        BagResponse response = new BagResponse();
        if( items == null || items.isEmpty() ) {
            return response;
        }
        
        if( !checkBagSlots1(roleId, items) ) {
            response.setErrorCode(BagErrorCode.BAG_SLOT_LESS);
            return response;
        }
        
        for (ItemModel itemModel : items) {
            putOneGoodsInBag(roleId, itemModel, response);
        }
        
        return response;
    }
    
    @Sync(component = COMPONENT_NAME, indexes = {0})
    @Override
    public boolean checkBagSlots1(String roleId, ItemModel itemModel) {
        if( itemModel.getGoodsCount() <= 0 ) {
            return true;
        }
        
        List<RoleBagSlot> list = roleBagSlotDao.cacheLoadAll(roleId, new StoreSlotFilter(BagConstants.BAG_MIN, BagConstants.BAG_MAX));
        int emptySlotCount = getEmptySlotCount(list);
        if( emptySlotCount >= 1 ) {
            return true;
        }
        
        GoodsConfig goodsConfig = getGoodsConfigById(itemModel.getGoodsId());
        if( null == goodsConfig ) {
            return true;
        }
        
        int maxStack = goodsConfig.getMaxStack();
        int goodsCount = itemModel.getGoodsCount();
        
        for (RoleBagSlot bag : list) {
            if( canStack(bag, itemModel, maxStack) ) {
                goodsCount -= maxStack - bag.getCount();
                if( goodsCount <= 0 ) {
                    return true;
                }
            }
        }
        
        return goodsCount <= 0;
    }
    
    @Override
    public boolean checkBagSlots1(String roleId, List<ItemModel> items) {
        if( items == null || items.isEmpty() ) {
            return true;
        }
        
        List<RoleBagSlot> list = this.roleBagSlotDao.cacheLoadAll(roleId, new StoreSlotFilter(BagConstants.BAG_MIN, BagConstants.BAG_MAX));
        int emptySlotCount = getEmptySlotCount(list);
        
        return emptySlotCount >= items.size();
    }

    
    private int getEmptySlotCount(List<RoleBagSlot> list) {
        int existSlotCount = 0;
        if( list != null ) {
            existSlotCount = list.size();
        }
        return BagConstants.BAG_MAX - existSlotCount;
    }
    
    private void putOneGoodsInBag(String roleId, ItemModel itemModel, BagResponse response) {
        GoodsConfig goodsConfig = getGoodsConfigById(itemModel.getGoodsId());
        if( null == goodsConfig ) {
            return;
        }
        
        int maxStack = goodsConfig.getMaxStack();
        int goodsCount = itemModel.getGoodsCount();
        
        List<RoleBagSlot> list = roleBagSlotDao.cacheLoadAll(roleId, new GoodsIdFilter(itemModel.getGoodsId()));
        if( list != null ) {
            Collections.sort(list, new BagSlotNumComparator());
        }
        
        RoleBagSlot roleBagSlot;
        if( list != null ) {
            Iterator<RoleBagSlot> iter = list.iterator();
            while( iter.hasNext() ) {
                roleBagSlot = iter.next();
                //判定叠加
                if( canStack(roleBagSlot, itemModel, maxStack) ) {
                    int remainCount = maxStack - roleBagSlot.getCount();
                    int addCount = remainCount > goodsCount ? goodsCount : remainCount;
                    
                    response.addUpdateSlot(roleBagSlot, addCount);
                    response.setGuid(roleBagSlot.getId());
                    
                    roleBagSlot.setCount(roleBagSlot.getCount() + addCount);
                    this.roleBagSlotDao.cacheUpdate(roleBagSlot, roleId);
                    
                    goodsCount -= addCount;
                    if( goodsCount <= 0 ) {
                        break;
                    }
                }
            }//end while
        }
        
        //new slot
        if( goodsCount > 0 ) {
            StoreGoods storeGoods = GoodsConvertUtil.model2Entity(itemModel);
            roleBagSlot = new RoleBagSlot(storeGoods);
            
            roleBagSlot.setId( idGenerator.getId4Module(BagModuleInfo.MODULE_NAME) );
            roleBagSlot.setUserRoleId(roleId);
            roleBagSlot.setCount(goodsCount);
            roleBagSlot.setSlotNum(getOneSlotNum(roleId));
            this.roleBagSlotDao.cacheInsert(roleBagSlot, roleId);
            
            response.setGuid(roleBagSlot.getId());
            response.addAddSlot(roleBagSlot);
            
            //autouse
        }
    }
    
    private int getOneSlotNum(String roleId) {
        List<RoleBagSlot> list = roleBagSlotDao.cacheLoadAll(roleId, new StoreSlotFilter(BagConstants.BAG_MIN, BagConstants.BAG_MAX));
        if( null == list ) {
            return 1;
        }
        
        //order by slotNum asc.
        Collections.sort(list, new BagSlotNumComparator());
        if( list.size() >= BagConstants.BAG_MAX ) {
            return -1;
        }
        
        int slot = 1;
        for (RoleBagSlot bag : list) {
            if( bag.getSlotNum() > slot ) {
                return slot;
            }
            slot ++;
        }
        
        return slot;
    }
    
    /**
     * 是否能叠加
     * 
     * @param roleBagSlot
     * @param itemModel
     * @param maxStack
     * @return
     */
    private boolean canStack(RoleBagSlot roleBagSlot, ItemModel itemModel, int maxStack) {
        if( !roleBagSlot.getGoodsId().equals(itemModel.getGoodsId()) ) {
            return false;
        }
        
        if( roleBagSlot.getBind() != itemModel.getBind() ) {
            return false;
        }
        
        if(roleBagSlot.getExpireTime() != itemModel.getExpireTime()) {
            return false;
        }
        
        return roleBagSlot.getCount() < maxStack;
    }
    
    private GoodsConfig getGoodsConfigById(String goodsId) {
        return goodsConfigService.loadById(goodsId);
    }

    @Sync(component = COMPONENT_NAME, indexes = {0})
    @Override
    public boolean checkGoodsesEnough(String roleId, String goodsId, int goodsCount) {
        if( goodsCount <= 0 ) {
            return true;
        }
        
        GoodsAmountFilter filter = new GoodsAmountFilter(goodsId, goodsCount);
        return checkGoodsesEnough(roleId, filter);
    }

    @Sync(component = COMPONENT_NAME, indexes = {0})
    @Override
    public boolean checkGoodsesEnough(String roleId, Map<String, Integer> goodsMap) {
        if( goodsMap == null || !goodsMap.isEmpty() ) {
            return true;
        }
        
        Iterator<String> iter = goodsMap.keySet().iterator();
        while( iter.hasNext() ) {
            String goodsId = iter.next();
            int goodsCount = goodsMap.get(goodsId);
            
            if( !checkGoodsesEnough(roleId, goodsId, goodsCount) ) {
                return false;
            }
        }
        return true;
    }
    
    private boolean checkGoodsesEnough(String roleId, IQueryCountFilter<RoleBagSlot> queryFilter) {
        this.roleBagSlotDao.cacheLoadAll(roleId, queryFilter);
        
        return queryFilter.isSatisfied();
    }

    @Sync(component = COMPONENT_NAME, indexes = {0})
    @Override
    public IBagDecrSingleResponse decrGoods(String roleId, String goodsId, int goodsCount) {
        BagResponse response = new BagResponse();
        if( goodsCount <= 0 ) {
            return response;
        }
        
        if( !checkGoodsesEnough(roleId, goodsId, goodsCount)) {
            response.setErrorCode(BagErrorCode.GOODS_LESS);
            return response;
        }
        
        decrOneGoods(roleId, goodsId, goodsCount, response);
        return response;
    }

    @Sync(component = COMPONENT_NAME, indexes = {0})
    @Override
    public IBagDecrSingleResponse decrGoodsByGuid(String roleId, String guid, int goodsCount) {
        BagResponse response = new BagResponse();
        if( goodsCount <= 0 ) {
            return response;
        }
        
        RoleBagSlot roleBagSlot = this.roleBagSlotDao.cacheLoad(guid, roleId);
        if( null == roleBagSlot ) {
            response.setErrorCode(BagErrorCode.NOT_FOUND_GOOODS);
            return response;
        }
        
        if( roleBagSlot.getCount() < goodsCount ) {
            response.setErrorCode(BagErrorCode.GOODS_LESS);
            return response;
        }
        
        if( roleBagSlot.getCount() == goodsCount ) {
            this.roleBagSlotDao.cacheDelete(roleBagSlot.getId(), roleId);
        } else {
            roleBagSlot.setCount( roleBagSlot.getCount() - goodsCount );
            this.roleBagSlotDao.cacheUpdate(roleBagSlot, roleId);
            
            response.addUpdateSlot(roleBagSlot, goodsCount);
        }
        return response;
    }

    @Sync(component = COMPONENT_NAME, indexes = {0})
    @Override
    public BagResponse decrGoods(String roleId, Map<String, Integer> goodsMap) {
        BagResponse response = new BagResponse();
        if( null == goodsMap || goodsMap.isEmpty() ) {
            return response;
        }
        
        if( !checkGoodsesEnough(roleId, goodsMap) ) {
            response.setErrorCode(BagErrorCode.GOODS_LESS);
            return response;
        }
        
        Iterator<String> iter = goodsMap.keySet().iterator();
        while( iter.hasNext() ) {
            String goodsId = iter.next();
            int goodsCount = goodsMap.get(goodsId);
            
            decrOneGoods(roleId, goodsId, goodsCount, response);
        }
        return response;
    }

    @Sync(component = COMPONENT_NAME, indexes = {0})
    @Override
    public IBagDecrSingleResponse decrGoodsById(String roleId, String goodsId, int goodsCount) {
        
        
        return null;
    }

    private void decrOneGoods(String roleId, String goodsId, int goodsCount, BagResponse response) {
        decrOneGoods(roleId, goodsId, goodsCount, response, new GoodsIdFilter(goodsId));
    }
    
    private void decrOneGoods(String roleId, String goodsId, int goodsCount, BagResponse response, IQueryFilter<RoleBagSlot> queryFilter) {
        List<RoleBagSlot> list = this.roleBagSlotDao.cacheLoadAll(roleId, queryFilter);
        if( list == null ) {
            return;
        }
        
        Collections.sort(list, new GoodsConsumeComparator());
        
        for (RoleBagSlot bag : list) {
            if( bag.getBind() == 1 ) {
                response.setBind(true);
            }
            
            //持有物品数量
            int existCount = bag.getCount();
            if( existCount >= goodsCount ) {
                decrOneGoodsById(bag, goodsCount, response);
                break;
            }
            
            goodsCount -= existCount;
            decrOneGoodsById(bag, existCount, response);
        }
    }
    
    private void decrOneGoodsById(RoleBagSlot roleBagSlot, int goodsCount, BagResponse response) {
        if( roleBagSlot.getCount() <= goodsCount ) {
            response.addDeleteSlot(roleBagSlot);
            response.setDecrItemModels(roleBagSlot, goodsCount);
            this.roleBagSlotDao.cacheDelete(roleBagSlot, roleBagSlot.getUserRoleId());
        } else {
            roleBagSlot.setCount( roleBagSlot.getCount() - goodsCount );
            response.addUpdateSlot(roleBagSlot, goodsCount);
            response.setDecrItemModels(roleBagSlot, goodsCount);
            this.roleBagSlotDao.cacheUpdate(roleBagSlot, roleBagSlot.getUserRoleId());
        }
    }

    @Sync(component = COMPONENT_NAME, indexes = {0})
    @Override
    public ItemModel loadGoodsByGuid(String roleId, String guid) {
        RoleBagSlot roleBagSlot = this.roleBagSlotDao.cacheLoad(guid, roleId);
        if( null == roleBagSlot ) {
            return null;
        }
        
        int slotNum = roleBagSlot.getSlotNum();
        if( slotNum >= BagConstants.BAG_MIN && slotNum <= BagConstants.BAG_MAX ) {
            return GoodsConvertUtil.entity2Model(roleBagSlot);
        }
        return null;
    }

    @Sync(component = COMPONENT_NAME, indexes = {0})
    @Override
    public RoleBagSlot loadGoodsBySlot(String roleId, int slotNum) {
        List<RoleBagSlot> list = this.roleBagSlotDao.cacheLoadAll(roleId, new SlotNumFilter(slotNum));
        if (list == null || list.isEmpty()) {
            return null;
        }
        
        return list.get(0);
    }

    @Sync(component = COMPONENT_NAME, indexes = {0})
    @Override
    public IBagRemoveResponse removeGoodsById(String roleId, String guid) {
        BagResponse response = new BagResponse();
        
        RoleBagSlot roleBagSlot = this.roleBagSlotDao.cacheLoad(guid, roleId);
        if( roleBagSlot == null ) {
            response.setErrorCode(BagErrorCode.NO_ENOUGH_GOODS);
            return response;
        }
        
        response.addDeleteSlot(roleBagSlot);
        this.roleBagSlotDao.cacheDelete(guid, roleId);
        
        return response;
    }

    @Sync(component = COMPONENT_NAME, indexes = {0})
    @Override
    public IBagRemoveResponse removeGoodsByIds(String roleId, List<String> guids) {
        BagResponse response = new BagResponse();
        if (guids == null || guids.isEmpty()) {
            return response;
        }
        
        RoleBagSlot roleBagSlot = null;
        for (String guid : guids) {
            roleBagSlot = this.roleBagSlotDao.cacheLoad(guid, roleId);
            response.addDeleteSlot(roleBagSlot);
            this.roleBagSlotDao.cacheDelete(guid, roleId);
        }
        
        return response;
    }

    @Override
    public Object clearup(String roleId) {
        List<RoleBagSlot> list = this.roleBagSlotDao.cacheLoadAll(roleId, new StoreSlotFilter(BagConstants.BAG_MIN, BagConstants.BAG_MAX));
        if( null == list || list.isEmpty() ) {
            return null;
        }
        
        if( list.size() == 1 ) {
            RoleBagSlot roleBagSlot = list.get(0);
            roleBagSlot.setSlotNum(BagConstants.BAG_MIN);
            this.roleBagSlotDao.cacheUpdate(roleBagSlot, roleId);
            
            busMsgSender.send2BusInner(BagCommands.GET_BAG_GOODS, roleId, null);
            return new Object[] {StoreGoodsOutput.formart(roleBagSlot)};
        }
        
        ClearBagGoodsCheck check = new ClearBagGoodsCheck();
        List<RoleBagSlot> result = BagClearupUtil.pickup(list, check);
        
        //删除
        for(String guid : check.getDelIds()) {
            this.roleBagSlotDao.cacheDelete(guid, roleId);
        }
        
        //output format object
        Object[] output = new Object[result.size()];
        
        //reset slot num
        int index = 0;
        int minSlot = BagConstants.BAG_MIN;
        
        for (RoleBagSlot roleBagSlot : result) {
            roleBagSlot.setSlotNum(minSlot++);
            this.roleBagSlotDao.cacheUpdate(roleBagSlot, roleId);
            
            output[index++] = StoreGoodsOutput.formart(roleBagSlot);
        }
        
        //重发物品列表
        busMsgSender.send2BusInner(BagCommands.GET_BAG_GOODS, roleId, null);
        
        return output;
    }
    
}
