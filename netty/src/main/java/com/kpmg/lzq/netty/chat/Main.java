package com.kpmg.lzq.netty.chat;

import com.kpmg.lzq.netty.chat.client.LiClient;
import com.kpmg.lzq.netty.chat.common.MsgRepository;
import com.kpmg.lzq.netty.chat.protocal.codec.PacketCodeC;
import com.kpmg.lzq.netty.chat.protocal.packet.MsgPacket;
import com.kpmg.lzq.netty.chat.server.ZhangServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.concurrent.TimeUnit;

import static com.kpmg.lzq.netty.chat.client.LiClient.*;
import static com.kpmg.lzq.netty.chat.common.MsgConstant.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = ZhangServer.bootstrap();
        ChannelFuture serverChannelFuture = ZhangServer.bind(serverBootstrap, PORT);
        Bootstrap clientBootstrap = LiClient.bootstrap();
        ChannelFuture clientChannelFuture = LiClient.connect(clientBootstrap, HOST, PORT, MAX_RETRY);
        serverChannelFuture.await();
        clientChannelFuture.await();
        TimeUnit.SECONDS.sleep(1);
        for (int i = 0; i < COUNT_LEVEL_3; i++) {
            MsgPacket one = MsgRepository.getInstance().getZhangMsgPacket(MSG_SESSION_ONE);
            MsgPacket two = MsgRepository.getInstance().getLiMsgPacket(MSG_SESSION_TWO);
            MsgPacket three = MsgRepository.getInstance().getLiMsgPacket(MSG_SESSION_THREE);
            sendMsg(ZhangServer.getChannel(HOST), one);
            sendMsg(clientChannelFuture.channel(), two);
            sendMsg(clientChannelFuture.channel(), three);
        }
    }

    private static void sendMsg(Channel channel, MsgPacket packet) {
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(packet);
        channel.writeAndFlush(byteBuf);
    }
}
