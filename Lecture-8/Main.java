// package Lecture-8;

/* 
 !Custom ThreadPools 
 import java.util.concurrent.*;

    public class CustomThreadPoolExample {
        public static void main(String[] args) {
            // Create a custom thread pool with 3 minimum threads, 5 maximum threads,
            // a 60-second keep-alive time for idle threads, and a LinkedBlockingQueue for the task queue
            ExecutorService pool = new ThreadPoolExecutor(3, 5, 60L, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>());

            // Submit tasks to the custom thread pool
            for (int i = 0; i < 10; i++) {
                int taskId = i;
                executor.execute(() -> {
                    System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
                });
            }

            // Shutdown the thread pool after all tasks are completed
            executor.shutdown();
        }
    }
    ! explanation
    In this example, we create a custom thread pool using ThreadPoolExecutor with specific parameters. The custom thread pool has a minimum of 3 threads, a maximum of 5 threads, a keep-alive time of 60 seconds for idle threads, and a LinkedBlockingQueue as the task queue.
    We submit 10 tasks to the custom thread pool using the execute() method. Each task is a lambda expression that prints a message with its task ID and the name of the executing thread.
    Finally, we call executor.shutdown() to gracefully shut down the custom thread pool after all tasks are completed.
    By using LinkedBlockingQueue as the task queue, the custom thread pool allows for an unbounded number of tasks to be added to the queue, ensuring that tasks can be submitted without reaching a maximum capacity limit.
 */

 /* 
  !synchronousQueue
    import java.util.concurrent.*;

    public class CustomThreadPoolExample {
        public static void main(String[] args) {
            // Create a custom thread pool with 3 minimum threads, 5 maximum threads,
            // a 60-second keep-alive time for idle threads, and a SynchronousQueue for the task queue
            ExecutorService executor = new ThreadPoolExecutor(3, 5, 60, TimeUnit.SECONDS,
                    new SynchronousQueue<>(), CallerRunsPolicy);

            // Submit tasks to the custom thread pool
            for (int i = 0; i < 10; i++) {
                int taskId = i;
                executor.execute(() -> {
                    System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
                });
            }

            // Shutdown the thread pool after all tasks are completed
            executor.shutdown();
        }
    }
    In this example, we create a custom thread pool using ThreadPoolExecutor with specific parameters. The custom thread pool has a minimum of 3 threads, a maximum of 5 threads, a keep-alive time of 60 seconds for idle threads, and a SynchronousQueue as the task queue.
    We submit 10 tasks to the custom thread pool using the execute() method. Each task is a lambda expression that prints a message with its task ID and the name of the executing thread.
    Finally, we call executor.shutdown() to gracefully shut down the custom thread pool after all tasks are completed.
    By using SynchronousQueue as the task queue, the custom thread pool enforces a handoff mechanism, where each task submitted to the pool requires a waiting worker thread to pick it up. This ensures that tasks are executed immediately without being enqueued, and the number of submitted tasks cannot exceed the number of available threads in the pool.
  */


  /* 
   ! Some policy that we have that would be placed as the last argument of the ThreadPoolExecutor() method
   ?Abort Policy (Default):
        The Abort Policy, also known as the ThreadPoolExecutor.AbortPolicy, is the default rejection policy in Java. When the thread pool's task queue is full, and there are no available worker threads to handle new tasks, the execute() method throws a RejectedExecutionException and rejects the task. This policy ensures that new tasks are immediately rejected and not executed.
    ?Discard Policy:
        The Discard Policy, also known as the ThreadPoolExecutor.DiscardPolicy, is a rejection policy where the execute() method silently discards the rejected task. When the task queue is full, and there are no available worker threads, the newly submitted task is discarded, and no exception is thrown. This policy is useful when it's acceptable to discard tasks without any notification or feedback.
    ?Discard Oldest Policy:
        The Discard Oldest Policy, also known as the ThreadPoolExecutor.DiscardOldestPolicy, is a rejection policy that discards the oldest unprocessed task from the task queue when it is full. The newly submitted task is then added to the queue for processing. This policy allows the thread pool to continue executing tasks while discarding the least urgent or oldest tasks in the queue. No exception is thrown when a task is discarded.
    ?CallerRunsPolicy()
        When the task queue is full and no worker threads are available to execute new tasks, the Caller-Runs policy allows the task to be executed by the thread that submitted it. In other words, the caller thread itself runs the task instead of being rejected or enqueued. This policy can help to avoid task loss and provides a form of backpressure when the thread pool is overloaded.
   */
public class Main {
    
}
