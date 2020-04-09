package com.lzq.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RpcExporter {
    private static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    public static void exporter(String hostName,int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(hostName,port));
        try{
            while (true){
                executor.execute(new ExporterTask(serverSocket.accept()));
            }
        }finally {
            serverSocket.close();
        }
    }
    private static class ExporterTask implements Runnable{
        Socket client = null;
        public ExporterTask(Socket client){
            this.client = client;
        }
        @Override
        public void run() {
            ObjectInputStream objectInputStream = null;
            ObjectOutputStream objectOutputStream = null;
            try{
                objectInputStream = new ObjectInputStream(client.getInputStream());
                String interfaceName = objectInputStream.readUTF();//接口名
                Class<?> service = Class.forName(interfaceName);
                String methodName = objectInputStream.readUTF();
                Class<?>[] parameterTypes = (Class<?>[]) objectInputStream.readObject();
                Object[] arguments = (Object[]) objectInputStream.readObject();
                Method method = service.getMethod(methodName,parameterTypes);
                Object result = method.invoke(service.newInstance(),arguments);
                objectOutputStream = new ObjectOutputStream(client.getOutputStream());
                objectOutputStream.writeObject(result);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                    try {
                        if (objectOutputStream != null) {
                            objectOutputStream.close();
                        }
                        if (objectInputStream != null){
                            objectInputStream.close();
                        }
                        if (client != null){
                            client.close();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}
