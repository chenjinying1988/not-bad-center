package com.jychan.notbad.zookeeper;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by chenjinying on 2017/6/21.
 * mail: 415683089@qq.com
 */
public abstract class AbstractZooKeeper implements Watcher {

    private static final int SESSION_TIME = 2000;
    protected ZooKeeper zooKeeper;
    protected CountDownLatch countDownLatch = new CountDownLatch(1);

    public void connect(String hosts) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(hosts, SESSION_TIME, this);
        countDownLatch.await();
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }
}
