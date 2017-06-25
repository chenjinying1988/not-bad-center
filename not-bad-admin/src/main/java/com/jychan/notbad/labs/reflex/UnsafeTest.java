package com.jychan.notbad.labs.reflex;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 突破私有构造函数
 *
 * Created by chenjinying on 2017/6/25.
 * mail: 415683089@qq.com
 */
public class UnsafeTest {

    /*
    说明：
    因为 Hello 的构造函数是私有的，private Hello() {}，
    因此无法 new Hello 生成实例，如何突破私有构造函数
     */
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
//        Hello hello = new Hello();

        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        Hello hello = (Hello) unsafe.allocateInstance(Hello.class);
        System.out.println(hello);
        hello.setAge(3);
        hello.setName("Jack");
        System.out.println(hello);

    }
}

class Hello {
    private int age = 10;
    private String name = "hello";

    private Hello() {
        this.age = 20;
        this.name = "tom";
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Hello{");
        sb.append("age=").append(age);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}