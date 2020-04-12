package com.iammybest.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by MrDeng on 2016/12/27.
 */
public class NioCallback {
    public static void main(String[] args) {

        Path file = Paths.get("G:\\PRD需求文档.docx");

        try {
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);

            ByteBuffer buffer = ByteBuffer.allocate(100_000);

            channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("IO 读写完成");
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    System.out.println("IO 读写失败");
                }
            });
//
//            Future<Integer> result =  channel.read(buffer,0);
//            while (!result.isDone()){
//                System.out.println("等待IO 执行完成"+result.isDone());
//            }
            System.out.println("主线程完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
