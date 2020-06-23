package com.iammybest.mrzhang.lzccb;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @DESCRIBE:
 * @TIME: 2020/6/13 0:30
 * @AUTHOR: qinghai.deng
 **/
public class StatisticsBalance {

    static File correctAccountFile = new File("D:\\lzccb\\账号推荐人修正_改.xlsx");
    static File correctRefererFile = new File("D:\\lzccb\\修正推荐人.xlsx");
    static File sourceFile =new File("D:\\lzccb\\20200618.xlsx");
    static File refererFile =new File("D:\\lzccb\\花名册20200612.xlsx");

    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
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
            refererChangeMap.put(accountStaff.getAccount(),accountStaff.getReferrer());
        }
        List<Deposit> sourceDatas = ExcelUtils.readExcel(Deposit.class, sourceFile);
        //根据最开始 账号推荐人修正 和 推荐人修正 修正账号推荐人信息
        for (Deposit deposit:sourceDatas){
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
            //20200430以后再为空设置为 营业部
            if(StringUtils.isEmpty(deposit.getReferrer())){
                deposit.setReferrer("营业部");
                ifChange=true;
            }
        }
        //保存修正后原始数据
        String[] names = sourceFile.getName().split("\\.");
        ExcelUtils.writeExcel(sourceDatas,Deposit.class,names[0],"D:\\lzccb\\"+names[0]+"_数据修正.xlsx");


        //加载花名册
        List<Referer> refererList = ExcelUtils.readExcel(Referer.class, refererFile);
        Map<String, Referer> refererBalanceMap = new HashMap<>();
        for(Referer referer :refererList){
            if(isNumeric(referer.getReferrer())){
                //修正花名册中 数字 的推荐人工号
                if(referer.getReferrer().length()==2){
                    referer.setReferrer("00"+ referer.getReferrer());
                }else if(referer.getReferrer().length()==3){
                    referer.setReferrer("0"+ referer.getReferrer());
                }
            }
            refererBalanceMap.put(referer.getReferrer(), referer);
        }
        //修正推荐人为空情况
        List<Deposit> noRefererDeposits = new ArrayList<>();
        double cntBalance = 0D;
        double cntPerDayBalance = 0D;
        for (Deposit deposit:sourceDatas){
            if(refererBalanceMap.containsKey(deposit.getReferrer())){
                refererBalanceMap.get(deposit.getReferrer()).setBalance(refererBalanceMap.get(deposit.getReferrer()).getBalance()+deposit.getBalance());
                refererBalanceMap.get(deposit.getReferrer()).setPerDayBalance(refererBalanceMap.get(deposit.getReferrer()).getBalance()+deposit.getPerDayBalance());
            }else{
                cntBalance+=deposit.getBalance();
                cntPerDayBalance+=deposit.getPerDayBalance();
                System.out.println(deposit.display()+" \t "+cntBalance);
                noRefererDeposits.add(deposit);
            }
        }
        List<RefererBalanceVo> vos = new ArrayList<>();
        for(Referer referer :refererList){
            RefererBalanceVo vo =new RefererBalanceVo();
            BeanUtils.copyProperties(referer,vo);
            vo.setBalance(decimalFormat.format(referer.getBalance()));
            vo.setPerDayBalance(decimalFormat.format(referer.getPerDayBalance()));
            vos.add(vo);
        }
        System.out.println("总共"+noRefererDeposits.size()+"无推荐人 总存款："+decimalFormat.format(cntBalance)+"  总日均："+decimalFormat.format(cntPerDayBalance));
        ExcelUtils.writeExcel(vos,RefererBalanceVo.class,names[0]+"统计结果","D:\\lzccb\\"+names[0]+"_统计结果.xlsx");
        // TODO: 2020/6/20 存在一些未对应上的 在推荐人修正里面 将推荐人对应上 再次执行 数据修正 再统计
        ExcelUtils.writeExcel(noRefererDeposits,Deposit.class,names[0]+"花名册中不存在","D:\\lzccb\\"+names[0]+"_花名册中不存在.xlsx");
    }
}
