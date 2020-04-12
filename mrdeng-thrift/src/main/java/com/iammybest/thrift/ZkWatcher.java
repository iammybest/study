package com.iammybest.thrift;

import com.iammybest.thrift.client.ZkThriftConsumer;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by MrDeng on 2016/10/10.
 */
public class ZkWatcher implements Watcher {
    private static final Logger logger = LoggerFactory.getLogger(ZkWatcher.class);

    private ZkThriftConsumer thriftClient;

    public void setThriftClient(ZkThriftConsumer thriftClient) {
        this.thriftClient = thriftClient;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        logger.info("***Zookeeper state changed****"+watchedEvent.getPath()+"    "+watchedEvent.getType());
        if(watchedEvent!=null&&watchedEvent.getType()== Event.EventType.NodeChildrenChanged){
            logger.info("**************重连服务*****************"+watchedEvent.getPath());
            try {
                thriftClient.init();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
            }
        }
    }
}
