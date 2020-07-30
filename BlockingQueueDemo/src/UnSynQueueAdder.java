public class UnSynQueueAdder implements Runnable{

    private UnSynQueue queue;
    private int num;

    public UnSynQueueAdder(UnSynQueue queue, int num) {
        this.queue = queue;
        this.num = num;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (i < num) {
                this.queue.put(queue.size() + 1);
                System.out.println("Add: " + i + ". Queue Size: " + queue.size());
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
