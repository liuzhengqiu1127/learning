package com.lzq.study.concurrent;

import java.util.concurrent.CompletableFuture;

/**
 * Created by liuzhengqiu on 2019/11/20.
 */
public class TransferServiceImpl implements TransferService {

    private AccountService accountService;
    @Override
    public CompletableFuture<Void> transfer(int fromAccount, int toAccount, int amount) {
        return accountService.add(fromAccount,-1*amount)
                .thenCompose(v -> accountService.add(toAccount,amount));
    }
}
