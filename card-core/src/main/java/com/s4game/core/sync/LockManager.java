package com.s4game.core.sync;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author zeusgooogle
 * @date 2014-10-2 下午07:40:53
 */
public class LockManager {

    private static final Logger LOG = LogManager.getLogger(LockManager.class);

    private ConcurrentMap<String, ConcurrentMap<String, Lock>> components = new ConcurrentHashMap<String, ConcurrentMap<String, Lock>>();

    private static final long  CLEAN_PERIOD = 1800000L;

    public LockManager() {
        Thread thread = new Thread("LockManager-Cleaner") {

            public void run() {
                try {
                    for (;;) {
                        Thread.sleep(CLEAN_PERIOD);
                        try {
                            clean();
                        } catch (Exception e) {
                            LOG.error("", e);
                        }
                    }
                } catch (Exception e) {
                    LOG.error("", e);
                }
            }
        };
        
        thread.setDaemon(true);
        thread.start();
    }

    public Lock getLock(String component, String roleId) {
        ConcurrentMap<String, Lock> map = getComponentLocks(component);
        synchronized (map) {
            Lock lock = map.get(roleId);
            if (null == lock) {
                lock = new Lock(roleId);
                map.put(roleId, lock);
            }
            
            lock.update();
            return lock;
        }
    }

    private ConcurrentMap<String, Lock> getComponentLocks(String component) {
        ConcurrentMap<String, Lock> map = this.components.get(component);
        if (null == map) {
            synchronized (this.components) {
                map = this.components.get(component);
                if (null == map) {
                    map = new ConcurrentHashMap<String, Lock>();
                    this.components.put(component, map);
                }
            }
        }
        return map;
    }

    public void clean() {
        int i = 0;
        long l = 0L;
        Iterator<ConcurrentMap<String, Lock>> iterator = this.components.values().iterator();
        while (iterator.hasNext()) {
            ConcurrentMap<String, Lock> localConcurrentMap = iterator.next();
            synchronized (localConcurrentMap) {
                Iterator<Lock> localIterator2 = localConcurrentMap.values().iterator();
                while (localIterator2.hasNext()) {
                    Lock localLock = localIterator2.next();
                    if (localLock.canClean()) {
                        localConcurrentMap.remove(localLock.getKey());
                        i++;
                    }
                }
                l += localConcurrentMap.size();
            }
        }
        LOG.error("LockManager-Cleaner:cleaned {},remain {}", new Object[] { Integer.valueOf(i), Long.valueOf(l) });
    }

}
