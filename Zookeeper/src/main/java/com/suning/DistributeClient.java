package com.suning;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lynn
 * @Date 2020/7/22 19:47
 */
public class DistributeClient {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        DistributeClient distributeClient = new DistributeClient();
        //创建连接
        distributeClient.getConnection();
        //获取节点并监听
        distributeClient.getDataAndWatch();
        //业务逻辑
        distributeClient.business();

    }

    /**
     * 业务逻辑
     * @throws InterruptedException
     */
    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 获取节点数据
     * @throws KeeperException
     * @throws InterruptedException
     */
    private void getDataAndWatch() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/servers", true);
        //存储服务器节点
        ArrayList<String> list = new ArrayList<>();
        for (String child : children) {
            byte[] data = zkClient.getData("/servers/" + child, false, null);
            list.add(new String(data));
        }
        System.out.println(list);
    }

    ZooKeeper zkClient;

    /**
     * 创建连接
     * @throws IOException
     */
    private void getConnection() throws IOException {
        String connect = "bd1301:2181,bd1302:2181,bd1303:2181";
        int timeOut = 2000;
        zkClient = new ZooKeeper(connect, timeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    getDataAndWatch();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
