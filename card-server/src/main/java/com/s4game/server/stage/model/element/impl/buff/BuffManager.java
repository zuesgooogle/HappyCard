package com.s4game.server.stage.model.element.impl.buff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.s4game.server.stage.configure.export.impl.BuffConfig;
import com.s4game.server.stage.model.core.element.IBuff;
import com.s4game.server.stage.model.core.element.IBuffManager;
import com.s4game.server.stage.model.core.element.IFighter;
import com.s4game.server.stage.model.core.stage.ElementType;

import com.s4game.server.stage.model.element.role.IRole;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月21日 下午6:34:22
 * 
 */

public class BuffManager implements IBuffManager {

    private ConcurrentMap<String, IBuff> buffs = new ConcurrentHashMap<String, IBuff>();
    private ConcurrentMap<String, IBuff> readyForRecoveredBuffs = null;

    private IFighter fighter;
    private BuffStatistics buffStatistics;

    public BuffManager(IFighter fighter) {
        this.fighter = fighter;
        this.buffStatistics = new BuffStatistics();
    }

    @Override
    public void addBuff(IBuff buff) {
        IBuff oldBuff = getBuff(buff.getBuffCategory());
        BuffConfig buffConfig = buff.getBuffConfig();

        if (null == oldBuff) {
            List<String> huchi = buffConfig.getHuchi();
            if (null != huchi) {
                for (String category : huchi) {
                    if (null != getBuff(category)) {
                        return;
                    }
                }
            }

            List<String> exclude = buffConfig.getExcludeBuffCategory();
            if (null != exclude) {
                for (String category : exclude) {
                    IBuff excludeBuff = getBuff(category);
                    if (null != excludeBuff) {
                        removeBuffAndFree(excludeBuff);
                    }
                }
            }
            addBuffWithActivate(buff);
        } else if (!buffConfig.canOverlap()) { //重叠，覆盖
            if (buff.getLevel().intValue() >= oldBuff.getLevel().intValue()) {
                removeBuffAndFree(oldBuff);
                this.buffStatistics.removeBuff(oldBuff);
                addBuffWithActivate(buff);
            }
        } else if (buff.getLevel().intValue() > oldBuff.getLevel().intValue()) {
            removeBuffAndFree(oldBuff);
            this.buffStatistics.removeBuff(oldBuff);
            addBuffWithActivate(buff);
            
        } else if (buff.getLevel() == oldBuff.getLevel()) {
            removeBuffAndFree(oldBuff);

            int layer = buff.getLayer() + oldBuff.getLayer();
            if (layer > buffConfig.getMaxStack()) {
                layer = buffConfig.getMaxStack();
            }
            buff.setLayer(layer);
            addBuffWithActivate(buff);
        }

    }

    protected void addBuffWithActivate(IBuff buff) {
        this.buffs.put(buff.getBuffCategory(), buff);
        buff.start();

        //EXP 加成处理
        float expOdds = buff.getBuffConfig().getExpBeilv();
        if( expOdds > 0 && ElementType.isRole(fighter.getElementType()) ) {
            ((IRole)this.fighter).getRoleBusinessData().incrExpOdds(expOdds);
        }
        
        this.buffStatistics.addBuff(buff);
    }

    @Override
    public void recoverBuff(IBuff buff) {
        if (null == readyForRecoveredBuffs) {
            readyForRecoveredBuffs = new ConcurrentHashMap();
        }
        readyForRecoveredBuffs.put(buff.getBuffCategory(), buff);
    }

    @Override
    public void startReadyForRecoveredBuffsAll() {
        if (null != readyForRecoveredBuffs) {
            for (IBuff buff : readyForRecoveredBuffs.values()) {
                addBuff(buff);
            }
            readyForRecoveredBuffs = null;
        }
    }

    @Override
    public void removeBuff(String id, String category) {
        IBuff buff = getBuff0(id, category);
        if (null != buff) {
            removeBuffAndFree(buff);
        }
    }

    @Override
    public IBuff getBuff(String id, String category) {
        return getBuff0(id, category);
    }

    @Override
    public IBuff getBuff(String category) {
        return buffs.get(category);
    }

    private IBuff getBuff0(String id, String category) {
        IBuff buff = this.buffs.get(category);
        if (null != buff && buff.getId().equals(id)) {
            return buff;
        }
        return null;
    }

    @Override
    public Collection<IBuff> getBuffs() {
        return buffs.values();
    }

    @Override
    public void clearBuffsByDead() {
        for (IBuff buff : buffs.values()) {
            if (buff.isDeadRemoveOrNot()) {
                removeBuffAndFree(buff);
            }
        }
    }

    @Override
    public void clearAll() {
        for (IBuff buff : buffs.values()) {
            removeBuffAndFree(buff);
        }
    }

    protected void removeBuffAndFree(IBuff buff) {
        buffs.remove(buff.getBuffCategory());
        buff.end();

        //EXP 加成取消
        float expOdds = buff.getBuffConfig().getExpBeilv();
        if( expOdds > 0 && ElementType.isRole(fighter.getElementType()) ) {
            ((IRole)this.fighter).getRoleBusinessData().decrExpOdds(expOdds);
        }

        buffStatistics.removeBuff(buff);
    }

    @Override
    public Object getBuffClientMsgs() {
        List<Object> datas = new ArrayList<Object>();
        for (IBuff buff : buffs.values()) {
            datas.add(buff.getClientMsg());
        }

        return datas.toArray();
    }

    @Override
    public IBuff getBuffById(String buffId) {
        for (IBuff buff : buffs.values()) {
            if (buff.getBuffId().equals(buffId)) {
                return buff;
            }
        }
        return null;
    }

    @Override
    public BuffStatistics getBuffStatistics() {
        return buffStatistics;
    }

}
