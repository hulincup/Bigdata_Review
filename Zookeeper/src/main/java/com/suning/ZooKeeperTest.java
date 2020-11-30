package com.suning;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.Externalizable;
import java.io.IOException;
import java.util.List;
import java.util.PrimitiveIterator;

/**
 * @Author lynn
 * @Date 2020/7/22 17:22
 */
public class ZooKeeperTest {
    private String connect = "bd1301:2181,bd1302:2181,bd1303:2181";
    private int timeOut = 2000;
    ZooKeeper zkClient;

    /**
     * 初始化工作 创建client
     * @throws IOException
     */
    @Before
    public void init() throws IOException {

        zkClient = new ZooKeeper(connect, timeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("=============start================");
                List<String> children = null;
                try {
                    children = zkClient.getChildren("/city", true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (String child:children){
                    System.out.println(child);
                }
                System.out.println("=============end================");
            }
        });
    }

    /**
     * 创建节点
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void createNode() throws KeeperException, InterruptedException {
        String zNode = zkClient.create("/city", "shanghai".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(zNode);
    }

    /**
     * 获取子节点并且监听
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void getDataAndWatch() throws KeeperException, InterruptedException {
        /*List<String> children = zkClient.getChildren("/city", false);
        for (String child:children){
            System.out.println(child);
        }*/

        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 节点是否存在
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void existsZnode() throws KeeperException, InterruptedException {
        Stat exists = zkClient.exists("/city", false);
        System.out.println(exists);
    }




}
