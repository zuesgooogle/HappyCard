package com.s4game.server.stage.model.stage.copy;

import java.util.Map;

import com.s4game.server.stage.model.element.monster.IMonster;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月16日 下午3:52:26
 * 
 */

public interface IStageCopy {

    public void killMonster(IMonster monster);

    public boolean isFinished();

    public boolean isExpired();

    public void clear();

    public Integer getStoryId();

    public void overHandle();

    public void scheduleForcedLeave();

    public void scheduleExpireCheck();

    public void setChallengeOver(boolean paramBoolean);

    public int getCostTime();

    public Map<String, Boolean> getMonsterMap();

    public boolean canRetrieve();

    public void setExitNormal(boolean paramBoolean);

}
