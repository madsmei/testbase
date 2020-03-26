package com.zg.thread;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/******
 * 带返回值的线程
 */
public class CallbaleTest {

    public void demo() throws ExecutionException, InterruptedException {
        Callable<JSONPObject> call1 = new Callable<JSONPObject>() {
            @Override
            public JSONPObject call() throws Exception {

                //具体的业务逻辑
                return null;
            }
        };

        FutureTask<JSONPObject> task = new FutureTask<JSONPObject>(call1);

        new Thread(task).start();

        task.get();//渠道返回值
    }

    public static void main(String[] args) {
        System.out.println("");
    }
}
