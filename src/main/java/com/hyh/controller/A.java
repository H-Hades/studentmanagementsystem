package com.hyh.controller;
class C{
    public C() {
        System.out.println("GC");
    }
}
class B{
    C c = new C();
    {
        System.out.println("sd");
    }
    public  void  TestB(){
        System.out.println("B");
    }

    public B() {
        System.out.println("Gb");
    }
}
public class A extends B{
    public A() {
        System.out.println("ga");
    }

    public void TestA(){
        System.out.println("A");
    }

    public static void main(String[] args) {
        A a = new A();
    }
}

