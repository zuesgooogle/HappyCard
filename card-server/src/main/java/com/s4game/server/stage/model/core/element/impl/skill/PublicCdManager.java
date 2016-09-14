package com.s4game.server.stage.model.core.element.impl.skill;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.s4game.server.stage.configure.export.IPublicCdExportService;
import com.s4game.server.stage.configure.export.impl.PublicCdConfig;
import com.s4game.server.stage.model.core.element.IFighter;

@Component
public class PublicCdManager {
    
    private static IPublicCdExportService publicCdExportService;
    
    private Map<String, Long> lastTriggerTimes = new HashMap();

    public long remainCd(String cdId) {
        if ((null == cdId) || ("".equals(cdId))) {
            return 0L;
        }
        Long cdTime = this.lastTriggerTimes.get(cdId);
        if (null == cdTime) {
            return 0L;
        }
        return cdTime.longValue() - System.currentTimeMillis();
    }

    public int toDynamicCd(String cdId, IFighter paramIFighter) {
        PublicCdConfig publicCdConfig = publicCdExportService.loadById(cdId);
        if (null == publicCdConfig) {
            return 0;
        }
        
        int i = publicCdConfig.getTime();
        int j = (int) (i / (1.0F + paramIFighter.getFightAttribute().getGongJiSuDu() / 2000.0F));
        toCd(cdId, j);
        return j;
    }

    public void toCd(String cdId) {
        PublicCdConfig publicCdConfig = publicCdExportService.loadById(cdId);
        if (null != publicCdConfig) {
            int i = publicCdConfig.getTime();
            
            toCd(cdId, i);
        }
    }

    protected void toCd(String cdId, int cdTime) {
        this.lastTriggerTimes.put(cdId, System.currentTimeMillis() + cdTime);
    }

    public Integer getCd(String cdId) {
        if ((null == cdId) || ("".equals(cdId))) {
            return Integer.valueOf(0);
        }
        Long cdTime = this.lastTriggerTimes.get(cdId);
        if (null != cdTime) {
            int i = (int) (cdTime.longValue() - System.currentTimeMillis());
            if (i > 0) {
                return Integer.valueOf(i);
            }
        }
        return Integer.valueOf(0);
    }

    @Autowired
    public void setPublicCdExportService(IPublicCdExportService publicCdExportService1) {
        publicCdExportService = publicCdExportService1;
    }
}