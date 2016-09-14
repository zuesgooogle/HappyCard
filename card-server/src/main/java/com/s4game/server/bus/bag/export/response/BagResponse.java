package com.s4game.server.bus.bag.export.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.s4game.server.bus.bag.entity.RoleBagSlot;
import com.s4game.server.gamerule.goods.creator.ItemModel;
import com.s4game.server.gamerule.goods.util.GoodsConvertUtil;
import com.s4game.server.gamerule.money.MoneyType;

/**
*
* @Author zeusgooogle@gmail.com
* @sine   2015年8月11日 下午3:08:57
*
*/
public class BagResponse implements IBagIncrSingleResponse, IBagIncrMultiResponse, IBagRemoveResponse, IBagDecrMultiResponse, IBagDecrSingleResponse, IBagDecrMallCheckResponse {
    
    private List<RoleBagSlot> addSlots;
    private List<RoleBagSlot> updateSlots;
    private List<RoleBagSlot> deleteSlots;
    private List<RoleBagSlot> decrSlots;
    private Integer errorCode;
    private Map<String, Integer> goodsCountMap;
    private Map<String, Integer> decrGoodsMap;
    private String goodsId;
    private String decrGoodsId;
    private boolean decrGoodsBind = false;
    private Object[] updateInfos;
    private Object[] addInfos;
    private String[] removeIds;
    private Map<MoneyType, Integer> moneyMap = new Hashtable();
    private Map<String, Integer> decrMallGoodsMap = new Hashtable();
    private Map<String, Integer> mallBuyGoodsMap = new Hashtable();
    private String guid;
    private boolean bind;
    private List<ItemModel> decrItemModels = new ArrayList();

    public void addUpdateSlot(RoleBagSlot roleBagSlot, int count) {
        if (this.updateSlots == null) {
            this.updateSlots = new ArrayList();
        }
        this.updateSlots.add(roleBagSlot);
        if (this.goodsCountMap == null) {
            this.goodsCountMap = new HashMap();
        }
        this.goodsCountMap.put(roleBagSlot.getGoodsId(), Integer.valueOf(count));
        this.goodsId = roleBagSlot.getGoodsId();
        if (!this.decrGoodsBind) {
            this.decrGoodsBind = (roleBagSlot.getBind() == 1);
        }
    }

    public void addAddSlot(RoleBagSlot roleBagSlot) {
        if (this.addSlots == null) {
            this.addSlots = new ArrayList();
        }
        this.addSlots.add(roleBagSlot);
        if (this.goodsCountMap == null) {
            this.goodsCountMap = new HashMap();
        }
        this.goodsCountMap.put(roleBagSlot.getGoodsId(), roleBagSlot.getCount());
        this.goodsId = roleBagSlot.getGoodsId();
        if (!this.decrGoodsBind) {
            this.decrGoodsBind = (roleBagSlot.getBind() == 1);
        }
    }

    public Map<String, Integer> getGoodsMap() {
        return this.goodsCountMap;
    }

    public String getGoodsId() {
        return this.goodsId;
    }

    public int getGoodsCount() {
        return ((Integer) this.goodsCountMap.get(this.goodsId)).intValue();
    }

    public void addDecrSlot(RoleBagSlot roleBagSlot, int count) {
        if (this.decrSlots == null) {
            this.decrSlots = new ArrayList();
        }
        this.decrSlots.add(roleBagSlot);
        if (this.decrGoodsMap == null) {
            this.decrGoodsMap = new HashMap();
        }
        this.decrGoodsMap.put(roleBagSlot.getGoodsId(), Integer.valueOf(count));
        this.decrGoodsId = roleBagSlot.getGoodsId();
        if (!this.decrGoodsBind) {
            this.decrGoodsBind = (roleBagSlot.getBind() == 1);
        }
    }

    public void addDeleteSlot(RoleBagSlot roleBagSlot) {
        if (this.deleteSlots == null) {
            this.deleteSlots = new ArrayList();
        }
        this.deleteSlots.add(roleBagSlot);
        if (this.decrGoodsMap == null) {
            this.decrGoodsMap = new HashMap();
        }
        this.decrGoodsMap.put(roleBagSlot.getGoodsId(), roleBagSlot.getCount());
        this.decrGoodsId = roleBagSlot.getGoodsId();
        if (!this.decrGoodsBind) {
            this.decrGoodsBind = (roleBagSlot.getBind() == 1);
        }
    }

    public Object[] getUpdateInfos() {
        if (this.updateInfos != null) {
            return this.updateInfos;
        }
        if (this.updateSlots == null) {
            return null;
        }
        this.updateInfos = new Object[this.updateSlots.size()];
        for (int i = 0; i < this.updateInfos.length; i++) {
            this.updateInfos[i] = formatUpdates((RoleBagSlot) this.updateSlots.get(i));
        }
        return this.updateInfos;
    }

    public Object[] getAddInfos() {
        if (this.addInfos != null) {
            return this.addInfos;
        }
        if (this.addSlots == null) {
            return null;
        }
        this.addInfos = new Object[this.addSlots.size()];
        for (int i = 0; i < this.addInfos.length; i++) {
            this.addInfos[i] = formatAdds((RoleBagSlot) this.addSlots.get(i));
        }
        return this.addInfos;
    }

    public Object[] getAllInfos() {
        int i = getAddInfos() == null ? 0 : this.addInfos.length;
        int j = getUpdateInfos() == null ? 0 : this.updateInfos.length;
        int k = i + j;
        if (k <= 0) {
            return null;
        }
        Object[] arrayOfObject = new Object[k];
        if (i > 0) {
            System.arraycopy(this.addInfos, 0, arrayOfObject, 0, i);
        }
        if (j > 0) {
            System.arraycopy(this.updateInfos, 0, arrayOfObject, i, j);
        }
        return arrayOfObject;
    }

    private Object[] formatUpdates(RoleBagSlot roleBagSlot) {
        return new Object[] { roleBagSlot.getId(), roleBagSlot.getCount() };
    }

    private Object formatAdds(RoleBagSlot roleBagSlot) {
        //TODO return StoreGoodsOutput.formart(roleBagSlot);
        return null;
    }

    public boolean isSuccess() {
        return this.errorCode == null;
    }

    public String[] getRemoveIds() {
        if (this.deleteSlots != null) {
            this.removeIds = new String[this.deleteSlots.size()];
            for (int i = 0; i < this.removeIds.length; i++) {
                this.removeIds[i] = ((RoleBagSlot) this.deleteSlots.get(i)).getId();
            }
        }
        return this.removeIds;
    }

    public String getDecrGoodsId() {
        return this.decrGoodsId;
    }

    public int getDecrGoodsCount() {
        return ((Integer) this.decrGoodsMap.get(this.decrGoodsId)).intValue();
    }

    public Map<String, Integer> getDecrGoodsMap() {
        return this.decrGoodsMap;
    }

    public Map<String, Integer> getDecrMallGoodsMap() {
        return this.decrMallGoodsMap;
    }

    public Map<String, Integer> getMallBuyGoodsMap() {
        return this.mallBuyGoodsMap;
    }

    public Map<MoneyType, Integer> getMallDecrMoney() {
        return this.moneyMap;
    }

    public void addMallDecrMoney(MoneyType paramMoneyType, int count) {
        if (this.moneyMap.containsKey(paramMoneyType)) {
            this.moneyMap.put(paramMoneyType, Integer.valueOf(((Integer) this.moneyMap.get(paramMoneyType)).intValue() + count));
        } else {
            this.moneyMap.put(paramMoneyType, Integer.valueOf(count));
        }
    }

    public void addMallDecrMoney(Map<MoneyType, Integer> paramMap) {
        if (paramMap != null) {
            Iterator localIterator = paramMap.keySet().iterator();
            while (localIterator.hasNext()) {
                MoneyType localMoneyType = (MoneyType) localIterator.next();
                addMallDecrMoney(localMoneyType, ((Integer) paramMap.get(localMoneyType)).intValue());
            }
        }
    }

    public void addMallBuyGoodsMap(String paramString, int count) {
        if (this.mallBuyGoodsMap.containsKey(paramString)) {
            this.mallBuyGoodsMap.put(paramString, Integer.valueOf(((Integer) this.mallBuyGoodsMap.get(paramString)).intValue() + count));
        } else {
            this.mallBuyGoodsMap.put(paramString, Integer.valueOf(count));
        }
    }

    public void addMallBuyGoodsMap(Map<String, Integer> paramMap) {
        if (paramMap != null) {
            Iterator localIterator = paramMap.keySet().iterator();
            while (localIterator.hasNext()) {
                String str = (String) localIterator.next();
                addMallBuyGoodsMap(str, ((Integer) paramMap.get(str)).intValue());
            }
        }
    }

    public void addDecrMallGoodsMap(String paramString, int count) {
        if (this.decrMallGoodsMap.containsKey(paramString)) {
            this.decrMallGoodsMap.put(paramString, Integer.valueOf(((Integer) this.decrMallGoodsMap.get(paramString)).intValue() + count));
        } else {
            this.decrMallGoodsMap.put(paramString, Integer.valueOf(count));
        }
    }

    public void addDecrMallGoodsMap(Map<String, Integer> paramMap) {
        if (paramMap != null) {
            Iterator localIterator = paramMap.keySet().iterator();
            while (localIterator.hasNext()) {
                String str = (String) localIterator.next();
                addDecrMallGoodsMap(str, ((Integer) paramMap.get(str)).intValue());
            }
        }
    }

    public Integer getError() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<RoleBagSlot> getAddSlots() {
        return this.addSlots;
    }

    public List<RoleBagSlot> getUpdateSlots() {
        return this.updateSlots;
    }

    public void setGuid(String paramString) {
        this.guid = paramString;
    }

    public String getGuId() {
        return this.guid;
    }

    public boolean isDecrGoodsBind() {
        return this.decrGoodsBind;
    }

    public boolean isBind() {
        return this.bind;
    }

    public void setBind(boolean paramBoolean) {
        this.bind = paramBoolean;
    }

    public void setDecrItemModels(RoleBagSlot roleBagSlot, int count) {
        ItemModel localItemModel = GoodsConvertUtil.entity2Model(roleBagSlot);
        if (count > 0) {
            localItemModel.setGoodsCount(count);
        }
        this.decrItemModels.add(localItemModel);
    }

    public List<ItemModel> getDecrItemModels() {
        return this.decrItemModels;
    }
}
