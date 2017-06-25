package com.jychan.notbad.designmode;

/**
 * Created by chenjinying on 2017/6/25.
 * mail: 415683089@qq.com
 */
public class SingletonDemo {

    /*
    说明：
    1. instance 使用volatile 修饰，可使instance初始化时不被 JIT 重排序。
    2. instance = new SingletonDemo(); 可以分为三个步骤：
           A) memory = allocate(); //分配对象的内存空间
           B) actorInstance(memory); //初始化对象
           C) instance = memory; //设置instance指向刚分配的内存地址
    3. 上面的 B)、C) 两步骤可能被 JIT 重排序，导致instance拿到内存空间，但实际还没初始化完成
    4. 因此加上 volatile 修饰使其不被重排序
     */

    private volatile static SingletonDemo instance;

    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }
}
