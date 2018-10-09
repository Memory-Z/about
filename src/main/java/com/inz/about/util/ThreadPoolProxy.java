package com.inz.about.util;

import java.util.concurrent.*;

/**
 * 线程池代理
 * Create by inz
 *
 * @author Zhenglj
 * @version 1.0.0
 * Create by 2018/8/2 17:58.
 */
public class ThreadPoolProxy {

    private static ThreadPoolExecutor mThreadPoolExecutor;

    /**
     * 默认线程池大小
     */
    private static final int DEF_CORE_POOL_SIZE = 10;
    /**
     * 默认最大线程池大小
     */
    private static final int DEF_MAX_POOL_SIZE = 30;
    /**
     * 默认存活时间
     */
    private static final int DEF_KEEP_ALIVE_TIME = 30;

    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime;

    /**
     * 获取线程实例
     *
     * @return 线程池实例
     */
    public static ThreadPoolExecutor getInstance() {
        if (mThreadPoolExecutor == null) {
            new ThreadPoolProxy(DEF_CORE_POOL_SIZE, DEF_MAX_POOL_SIZE, DEF_KEEP_ALIVE_TIME);
        }
        return mThreadPoolExecutor;
    }

    /**
     * @param corePoolSize    线程池容量
     * @param maximumPoolSize 最大池容量
     * @param keepAliveTime   最大存活时间
     */
    private ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        mThreadPoolExecutor = initExecutor();
    }

    /**
     * 初始化 默认持续时间单位：分钟
     *
     * @return 线程池
     */
    private ThreadPoolExecutor initExecutor() {
        if (mThreadPoolExecutor == null) {
            synchronized (ThreadPoolProxy.class) {
                if (mThreadPoolExecutor == null) {
                    TimeUnit unit = TimeUnit.MINUTES;
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
                    LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
                    mThreadPoolExecutor = new ThreadPoolExecutor(
                            //核心线程数
                            corePoolSize,
                            //最大线程数
                            maximumPoolSize,
                            //保持时间
                            keepAliveTime,
                            //保持时间对应的单位
                            unit,
                            workQueue,
                            //线程工厂
                            threadFactory,
                            //异常捕获器
                            handler);
                }
            }
        }
        return mThreadPoolExecutor;
    }

    /**
     * 执行任务
     *
     * @param r 任务
     */
    public void executeTask(Runnable r) {
        initExecutor();
        mThreadPoolExecutor.execute(r);
    }

    /**
     * 提交任务
     *
     * @param r 任务
     */
    public Future<?> commitTask(Runnable r) {
        initExecutor();
        return mThreadPoolExecutor.submit(r);
    }

    /**
     * 删除任务
     *
     * @param r 任务
     */
    public void removeTask(Runnable r) {
        initExecutor();
        mThreadPoolExecutor.remove(r);
    }
}
