package com.lzq.study.concurrent;

import java.util.concurrent.CompletableFuture;

/**
 * Created by liuzhengqiu on 2019/11/20.
 */
public interface AccountService {
    CompletableFuture<Void> add(int account, int amount);
}
