import java.util.Vector;
import java.util.concurrent.BlockingQueue;

public class ThreadDemo {
    public static void main(String[] args){
        ThreadB b = new ThreadB();
        b.start();
        Vector
        synchronized(b){
            try{
                System.out.println("Waiting for b to complete...");
                b.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("Total is: " + b.total);
        }
        System.out.println("End");
    }
}

class ThreadB extends Thread{
    int total;
    @Override
    public void run(){
        synchronized(this){
            for(int i=0; i<100 ; i++){
                total += i;
            }
            notify();
        }
    }
}