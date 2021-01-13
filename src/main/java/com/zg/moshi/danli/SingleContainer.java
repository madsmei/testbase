package com.zg.moshi.danli;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 容器的写法，参考 Enum源码的方式，
 * @Date 2020/4/20
 * @Version V1.0
 * @Author Mads
 **/
public class SingleContainer {
    private SingleContainer() {
    }

    private static ConcurrentHashMap<String, Object> ioc = new ConcurrentHashMap<>();

    public static Object getInstance(String classname) {
        synchronized (ioc) {
            if (!ioc.containsKey(classname)) {
                Object object = null;
                try {
                    object = Class.forName(classname).newInstance();
                    ioc.put(classname, object);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return object;
            } else {
                return ioc.get(classname);
            }
        }
    }


}

