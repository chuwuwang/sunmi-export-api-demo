package com.sm.l3.demo.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ThreadPoolManager {

    private ExecutorService cachePool = Executors.newCachedThreadPool();

    private ExecutorService singlePool = Executors.newSingleThreadExecutor();

    private static final class SingletonHolder {
        private static final ThreadPoolManager instance = new ThreadPoolManager();
    }

    private ThreadPoolManager() {
    }

    public static void executeInSinglePool(Runnable runnable) {
        if (runnable != null) {
            SingletonHolder.instance.singlePool.execute(runnable);
        }
    }

    public static void executeInCachePool(Runnable runnable) {
        if (runnable != null) {
            SingletonHolder.instance.cachePool.execute(runnable);
        }
    }


}
