package com.company;

import java.lang.*;
import java.util.Scanner;

class SomeClass extends Main.Abstract1 implements Main.IInterface {

    SomeClass() {}

    @Override
    int aMethod2() {
        System.out.println("Object called overrided abstract method.");
        return 0;
    }

    @Override
    public void iFunc1() {
        System.out.println("Object called overrided first interface  method.");
    }

    @Override
    public int iFunc2() {
        System.out.println("Object called overrided second interface method.");
        return 0;
    }

    @Override
    String aMethod3() {
        System.out.println("Overrided abstract method is called.");
        return "";
    }
}


class Main {

    static abstract class Abstract1 {
        void aMethod1() {};
        abstract int aMethod2();

        String aMethod3() {
            String str = "Abstract class method is called.";
            System.out.println(str);
            return str;
        }
    }

    interface IInterface {
        void iFunc1();
        int iFunc2();

        default void def_iFunc() {
            System.out.println("Default interface method is called.");
        };
    }

    public static void main(String[] args) {
        SomeClass object = new SomeClass();

        object.aMethod1();
        object.aMethod2();
        object.iFunc1();
        object.iFunc2();
        object.def_iFunc();

        System.out.println();
        System.out.println();

        Abstract1 abstr = object;
        abstr.aMethod3();

        IInterface interf = object;
        interf.def_iFunc();
    }
}

