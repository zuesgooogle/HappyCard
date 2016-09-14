package com.s4game.server.stage.model.element.impl.buff;

import java.util.ArrayList;
import java.util.List;

import com.s4game.server.stage.model.core.element.IBuff;

public class BuffStatistics {
    
    private List<Object> addBuffs;
    
    private List<String> removeBuffs;

    public void addBuff(IBuff buff) {
        if (this.addBuffs == null) {
            this.addBuffs = new ArrayList();
        }
        this.addBuffs.add(buff.getClientMsg());
    }

    public void removeBuff(IBuff buff) {
        if (this.removeBuffs == null) {
            this.removeBuffs = new ArrayList();
        }
        this.removeBuffs.add(buff.getId());
    }

    public Object[] getAddBuffInfos() {
        if ((this.addBuffs == null) || (this.addBuffs.size() == 0)) {
            return null;
        }
        return this.addBuffs.toArray();
    }

    public Object[] getRemoveBuffIds() {
        if ((this.removeBuffs == null) || (this.removeBuffs.size() == 0)) {
            return null;
        }
        return this.removeBuffs.toArray();
    }

    public void clear() {
        this.addBuffs = null;
        this.removeBuffs = null;
    }
}