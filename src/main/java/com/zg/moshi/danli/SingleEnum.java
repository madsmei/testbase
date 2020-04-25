package com.zg.moshi.danli;

/**
 * @Description: 枚举方式 (注册方式) 单例  通过观察Enum源码发现属于饿汉式的创建。
 *
 * 缺点：不能大面积应用，因为源码里 使用map去存储。会造成内存浪费。
 *
 * 优点：代码优雅。线程安全。效率最高
 * @Date 2020/4/20
 * @Version V1.0
 * @Author Mads
 **/
public enum  SingleEnum {

    INSTANCE;
    private Object object;

    public static SingleEnum getInstance() {
        return INSTANCE;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
