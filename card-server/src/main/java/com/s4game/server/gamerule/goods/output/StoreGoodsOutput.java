package com.s4game.server.gamerule.goods.output;

import java.util.HashMap;
import java.util.Map;

import com.s4game.server.gamerule.goods.creator.ItemModel;
import com.s4game.server.gamerule.goods.entity.StoreGoods;

public class StoreGoodsOutput {

    public static Map<String, Object> formart(StoreGoods storeGoods) {
        if (storeGoods == null) {
            return null;
        }
        HashMap map = new HashMap();

        map.put("0", storeGoods.getGoodsId());
        map.put("1", storeGoods.getId());
        map.put("2", storeGoods.getSlotNum());
        map.put("3", storeGoods.getCount());
        map.put("4", storeGoods.getUserRoleId());
        map.put("5", storeGoods.getItemLevel());
        map.put("6", storeGoods.getRareLevel());
        map.put("7", storeGoods.getBind());
        map.put("8", storeGoods.getExpireTime());

        return map;
    }

    public static Map<String, Object> formart(String roleId, ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        HashMap map = new HashMap();

        map.put("0", itemModel.getGoodsId());
        map.put("1", itemModel.getId());
        map.put("2", Integer.valueOf(itemModel.getSlotNum()));
        map.put("3", Integer.valueOf(itemModel.getGoodsCount()));
        map.put("4", roleId);
        map.put("5", Integer.valueOf(itemModel.getItemLevel()));
        map.put("6", Integer.valueOf(itemModel.getRareLevel()));
        map.put("7", itemModel.getBind());
        map.put("8", Long.valueOf(itemModel.getExpireTime()));

        return map;
    }

    public static Object[] formatMove(StoreGoods storeGoods) {
        if (storeGoods == null) {
            return null;
        }
        return new Object[] { storeGoods.getId(), storeGoods.getSlotNum(), storeGoods.getCount(), storeGoods.getBind() };
    }

    public static Object[] formatMove(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        return new Object[] { itemModel.getId(), Integer.valueOf(itemModel.getSlotNum()), Integer.valueOf(itemModel.getGoodsCount()), itemModel.getBind() };
    }
}