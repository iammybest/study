package com.iammybest.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by MrDeng on 2016/12/27.
 */
public class NioFuture {
    public static void main(String[] args) {

        Path file = Paths.get("G:\\PRD需求文档.docx");

        try {
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);

            ByteBuffer buffer = ByteBuffer.allocate(100_000);

            Future<Integer> result =  channel.read(buffer,0);
            while (!result.isDone()){
                System.out.println("等待IO 执行完成"+result.isDone());
            }
            System.out.println("共读取字节数量："+result.get());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
