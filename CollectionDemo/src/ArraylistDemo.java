import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class ArraylistDemo {
    public static void main(String[] args) {
        //compareArrayListVsLinkedList();

       ArrayList listA = new ArrayList<String>();
       listA.add(123);
        System.out.println(listA.get(0));
    }

    static void compareArrayListVsLinkedList(){
        int n = 100000;
        ArrayList arrayList = new ArrayList<Integer>(n);
        LinkedList linkedList = new LinkedList();
        long startTime, endTime;

        for(int i = 0; i<n; i++){
            arrayList.add(i);
            linkedList.add(i);
        }

        startTime = System.nanoTime();
        for(int i = 0; i < n; i++){
            arrayList.get(i);
        }
        endTime = System.nanoTime();
        System.out.println("Get time of Array List: " + 1.0 * (endTime - startTime)/1000000 + "ms");

        startTime = System.nanoTime();
        for(Object itg: arrayList){

        }
        endTime = System.nanoTime();
        System.out.println("Get time of Array List 2: " + 1.0 * (endTime - startTime)/1000000 + "ms");

        startTime = System.nanoTime();
        for(int i = 0; i < n; i++){
            linkedList.get(i);
        }

        endTime = System.nanoTime();
        System.out.println("Get time of Linked List: " + 1.0 * (endTime - startTime)/1000000 + "ms");

        startTime = System.nanoTime();
        while(arrayList.size() > 0){
            arrayList.remove(0);
        }
        endTime = System.nanoTime();
        System.out.println("Delete time of Array List: " + 1.0 * (endTime - startTime)/1000000 + "ms");

        startTime = System.nanoTime();
        while(linkedList.size() > 0){
            linkedList.remove(0);
        }
        endTime = System.nanoTime();
        System.out.println("Delete tim of Linked List: " + 1.0 * ( endTime - startTime)/1000000 + "ms");
    }
}
