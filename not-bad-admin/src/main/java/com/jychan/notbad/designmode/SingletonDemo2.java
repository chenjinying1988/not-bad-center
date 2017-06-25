package com.jychan.notbad.designmode;

/**
 * Created by chenjinying on 2017/6/25.
 * mail: 415683089@qq.com
 */
public class SingletonDemo2 {

    /*
    说明：
    1. instance = new SingletonDemo2(); 可以分为三个步骤：
           A) memory = allocate(); //分配对象的内存空间
           B) actorInstance(memory); //初始化对象
           C) instance = memory; //设置instance指向刚分配的内存地址
    2. 上面的 B)、C) 两步骤可能被 JIT 重排序，即instance拿到内存空间，但实际还没初始化完成
    3. 这里允许 instance 的初始化被 JIT 重排序，但只是在 class InstanceHolder 内
    4. 因为对象初始化时会被上锁，多线程需等待初始化完成。
    5. 初始化对象时会同时对 static 静态资源进行初始化。
     */

    private static class InstanceHolder {
        public static SingletonDemo2 instance = new SingletonDemo2();
    }

    public static SingletonDemo2 getInstance() {
        return InstanceHolder.instance;
    }
}
