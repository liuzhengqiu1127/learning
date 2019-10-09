package com.lzq.study.geektime.structure.queue;

public class Consumer {
    private Queue queue;
    public Consumer(Queue queue){
        this.queue = queue;
    }
    public void consume() throws InterruptedException{
        while (true){
            Long data = queue.poll();
            if (data == null){
                Thread.sleep(100);
            }else {
                System.out.println(data);
            }
        }
    }
}
