import java.util.Collection;
import java.util.LinkedList;

public class LinkedBlockingQueue1<E>{

    private LinkedList<E> items;
    private boolean isLocked = false;

    public LinkedBlockingQueue1() {
        this.items = new LinkedList<>();
    }

    public LinkedBlockingQueue1(Collection<? extends E> items) {

    }

    public int size() throws InterruptedException{
        this.lock();
        try{
            return items.size();
        }
        finally {
            this.unlock();
        }
    }

    public boolean isEmpty() throws InterruptedException{
        return this.size()==0;
    }

    public void enqueue(E element){
        this.items.add(element);
    }

    public E dequeue(){
        return (E) this.items.removeFirst();
    }

    public E take() throws InterruptedException{
        this.lock();
        Object element;
        try{
            element = this.dequeue();
        } finally {
            this.unlock();
        }
        return (E) element;
    }

    public void put(E element) throws InterruptedException{
        if(element == null) {
            throw new NullPointerException();
        }
        else {
            this.lock();
            try{
                this.enqueue(element);
            }
            finally {
                this.unlock();
            }
        }
    }

    public void clear() throws InterruptedException{
        this.lock();
        try{
            this.items.clear();
        }
        finally {
            this.unlock();
        }
    }

    private synchronized void lock() throws InterruptedException{
        while(this.isLocked){
            wait();
        }
        this.isLocked = true;
    }

    private synchronized void unlock() {
        this.isLocked = false;
        notify();
    }
}
