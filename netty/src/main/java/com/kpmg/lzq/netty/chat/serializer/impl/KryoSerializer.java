package com.kpmg.lzq.netty.chat.serializer.impl;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.kpmg.lzq.netty.chat.serializer.Serializer;
import com.kpmg.lzq.netty.chat.serializer.SerializerAlgorithm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class KryoSerializer implements Serializer {
    private static final ThreadLocal<Kryo> kryoLocal = ThreadLocal.withInitial(()->{
        Kryo kryo = new Kryo();
        kryo.setReferences(true);
        kryo.setRegistrationRequired(false);
        return kryo;
    });

    public static Kryo getInstance(){
        return kryoLocal.get();
    }

    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.KRYO;
    }

    public byte[] serialize(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);

        Kryo kryo = getInstance();
        kryo.writeClassAndObject(output, object);
        output.flush();

        return byteArrayOutputStream.toByteArray();
    }

    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Input input = new Input(byteArrayInputStream);
        Kryo kryo = getInstance();
        return (T) kryo.readClassAndObject(input);
    }
}
