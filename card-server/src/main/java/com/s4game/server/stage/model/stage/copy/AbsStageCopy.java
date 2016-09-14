package com.s4game.server.stage.model.stage.copy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.s4game.server.stage.StageModuleInfo;
import com.s4game.server.stage.constants.StageCopyConstant;
import com.s4game.server.stage.model.core.stage.ElementType;
import com.s4game.server.stage.model.element.monster.IMonster;
import com.s4game.server.stage.model.stage.aoi.AoiStage;
import com.s4game.server.stage.schedule.StageScheduleManager;
import com.s4game.server.utils.MapUtil;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月16日 下午5:03:18
 * 
 */

public abstract class AbsStageCopy extends AoiStage implements IStageCopy {

    protected StageScheduleManager scheduleManager;

    protected long startTime;
    protected long endTime;
    protected long expireDelay;
    protected int challengeId;
    protected boolean isChallengeOver;
    protected Map<String, Integer> killedMap;
    protected Map<String, Integer> finishMap;
    protected int forceLeaveCount = 0;

    private static final Integer expireCheckInterval = 60000;

    public AbsStageCopy(String id, String mapId, int challengeId, long expireDelay, long startTime, Map<String, Integer> killedMap) {
        super(id, mapId);

        this.challengeId = challengeId;
        this.expireDelay = expireDelay;
        this.startTime = startTime;
        this.killedMap = killedMap;
        this.scheduleManager = new StageScheduleManager();

        scheduleExpireCheck();
    }

    @Override
    public void scheduleExpireCheck() {
        this.scheduleManager.schedule(StageModuleInfo.MODULE_NAME, getExpireCheckTaskId(), getExpireCheckCommand(), getId(), expireCheckInterval.intValue(), TimeUnit.MILLISECONDS);
    }

    protected abstract String getExpireCheckCommand();
    
    protected abstract String getForceLeaveCommand();

    @Override
    public void killMonster(IMonster monster) {
        MapUtil.addMapValue(this.killedMap, monster.getMonsterId(), 1);
    }

    @Override
    public boolean isFinished() {
        return this.isChallengeOver;
    }

    @Override
    public boolean isExpired() {
        return System.currentTimeMillis() - this.startTime >= this.expireDelay;
    }

    @Override
    public void clear() {
        this.scheduleManager.cancelSchedule(StageModuleInfo.MODULE_NAME, getExpireCheckTaskId());
        this.scheduleManager.cancelSchedule(StageModuleInfo.MODULE_NAME, getExpireForceTaskId());

        getStageProduceManager().cancelAllSchedule();

        this.scheduleManager.clear();
        
        Collection localCollection = getElementsByType(ElementType.MONSTER);
        if (localCollection.size() > 0) {
            ArrayList localArrayList = new ArrayList(localCollection);
            Iterator localIterator = localArrayList.iterator();
            while (localIterator.hasNext()) {
                IMonster localIMonster = (IMonster) localIterator.next();
                leave(localIMonster);
            }
        }
    }

    @Override
    public Integer getStoryId() {
        return Integer.valueOf(this.challengeId);
    }

    @Override
    public void overHandle() {
        setChallengeOver(true);
    }

    @Override
    public void scheduleForcedLeave() {
        this.scheduleManager.schedule(StageModuleInfo.MODULE_NAME, getExpireForceTaskId(), getForceLeaveCommand(), getId(), StageCopyConstant.forcedLeaveDelay, TimeUnit.MILLISECONDS);
    }

    @Override
    public void setChallengeOver(boolean challengeOver) {
        this.isChallengeOver = challengeOver;

        if (this.isChallengeOver) {
            this.endTime = System.currentTimeMillis();
        }
    }

    @Override
    public int getCostTime() {
        if (this.isChallengeOver) {
            return (int) (this.endTime - this.startTime);
        }
        return (int) (System.currentTimeMillis() - this.startTime);
    }

    @Override
    public Map<String, Boolean> getMonsterMap() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean canRetrieve() {
        return isChallengeOver;
    }

    @Override
    public void setExitNormal(boolean paramBoolean) {
        // TODO Auto-generated method stub

    }

    protected String getExpireCheckTaskId() {
        return getId() + "-" + "expirecheck";
    }

    protected String getExpireForceTaskId() {
        return getId() + "-" + "forceleave";
    }

    @Override
    public boolean isCopy() {
        return true;
    }
}
