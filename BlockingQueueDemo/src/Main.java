import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Integer> synQueue = new LinkedBlockingQueue<>();
        UnSynQueue<Integer> unSynQueue = new UnSynQueue<>();
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue();
//        Thread putter = new Thread(new ArrayBlockingQueueAdder(arrayBlockingQueue, 100));
//        Thread taker = new Thread(new ArrayBlockingQueueTaker(arrayBlockingQueue, 100));
//        putter.start();
//        taker.start();
//        System.out.println(arrayBlockingQueue.size());
        ArrayBlockingQueue<Integer> integerArrayBlockingQueue = new ArrayBlockingQueue<>(50);
        for(int i = 0; i < 50; i++){
            integerArrayBlockingQueue.put(i);
        }
        Thread putter = new Thread(() -> {
            for(int i = 0; i < 50; i++){
                try{
                    integerArrayBlockingQueue.put(i);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread adder = new Thread(() -> {
            for(int i = 0; i < 100; i++){
                try{
                    integerArrayBlockingQueue.take();
                    Thread.sleep(10);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        putter.start();
        adder.start();


//        runArrayAdder(arrayBlockingQueue);
//        runArrayTaker(arrayBlockingQueue);

//        runSynQueueAdder(synQueue);

//        runUnSynQueueAdder(unSynQueue);

//        int size = 10000;
//        for(int i = 1; i <= size; i++){
//            synQueue.put(i);
//            unSynQueue.put(i);
//            arrayBlockingQueue.put(i);
//
//        }
//        System.out.println(unSynQueue.size());
//
//        runSynQueueTaker(synQueue);
//
//        runUnSynQueueTaker(unSynQueue);


    }

    public static void runArrayTaker(ArrayBlockingQueue queue) throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 10; i++) {
            executors.execute(new ArrayBlockingQueueTaker(queue, 10));
        }
        executors.shutdown();
//        while (!executors.isTerminated()) {
//
//        }
//        System.out.println(queue.size());
    }

    public static void runArrayAdder(ArrayBlockingQueue queue) {

        ExecutorService executors = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 10; i++) {
            executors.execute(new ArrayBlockingQueueAdder(queue, 10));
        }
        executors.shutdown();
//        while (!executors.isTerminated()) {
//
//        }
//        System.out.println(queue.size());
    }



    public static void runUnSynQueueAdder(UnSynQueue queue) {

        ExecutorService executors = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 100; i++) {
            executors.execute(new UnSynQueueAdder(queue, 100));
        }
        executors.shutdown();
        while (!executors.isTerminated()) {

        }
        System.out.println(queue.size());
    }

    public static void runSynQueueAdder(LinkedBlockingQueue queue) throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 100; i++) {
            executors.execute(new SynQueueAdder(queue, 100));
        }
        executors.shutdown();
        while (!executors.isTerminated()) {

        }
        System.out.println(queue.size());
    }

    public static void runSynQueueTaker(LinkedBlockingQueue queue) throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 100; i++) {
            executors.execute(new SynQueueTaker(queue, 100));
        }
        executors.shutdown();
        while (!executors.isTerminated()) {

        }
        System.out.println(queue.size());
    }

    public static void runUnSynQueueTaker(UnSynQueue queue) {
        ExecutorService executors = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 100; i++) {
            executors.execute(new UnSynQueueTaker(queue, 100));
        }
        executors.shutdown();
        while (!executors.isTerminated()) {

        }
        System.out.println(queue.size());
    }
}
