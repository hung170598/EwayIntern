import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(100);
        MyThreadPool myThreadPool = new MyThreadPool(5, 10, taskQueue, true);
        int numTask = 1000;
        for(int i = 0; i< numTask; i++){
//            System.out.print("Num:" + i + "|");
            long startTime = System.currentTimeMillis();
            MyTask task = new MyTask(i + 1, startTime);
            Thread thread = new Thread(task);
            myThreadPool.execute(thread);
        }

//        System.out.println("Re Push!");
//        for(int i = 0; i< numTask; i++){
////            System.out.print("Num:" + i + "|");
//            MyTask task = new MyTask(i + 1);
//            myThreadPool.execute(task);
//        }
        myThreadPool.shutdown();

        System.out.println("Pool Terminatated");
//        try{
//            Thread.sleep(10);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("End");
    }
}
