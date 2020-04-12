package com.iammybest.thrift.client;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by MrDeng on 2016/10/8.
 */
public class ZkThriftConsumer {
    private static final Logger logger = LoggerFactory.getLogger(ZkThriftConsumer.class);
    private String zkRootPath;
    private ZooKeeper zooKeeper;
    private String serverName;
    private String vesion;
    private int port;
    private Map<String, String> serviceMap;
    private Map<String, Object> clientMap;
    private TTransport transport;

    public void setZkRootPath(String zkRootPath) {
        this.zkRootPath = zkRootPath;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setVesion(String vesion) {
        this.vesion = vesion;
    }

    public void setClientMap(Map<String, Object> clientMap) {
        this.clientMap = clientMap;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setServiceMap(Map<String, String> serviceMap) {
        this.serviceMap = serviceMap;
    }

    public Object getClient(String name) {
        if (this.clientMap==null||this.clientMap.isEmpty())
            return null;
        return clientMap.get(name);
    }

    public void init() throws KeeperException, InterruptedException {
        StringBuffer stb = new StringBuffer();
        stb.append("/").append(this.zkRootPath).append("/").append(this.serverName).append("/").append(this.vesion);
        List<String> childs = zooKeeper.getChildren(stb.toString(),true);
        logger.info(childs.toString());
        if (childs == null || childs.isEmpty()){
            logger.info("********************无可用服务***************************");
            return;
        }
        clientMap = new HashMap<String, Object>();
        try {
            String ip = childs.get(new Random(System.currentTimeMillis()).nextInt() % childs.size());
            logger.info("监听使用 " + ip + " 服务");
            transport = new TSocket(ip, port);
            TProtocol protocol = new TBinaryProtocol(transport);
            for (Map.Entry<String, String> entry : serviceMap.entrySet()) {
                String obj = entry.getValue();
                logger.info(entry.getKey() + " " + entry.getValue());
                TMultiplexedProtocol mp = new TMultiplexedProtocol(protocol,
                        entry.getKey());
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                Class<?> objectClass = classLoader.loadClass(obj + "$Client");
                Constructor<?> stor = objectClass.getDeclaredConstructor(TProtocol.class);
                Object client = stor.newInstance(mp);
                clientMap.put(entry.getKey(), client);
            }
            if (!transport.isOpen())
                transport.open();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
    public boolean isOpen(){
        return transport==null?false:transport.isOpen();
    }
    public void close() {
        transport.close();
    }
}
