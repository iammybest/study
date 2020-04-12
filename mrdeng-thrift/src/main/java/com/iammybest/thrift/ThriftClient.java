package com.iammybest.thrift;

import com.iammybest.thrift.client.ZkThriftConsumer;
import org.apache.thrift.TException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by MrDeng on 2016/10/8.
 */
public class ThriftClient {
    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("thrift-client.xml");
            ZkThriftConsumer springClient = (ZkThriftConsumer) context.getBean("zkThriftClient");
            //调用登录服务
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    String res = null;
                    while (true) {
                        try {
                            if(springClient.isOpen()){
                                Hello.Client helloService = (Hello.Client) springClient.getClient("HelloService");
                                if (springClient.isOpen()&&helloService != null)
                                    res = helloService.hello("mr deng");
                                System.out.println(springClient.isOpen()+"   "+res);
                            }else{
                                System.out.println("****************连接已断开*******************"+springClient.isOpen());
                            }
                        } catch (TException e) {
                            e.printStackTrace();
                        }finally {
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
            t.start();
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
