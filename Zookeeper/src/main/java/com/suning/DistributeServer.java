package com.suning;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @Author lynn
 * @Date 2020/7/22 19:37
 */
public class DistributeServer {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        DistributeServer distributeServer = new DistributeServer();
        //创建连接
        distributeServer.getConnection();

        //创建节点
        distributeServer.createZnode(args[0]);
        //业务逻辑
        distributeServer.business();
    }

    /**
     * 业务逻辑
     * @throws InterruptedException
     */
    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 创建节点
     * @param hostName
     * @throws KeeperException
     * @throws InterruptedException
     */
    private void createZnode(String hostName) throws KeeperException, InterruptedException {
        zkClient.create("/servers/server", hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

    }

    ZooKeeper zkClient;

    /**
     * 创建连接
     * @throws IOException
     */
    private  void getConnection() throws IOException {
        String connect = "bd1301:2181,bd1302:2181,bd1303:2181";
        int timeOut = 2000;
        zkClient = new ZooKeeper(connect, timeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }

}
