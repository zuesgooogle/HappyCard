package com.s4game.server.stage.model.element.role;

import com.s4game.server.stage.model.core.element.IBuffManager;
import com.s4game.server.stage.model.core.element.IElementEventManager;
import com.s4game.server.stage.model.core.element.IFightAttribute;
import com.s4game.server.stage.model.core.element.ISkillManager;
import com.s4game.server.stage.model.core.element.IStateManager;
import com.s4game.server.stage.model.core.element.impl.AbsFighter;
import com.s4game.server.stage.model.core.fight.IHarm;
import com.s4game.server.stage.model.core.stage.ElementType;
import com.s4game.server.stage.model.core.stage.IStage;
import com.s4game.server.stage.schedule.StageScheduleManager;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月21日 下午5:39:25
 * 
 */

public class Role extends AbsFighter implements IRole {

    private IRoleFightAttribute fightAttribute;
    
    private IStateManager stateManager;
    
    private IBuffManager buffManager;
    
    private IElementEventManager eventManager;
    
    private ISkillManager skillManager;
    
    private StageScheduleManager scheduleManager;
    
    private RoleBusinessData roleBusinessData;

    public Role(String id, String name, String camp) {
        super(id, name, null, camp);
    }

    @Override
    public int getLevel() {
        return roleBusinessData.getLevel();
    }

    @Override
    public IFightAttribute getFightAttribute() {
        return fightAttribute;
    }

    @Override
    public IBuffManager getBuffManager() {
        return buffManager;
    }

    @Override
    public ISkillManager getSkillManager() {
        return skillManager;
    }

    @Override
    public StageScheduleManager getScheduleManager() {
        return scheduleManager;
    }

    @Override
    public IElementEventManager getEventManager() {
        return eventManager;
    }

    @Override
    public long getFightRecord() {
        return 0;
    }

    @Override
    public IStateManager getStateManager() {
        return stateManager;
    }

    @Override
    public ElementType getElementType() {
        return ElementType.ROLE;
    }

    @Override
    public Object getMsgData() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deadHandle(IHarm paramIHarm) {
        // TODO Auto-generated method stub

    }
    
    @Override
    public void leaveStageHandle(IStage stage) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterStageHandle(IStage stage) {
        // TODO Auto-generated method stub

    }

    @Override
    public RoleBusinessData getRoleBusinessData() {
        return this.roleBusinessData;
    }

    public void setBusinessData(RoleBusinessData roleBusinessData) {
        this.roleBusinessData = roleBusinessData;
    }

    public void setStateManager(IStateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void setBuffManager(IBuffManager buffManager) {
        this.buffManager = buffManager;
    }

    public void setEventManager(IElementEventManager eventManager) {
        this.eventManager = eventManager;
    }

    public void setSkillManager(ISkillManager skillManager) {
        this.skillManager = skillManager;
    }

    public void setScheduleManager(StageScheduleManager scheduleManager) {
        this.scheduleManager = scheduleManager;
    }

    public void setFightAttribute(IRoleFightAttribute fightAttribute) {
        this.fightAttribute = fightAttribute;
    }

}
