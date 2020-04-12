package com.iammybest.thrift.server;

import org.I0Itec.zkclient.ZkClient;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Constructor;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * Created by MrDeng on 2016/10/8.
 */
public class ZkThriftProvider {
    private static final Logger logger = LoggerFactory.getLogger(ZkThriftProvider.class);
    private String zkRootPath;
    private ZkClient zkClient;
    private String serverName;
    private String vesion;
    private Map<String, Object> serviceList;
    private int port;
    private TServerSocket serverTransport;

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setZkRootPath(String zkRootPath) {
        this.zkRootPath = zkRootPath;
    }

    public void setVesion(String vesion) {
        this.vesion = vesion;
    }

    public void setServiceList(Map<String, Object> serviceList) {
        this.serviceList = serviceList;
    }

    public void setZkClient(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private void registerZk() {

        ///注册到Zookeeper
//        ZkClient zkClient = new ZkClient("192.168.1.200:2181");
//        boolean rootExists = zkClient.exists(this.zkRootPath);
        StringBuffer stb = new StringBuffer();
//        if (!this.zkRootPath.startsWith("/"))
        stb.append("/").append(this.zkRootPath);
        if (!zkClient.exists(stb.toString())) {
            System.out.println("*********************不存在节点 "+stb.toString());
            zkClient.create(stb.toString(), 100000, CreateMode.PERSISTENT);
        }
        stb.append("/").append(this.serverName);
        if (!zkClient.exists(stb.toString())) {
            System.out.println("*********************不存在节点 "+stb.toString());
            zkClient.create(stb.toString(), 100000, CreateMode.PERSISTENT);
        }
        stb.append("/").append(this.vesion);
        if (!zkClient.exists(stb.toString())) {
            System.out.println("*********************不存在节点 "+stb.toString());
            zkClient.create(stb.toString(), 100000, CreateMode.PERSISTENT);
        }
        if (zkClient.exists(stb.toString())) {
            InetAddress addr = null;
            try {
                addr = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            String ip = addr.getHostAddress().toString();
            stb.append("/").append(ip);
            // 注册当前服务 如果当前服务已注册则删除重新注册
            if (zkClient.exists(stb.toString())){
                zkClient.delete(stb.toString());
            }
            logger.info(stb.toString());
            zkClient.createEphemeral(stb.toString() );
        }
    }

    public void init() {
        try {
            serverTransport = new TServerSocket(port);
            TMultiplexedProcessor mprocessor = new TMultiplexedProcessor();
            for (Map.Entry<String, Object> entry : serviceList.entrySet()) {
                Object obj = entry.getValue();
                Class<?> serviceClass = obj.getClass();
                // 获取实现类接口
                Class<?>[] interfaces = serviceClass.getInterfaces();
                TProcessor processor = null;
                String serviceName = null;
                for (Class<?> clazz : interfaces) {
                    logger.info("ThriftServer=========" + clazz.getSimpleName());
                    String className = clazz.getEnclosingClass().getSimpleName();
                    serviceName = clazz.getEnclosingClass().getName();
                   logger.info("serviceName=========" + serviceName + " " + className);
                    String pname = serviceName + "$Processor";
                   logger.info(pname);
                    try {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Class<?> pclass = classLoader.loadClass(pname);
                        if (!TProcessor.class.isAssignableFrom(pclass)) {
                            continue;
                        }
                        Constructor<?> constructor = pclass.getConstructor(clazz);
                        processor = (TProcessor) constructor.newInstance(obj);
                       logger.info("processor=========" + processor.getClass().getSimpleName());
                        mprocessor.registerProcessor(entry.getKey(), processor);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (processor == null) {
                    throw new IllegalClassFormatException("service-class should implements Iface");
                }
            }
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(
                    serverTransport).processor(mprocessor));
            registerZk();
            server.serve();
           logger.info("Starting server on port 9900 ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void close() {
        serverTransport.close();
    }
}
