package com.kpmg.lzq.netty.chat.protocal.packet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static com.kpmg.lzq.netty.chat.protocal.commond.Commond.MSG;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MsgPacket extends Packet {
    /**
     * 序号
     */
    private Integer no;

    /**
     * 会话ID
     */
    private Integer session;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 内容
     */
    private String content;

    @Override
    public Byte getCommand() {
        return MSG;
    }
}
