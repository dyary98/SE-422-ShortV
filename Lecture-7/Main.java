/* 

 * A thread pool in Java is a managed group of worker threads that --
 * can be used to execute tasks concurrently. It provides a pool --
 * of pre-initialized threads that are ready to execute tasks, which --
 * improves performance and reduces the overhead of creating and destroying -- 
 * threads. Java's built-in ExecutorService interface and the Executors --
 * class provide convenient APIs for working with thread pools.
 */


/* 
    ! Advantages of ThreadPool  vs Thread class
    ?Reusability: Threads are pre-created and reused, eliminating the overhead of creating and destroying threads for each task.
    Resource Management: Efficiently manages the number of threads, optimizing resource utilization.
    ?Scalability: Handles a large number of tasks more efficiently by distributing them among available threads.
    Load Balancing: Automatically distributes tasks across threads, preventing overload on individual threads.
    ?Simplified Thread Management: Eases the process of managing threads in concurrent programming.
    Enhanced Performance: Improves overall performance and responsiveness in concurrent scenarios.
  */



  /* 
  ! DIfferent types of threadpools
   newFixedThreadPool();
   newCachedThreadPool();
   newScheduledThreadPool();

   we have custome ones also
   */



  /* 
    ! Example of using newFixedThreadpool()
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;

    public class FixedThreadPoolExample {
        public static void main(String[] args) {
            ?the following two comments YOu can know how many cores you can use and create a fixed threadpool with that number of cores 
            //  int cores = Runtime.getRuntime().availableProcessors(); // it will tell you your logical cores
            //  ExecutorService executor = Executors.newFixedThreadPool(cores);

            // Create a fixed thread pool with 3 threads
            ExecutorService pool = Executors.newFixedThreadPool(5);

            // Submit tasks to the thread pool
           for (int i = 0; i < 20; i++) {
            pool.submit(new Task());           
        }

            // Shutdown the thread pool after all tasks are completed
            pool.shutdown();
            
        }
    }
    class Task extend Thread{
        
       
    }
    class Task implements Runnable{
        public void run(){
            for (int i = 0; true; i++) {
                System.out.println(i); 
            }
        }
    }
    */



    /* 
        ! Shutdown types and checking
        pool.shutdown();// you can not submit tasks to it and all your threads will die
        pool.shutdownNow(); // chawarenaka yaksar shutdown aka forecfully
        pool.awaitTermination(cores, null)// gives you a delay and then shutsdwon
        pool.isShutdown();
        pool.isTerminated();
     */


     /* 
      ! newCachedThreadPool()
      A cached thread pool in Java dynamically creates and manages
      threads based on the workload, allowing for automatic thread
      recycling and unlimited pool size. It is ideal for bursty workloads
      and simplifies thread management.

      bo tasky bchwk basha, agar yakek la threadkani am threadpool a taskakai xoi tawaw krd ta 60s
      hich taskeki try warnagrt awa xoi terminate abe threadaka automatically

      */


      /* 
       
        ! cached Threadpool
        When the pool is created no thread is created, it depends on the tasks if you sumbit 10 tasks it will create 10 threads, 100 100 threads, but if you have an ideal thread....
        and you submit a task to it and the 60 second of the ideal thread hasnt passed, it will not create another thread but rather uses the ideal thread to execute the task
        automatically if a thread was ideal for 60secs, java will terminate it 
        This solution is very dynamic and it it really good when you have lots of small tasks     

        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;
        import java.util.concurrent.ScheduledThreadPoolExecutor;
        import java.util.concurrent.ThreadPoolExecutor;

        public class Main {
            public static void main(String[] args) {
                !ExecutorService pool = Executors.newCachedThreadPool();
                ThreadPoolExecutor pool1 = (ThreadPoolExecutor) Executors.newCachedThreadPool(); // this way we can use more methods for example we can change the ideal thread time for termination
                
                ExecutorService pool2 = Executors.newCachedThreadPool();
                pool2.submit(new Runnable() {

                    @Override
                    public void run() {
                        
                    }
                    
                })
                !amay xwaraway lagal nia, away sarawa

                for (int i = 0; i < 20; i++) {
                    pool2.submit(new Task());           
                }
            
            }
        }

        class Task implements Runnable{

            public void run(){
                for (int i = 0; true; i++) {
                    System.out.println(i);
                    Thread t1 = new Thread(){
                        public void run(){
                            System.out.println();
                        }
                    };
                }
            }
        }
       */


       /* 
        !newScheduledThreadPool()
        ?A scheduled thread pool allows you to schedule tasks for execution in the future,
        either at a specific time or with a fixed delay between executions.
        It manages a pool of threads that can be reused for executing
        these scheduled tasks.

        import java.util.concurrent.Executors;
        import java.util.concurrent.ScheduledExecutorService;
        import java.util.concurrent.TimeUnit;

        public class ScheduledThreadPoolExample {
            public static void main(String[] args) {
                // Create a scheduled thread pool with 3 threads
                ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);

                Schedule a task to run after a delay of 2 seconds
                pool.schedule(() -> {
                    System.out.println("Task executed after 2 seconds");
                }, 2, TimeUnit.SECONDS);

                ! Schedule a task to run every 5 seconds starting after an initial delay of 1 second
                pool.scheduleAtFixedRate(() -> {
                    System.out.println("Task executed repeatedly every 5 seconds");
                }, 1, 5, TimeUnit.SECONDS);

                // Shutdown the scheduled thread pool after all tasks are completed
                pool.shutdown();
        }
}
        */
public class Main {

    public static void main(String[] args) {
        
    }
}