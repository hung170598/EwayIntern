import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.BlockingQueue;

public class ReadDirNoThread implements Runnable{
    private File dir;
    private int sttFile;
    String[] listFile;
    LinkedHashMap<String, Integer> hashMap;

    public ReadDirNoThread(File dir) {
        this.dir = dir;
        this.sttFile = 0;
        this.listFile = dir.list();
        this.hashMap = new LinkedHashMap<>(50000);
    }

    @Override
    public void run() {
        if(this.sttFile >= this.listFile.length) return;
        String str, key;
        int value;
        BufferedReader fin = null;
        //System.out.println("Readding file: " + this.listFile[this.sttFile]);
        try{
            fin = new BufferedReader(new InputStreamReader(
                    new FileInputStream(this.dir.getName() + "/" + this.listFile[this.sttFile])));
            while((str = fin.readLine()) != null){
                StringTokenizer strTokenizer = new StringTokenizer(str, " .,+-:()!=?;\"{}");
                while(strTokenizer.hasMoreTokens()){
                    key = strTokenizer.nextToken();
                    if(this.hashMap.containsKey(key)){
                        value = this.hashMap.get(key);
                        this.hashMap.put(key, value + 1);
                    } else {
                        this.hashMap.put(key, 1);
                    }
                }
            }
            fin.close();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println("IO Exception");
        } finally {
            this.sttFile++;
        }

    }

    public static void main(String[] args) {
        long startTime, endTime;
        File dir = new File("input");
        ReadDirNoThread readAFileThread = new ReadDirNoThread(dir);
        HashMap<String, Integer> map = new HashMap<>(50000);
        System.out.println("Start read input dir: " +
                readAFileThread.listFile.length + "file");

        startTime = System.nanoTime();
        while(readAFileThread.sttFile < readAFileThread.listFile.length){
            readAFileThread.run();
        }
        endTime = System.nanoTime();
        System.out.println("Time process: " + 1.0 * (endTime - startTime)/1000000 + "ms");

        File outFile = new File("output1.txt");
        BufferedWriter fout;

        try{
            fout = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile)));
            Set<String> keySet = readAFileThread.hashMap.keySet();
            for(String key: keySet){
                fout.write(key + ":" + readAFileThread.hashMap.get(key) + "\n");
            }
            fout.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println(readAFileThread.sttFile + " " + readAFileThread.listFile.length);
    }
}
