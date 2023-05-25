import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Main4 {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.completedFuture("Hello from");

        future = future.thenApply(phrase -> phrase + " the class ")
                .thenApply(phrase -> phrase + "SE")
                .thenApply(phrase -> phrase + " " + generateRandomNumber());

        future.thenAccept(result -> System.out.println(result));
    }

    private static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(500);
    }
}