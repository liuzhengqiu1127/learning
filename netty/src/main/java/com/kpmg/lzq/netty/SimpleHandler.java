package com.kpmg.lzq.netty;

import com.kpmg.lzq.netty.thread.MyThread;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("开始读取客户端数据=========");
        if(msg instanceof ByteBuf){
            ByteBuf req = (ByteBuf) msg;
            String content = req.toString(Charset.defaultCharset());
            System.out.println(content);

            MyThread myThread = new MyThread(ctx.channel(),"this is test response\r\n");
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(myThread);
            ctx.channel().writeAndFlush("李四\r\n");
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }
}
