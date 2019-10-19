package com.kpmg.lzq.netty.thread;

import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyThread implements Runnable {
    private Channel channel;
    private Object msg;

    public void run() {
        channel.writeAndFlush(msg);
    }
}
