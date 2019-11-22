package com.kpmg.lzq.netty.chat.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.kpmg.lzq.netty.chat.serializer.Serializer;
import com.kpmg.lzq.netty.chat.serializer.SerializerAlgorithm;

public class JSONSerializer implements Serializer {
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
