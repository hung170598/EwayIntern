import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue1<Integer> synQueue = new LinkedBlockingQueue1<>();
        UnSynQueue<Integer> unSynQueue = new UnSynQueue<>();

//        runSynQueueAdder(synQueue);

//        runUnSynQueueAdder(unSynQueue);

        int size = 10000;
        for(int i = 0; i < size; i++){
            synQueue.put(i);
            unSynQueue.put(i);
        }
        System.out.println(unSynQueue.size());

        runSynQueueTaker(synQueue);

//        runUnSynQueueTaker(unSynQueue);

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

    public static void runSynQueueAdder(LinkedBlockingQueue1 queue) throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 100; i++) {
            executors.execute(new SynQueueAdder(queue, 100));
        }
        executors.shutdown();
        while (!executors.isTerminated()) {

        }
        System.out.println(queue.size());
    }

    public static void runSynQueueTaker(LinkedBlockingQueue1 queue) throws InterruptedException {
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
