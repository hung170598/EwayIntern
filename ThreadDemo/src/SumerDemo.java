import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.*;

public class SumerDemo {
    public static void main(String[] args) throws Exception{
        long endNum = 2000000000;
        long sum = 0;
        ArrayList<Callable<Long>> callableList = new ArrayList<>();
        callableList.add(new SingleThreadSumer(0, endNum/4));
        callableList.add(new SingleThreadSumer(endNum/4 + 1, endNum/2));
        callableList.add(new SingleThreadSumer(endNum/2 + 1, 3 * endNum/4));
        callableList.add(new SingleThreadSumer(3 * endNum/4 + 1, endNum));
        ExecutorService pool = Executors.newFixedThreadPool(4);

        long startTime, endTime;
        startTime = System.nanoTime();
        List<Future<Long>> resultList = pool.invokeAll(callableList);
        for(Future<Long> future:resultList){
            sum += future.get();
        }
        pool.shutdown();
        //for(long i = 0; i<= endNum; i++) sum+=i;

        endTime = System.nanoTime();
        System.out.println("Time calculator: " + (endTime - startTime));
        System.out.println("Sum = " + sum);
    }
}

class SingleThreadSumer implements Callable{
    long startNum;
    long endNum;
    long sum;

    public SingleThreadSumer(long startNum, long endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
        this.sum = 0;
    }

    @Override
    public Object call() throws Exception {
        for(long ele = this.startNum; ele <= endNum; ele++){
            this.sum += ele;
        }
        return this.sum;
    }
}
