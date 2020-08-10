package Synchronize;

public class Thread2 extends Thread{
    MyClass1 object;

    public Thread2(MyClass1 object) {
        this.object = object;
    }

    @Override
    public void run() {
        object.action2();
    }
}
