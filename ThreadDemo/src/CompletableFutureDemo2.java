import java.io.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        File dir = new File("input");
        File[] listFile = dir.listFiles();
        ArrayList<CompletableFuture> futureList = new ArrayList<>();
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>(50000);

        for(File file: listFile){
            CompletableFuture future = CompletableFuture.supplyAsync(() -> {
                LinkedHashMap<String, Integer> hashMap = new LinkedHashMap<>(50000);
                //System.out.println("Reading File: " + file.getName());
                try{
                    BufferedReader fin = new BufferedReader( new InputStreamReader(
                            new FileInputStream(file), "UTF-8"
                    ));
                    String str;
                    while ((str = fin.readLine()) != null){
                        StringTokenizer strTokenizer = new StringTokenizer(str, " .,+-:()!=?;\"{}");
                        while(strTokenizer.hasMoreTokens()){
                            String key = strTokenizer.nextToken();
                            if(hashMap.containsKey(key)){
                                int value = hashMap.get(key);
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
            }).thenAccept(hashMap -> {
                hashMap.forEach((key, value) ->{
                    map.merge(key, value, (v1, v2) -> (v1 + v2));
                });
            });
            futureList.add(future);
        }
        System.out.println("Start running CompletableFuture");
        long startTime, endTime;
        startTime = System.nanoTime();

        for(CompletableFuture future:futureList){
            future.get();
        }
        endTime = System.nanoTime();
        System.out.println("Process Timme: " + (endTime - startTime) + " in " +
                futureList.size() + " file");

        File outFile = new File("output2.txt");
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

        System.out.println("Done!");
    }
}
