package com.s4game.server.bus.equip.dao.filter;

import com.s4game.core.data.IQueryFilter;
import com.s4game.server.bus.equip.entity.RoleEquipSlot;

/**
*
* @Author zeusgooogle@gmail.com
* @sine   2015年8月14日 下午6:54:42
*
*/
public class EquipGoodsIdFilter implements IQueryFilter<RoleEquipSlot> {
    
    private String guid;
    private boolean found;

    public EquipGoodsIdFilter(String guid) {
        this.guid = guid;
    }

    public boolean check(RoleEquipSlot roleEquipSlot) {
        boolean bool = roleEquipSlot.getId().equals(this.guid);
        if (bool) {
            bool = true;
        }
        return bool;
    }

    public boolean stopped() {
        return this.found;
    }
}
