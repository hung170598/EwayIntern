import java.io.File;
import java.util.Date;
import java.util.HashMap;

public class ThreadSchedulerDemo extends Thread{
    HashMap<String, Long> listFile;
    long lastModifiedTime;
    long delayTime;
    File dir;
    boolean isStop;

    public ThreadSchedulerDemo(File dir, long delayTime) {
        this.dir = dir;
        this.delayTime = delayTime;
        this.lastModifiedTime = dir.lastModified();
        this.listFile = new HashMap<>();
        for(File file: this.dir.listFiles()){
            this.listFile.put(file.getName(), file.lastModified());
        }
        this.isStop = false;
    }



    @Override
    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeMillis);

        while(!this.isStop){
            currentTimeMillis = System.currentTimeMillis();
            currentDate.setTime(currentTimeMillis);
            long lastModifiedTime = this.dir.lastModified();
            if(this.lastModifiedTime >= lastModifiedTime){
                System.out.println(currentDate + " - No File Change");
            }
            else {
                System.out.println(currentDate + " - Has File Change");
                this.lastModifiedTime = lastModifiedTime;
                File[] arrFile2 = this.dir.listFiles();
                for(File file:arrFile2){
                    if(this.listFile.containsKey(file.getName())){
                        if(this.listFile.get(file.getName()) < file.lastModified()){
                            this.listFile.put(file.getName(), file.lastModified());
                            System.out.println("Update File " + file.getName());
                        }
                    }
                    else{
                        this.listFile.put(file.getName(), file.lastModified());
                        System.out.println("Create File " + file.getName());
                    }
                }
            }
            try{
                Thread.sleep(this.delayTime);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        File dir = new File("input_1");
        Thread fileChangeChecker = new ThreadSchedulerDemo(dir, 2000);
        System.out.println("Start Check File Change:");
        fileChangeChecker.start();
    }
}
