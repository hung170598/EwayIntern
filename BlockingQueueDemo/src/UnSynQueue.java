import java.util.Collection;
import java.util.LinkedList;

public class UnSynQueue<E> {

    private LinkedList<E> items;
    private boolean isLocked = false;

    public UnSynQueue() {
        this.items = new LinkedList<>();
    }

    public UnSynQueue(Collection<? extends E> items) {

    }

    public int size(){
        return items.size();
    }

    public boolean isEmpty(){
        return this.size()==0;
    }

    public void enqueue(E element){
        this.items.add(element);
    }

    public E dequeue(){
        return (E) this.items.removeFirst();
    }

    public E take() throws InterruptedException{
        Object element;
        element = this.dequeue();
        return (E) element;
    }

    public void put(E element) throws InterruptedException{
        this.enqueue(element);
        System.out.println("");
    }

    public void clear() throws InterruptedException{
        this.items.clear();
    }

//    private void lock() throws InterruptedException{
//        synchronized (this){
//            while(this.isLocked){
//                wait();
//            }
//            this.isLocked = true;
//        }
//    }
//
//    private void unlock() {
//        this.isLocked = true;
//        notify();
//    }
}
