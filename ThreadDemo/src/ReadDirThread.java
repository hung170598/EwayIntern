import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

public class ReadDirThread{

    public static void main(String[] args) {
        long startTime, endTime;
        File dir = new File("input");
        File[] listFile = dir.listFiles();
        HashMap<String, Integer> map = new HashMap<>(100000);
        System.out.println("Start read input dir: " +
                listFile.length + "file");
        int sttFile = 0;

    }


}

class ReadFileThread extends Thread{
    File file;
    LinkedHashMap<String, Integer> hashMap;

    public ReadFileThread(File file) {
        this.file = file;
        this.hashMap = new LinkedHashMap<>(50000);
    }

    @Override
    public void run() {
        String str, key;
        int value;
        BufferedReader fin = null;
        //System.out.println("Readding file: " + this.listFile[this.sttFile]);
        try{
            fin = new BufferedReader(new InputStreamReader(
                    new FileInputStream(this.file)));
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
        }
    }
}