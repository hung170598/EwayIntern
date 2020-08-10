package Synchronize;

public class Main {
    public static void main(String[] args) {
        MyClass1 object = new MyClass1();

        Thread1 thread1 = new Thread1(object);
        Thread2 thread2 = new Thread2(object);

        thread1.start();
        thread2.start();
    }
}
