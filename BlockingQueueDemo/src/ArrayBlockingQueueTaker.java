public class ArrayBlockingQueueTaker implements Runnable{
    ArrayBlockingQueue queue;
    int num;

    public ArrayBlockingQueueTaker(ArrayBlockingQueue queue, int num) {
        this.queue = queue;
        this.num = num;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (i < num) {
                this.queue.take();
                i++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
