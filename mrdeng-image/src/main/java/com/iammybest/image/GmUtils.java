package com.iammybest.image;

import org.im4java.core.*;
import org.im4java.process.ArrayListOutputConsumer;
import org.im4java.process.StandardStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by MrDeng on 2016/12/29.
 * GraphicsMagick 调用
 */
public class GmUtils {

    public static void main(String[] args) {
//        System.out.println("Image Size : "+GmUtils.getSize("E:\\1.jpg")+" kb");
        System.out.println("Image Width : " + GmUtils.getImageWidth("E:\\1.jpg") + " px");

//        GmUtils.draw("E:\\1.jpg","E:\\2.jpg",200,120);
    }


    public static long getSize(String imgPath) {
        try {
            return Files.size(Paths.get(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getImageWidth(String imgPath) {
        int line = 0;
        try {
            IMOperation op = new IMOperation();
            op.format("%w"); // 设置获取宽度参数
            op.addImage(1);
            IdentifyCmd identifyCmd = new IdentifyCmd(true);
            ArrayListOutputConsumer output = new ArrayListOutputConsumer();
            identifyCmd.setOutputConsumer(output);
            identifyCmd.run(op, imgPath);
            ArrayList<String> cmdOutput = output.getOutput();
            assert cmdOutput.size() == 1;
            line = Integer.parseInt(cmdOutput.get(0));
        } catch (Exception e) {
            line = 0;
            e.printStackTrace();
            System.out.println("运行指令出错!");
        }
        return line;
    }


    public static void draw(String sourcePath, String targetPath, int dWidth, int dHeight) {
        IMOperation op = new IMOperation();
        op.addImage();
        op.resize(dWidth, dHeight);
        op.font("Arial").fill("red").draw("text 100,100 www.taobao.com");
        op.quality(85d);
        op.addImage();
        //IM4JAVA是同时支持ImageMagick和GraphicsMagick的，如果为true则使用GM，如果为false支持IM。
        ConvertCmd cmd = new ConvertCmd(true);
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.indexOf("win") >= 0) {  //linux下不要设置此值，不然会报错
            cmd.setSearchPath("C:\\Program Files\\GraphicsMagick-1.3.25-Q8");
        }
        cmd.setErrorConsumer(StandardStream.STDERR);
        try {
            cmd.run(op, sourcePath, targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IM4JavaException e) {
            e.printStackTrace();
        }
    }
}
