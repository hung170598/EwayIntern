package Synchronize;

public class Thread1 extends Thread{
    MyClass1 object;

    public Thread1(MyClass1 object) {
        this.object = object;
    }

    @Override
    public void run() {
        object.action1();
    }
}
