package com.s4game.server.stage.schedule;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.s4game.server.share.export.IStageScheduleExportService;

@Component
public class StageScheduleManager {

    private static IStageScheduleExportService stageScheduleExportService;

    /**
     * key: taskId
     */
    private Map<String, String> scheduleMap = new HashMap();

    public void schedule(String moduleName, String taskId, String command, Object data, long delay, TimeUnit timeUnit) {
        this.scheduleMap.put(taskId, moduleName);
        
        stageScheduleExportService.schedule(moduleName, taskId, command, data, delay, timeUnit);
    }

    public void schedule(String moduleName, String taskId, String command, String roleId, String stageId, Object data, long delay, TimeUnit timeUnit) {
        this.scheduleMap.put(taskId, moduleName);
        
        stageScheduleExportService.schedule(moduleName, taskId, command, roleId, stageId, data, delay, timeUnit);
    }

    public void cancelSchedule(String moduleName, String taskId) {
        stageScheduleExportService.cancelSchedule(moduleName, taskId);
    }

    public void clear() {
        Iterator<String> iterator = this.scheduleMap.keySet().iterator();
        while (iterator.hasNext()) {
            String taskId = iterator.next();
            cancelSchedule(this.scheduleMap.get(taskId), taskId);
        }
    }

    @Autowired
    public void setStageScheduleExportService(IStageScheduleExportService stageScheduleExportService0) {
        stageScheduleExportService = stageScheduleExportService0;
    }
}