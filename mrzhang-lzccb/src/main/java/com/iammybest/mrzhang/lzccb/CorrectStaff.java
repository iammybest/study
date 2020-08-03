package com.iammybest.mrzhang.lzccb;

import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;

/**
 * @DESCRIBE:
 * @TIME: 2020/6/13 0:30
 * @AUTHOR: qinghai.deng
 **/
public class CorrectStaff {

    static File correctAccountFile = new File("D:\\lzccb\\id_referer_correct.xlsx");
    static File correctRefererFile = new File("D:\\lzccb\\referer_correct.xlsx");
    static File sourceFile =new File("D:\\lzccb\\20200531.xlsx");
    public static void main(String[] args) {
        List<IdNumberStaff> idReffererList = ExcelUtils.readExcel(IdNumberStaff.class, correctAccountFile);
        Map<String,String> idReffererMap = new HashMap<>();
        System.out.println("账号推荐人修正 总共"+idReffererList.size()+"条数据");
        for (IdNumberStaff idReferer:idReffererList){
            idReffererMap.put(idReferer.getIdNumber(),idReferer.getReferrer());
        }

        List<RefererChange> refererChangeList = ExcelUtils.readExcel(RefererChange.class, correctRefererFile);
        Map<String,String> refererChangeMap = new HashMap<>();
        System.out.println("推荐人名称修正 总共"+refererChangeList.size()+"条数据");
        for (RefererChange accountStaff:refererChangeList){
//            System.out.println(accountStaff.toString());
            refererChangeMap.put(accountStaff.getAccount(),accountStaff.getReferrer());
        }
        List<Deposit> sourceDatas = ExcelUtils.readExcel(Deposit.class, sourceFile);


        Map<String,Double> noIdBalance = new HashMap<>();
        Map<String,Double> noIdDayBalance = new HashMap<>();
        Map<String,String> idName=new HashMap<>();
        List<Deposit> noRefererDeposits= new ArrayList<>();
        long count =0;
        //根据最开始 账号推荐人修正 和 推荐人修正 修正账号推荐人信息
        for (Deposit deposit:sourceDatas){
            //生成 未录推荐人信息的
            idName.put(deposit.getIdNumber(),deposit.getAccountName());
//            if(deposit.getIdNumber().equalsIgnoreCase("510122196803290533")){
//                System.out.println("510122196803290533");
//            }
            //修正推荐人
            boolean ifChange = false;
            if(idReffererMap.containsKey(deposit.getIdNumber())){
                deposit.setReferrer(idReffererMap.get(deposit.getIdNumber()));
                ifChange=true;
            }
            if(refererChangeMap.containsKey(deposit.getReferrer())){
                deposit.setReferrer(refererChangeMap.get(deposit.getReferrer()));
                ifChange=true;
            }
            if(StringUtils.isEmpty(deposit.getReferrer())){
                if(!noIdBalance.containsKey(deposit.getIdNumber())){
                    noIdBalance.put(deposit.getIdNumber(),deposit.getBalance());
                    noIdDayBalance.put(deposit.getIdNumber(),deposit.getPerDayBalance());
                }else{
                    noIdBalance.put(deposit.getIdNumber(),noIdBalance.get(deposit.getIdNumber())+deposit.getBalance());
                    noIdDayBalance.put(deposit.getIdNumber(),noIdDayBalance.get(deposit.getIdNumber())+deposit.getPerDayBalance());
                }
            }
            //20200430以后再为空设置为 营业部
//            if(StringUtils.isEmpty(deposit.getReferrer())){
////                deposit.setReferrer("营业部");
//                noRefererDeposits.add(deposit);
////                ifChange=true;
//            }
//            if(ifChange){
//                count++;
//            }
        }
        //修正推荐人为空情况
        for (Deposit deposit:sourceDatas){
            if(StringUtils.isEmpty(deposit.getReferrer())){
                if(noIdBalance.containsKey(deposit.getIdNumber())){
                    if(noIdBalance.get(deposit.getIdNumber())>50000){
                        deposit.setReferrer("个人业务一部");
                    }else{
                        deposit.setReferrer("营业部");
                    }
                    System.out.println("推荐人为空 账号："+deposit.getIdNumber()+" 存款余额："+noIdBalance.get(deposit.getIdNumber())+" 推荐人修正为:"+deposit.getReferrer());
                }

            }
        }
        System.out.println("推荐人为空账号 共："+noIdBalance.size());
        System.out.println(sourceFile.getName());
        String[] names = sourceFile.getName().split("\\.");
//        ExcelUtils.writeExcel(sourceDatas,Deposit.class,names[0],"D:\\lzccb\\"+names[0]+"_修正.xlsx");
        ExcelUtils.writeExcel(noRefererDeposits,Deposit.class,names[0],"D:\\lzccb\\"+names[0]+"_无推荐人.xlsx");

//        //生成 未录推荐人信息的
//        System.out.println("推荐人名称修正 总共"+sourceDatas.size()+"条数据 修正："+count+"条");
//
//        //拆分未录推荐人数据
        List<Deposit> up5wMap =new ArrayList<>();
        List<Deposit> low5wMap =new ArrayList<>();
//
        List<IdNumberStaff> up5wList =new ArrayList<>();
        List<IdNumberStaff> low5wList =new ArrayList<>();

        Set<String> keys= noIdBalance.keySet();
        for(String key:keys){
            Deposit deposit = new Deposit();
            deposit.setAccountName(idName.get(key));
            deposit.setIdNumber(key);
            deposit.setBalance(noIdBalance.get(key));
            deposit.setPerDayBalance(noIdDayBalance.get(key));
            if(noIdBalance.get(key)>50000){
                up5wMap.add(deposit);
                up5wList.add(new IdNumberStaff(key,"个人业务一部"));
            }else{
                low5wMap.add(deposit);
                low5wList.add(new IdNumberStaff(key,"营业部"));
            }
        }
        idReffererList.addAll(up5wList);
        idReffererList.addAll(low5wList);
        ExcelUtils.writeExcel(up5wMap,Deposit.class,"大于5万","D:\\lzccb\\20200430大于5万.xlsx");
        ExcelUtils.writeExcel(low5wMap,Deposit.class,"小于5万","D:\\lzccb\\20200430小于5万.xlsx");
        ExcelUtils.writeExcel(idReffererList,IdNumberStaff.class,"小于5万","D:\\lzccb\\账号推荐人修正_改.xlsx");
    }
}
