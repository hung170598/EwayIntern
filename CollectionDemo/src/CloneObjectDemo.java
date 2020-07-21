import java.util.Iterator;
import java.util.TreeSet;

public class CloneObjectDemo {
    public static void main(String[] args) throws CloneNotSupportedException{
        TreeSet<Student> set = new TreeSet<>();
        set.add(new Student(5, "A", "B"));
        set.add(new Student(3, "A", "B"));
        set.add(new Student(5, "B", "C"));
        set.add(new Student(5, "B", "A"));
        set.add(new Student(5, "A", "B"));

        for(Student i : set){
            System.out.println(i);
        }
    }
}