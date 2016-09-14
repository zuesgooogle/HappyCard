package com.s4game.server.stage.model.core.element;

import com.s4game.server.stage.model.core.fight.IHarm;
import com.s4game.server.stage.schedule.StageScheduleManager;


/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月16日 下午4:06:56
 * 
 */

public interface IFighter extends IElement {

    public int getLevel();

    public BattleMode getBattleMode();

    public void setBattleMode(BattleMode paramBattleMode);

    public IFightAttribute getFightAttribute();

    public IBuffManager getBuffManager();

    //public IHatredManager getHatredManager();

    public ISkillManager getSkillManager();

    public StageScheduleManager getScheduleManager();

    public IElementEventManager getEventManager();

    public long getFightRecord();

    public void deadHandle(IHarm paramIHarm);

}
