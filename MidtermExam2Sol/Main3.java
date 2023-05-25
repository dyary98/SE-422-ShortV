package MidtermExam2Sol;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class Main3 {
    public static void main(String[] args) {
        
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        LinkedBlockingQueue<String> queue2 = new LinkedBlockingQueue<String>();
        Thread1 t1 = new Thread1(queue);
        Thread2 t2 = new Thread2(queue, queue2);
        Thread3 t3 = new Thread3(queue2);
        t1.start();
        t2.start();
        t3.start();
    }
    }

 class Thread1 extends Thread {
    LinkedBlockingQueue<String> queue;
    public Thread1(LinkedBlockingQueue<String> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        while (true) {
            try {
                String text = "Hello From";
                queue.put(text);
                // queue.add(text);
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

 class Thread2 extends Thread {
    LinkedBlockingQueue<String> queue, queue2;
    public Thread2(LinkedBlockingQueue<String> queue, LinkedBlockingQueue<String> queue2){
        this.queue = queue;
        this.queue2 = queue2;
    }

    public void run(){
        try {
            while (true) {
                String text;
                text = queue.take();
                String newText = text + "The Class"; // Hello from the class
                queue2.put(newText);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }  //Hello from
        // String text = queue.remove();  //Hello from

    }
}

 class Thread3 extends Thread {
    LinkedBlockingQueue<String> queue2;
    public Thread3(LinkedBlockingQueue<String> queue2 ){
        this.queue2 = queue2;
    }

    @Override
    public void run(){
        try {
            while (true) {
                String text;
                text = queue2.take();
                String newText = text + "SE";
                Random rand = new Random();
                int num = rand.nextInt(499);
                System.out.println(newText+num); // Hello from the class SE 100 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } //Hello from the class
    }
}