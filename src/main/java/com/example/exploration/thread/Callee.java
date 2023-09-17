package com.example.exploration.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Callee {

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
              log.info("t1 running");
              callee.instanceMethodOne();
              log.info("t1 finished;");
            });
    Thread t2 =
        new Thread(
            () -> {
              log.info("t2 running");
              callee.instanceMethodTwo();
              log.info("t2 finished;");
            });
    t1.start();
    t2.start();

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
}
