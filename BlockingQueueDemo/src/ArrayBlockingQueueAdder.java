public class ArrayBlockingQueueAdder implements Runnable {
    private ArrayBlockingQueue queue;
    private int num;

    public ArrayBlockingQueueAdder(ArrayBlockingQueue queue, int num) {
        this.queue = queue;
        this.num = num;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (i < num) {
                this.queue.put(Thread.currentThread().getName() + "-" + i);
                Thread.sleep(1);
//                System.out.println("Add: " + i + ". Queue Size: " + queue.size());
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
