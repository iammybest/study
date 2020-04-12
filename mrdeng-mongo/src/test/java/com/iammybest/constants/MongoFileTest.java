package com.iammybest.constants;

import com.iammybest.mongo.MongoFactory;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by MrDeng on 2017/3/30.
 */
public class MongoFileTest {
    @Test
    public void test() throws IOException {
        long start = new Date().getTime();
        MongoClient mongoClient = new MongoClient("59.110.45.40:28100");
//        mongoClient.getDB("gridfs");
        GridFS gridFS= new GridFS(mongoClient.getDB("mrdeng"));
        File f = new File("H:\\资料\\Lua程序设计第二版.pdf");
        GridFSInputFile inputFile = gridFS.createFile(f);
        inputFile.save();
        DBCursor cursor = gridFS.getFileList();
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }
        mongoClient.close();
        long endTime = new Date().getTime();
        System.out.println(endTime - start);
        System.out.println((endTime - start) / 10000000);
    }

}
