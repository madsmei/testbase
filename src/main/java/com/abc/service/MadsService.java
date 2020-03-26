package com.abc.service;

import java.util.concurrent.ExecutionException;

public interface MadsService {

    void hello();

    void byby() throws ExecutionException, InterruptedException;
}
