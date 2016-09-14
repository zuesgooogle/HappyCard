package com.s4game.server.stage.configure.export;

import com.s4game.server.stage.configure.export.impl.PublicCdConfig;

public interface IPublicCdExportService {
    
    public PublicCdConfig loadById(String id);
    
}
