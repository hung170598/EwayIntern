import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApiDemo {

    public static void main(String[] args) {
        try{
            Stream stream = Files.lines(Paths.get("text.txt"));
            stream = stream.filter(s-> s.equals("Phong"));
            stream.forEach(System.out::println);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

