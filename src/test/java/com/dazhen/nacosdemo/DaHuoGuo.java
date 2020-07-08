package com.dazhen.nacosdemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: monitor-plat
 * @description: test
 * @author: water
 * @create: 2020-06-28 17:29
 */
public class DaHuoGuo {
    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + "```");
            Thread.sleep(2000);
            return "";
        });

        Thread thread = new Thread(futureTask);
        thread.start();
        String result = null;
        try {
            result = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
