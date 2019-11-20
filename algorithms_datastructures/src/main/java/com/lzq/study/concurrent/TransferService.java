package com.lzq.study.concurrent;

import java.util.concurrent.CompletableFuture;

/**
 * Created by liuzhengqiu on 2019/11/20.
 */
public interface TransferService {
    CompletableFuture<Void> transfer(int fromAccount, int toAccount, int amount);
}
