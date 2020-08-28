import java.util.concurrent.CompletableFuture;


public class ThreadDemo {
    public static void main(String[] args) throws Exception {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() ->{
            return 1;
        }).handle((a, b) -> {
           return a + 1;
        });
        System.out.println(completableFuture.get());
    }
}
