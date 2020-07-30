public class UnSynQueueTaker implements Runnable {

    UnSynQueue<Integer> queue;
    int num;

    public UnSynQueueTaker(UnSynQueue<Integer> queue, int num) {
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
