package com.s4game.server.gamerule.goods.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.s4game.server.gamerule.goods.creator.ItemModel;
import com.s4game.server.gamerule.goods.entity.StoreGoods;

@Component
public class GoodsConvertUtil {

    public static ItemModel entity2Model(StoreGoods storeGoods) {
        if (storeGoods == null) {
            return null;
        }
        
        ItemModel itemModel = new ItemModel(storeGoods.getGoodsId(), storeGoods.getCount());
        itemModel.setId(storeGoods.getId());
        itemModel.setBind(storeGoods.getBind());
        itemModel.setExpireTime(storeGoods.getExpireTime());
        itemModel.setItemLevel(storeGoods.getItemLevel());
        itemModel.setRareLevel(storeGoods.getRareLevel());
        itemModel.setSlotNum(storeGoods.getSlotNum());
        itemModel.setAttributes(storeGoods.getAttributes());
        
        return itemModel;
    }

    public static List<ItemModel> entity2Model(List<? extends StoreGoods> list) {
        if (list == null) {
            return null;
        }
        ArrayList itemModels = new ArrayList();
        Iterator localIterator = list.iterator();
        while (localIterator.hasNext()) {
            StoreGoods storeGoods = (StoreGoods) localIterator.next();
            itemModels.add(entity2Model(storeGoods));
        }
        return itemModels;
    }

    public static StoreGoods model2Entity(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        
        StoreGoods localStoreGoods = new StoreGoods();
        localStoreGoods.setId(itemModel.getId());
        localStoreGoods.setCount(itemModel.getGoodsCount());
        localStoreGoods.setGoodsId(itemModel.getGoodsId());
        localStoreGoods.setBind(itemModel.getBind());
        localStoreGoods.setExpireTime(itemModel.getExpireTime());
        localStoreGoods.setItemLevel(itemModel.getItemLevel());
        localStoreGoods.setRareLevel(itemModel.getRareLevel());
        localStoreGoods.setSlotNum(itemModel.getSlotNum());
        localStoreGoods.setAttributes(itemModel.getAttributes());
        return localStoreGoods;
    }
}
