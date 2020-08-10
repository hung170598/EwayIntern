public class MyTask implements Runnable{

    int id;
    long startTime;

    public MyTask(int id, long startTime) {
        this.id = id;
        this.startTime = startTime;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(200);
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("ThreadName: " + Thread.currentThread().getName() + "|ThreadID:" + Thread.currentThread().getId() +
                "|TaskID:" + this.id + "|ProcessTime:" + (System.currentTimeMillis() - this.startTime) +"ms" );
    }
}
