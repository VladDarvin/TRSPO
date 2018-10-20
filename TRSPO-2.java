package com.company;
import java.util.Scanner;
import java.util.ArrayList;

class MyThread extends Thread {
    Thread t;
    String name;
    int delay;
    
    public MyThread(String name, int delay) {
        this.name = name;
        this.delay = delay;
        System.out.println("Thread '" + name + "' created.");
    }
    
    public void run() {
        System.out.println("Thread '" + name + "' began working.");
        try {
            while (true) {
                Thread.sleep(delay);
                System.out.println(name);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread '" + name + "' was interrupted.");
        }
        System.out.println("Thread '" + name + "' finishes working ...");
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<MyThread> threads = new ArrayList<MyThread>();
        while (true) {
            try {
                String name = scanner.next();
                if (name.equalsIgnoreCase("exit"))
                    break;
                String arg = scanner.next();
                if (arg.equalsIgnoreCase("stop")) {
                    for (int i = 0; i < threads.size(); ++i)
                        if (threads.get(i).name.equalsIgnoreCase(name)) {
                            System.out.println("Thread '" + name + "' stopped.");
                            threads.get(i).interrupt();
                            threads.remove(threads.get(i));
                        }
                } else {
                    MyThread t = new MyThread(name, Integer.parseInt(arg));
                    t.start();
                    threads.add(t);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
