package com.jychan.notbad.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Created by chenjinying on 2017/6/21.
 * mail: 415683089@qq.com
 */
public class ZooKeeperOperator extends AbstractZooKeeper {

    public void create(String path, byte[] data) throws KeeperException, InterruptedException {
        this.zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public void getChild(String path) throws KeeperException, InterruptedException {
        List<String> children = this.zooKeeper.getChildren(path, false);
        if (children.isEmpty()) {
            return;
        } else {
            for (String child: children) {
                System.out.println(child);
            }
        }
    }

    public byte[] getData(String path) throws KeeperException, InterruptedException {
        return this.zooKeeper.getData(path, false, null);
    }

    public Stat exists(String path, boolean watch) throws KeeperException, InterruptedException {
        return this.zooKeeper.exists(path, watch);
    }

    public Stat exists(String path, Watcher watcher) throws KeeperException, InterruptedException {
        return this.zooKeeper.exists(path, watcher);
    }

    public String create(String path, byte[] data, List<ACL> acl, CreateMode createMode)
            throws KeeperException, InterruptedException {
        return this.zooKeeper.create(path, data, acl, createMode);
    }

    public Stat set(String path, byte[] data) throws KeeperException, InterruptedException {
        return this.zooKeeper.setData(path, data, -1);
    }

    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            countDownLatch.countDown();//断开连接，不再监听
        }
    }
}
