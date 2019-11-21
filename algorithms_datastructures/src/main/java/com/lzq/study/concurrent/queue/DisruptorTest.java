package com.lzq.study.concurrent.queue;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 批量消费场景
 */
public class DisruptorTest {

    public static class MessageEvent{
        @Getter
        @Setter
        private String message;
    }

    public static class MessageEventFactory implements EventFactory<MessageEvent>{
        @Override
        public MessageEvent newInstance() {
            return new MessageEvent();
        }
    }

    public static class MessageEventTranslator implements EventTranslatorOneArg<MessageEvent,String>{

        @Override
        public void translateTo(MessageEvent messageEvent, long l, String s) {
            messageEvent.setMessage(s);
        }
    }

    public static class MessageThreadFactory implements ThreadFactory{

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"simple disruptor test thread");
        }
    }

    public static class MessageEventHandler implements EventHandler<MessageEvent>{
        private List<String> collections = new ArrayList<>();
        @Override
        public void onEvent(MessageEvent messageEvent, long l, boolean b) throws Exception {
            collections.add(messageEvent.getMessage());
            if (collections.size()>=100){
                TimeUnit.MILLISECONDS.sleep(100);
                collections.forEach(s -> System.out.printf(s+","));
                System.out.println();
                collections.clear();
            }
        }
    }


    public static class MessageExceptionHandler implements ExceptionHandler<MessageEvent>{

        @Override
        public void handleEventException(Throwable throwable, long l, MessageEvent messageEvent) {
            throwable.printStackTrace();
        }

        @Override
        public void handleOnStartException(Throwable throwable) {
            throwable.printStackTrace();
        }

        @Override
        public void handleOnShutdownException(Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static class MessageEventProducer{
        private RingBuffer<MessageEvent> ringBuffer;
        public MessageEventProducer(RingBuffer<MessageEvent> ringBuffer){
            this.ringBuffer = ringBuffer;
        }
        public void onData(String message){
            EventTranslatorOneArg<MessageEvent,String> translator = new MessageEventTranslator();
            ringBuffer.publishEvent(translator,message);
        }
    }

    public static void main(String[] args) {
        int ringBufferSize = 1024;//2的N次方
        Disruptor<MessageEvent> disruptor = new Disruptor<>(new MessageEventFactory(),ringBufferSize,new MessageThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith(new MessageEventHandler());
        disruptor.setDefaultExceptionHandler(new MessageExceptionHandler());
        RingBuffer<MessageEvent> ringBuffer = disruptor.start();
        MessageEventProducer producer = new MessageEventProducer(ringBuffer);
        String message = "hello Disruptor";
        for (int i=0;i<=5000;i++){
            producer.onData(message+i);
        }
    }
}
