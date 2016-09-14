package com.s4game.server.share.export.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.schedule.ScheduleExecutor;
import com.s4game.core.token.TokenManager;
import com.s4game.server.share.export.IStageScheduleExportService;
import com.s4game.server.stage.share.schedule.StageTokenRunnable;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年9月8日 下午2:44:50
 * 
 */
@Component
public class StageScheduleExportServiceImpl implements IStageScheduleExportService {

    @Resource
    private TokenManager tokenManager;

    @Resource
    private ScheduleExecutor stageScheduleExecutor;

    private ConcurrentMap<String, ConcurrentMap<String, ScheduledFuture>> taskFutureMap = new ConcurrentHashMap();

    @Override
    public void schedule(String moduleName, String taskId, String command, Object data, long delay, TimeUnit timeUnit) {
        removeFutureQueue(moduleName, taskId);

        StageTokenRunnable tokenRunnable = new StageTokenRunnable(moduleName, command, data);
        tokenRunnable.setToken(new Object[] { taskId, tokenManager.createToken(moduleName, taskId) });
    
        addFutureQueue(taskId, moduleName, stageScheduleExecutor.schedule(tokenRunnable, delay, timeUnit));
    }

    @Override
    public void schedule(String moduleName, String taskId, String command, String roleId, String stageId, Object data, long delay, TimeUnit timeUnit) {
        removeFutureQueue(moduleName, taskId);

        StageTokenRunnable tokenRunnable = new StageTokenRunnable(moduleName, roleId, stageId, command, data);
        tokenRunnable.setToken(new Object[] { taskId, tokenManager.createToken(moduleName, taskId) });
    
        addFutureQueue(taskId, moduleName, stageScheduleExecutor.schedule(tokenRunnable, delay, timeUnit));
    }

    @Override
    public void cancelSchedule(String moduleName, String taskId) {
        tokenManager.removeToken(moduleName, taskId);
        removeFutureQueue(moduleName, taskId);
    }

    private void addFutureQueue(String taskId, String moduleName, ScheduledFuture<?> scheduledFuture) {
        ConcurrentMap map = this.taskFutureMap.get(moduleName);
        if (null == map) {
            map = new ConcurrentHashMap();
            this.taskFutureMap.put(moduleName, map);
        }
        map.put(taskId, scheduledFuture);
    }

    private void removeFutureQueue(String moduleName, String taskId) {
        ConcurrentMap map = this.taskFutureMap.remove(moduleName);
        if (null != map) {
            ScheduledFuture scheduledFuture = (ScheduledFuture) map.remove(taskId);
            if (null != scheduledFuture) {
                scheduledFuture.cancel(true);
            }
        }// end if
    }
}
