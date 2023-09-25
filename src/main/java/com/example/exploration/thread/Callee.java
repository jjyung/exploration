package com.example.exploration.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Callee {

  private final Object lock = new Object();

  public static synchronized void staticMethodOne() {
    log.info("staticMethodOne");
  }

  public static synchronized void staticMethodTwo() {
    log.info("staticMethodTwo");
  }

  public static void main(String[] args) {
    //

    Callee callee = new Callee();

    Thread t1 =
        new Thread(
            () -> {
              log.info(Thread.currentThread().getName() + " running");
              callee.instanceMethodOne();
              log.info(Thread.currentThread().getName() + " finished");
            });
    Thread t2 =
        new Thread(
            () -> {
              log.info(Thread.currentThread().getName() + " running");
              callee.instanceMethodTwo();
              log.info(Thread.currentThread().getName() + " finished");
            });
    Thread t3 =
        new Thread(
            () -> {
              log.info(Thread.currentThread().getName() + " running");
              callee.instanceMethodTwo();
              log.info(Thread.currentThread().getName() + " finished");
            });
    Thread t4 =
        new Thread(
            () -> {
              log.info(Thread.currentThread().getName() + " running");
              callee.instanceMethodFour();
              log.info(Thread.currentThread().getName() + " finished");
            });
    t1.start();
    t2.start();
    t3.start();
    t4.start();
  }

  public synchronized void instanceMethodOne() {
    log.info("instanceMethodOne");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      log.error(e.getMessage());
    }
  }

  public synchronized void instanceMethodTwo() {
    log.info("instanceMethodTwo");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      log.error(e.getMessage());
    }
  }

  public void instanceMethodThree() {
    synchronized (this) {
      log.info("instanceMethodThree");
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        log.error(e.getMessage());
      }
    }
  }

  public void instanceMethodFour() {
    synchronized (this.lock) {
      log.info("instanceMethodFour");
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        log.error(e.getMessage());
      }
    }
  }
}
