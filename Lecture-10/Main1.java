// package Lecture-10;
/* 
 !Visibility Problem

    In concurrent programming, the visibility problem refers to a situation where changes made to a shared variable by one thread may not be immediately visible to other threads. This can lead to incorrect behavior, unexpected results, or even program errors.
    The visibility problem occurs due to various factors, including CPU caching, compiler optimizations, and out-of-order execution. When a thread modifies a variable, it may store the updated value in its local cache instead of immediately writing it back to the main memory. Other threads, which have their own copies of the variable in their local caches, may continue reading the stale value from their caches, unaware of the update.

    To address the visibility problem, synchronization mechanisms are used to establish a happens-before relationship between threads, ensuring that changes made by one thread are visible to others. Some commonly used synchronization constructs are:

        ?Synchronization Blocks/Methods:
            Using the synchronized keyword, you can create critical sections that allow only one thread to execute them at a time. Synchronization ensures that changes made within synchronized blocks are visible to other threads when they acquire the same lock.
        ?Volatile Variables: 
            Declaring a variable as volatile ensures that any read or write operation on that variable is directly performed on the main memory, bypassing local caches. This guarantees visibility across threads, as changes made to a volatile variable are immediately visible to other threads.
        ?Locks: 
            Explicit lock objects from the java.util.concurrent.locks package, such as ReentrantLock, provide more fine-grained control over synchronization. Threads can acquire and release locks to control access to shared resources, ensuring visibility of changes made within the critical sections protected by the locks.
        ?Atomic Variables: 
            Classes in the java.util.concurrent.atomic package, such as AtomicInteger or AtomicReference, provide atomic operations on variables without requiring explicit synchronization. These atomic variables ensure visibility and atomicity for common operations like read-modify-write.
 */



 /* 
    ! disadvantage -> performance
    ! Volatile

    In Java, the volatile keyword is used to indicate that a variable may be modified asynchronously
    by multiple threads. When a variable is declared as volatile, it ensures that any read or write operation
    on that variable is directly performed on the main memory, rather than using a thread's local cache.


    Visibility: The volatile keyword guarantees visibility across threads. When a thread modifies
    a volatile variable, the updated value is immediately visible to other threads. Similarly,
    when a thread reads a volatile variable, it always sees the most up-to-date value
  */


/*   
    ! Volatile 
        public class Main {
            private volatile boolean flag = false;

            public static void main(String[] args) {
                Main example = new Main();

                // Thread 1: Updates the flag to true
                Thread thread1 = new Thread(() -> {
                    System.out.println("Thread 1: Setting flag to true");
                    example.setFlag(true);
                });

                // Thread 2: Reads the flag value
                Thread thread2 = new Thread(() -> {
                    System.out.println("Thread 2: Reading flag value: " + example.getFlag());
                });

                thread1.start();
                thread2.start();
            }

            public void setFlag(boolean value) {
                flag = value; // Update the value of the flag
            }

            public boolean getFlag() {
                return flag; // Read the value of the flag
            }
        }
 */

 /* 
    ! Locks - This way is slower
        import java.util.concurrent.locks.Lock;
        import java.util.concurrent.locks.ReentrantLock;

        public class ReentrantLockExample {
            private boolean flag = false;
            private Lock lock = new ReentrantLock();

            public static void main(String[] args) {
                ReentrantLockExample example = new ReentrantLockExample();

                // Thread 1: Updates the flag to true
                Thread thread1 = new Thread(() -> {
                    System.out.println("Thread 1: Setting flag to true");
                    example.setFlag(true);
                });

                // Thread 2: Reads the flag value
                Thread thread2 = new Thread(() -> {
                    lock.lock();
                    try {
                        System.out.println("Thread 2: Reading flag value: " + example.getFlag());
                    } finally {
                        lock.unlock();
                    }
                });

                thread1.start();
                thread2.start();
            }

            public void setFlag(boolean value) {
                lock.lock();
                try {
                    flag = value; // Update the value of the flag
                } finally {
                    lock.unlock();
                }
            }

            public boolean getFlag() {
                lock.lock();
                try {
                    return flag; // Read the value of the flag
                } finally {
                    lock.unlock();
                }
            }
        }

  */


/* 
 !RentrantReadWriteLock

    The ReentrantReadWriteLock is a synchronization mechanism provided by Java      
    that allows concurrent access to shared resources. It is designed to provide better
    concurrency than using a simple ReentrantLock when there are multiple readers and
    infrequent writers.
    The ReentrantReadWriteLock maintains two locks: a read lock and a write lock.    
    Multiple threads can acquire the read lock simultaneously as long as no thread holds
    the write lock. This allows for concurrent reading and improves performance when the
    shared resource is mostly read.
    However, when a thread wants to modify the shared resource, it needs to acquire  
    the write lock exclusively. The write lock allows only one thread to hold it, ensuring 
    that no other threads are reading or writing to the shared resource while a write operation 
    is in progress. This exclusive access ensures data consistency and prevents data corruption.
    The ReentrantReadWriteLock is useful in scenarios where multiple threads need to 
    read a shared resource concurrently, and write operations occur less frequently. It is commonly
    used in scenarios such as caching, database access, and read-heavy applications.
    */
import java.util.concurrent.locks.*;

public class Main1 {
    public static void main(String[] args) {
        System.out.println("slaw");
        SharedObject2 sharedObject = new SharedObject2();
        // Writer thread
        Thread writerThread = new Thread(() -> {
            sharedObject.inc();
            System.out.println("Writer thread: Incremented sCount");
        });

        // Reader thread
        Thread readerThread = new Thread(() -> {
            int count = sharedObject.get();
            System.out.println("Reader thread: Retrieved sCount: " + count);
        });

        writerThread.start();
        readerThread.start();
    }
}

class SharedObject2 {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private int sCount = 0;

    public void inc() {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            sCount++;
        } finally {
            writeLock.unlock();
        }
    }

    public int get() {
        Lock readLock = lock.readLock();
        readLock.lock();
        try {
            return sCount;
        } finally {
            readLock.unlock();
        }
    }
}