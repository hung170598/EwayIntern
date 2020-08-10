package Synchronize;

 public class MyClass1 {
    volatile Object object;
    int count;
    Object lock1 = new Object();
    Object lock2 = new Object();

//    public synchronized void action1(){
//        System.out.println("Start Action 1");
//        while(true){
//
//        }
//    }
//
//    public synchronized void action2(){
//        System.out.println("Start Action 2");
//        while(true){
//
//        }
//    }

    public void action1(){
        synchronized (lock1){
            System.out.println("Start Action 1");
            while(true){

            }
        }
    }

    public void action2(){

        synchronized (lock2){
            System.out.println("Start Action 2");
            while(true){

            }
        }
    }
}
