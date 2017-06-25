package com.jychan.notbad.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * Created by chenjinying on 2017/6/22.
 * mail: 415683089@qq.com
 */
public class ZookeeperDao {

    private ZooKeeperOperator zkOperator;
    public ZookeeperDao(String host) throws IOException, InterruptedException {
        zkOperator = new ZooKeeperOperator();
        connect(host);
    }

    public void connect(String host) throws IOException, InterruptedException {
        zkOperator.connect(host);
    }

    public void existRoot(String path) throws KeeperException, InterruptedException {
        if (zkOperator.exists(path, false) == null) {
            zkOperator.create(path, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    public void watchPath(String path) throws KeeperException, InterruptedException {
        if (zkOperator.exists(path, watchedEvent -> {
            try {
                watchPath(path);
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
            doAction(watchedEvent.getPath());
        }) == null) {
            zkOperator.create(path, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    public void doAction(String path) {
        try {
            byte[] bytes = zkOperator.getData(path);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() throws InterruptedException {
        zkOperator.close();
    }
}
