import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

public class MyThreadPool {

    private int corePoolSize;
    private int maxPoolSize;
    private BlockingQueue<Runnable> taskQueue;
    private HashSet<WorkerThread> workers;
    private boolean rejectedHandler;
    private boolean isStopped = false;
    private int numTask = 0;
    private int numCompleteTask = 0;
    private Object incCompleteTaskLock = new Object();

    public MyThreadPool(int corePoolSize, int maxPoolSize, BlockingQueue<Runnable> taskQueue, boolean rejectedHandler) {
        this.workers = new HashSet<>();
        if (corePoolSize >= 0 && maxPoolSize >= 0 && maxPoolSize >= corePoolSize) {
            if (taskQueue != null) {
                this.corePoolSize = corePoolSize;
                this.maxPoolSize = maxPoolSize;
                this.taskQueue = taskQueue;
                this.rejectedHandler = rejectedHandler;
            } else {
                throw new NullPointerException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public MyThreadPool(int corePoolSize, int maxPoolSize, BlockingQueue<Runnable> taskQueue) {
        this(corePoolSize, maxPoolSize, taskQueue, false);
    }

    public synchronized void execute(Runnable task) {
        if (this.isStopped) throw
                new IllegalStateException("Pool is stop");
        if(task == null) throw
                new NullPointerException();

//        System.out.println("PRE Put:PoolWorkerSize:" + this.workers.size() + "|Task Queue:" + this.taskQueue.size());
        this.putTaskToQueue(task);
//        System.out.println("After Put:PoolWorkerSize:" + this.workers.size() + "|Task Queue:" + this.taskQueue.size());
        if(this.workers.size() <= this.corePoolSize ||
                (this.taskQueue.remainingCapacity() == 0 && this.workers.size() < maxPoolSize)){
            WorkerThread worker = this.createWorker();
            this.workers.add(worker);
            worker.start();
        }
    }

    public synchronized void shutdownNow() {
        this.isStopped = true;
        this.numTask = this.numCompleteTask;
        Iterator itr = this.workers.iterator();
        while (itr.hasNext()){
            WorkerThread thread = (WorkerThread) itr.next();
            thread.doStop();
            itr.remove();
        }
    }

    public synchronized void shutdown() {
        this.isStopped = true;
        if(this.isTerminated()){
            this.shutdownNow();
        }
    }

    private synchronized WorkerThread createWorker() {
        WorkerThread worker = new WorkerThread(this.taskQueue);
        worker.setParent(this);
        worker.setName("mypool-thread-" + this.workers.size());
        return worker;
    }

    public boolean isTerminated() {
        return (this.isStopped && this.numTask == this.numCompleteTask);
    }

    private void putTaskToQueue(Runnable task){
        try{
            if(this.rejectedHandler){
                this.taskQueue.put(task);
                this.numTask++;
            } else {
                this.numTask += this.taskQueue.offer(task) ? 1:0;
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public boolean isRunning(){
        return !this.isStopped;
    }

    public void incCompleteTask(){
        synchronized (this.incCompleteTaskLock){
            this.numCompleteTask++;
            if(this.isTerminated()){
                this.shutdownNow();
            }
        }
    }
}

class WorkerThread extends Thread {

    private BlockingQueue<Runnable> taskQueue;
    private boolean isStopped = false;
    private MyThreadPool parent;

    public WorkerThread(BlockingQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while(!this.isStopped){
            if(!parent.isRunning() && this.taskQueue.isEmpty()){
                this.doStop();
                return;
            }
            try{
//                long startTime = System.currentTimeMillis();
                Runnable runnable = taskQueue.take();
                runnable.run();
                parent.incCompleteTask();
//                long endTime = System.currentTimeMillis();
//                System.out.println("|TimeProcess:" + (endTime - startTime));
            } catch(InterruptedException e ){

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void doStop() {
        if(this.isStopped) return;
        this.isStopped = true;
        System.out.println(this.getName() + " Stopped!");
        this.interrupt();
    }

    public void setParent(MyThreadPool parent) {
        this.parent = parent;
    }
}