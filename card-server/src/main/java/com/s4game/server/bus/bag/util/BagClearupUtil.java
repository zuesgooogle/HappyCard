package com.s4game.server.bus.bag.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.s4game.server.bus.bag.comparator.GoodsIdComparator;
import com.s4game.server.bus.bag.entity.RoleBagSlot;
import com.s4game.server.gamerule.goods.configure.export.IGoodsConfigService;
import com.s4game.server.gamerule.goods.configure.export.impl.GoodsConfig;
import com.s4game.server.gamerule.goods.util.GoodsCategoryUtil;

@Component
public class BagClearupUtil {
    
    private static IGoodsConfigService goodsConfigService;

    @Autowired
    public void setGoodsConfigExportService(IGoodsConfigService goodsConfigService1) {
        goodsConfigService = goodsConfigService1;
    }

    private static int getGoodsOrder(String goodsId) {
        GoodsConfig goodsConfig = goodsConfigService.loadById(goodsId);
        if (goodsConfig == null) {
            throw new RuntimeException("goods id:" + goodsId + " goods config is null ");
        }
        return goodsConfig.getOrder();
    }

    private static int getGoodsMaxStack(String goodsId) {
        GoodsConfig goodsConfig = goodsConfigService.loadById(goodsId);
        if (goodsConfig == null) {
            throw new RuntimeException("goods id:" + goodsId + " goods config is null ");
        }
        return goodsConfig.getMaxStack();
    }

    public static List<RoleBagSlot> pickup(List<RoleBagSlot> list, ClearBagGoodsCheck clearBagGoodsCheck) {
        Map<Integer, ArrayList> tempMap = new HashMap<Integer, ArrayList>();
        
        for (RoleBagSlot roleBagSlot : list) {
            //排序
            int orderNum = getGoodsOrder(roleBagSlot.getGoodsId());
            roleBagSlot.setOrderNum(orderNum);
            
            if( tempMap.containsKey(orderNum) ) {
                tempMap.get(orderNum).add(roleBagSlot);
            } else {
                ArrayList tempList = new ArrayList();
                tempList.add(roleBagSlot);
                
                tempMap.put(orderNum, tempList);
            }
        }
        
        //sort by goodsId
        for (ArrayList tempList : tempMap.values()) {
            Collections.sort(tempList, new GoodsIdComparator());
            merge(tempList);
        }

        List<RoleBagSlot> result = new ArrayList<RoleBagSlot>();
        for (int i = 0; i <= GoodsCategoryUtil.GOODS_ORDER_SIZE; i++) {
            List<RoleBagSlot> tempList = tempMap.get(i);
            if( tempList != null ) {
                for (RoleBagSlot roleBagSlot : tempList) {
                    if( roleBagSlot.getCount() > 0 ) {
                        result.add(roleBagSlot);
                    } else {
                        clearBagGoodsCheck.addDelId(roleBagSlot.getId());
                    }
                }//end for
            }
        }

        //sort
        orderByExpireTime(result);
        orderByGoodsCount(result);
        
        return result;
    }

    /**
     * 合并操作
     * 
     * 把列表前面的 物品数量 堆叠 到 maxStack
     * 
     * 列表后面的物品减少到 0，后期做删除
     * 
     * @param list
     */
    public static void merge(List<RoleBagSlot> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            RoleBagSlot pre = list.get(i);
            
            int maxStack = getGoodsMaxStack(pre.getGoodsId());
            for (int j = 0; j < list.size(); j++) {
                RoleBagSlot next = list.get(j);
                //达到最大堆叠数
                if(pre.getCount() == maxStack) {
                    break;
                }
                
                if( pre.getGoodsId().equals(next.getGoodsId()) 
                        && pre.getBind() == next.getBind() 
                        && BagUtil.compareExpireTime(pre.getExpireTime(), next.getExpireTime()) ) {
                    int stackNum = pre.getCount() + next.getCount() > maxStack ? maxStack - pre.getCount() : next.getCount();
                    
                    pre.setCount( pre.getCount() + stackNum );
                    next.setCount( next.getCount() - stackNum );
                }
            }//end for
        }
    }
    
    /**
     * 到期时间：由近到远
     * 
     * @param list
     */
    public static void orderByExpireTime(List<RoleBagSlot> list) {
        if( list == null || list.isEmpty() ) {
            return;
        }
        
        for (int i = 0; i < list.size() - 1; i++) {
            RoleBagSlot pre = list.get(i);
            
            for (int j = i + 1; j < list.size(); j++) {
                RoleBagSlot next = list.get(j);
                if( pre.getGoodsId().equals(next.getGoodsId())
                        && pre.getBind() == next.getBind()
                        && pre.getExpireTime() > next.getExpireTime()) {
                    
                    list.set(i, next);
                    list.set(j, pre);
                    
                    pre = list.get(i);
                }
            }//end for
        }
    }
    
    /**
     * 
     * 物品数量：从多到少
     * 
     * @param list
     */
    public static void orderByGoodsCount(List<RoleBagSlot> list) {
        if( list == null || list.isEmpty() ) {
            return;
        }
        
        for (int i = 0; i < list.size() - 1; i++) {
            RoleBagSlot pre = list.get(i);
            
            for (int j = i + 1; j < list.size(); j++) {
                RoleBagSlot next = list.get(j);
                if( pre.getGoodsId().equals(next.getGoodsId())
                        && pre.getBind() == next.getBind()
                        && pre.getCount() < next.getCount()) {
                    
                    list.set(i, next);
                    list.set(j, pre);
                    
                    pre = list.get(i);
                }
            }//end for
        }
    }
}