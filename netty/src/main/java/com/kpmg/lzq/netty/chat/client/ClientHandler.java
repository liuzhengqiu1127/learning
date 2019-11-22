package com.kpmg.lzq.netty.chat.client;

import com.kpmg.lzq.netty.chat.common.MsgCounter;
import com.kpmg.lzq.netty.chat.common.MsgRepository;
import com.kpmg.lzq.netty.chat.protocal.codec.PacketCodeC;
import com.kpmg.lzq.netty.chat.protocal.packet.MsgPacket;
import com.kpmg.lzq.netty.chat.protocal.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import static com.kpmg.lzq.netty.chat.common.MsgConstant.*;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf requestByteBuf = (ByteBuf) msg;

        // 解码
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        // 判断是否是消息请求数据包
        if (!(packet instanceof MsgPacket)) {
            return;
        }
        MsgPacket msgPacket = (MsgPacket) packet;
        Integer session = msgPacket.getSession();
        switch (session) {
            case MSG_SESSION_ONE: {
                MsgCounter.count();
                printMsg(msgPacket);
                sendMsg(ctx, MSG_SESSION_ONE);
                break;
            }
            case MSG_SESSION_TWO:
            case MSG_SESSION_THREE: {
                MsgCounter.count();
                printMsg(msgPacket);
                break;
            }
            default: {
                break;
            }
        }
    }


    private void sendMsg(ChannelHandlerContext ctx, Integer sessionId) {
        MsgPacket liMsgPacket = MsgRepository.getInstance().getLiMsgPacket(sessionId);
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(liMsgPacket);
        ctx.writeAndFlush(byteBuf);
    }

    private void printMsg(MsgPacket packet) {
        System.out.println("张大爷说：【" + packet.getSession() + ":" + packet.getContent() + "】");
    }

}
