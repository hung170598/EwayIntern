import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueue<E> {

    private Object[] items;
    private int count;
    private int capacity;
    private int putIndex;
    private int takeIndex;
    private Locker locker;
    private final int DEFAULT_CAPACITY = 65535;

    public ArrayBlockingQueue() {
        this.locker = new Locker();
        this.putIndex = 0;
        this.takeIndex = 0;
        this.capacity = DEFAULT_CAPACITY;
        this.items = new Object[DEFAULT_CAPACITY];
    }

    public ArrayBlockingQueue(int capacity) {
        this.locker = new Locker();
        this.putIndex = 0;
        this.takeIndex = 0;
        this.capacity = capacity;
        this.items = new Object[this.capacity];
    }

    public int size() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    private void enqueue(E element) {
        this.items[this.putIndex] = element;
        if (++this.putIndex == this.items.length) {
            this.putIndex = 0;
        }
        this.count++;
        System.out.println("EnQueue Take Index: " + this.takeIndex + "|PutIndex: " + this.putIndex + "|Size: " + this.size() +
                "|Element: " + element.toString() + "|ThreadName:" + Thread.currentThread().getId() + "|Time:" + System.currentTimeMillis());
    }

    private E dequeue() {
        Object element = this.items[this.takeIndex];
        this.items[this.takeIndex] = null;
        if (++this.takeIndex == this.items.length) {
            this.takeIndex = 0;
        }
        this.count--;
        System.out.println("DeQueue Take Index: " + this.takeIndex + "|PutIndex: " + this.putIndex + "|Size: " + this.size() +
                "|Element: " + element.toString() + "|ThreadName:" + Thread.currentThread().getId() + "|Time:" + System.currentTimeMillis());
        return (E) element;
    }

    public synchronized void offer(E element) {
        if (element == null) throw
                new NullPointerException();
        if (this.capacity == this.count) {
            return;
        }
        this.enqueue(element);
        if (this.count == 1) {
            notifyAll();
        }
    }

    public synchronized void put(E element) throws InterruptedException {
        if (element == null) {
            throw new NullPointerException();
        }
        while (this.capacity == this.count) {
            wait();
        }
        this.enqueue(element);
        if (this.count == 1) {
            notifyAll();
        }
    }

    public synchronized E poll() {
        Object element;
        if(this.count == 0) return (E) null;
        if(this.count == this.capacity){
            notifyAll();
        }
        return this.dequeue();
    }

    public synchronized E take() throws InterruptedException {
        while (this.count == 0) {
            wait();
        }
        if (this.count == this.capacity) {
            notifyAll();
        }
        return this.dequeue();
    }

    public synchronized void clear() {
        for (Object element : this.items) {
            this.items = null;
        }
        if (this.count == this.capacity) {
            notifyAll();
        }
        this.putIndex = this.takeIndex = 0;
        this.count = 0;

    }

}
