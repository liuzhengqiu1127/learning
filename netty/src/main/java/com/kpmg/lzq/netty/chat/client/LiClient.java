package com.kpmg.lzq.netty.chat.client;

import com.kpmg.lzq.netty.chat.common.MsgRepository;
import com.kpmg.lzq.netty.chat.protocal.codec.PacketCodeC;
import com.kpmg.lzq.netty.chat.protocal.packet.MsgPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.kpmg.lzq.netty.chat.common.MsgConstant.MSG_SESSION_TWO;

/**
 * @author switch
 * @since 2019/10/12
 */
public class LiClient {
    public static final int MAX_RETRY = 5;
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 18000;


    public static void main(String[] args) {
        Bootstrap bootstrap = LiClient.bootstrap();
        ChannelFuture channelFuture = connect(bootstrap, HOST, PORT, MAX_RETRY);
        MsgPacket msgPacket = MsgRepository.getInstance().getLiMsgPacket(MSG_SESSION_TWO);
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(msgPacket);
        channelFuture.channel().writeAndFlush(byteBuf);
    }

    public static Bootstrap bootstrap() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });

        return bootstrap;
    }

    public static ChannelFuture connect(Bootstrap bootstrap, String host, int port, int retry) {
        return bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 连接成功！");
            } else if (retry == 0) {
                System.err.println(" 重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第 " + order + " 次重连……");
                bootstrap.group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }
}
