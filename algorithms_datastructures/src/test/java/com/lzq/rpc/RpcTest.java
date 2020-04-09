package com.lzq.rpc;

import org.junit.Test;

import java.net.InetSocketAddress;

public class RpcTest {
    @Test
    public void test001(){
        new Thread(() -> {
            try {
                RpcExporter.exporter("localhost",8088);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        RpcImporter<EchoService> importer = new RpcImporter<>();
        EchoService echoService = importer.importer(EchoServiceImpl.class,new InetSocketAddress("localhost",8088));
        System.out.println(echoService.echo("Are you ok ?"));
    }
}
