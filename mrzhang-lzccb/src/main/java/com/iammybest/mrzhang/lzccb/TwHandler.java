package com.iammybest.mrzhang.lzccb;

import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @DESCRIBE:
 * @TIME: 2020/9/14 9:17
 * @AUTHOR: qinghai.deng
 **/
public class TwHandler {
    public static void main(String[] args) {
        Set<String>names = new HashSet<>();
        names.add("A");names.add("B");names.add("C");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:\\info.txt"))));
            Map<String, Map<String,String>> students= new HashMap<>();
            String line = null;
            Map<String,String> curInfo= new HashMap<>();
            while ((line = reader.readLine())!=null){
                System.out.println(line);
                line = line.trim();
                if(StringUtils.isEmpty(line))continue;
                String[] datas = line.split(":");
                if(datas==null){
                    datas  = line.split("：");
                }
                if(datas==null||datas.length!=2)continue;
                if(datas[0].contains("姓名")){
                    curInfo = students.get(datas[0]);
                    curInfo = curInfo==null?new HashMap<>():curInfo;
                    curInfo.put("姓名",datas[1]);
                    students.put(datas[1],curInfo);
                }else{
                    curInfo.put(datas[0],datas[1]);
                }
            }
            System.out.println(students.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
