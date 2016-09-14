package com.s4game.server.bus.bag.service;

import java.util.List;
import java.util.Map;

import com.s4game.server.bus.bag.entity.RoleBagSlot;
import com.s4game.server.bus.bag.export.response.BagResponse;
import com.s4game.server.bus.bag.export.response.IBagDecrSingleResponse;
import com.s4game.server.bus.bag.export.response.IBagIncrSingleResponse;
import com.s4game.server.bus.bag.export.response.IBagRemoveResponse;
import com.s4game.server.gamerule.goods.creator.ItemModel;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月11日 下午3:26:05
 *
 */

public interface IBagService {

    public void createRoleBagSlot(String roleId);
    
    /**
     * 添加物品到背包中
     * 
     * @param roleId
     * @param itemModel
     * @return
     */
    public IBagIncrSingleResponse putInBag1(String roleId, ItemModel itemModel);
    
    /**
     * 添加物品到背包中
     * 
     * @param roleId
     * @param items
     * @return
     */
    public BagResponse putInBag1(String roleId, List<ItemModel> items);
    
    /**
     * 检查背包是否有格子
     * 
     * @param roleId
     * @param itemModel
     * @return
     */
    public boolean checkBagSlots1(String roleId, ItemModel itemModel);
    
    /**
     * 检查背包是否有格子
     * 
     * @param roleId
     * @param 
     * @return
     */
    public boolean checkBagSlots1(String roleId, List<ItemModel> items);
    
    /**
     * 检查物品是否足够
     * 
     * @param roleId
     * @param goodsId
     * @param goodsCount
     * @return
     */
    public boolean checkGoodsesEnough(String roleId, String goodsId, int goodsCount);

    /**
     * 检查物品是否足够
     * 
     * @param roleId
     * @param goodsMap
     * @return
     */
    public boolean checkGoodsesEnough(String roleId, Map<String, Integer> goodsMap);
 
    /**
     * 扣除物品
     * 
     * @param roleId
     * @param goodsId
     * @param goodsCount
     * @return
     */
    public IBagDecrSingleResponse decrGoods(String roleId, String goodsId, int goodsCount);
    
    /**
     * 扣除物品
     * <p> 根据数据库Id <p>
     * 
     * @param roleId
     * @param guid
     * @param goodsCount
     * @return
     */
    public IBagDecrSingleResponse decrGoodsByGuid(String roleId, String guid, int goodsCount);
    
    /**
     * 扣除物品
     * 
     * @param roleId
     * @param goodsMap
     * @return
     */
    public BagResponse decrGoods(String roleId, Map<String, Integer> goodsMap);

    /**
     * 扣除物品
     * 
     * @param roleId
     * @param goodsId
     * @param goodsCount
     * @return
     */
    public IBagDecrSingleResponse decrGoodsById(String roleId, String goodsId, int goodsCount);
    
    /**
     * 获取物品
     * <p> 根据数据库 ID <p>
     * 
     * @param roleId
     * @param guid
     * @return
     */
    public ItemModel loadGoodsByGuid(String roleId, String guid);
    
    /**
     * 获取背包格子数据
     * <p> 根据 slotNum
     * 
     * @param roleId
     * @param slotNUm
     * @return
     */
    public RoleBagSlot loadGoodsBySlot(String roleId, int slotNUm);
    
    /**
     * 删除物品
     * 
     * @param roleId
     * @param guid
     * @return
     */
    public IBagRemoveResponse removeGoodsById(String roleId, String guid);
    
    /**
     * 删除物品
     * 
     * @param roleId
     * @param guids
     * @return
     */
    public IBagRemoveResponse removeGoodsByIds(String roleId, List<String> guids);
    
    /**
     * 清理背包，重置slot num
     * 
     * @param roleId
     * @return
     */
    public Object clearup(String roleId);
}
