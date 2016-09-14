package com.s4game.server.share.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年9月8日 上午10:34:59
 *
 */

public class Log {
    
    public static final String ERROR_LOGGER = "error_logger";
    public static final String CONFIG_LOGGER = "config_logger";

    public static final String SERVER_STATUS_LOGGER = "server_status_logger";
    
    
    
    private static Logger LOG = LogManager.getLogger(ERROR_LOGGER);
    
    public static void error(String message, Object... params) {
        LOG.error(message, params);
    }

}
