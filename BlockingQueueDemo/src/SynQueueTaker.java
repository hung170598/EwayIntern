public class SynQueueTaker implements Runnable {

    LinkedBlockingQueue<Integer> queue;
    int num = 100;

    public SynQueueTaker(LinkedBlockingQueue<Integer> queue, int num) {
        this.queue = queue;
        this.num = num;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (i < num) {
                System.out.println(this.queue.take());
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
