package com.jychan.notbad.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenjinying on 2017/6/22.
 * mail: 415683089@qq.com
 */
public class StartUp {

    private static void zookeeper() throws IOException, InterruptedException, KeeperException {
        String zkHost = "127.0.0.1";
        String zkRoot = "/myzk";
        String zkChild = zkRoot + "monitor";
        ZookeeperDao zkDao = new ZookeeperDao(zkHost);
        zkDao.existRoot(zkRoot);
        zkDao.watchPath(zkChild);
        while (true) {
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public static void main(String[] args) {
        try {
            zookeeper();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
