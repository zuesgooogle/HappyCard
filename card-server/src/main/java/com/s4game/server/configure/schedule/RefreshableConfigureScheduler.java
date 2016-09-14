package com.s4game.server.configure.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.server.configure.parser.AbsRefreshConfigureParser;
import com.s4game.server.share.export.IQuartzScheduleExportService;

@Component
public class RefreshableConfigureScheduler {
    
    @Resource
    private IQuartzScheduleExportService quartzScheduleExportService;
    
    private List<AbsRefreshConfigureParser> scheduleConfigures = new ArrayList<AbsRefreshConfigureParser>();
    
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void setScheduleConfigures(List<AbsRefreshConfigureParser> list) {
        this.scheduleConfigures = list;
    }

    public void addRefreshableParser(AbsRefreshConfigureParser absRefreshAbleConfigureParser) {
        this.scheduleConfigures.add(absRefreshAbleConfigureParser);
    }

    public void refreshAll() {
        this.executorService.execute(new Runnable(){

            @Override
            public void run() {
                for (AbsRefreshConfigureParser absRefreshAbleConfigureParser : RefreshableConfigureScheduler.this.scheduleConfigures) {
                    absRefreshAbleConfigureParser.versionRefresh();
                }
            }
        });
    }

    public void init() {
        this.quartzScheduleExportService.schedule((Object)this, "refreshAll", null, "0 */2 * * * ?");
    }

    public void refreshConfigureNameData(String string) {
        for (AbsRefreshConfigureParser absRefreshAbleConfigureParser : this.scheduleConfigures) {
            if (!absRefreshAbleConfigureParser.getConfigName().equals(string)) continue;
            absRefreshAbleConfigureParser.directRefresh();
        }
    }

}

