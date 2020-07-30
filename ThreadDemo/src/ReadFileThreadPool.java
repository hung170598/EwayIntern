import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class ReadFileThreadPool implements Callable {
    private File file;

    public ReadFileThreadPool(File file) {
        this.file = file;
    }

    @Override
    public LinkedHashMap<String, Integer> call(){
        LinkedHashMap<String, Integer> hashMap = new LinkedHashMap<>(50000);
        String str, key;
        int value;
        //System.out.println("Reading File " + file.getName());

        try{
            BufferedReader fin = new BufferedReader( new InputStreamReader(
                    new FileInputStream(this.file), "UTF-8"
            ));
            while ((str = fin.readLine()) != null){
                StringTokenizer strTokenizer = new StringTokenizer(str, " .,+-:()!=?;\"{}");
                while(strTokenizer.hasMoreTokens()){
                    key = strTokenizer.nextToken();
                    if(hashMap.containsKey(key)){
                        value = hashMap.get(key);
                        hashMap.put(key, value + 1);
                    } else {
                        hashMap.put(key, 1);
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return hashMap;
    }

    public static void main(String[] args) {
        File dir = new File("input");
        File[] arrFile = dir.listFiles();
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>(50000);
        LinkedHashMap<String, Integer> fileHashMap;
        Future<LinkedHashMap> future;
        ArrayList<Future> arrFuture = new ArrayList<>(100);
        Set<String> listKey;
        int value;
        long startTime, endTime;

        System.out.println("Start read input dir: " +
                arrFile.length + " file");


        ExecutorService executor = Executors.newFixedThreadPool(8);

        startTime = System.nanoTime();
        for(File file: arrFile){

            future = executor.submit(new ReadFileThreadPool(file));
            arrFuture.add(future);
        }
        executor.shutdown();

        for(Future future1: arrFuture){
            try{
                fileHashMap = (LinkedHashMap<String, Integer>) future1.get();
                listKey = fileHashMap.keySet();
                for(String key: listKey){
                    if(map.containsKey(key)){
                        value = map.get(key);
                        map.put(key, value + fileHashMap.get(key));
                    } else{
                        map.put(key, fileHashMap.get(key));
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        endTime = System.nanoTime();
        System.out.println("Read Time: " + TimeUnit.MILLISECONDS.convert(endTime - startTime,TimeUnit.NANOSECONDS));


        File outFile = new File("output.txt");
        BufferedWriter fout;
        try{
            fout = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile)));
            Set<String> keySet = map.keySet();
            for(String key: keySet){
                fout.write(key + ":" + map.get(key) + "\n");
            }
            fout.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
