package com.lzq.study.concurrent;

import org.junit.Test;

import java.util.concurrent.*;

public class ConcurrentTest {
    @Test
    public void test01(){
        System.out.println("Action...Go!");
        Semaphore semaphore = new Semaphore(5);
        for (int i=0; i < 10; i++){
            Thread thread = new Thread(new SemaphoreWorker(semaphore));
            thread.start();
        }
    }

    class SemaphoreWorker implements Runnable{
        private String name;
        private Semaphore semaphore;
        public SemaphoreWorker(Semaphore semaphore){
            this.semaphore = semaphore;
        }
        @Override
        public void run() {
            try {
                log("it is waiting for a permit!");
                semaphore.acquire();
                log("acquired a permit!");
                log("executed!");
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                log("release a permit!");
                semaphore.release();
            }
        }
        private void log(String msg){
            if (name == null){
                name = Thread.currentThread().getName();
            }
            System.out.println(name + " " + msg);
        }
    }

    @Test
    public void test02() throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        for (int i=0; i < 10; i++){
            Thread thread = new Thread(new MyWorker(semaphore));
            thread.start();
        }
        System.out.println("Action...Go");
        semaphore.release(5);
        System.out.println("Wait for permit off");
        while (semaphore.availablePermits() != 0){
            TimeUnit.MILLISECONDS.sleep(100);
        }
        System.out.println("Action...GO again!");
        semaphore.release(5);
    }

    class MyWorker implements Runnable {
        private Semaphore semaphore;
        public MyWorker(Semaphore semaphore) {
            this.semaphore = semaphore;
        }
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("Executed!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test03() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new FirstBatchWorker(latch));
            t.start();
        }
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new SecondBatchWorker(latch));
            t.start();
        }
        // 注意这里也是演示目的的逻辑，并不是推荐的协调方式
        while ( latch.getCount() != 1 ){
            Thread.sleep(100L);
        }
        System.out.println("Wait for first batch finish");
        latch.countDown();
        Thread.sleep(1000L);
    }

    class FirstBatchWorker implements Runnable {
        private CountDownLatch latch;
        public FirstBatchWorker(CountDownLatch latch) {
            this.latch = latch;
        }
        @Override
        public void run() {
            System.out.println("First batch executed!");
            latch.countDown();
        }
    }
    class SecondBatchWorker implements Runnable {
        private CountDownLatch latch;
        public SecondBatchWorker(CountDownLatch latch) {
            this.latch = latch;
        }
        @Override
        public void run() {
            try {
                latch.await();
                System.out.println("Second batch executed!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test04() throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("Action...GO again!");
            }
        });
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new CyclicWorker(barrier));
            t.start();
        }
        Thread.sleep(1000L);
    }

    class CyclicWorker implements Runnable {
        private CyclicBarrier barrier;
        public CyclicWorker(CyclicBarrier barrier) {
            this.barrier = barrier;
        }
        @Override
        public void run() {
            try {
                for (int i=0; i<3 ; i++){
                    System.out.println(i+"Executed!");
                    barrier.await();
                }
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static final String EXIT_MSG  = "Good bye!";

    @Test
    public void test05() throws InterruptedException {
        Executors.newSingleThreadExecutor();
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();
        TimeUnit.SECONDS.sleep(3);
    }

    class Producer implements Runnable{
        private BlockingQueue<String> queue;
        public Producer(BlockingQueue<String> q) {
            this.queue = q;
        }
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                try{
                    Thread.sleep(5L);
                    String msg = "Message" + i;
                    System.out.println("Produced new item: " + msg);
                    queue.put(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                System.out.println("Time to say good bye!");
                queue.put(EXIT_MSG);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable{
        private BlockingQueue<String> queue;
        public Consumer(BlockingQueue<String> q){
            this.queue=q;
        }

        @Override
        public void run() {
            try{
                String msg;
                while(!EXIT_MSG.equalsIgnoreCase( (msg = queue.take()))){
                    System.out.println("Consumed item: " + msg);
                    Thread.sleep(10L);
                }
                System.out.println("Got exit message, bye!");
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
