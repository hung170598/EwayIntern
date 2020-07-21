import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class SetDemo {
    public static void main(String[] args) {
        compareHashSetVsTreeSet();
    }

    /**
     *
     */
    static void compareHashSetVsTreeSet(){
        int n = 100000;
        HashSet hashSet = new HashSet();
        TreeSet treeSet = new TreeSet();
        long startTime, endTime;

        startTime = System.nanoTime();
        for(int i = n; i > 0; i--){
            hashSet.add(Math.random()*i);
        }
        endTime = System.nanoTime();
        System.out.println("Add time of Hash Set: " + 1.0 * (endTime - startTime)/1000000 + "ms");

        startTime = System.nanoTime();
        for(int i = n; i > 0; i--){
            treeSet.add(Math.random()*i);
        }

        endTime = System.nanoTime();
        System.out.println("Add time of Tree Set: " + 1.0 * (endTime - startTime)/1000000 + "ms");

        Iterator itr = hashSet.iterator();
        while(itr.hasNext()){
            System.out.print(itr.next() + " ");
        }
        System.out.println();

        itr = treeSet.iterator();
        while(itr.hasNext()){
            System.out.print(itr.next() + " ");
        }
        System.out.println();
    }
}
