//!Callable
/* 
! what is callable
    In simple terms, the Callable interface in Java represents a task
    that can be done in the background and gives a result. It's like a 
    recipe you give to someone to cook a dish and get back the cooked dish later.
 */




/* 
 ! ishi ama awaya zhmara la iak tawakw limit kobkatawa

    class ComputeSum implements Callable<Integer>{
        private int limit;
        private int result;

        public ComputeSum(int limit){
            this.limit = limit;
        }
        public int getResult() {
            return result;
        }
        
        @Override
        public Integer call() {
            for (int i = 0; i < limit + 1; i++) {
                result += i;
            }
            return result;
        }
    }
    public class Main {

        public static void main(String[] args) throws InterruptedException, ExecutionException {
            
            ExecutorService pool = Executors.newFixedThreadPool(1);   //yak thread drwst krawa
            ComputeSum cs = new ComputeSum(10000); // zhmarakanman la 0 tawkaw 100000 sum krdwa
            
            Future<Integer> futre = pool.submit(cs); // 7sabka task tekrdwa
            
            ! WRONG WAY -- MOT CORRECTSystem.out.println("the result using the old way: "+cs.getResult());
            You will get result = zero  0 cause the computation hasent started yet


            ! write WAY(baxwa nazanm rite chon anwsre) -- System.out.println("the result: "+futre.get()); // am method a ishaka awastene 

            ! Esta agar amay xwarawa bakar beni rasta chwkwm methodakai sarawa am threaday wastandwa, computationaka tawaw bwa
             System.out.println("the result using : "+cs.getResult());
        }
    }
 */




 /* 
    !What if you wanted to return more than one variable or you wanted to return an object
    Simply return an object
    

        class ComputeResult {
            public int sum;
            public long fact;
        }
        ! change all the types to the class's name
        class ComputeSum implements Callable<ComputeResult> {

            private int limit;
            private int result;
            public long factorial = 1;

            public ComputeSum(int limit){
                this.limit = limit;
            }

            @Override
            public ComputeResult call() {
                ComputeResult object1 = new ComputeResult();
                for (int i = 0; i < limit + 1; i++) {
                    result += i;
                }
                object1.sum = result;
                for (int i = 1; i < limit + 1; i++) {
                    factorial *= i;
                    System.out.println(factorial +" ");
                }
                object1.fact =factorial;
                return object1;
            }
        }

        public class Main {

        public static void main(String[] args) throws InterruptedException, ExecutionException {
            
            ExecutorService pool = Executors.newFixedThreadPool(1); 
            ComputeSum cs = new ComputeSum(10000);
        
            Future<ComputeResult> futre = pool.submit(cs); // 7sabka task tekrdwa

             System.out.println("the result: "+futre.get()); // am method a ishaka awastene 

        }
    }
 
  */




/* 
  !CompletableFuture 
  It provides a powerful and flexible way to handle asynchronous tasks, 
  compose multiple tasks, and process their results.
  */


        import java.util.concurrent.CompletableFuture;
        import java.util.concurrent.ExecutionException;
        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;
        
        public class Main {
            public static void main(String[] args) throws InterruptedException, ExecutionException {
                ExecutorService pool = Executors.newFixedThreadPool(10);    // Thread pool for getOrder()
                ExecutorService pool2 = Executors.newFixedThreadPool(100);   // Thread pool for processOrder() and saveToDb()
        
                CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> getOrder(), pool)
                        .thenApply(order -> processOrder(order))
                        .thenApplyAsync(details -> saveToDb(details), pool2);
        
                future.thenAccept(result -> {
                    System.out.println("Result: " + result);
                });
        
                // Other work can be done here while the CompletableFuture chain is executing
        
                // Block and wait for the result
        
                // Shutdown the thread pools after the work is completed
                pool.shutdown();
                pool2.shutdown();
            }
        
            public static int getOrder() {
                // Simulate fetching an order
                return 42;
            }
        
            public static String processOrder(int order) {
                // Simulate processing the order and generating details
                return "Order processed: " + order;
            }
        
            public static Integer saveToDb(String details) {
                // Simulate saving the details to a database
                return details.length(); // Returning the length of the details string as an example
            }
        }