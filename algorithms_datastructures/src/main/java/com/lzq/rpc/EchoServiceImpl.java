package com.lzq.rpc;

public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String ping) {
        return ping + ", I am ok";
    }
}
