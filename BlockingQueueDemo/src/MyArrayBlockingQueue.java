public class MyArrayBlockingQueue<E> {

    private Object[] items;
    private int count;
    private int capacity;
    private int putIndex;
    private int takeIndex;
    private Locker locker;
    private final int DEFAULT_CAPACITY = 65535;

    public MyArrayBlockingQueue() {
        this.locker = new Locker();
        this.putIndex = 0;
        this.takeIndex = 0;
        this.capacity = DEFAULT_CAPACITY;
        this.items = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayBlockingQueue(int capacity){
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

    private void enqueue(E element) throws InterruptedException {
        this.items[this.putIndex] = element;
        if (++this.putIndex == this.items.length) {
            this.putIndex = 0;
        }
        this.count++;
        System.out.println("EnQueue Take Index: " + this.takeIndex + "|PutIndex: " + this.putIndex + "|Size: " + this.size() +
                "|Element: " + element.toString() + "|ThreadName:" + Thread.currentThread().getId() + "|Time:" + System.currentTimeMillis());
    }

    private E dequeue() throws InterruptedException {
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

    public void put(E element) throws InterruptedException{
        if(element == null){
            throw new NullPointerException();
        }
        this.locker.lock();
        try{
            if(this.count == this.items.length){
                return;
            }
            this.enqueue(element);
        }
        finally {
            this.locker.unlock();
        }
    }

    public E take()throws InterruptedException{
        this.locker.lock();
        Object element;
        try{
            element = this.count == 0 ? Integer.valueOf(-1) : this.dequeue();
        }
        finally {
            this.locker.unlock();
        }
        return (E)element;
    }

    public void clear() throws InterruptedException{
        this.locker.lock();
        for(Object element: this.items){
            this.items = null;
        }
        this.putIndex = this.takeIndex = 0;
        this.count = 0;
    }

}
