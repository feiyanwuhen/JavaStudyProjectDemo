package com.study.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import sun.nio.ch.ThreadPool;

/**
 * @author hyl
 * @date 2021/06/02
 */
public class ThreadPoolAbortTest {

  public static void main(String[] args) {

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 3, 10,
        TimeUnit.SECONDS, new LinkedBlockingDeque<>(2), new RejectedExecutionHandler() {
      @Override
      public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //自定义拒绝方式
        System.out.println("执行自定义拒绝策略");
      }
    });

/*    for (int i = 0; i < 6; i++) {
      threadPoolExecutor.execute(()-> {
        System.out.println(Thread.currentThread().getName());
      });
    }*/

    ThreadPoolExecutor myThreadPoolExecutor = new MyThreadPoolExecutor(2,4,10,TimeUnit.SECONDS,new LinkedBlockingQueue<>());

    for (int i=0 ;i < 3 ; i++) {
      myThreadPoolExecutor.execute(()-> Thread.currentThread().getName());
    }
  }

  static class MyThreadPoolExecutor extends ThreadPoolExecutor {

    private final ThreadLocal<Long> localTime = new ThreadLocal<>();


    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
        TimeUnit unit, BlockingQueue<Runnable> workQueue) {
      super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t,Runnable r) {
      Long sTime = System.nanoTime();
      localTime.set(sTime);
      System.out.println(String.format("%s before | time = %s", t.getName(),sTime));
      super.beforeExecute(t,r);
    }

    @Override
    protected void afterExecute(Runnable r,Throwable t) {
      Long eTime = System.nanoTime();
      Long totalTime = eTime - localTime.get();
      System.out.println(String.format("%s after | time = %s | 耗时 %s 毫秒", Thread.currentThread().getName(),eTime,(totalTime / 1000000.0)));
      super.afterExecute(r,t);
    }
  }

}
