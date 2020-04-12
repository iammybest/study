package com.iammybest.thrift;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

//import org.I0Itec.zkclient.ZkClient;


/**
 * Created by MrDeng on 2016/10/11.
 */
public class ZkClientTest {
    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper("192.168.1.200:2181", 5000, null);

//            if (zooKeeper.exists("/iammybest", true) == null) {
//                String path = zooKeeper.create("/iammybest", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//                System.out.println(path);
//            }

            List<String> childs = zooKeeper.getChildren("/iammybest/thrift/v1", true);
            System.out.println(childs);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
