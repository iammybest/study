package com.iammybest.thread;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by MrDeng on 2016/12/22.
 */
public class Test {

    public static void main(String[] args) {
        Path source = Paths.get("G:\\PRD需求文档.docx");
        Path target = Paths.get("H:\\体育家后台0718");

        try {
            Path rst = Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(rst);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
