package com.devpail.multithreading.util;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * @description: 线程池
 * @author: zhangzhb
 * @create: 2019-07-31 08:37
 **/

public class ThreadFactoryBean {
    private ThreadFactory threadFactory =
            new BasicThreadFactory.Builder()
                    .namingPattern("sniper-pool-%d")
                    .daemon(true)
                    .build();
    /**
     * 核心线程池大小
     */
    private int corePoolSize = 100;
    /**
     * 最大线程池大小
     */
    private int maximumPoolSize = 2048;

    /**
     * 线程执行等待时间
     */
    private long awaitTime = 0L;

    /**
     * 线程空闲等待时间
     */
    private long keepAliveTime = 10000L;
    /**
     * 缓冲列队
     */
    private int deque = 2048;

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    public void setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    public void setAwaitTime(long awaitTime) {
        this.awaitTime = awaitTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public void setDeque(int deque) {
        this.deque = deque;
    }

    /**
     * 添加任务
     *
     * @param runnable 任务
     */
    @Deprecated
    public ExecutorService addBean(Runnable runnable) {
        //创建线程池
        ExecutorService executor = new ScheduledThreadPoolExecutor(corePoolSize, threadFactory);
        //execute用于无返回值的执行，submit用于可以返回值的
        executor.execute(runnable);
        return executor;
    }

    /**
     * 获取线程池
     * 1.当线程池小于corePoolSize时，新提交任务将创建一个新线程执行任务，即使此时线程池中存在空闲线程。
     * 2.当线程池达到corePoolSize时，新提交任务将被放入workQueue中，等待线程池中任务调度执行
     * 3.当workQueue已满，且maximumPoolSize>corePoolSize时，新提交任务会创建新线程执行任务
     * 4.当提交任务数超过maximumPoolSize时，新提交任务由RejectedExecutionHandler处理
     * 5.当线程池中超过corePoolSize线程，空闲时间达到keepAliveTime时，关闭空闲线程
     * 6.当设置allowCoreThreadTimeOut(true)时，线程池中corePoolSize线程空闲时间达到keepAliveTime也将关闭
     *
     * @return 线程池
     */
    public ExecutorService getExecutorPool() {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(deque), threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }


    /**
     * 添加任务
     *
     * @param runnable 任务
     */
    public void addRunnable(ExecutorService executorService, Runnable runnable) {
        executorService.execute(runnable);
    }

    public <V> Future<V> addCallable(ExecutorService executorService, Callable<V> callable) {
        return executorService.submit(callable);
    }

    /**
     * 添加任务
     *
     * @param thread 任务
     */
    public void addRunnable(ExecutorService executorService, Thread thread) {
        executorService.execute(thread);
    }

    /**
     * 关闭
     *
     * @param executor
     */
    public void shutdown(ExecutorService executor) {
        // Disable new tasks from being submitted
        try {
            executor.shutdown();
            // Wait a while for existing tasks to terminate
            if (awaitTime > 0 && !executor.awaitTermination(awaitTime, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
                // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!executor.awaitTermination(awaitTime, TimeUnit.MILLISECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            executor.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

}
