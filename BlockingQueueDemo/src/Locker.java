public class Locker {

    private boolean isLock = false;

    public Locker() {
    }

    public synchronized void lock()throws InterruptedException{
        while(this.isLock){
            wait();
        }
        this.isLock = true;
    }

    public synchronized void unlock() throws InterruptedException{
        this.isLock = false;
        notify();
    }
}
