package com.iammybest.mrzhang.lzccb;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @DESCRIBE:
 * @TIME: 2020/6/21 3:07
 * @AUTHOR: qinghai.deng
 **/
@Service
@Log4j2
public class StatisticsHandler {

    @Value("${correct.IdNumberRefererFile}")
    String correctAccountFileName;
    @Value("${correct.ReferFile}")
    String correctRefererFileName;
    @Value("${rosterFile}")
    String refererFileName;
    @Value("${outPath}")
    String outPath;

    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
    public String statistics(String sourceFileName){
        File sourceFile =new File(sourceFileName);
        File correctAccountFile = new File(correctAccountFileName);
        File correctRefererFile = new File(correctRefererFileName);
        File refererFile =new File(refererFileName);
        //保存修正后原始数据
        String[] sourceFileNames = sourceFile.getName().split("\\.");
        List<IdNumberStaff> idReffererList = ExcelUtils.readExcel(IdNumberStaff.class, correctAccountFile);
        Map<String,String> idReffererMap = new HashMap<>();
        log.info("账号推荐人修正 参照表 总共{}条数据",idReffererList.size());
        for (IdNumberStaff idReferer:idReffererList){
            idReffererMap.put(idReferer.getIdNumber(),idReferer.getReferrer());
        }
        List<RefererChange> refererChangeList = ExcelUtils.readExcel(RefererChange.class, correctRefererFile);
        Map<String,String> refererChangeMap = new HashMap<>();
        log.info("推荐人名称修正 参照表 总共{}条数据",refererChangeList.size());
        for (RefererChange accountStaff:refererChangeList){
            refererChangeMap.put(accountStaff.getAccount(),accountStaff.getReferrer());
        }
        List<Deposit> sourceDatas = ExcelUtils.readExcel(Deposit.class, sourceFile);
        List<Deposit> yybDeposit = new ArrayList<>();
        //根据最开始 账号推荐人修正 和 推荐人修正 修正账号推荐人信息
        log.info("开始修正{}原始数据............",sourceFileName);
        for (Deposit deposit:sourceDatas){
            if(!StringUtils.isEmpty(deposit.getReferrer())&&deposit.getReferrer().equalsIgnoreCase("营业部")){
                deposit.setRole("原始为营业部");
            }
            //修正推荐人
            if(idReffererMap.containsKey(deposit.getIdNumber())){
                deposit.setReferrer(idReffererMap.get(deposit.getIdNumber()));
                if(deposit.getReferrer().equalsIgnoreCase("营业部")){
                    deposit.setRole("账号推荐人修正表修正");
                }
            }
            if(refererChangeMap.containsKey(deposit.getReferrer())){
                deposit.setReferrer(refererChangeMap.get(deposit.getReferrer()));
            }
            //20200430以后再为空设置为 营业部
            if(StringUtils.isEmpty(deposit.getReferrer())){
                deposit.setReferrer("营业部");
                deposit.setRole("账号推荐人为空修正");
            }
        }
        log.info("结束修正原始数据............");
        File correctSourceFile = ExcelUtils.writeExcel(sourceDatas,Deposit.class,sourceFileNames[0],outPath+sourceFileNames[0]+"_数据修正.xlsx");
        log.info("原始数据修正结果输出到{}文件中",correctSourceFile.getPath());
        //加载花名册

        List<Referer> refererList = ExcelUtils.readExcel(Referer.class, refererFile);
        log.info("加载花名册 {}条记录",refererList.size());
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
        List<Deposit> noRefererDeposits = new ArrayList<>();
        double cntBalance = 0D;
        double cntPerDayBalance = 0D;
        for (Deposit deposit:sourceDatas){
            if(refererBalanceMap.containsKey(deposit.getReferrer())){
                refererBalanceMap.get(deposit.getReferrer()).setBalance(refererBalanceMap.get(deposit.getReferrer()).getBalance()+deposit.getBalance());
                refererBalanceMap.get(deposit.getReferrer()).setPerDayBalance(refererBalanceMap.get(deposit.getReferrer()).getBalance()+deposit.getPerDayBalance());
                if(refererBalanceMap.get(deposit.getReferrer()).getDepartment().equalsIgnoreCase("营业部")){
                    if(StringUtils.isEmpty(deposit.getRole())){
                        if(!refererBalanceMap.get(deposit.getReferrer()).getName().equalsIgnoreCase("营业部")){
                            deposit.setRole("营业部员工");
                        }else{
                            deposit.setRole("原始数据为营业部");
                        }
                    }
                    yybDeposit.add(deposit);
                }
            }else{
                cntBalance+=deposit.getBalance();
                cntPerDayBalance+=deposit.getPerDayBalance();
                noRefererDeposits.add(deposit);
            }

        }
        ExcelUtils.writeExcel(yybDeposit, Deposit.class, "营业部客户",outPath+"营业部客户.xlsx");

        List<RefererBalanceVo> vos = new ArrayList<>();
        for(Referer referer :refererList){
            RefererBalanceVo vo =new RefererBalanceVo();
            BeanUtils.copyProperties(referer,vo);
            vo.setBalance(decimalFormat.format(referer.getBalance()));
            vo.setPerDayBalance(decimalFormat.format(referer.getPerDayBalance()));
            vos.add(vo);
        }
        log.info("总共"+noRefererDeposits.size()+"无推荐人 总存款："+decimalFormat.format(cntBalance)+"  总日均："+decimalFormat.format(cntPerDayBalance));
        File resultFile = ExcelUtils.writeExcel(vos, RefererBalanceVo.class, sourceFileNames[0] + "统计结果", outPath + sourceFileNames[0] + "_统计结果.xlsx");
//        log.info("统计结果 文件输出到：{}路径下{}文件中",resultFile.getPath(),resultFile.getName());
        // TODO: 2020/6/20 存在一些未对应上的 在推荐人修正里面 将推荐人对应上 再次执行 数据修正 再统计

        File outResultFile = ExcelUtils.writeExcel(noRefererDeposits,Deposit.class,sourceFileNames[0]+"花名册中不存在",outPath+sourceFileNames[0]+"_花名册中不存在.xlsx");

        log.info("总共{}推荐人不在花名册中 输出到{}文件中",noRefererDeposits.size(),outResultFile.getPath());

        return "统计成功";
    }

    public String report(String firstSourceFileName,String secondSourceFileName){
         File firstFile =new File(firstSourceFileName);
        File  secondFile=new File(secondSourceFileName);
        List<RefererBalance> firstDataList = ExcelUtils.readExcel(RefererBalance.class, firstFile);
        List<RefererBalance> secondDataList = ExcelUtils.readExcel(RefererBalance.class, secondFile);

        if(firstDataList.size()!=secondDataList.size()){
            log.info("两个统计结果数据量不一致");
            return "两个统计结果数据量不一致";
        }

        List<ReportVo> result = new ArrayList<>();
        double firstDepartmentTotalBalance=0D,firstDepartmentTotalPerDayBalance=0D,secondDepartmentTotalBalance=0D,
                secondDepartmentTotalPerDayBalance=0D,totalDepartmentDiffBalance=0,totalDepartmentDiffPerDayBalance=0;
        double firstTotalBalance=0D,firstTotalPerDayBalance=0D,secondTotalBalance=0D,secondTotalPerDayBalance=0D;
        ReportVo tempVo = null;
        for(int index = 0;index<firstDataList.size()&&index<secondDataList.size();index++){
            RefererBalance first=firstDataList.get(index);
            RefererBalance second=secondDataList.get(index);
            ReportVo vo = new ReportVo();
            vo.setDepartment(first.getDepartment());
            vo.setName(first.getName());
            vo.setReferrer(first.getReferrer());
            vo.setFirstBalance(first.getBalance()/10000);
            vo.setFirstPerDayBalance(first.getPerDayBalance()/10000);
            vo.setSecondBalance(second.getBalance()/10000);
            vo.setSecondPerDayBalance(second.getPerDayBalance()/10000);
            vo.setDiffBalance((first.getBalance()-second.getBalance())/10000);
            vo.setDiffPerDayBalance((first.getPerDayBalance()-second.getPerDayBalance())/10000);
            if(tempVo==null){
                tempVo = vo;
            }else if(!tempVo.getDepartment().equalsIgnoreCase(vo.getDepartment())){
                tempVo.setFirstTotalBalance(firstDepartmentTotalBalance/10000);
                tempVo.setFirstTotalPerDayBalance(firstDepartmentTotalPerDayBalance/10000);
                tempVo.setSecondTotalBalance(secondDepartmentTotalBalance/10000);
                tempVo.setSecondTotalPerDayBalance(secondDepartmentTotalPerDayBalance/10000);
                tempVo.setTotalDiffBalance(totalDepartmentDiffBalance/10000);
                tempVo.setTotalDiffPerDayBalance(totalDepartmentDiffPerDayBalance/10000);
                firstDepartmentTotalBalance=0D;firstDepartmentTotalPerDayBalance=0D;secondDepartmentTotalBalance=0D;
                        secondDepartmentTotalPerDayBalance=0D;totalDepartmentDiffBalance=0;totalDepartmentDiffPerDayBalance=0;
                tempVo = vo;
            }
            firstDepartmentTotalBalance=firstDepartmentTotalBalance+first.getBalance();
            firstDepartmentTotalPerDayBalance=firstDepartmentTotalPerDayBalance+first.getPerDayBalance();
            secondDepartmentTotalBalance=secondDepartmentTotalBalance+second.getBalance();
            secondDepartmentTotalPerDayBalance=secondDepartmentTotalPerDayBalance+first.getPerDayBalance();
            totalDepartmentDiffBalance = totalDepartmentDiffBalance+(first.getBalance()-second.getBalance());
            totalDepartmentDiffPerDayBalance = totalDepartmentDiffPerDayBalance+(first.getPerDayBalance()-second.getPerDayBalance());
            //总额
            firstTotalBalance=firstDepartmentTotalBalance+first.getBalance();
            firstTotalPerDayBalance=firstDepartmentTotalPerDayBalance+first.getPerDayBalance();
            secondTotalBalance=secondDepartmentTotalBalance+second.getBalance();
            secondTotalPerDayBalance=secondDepartmentTotalPerDayBalance+first.getPerDayBalance();
            result.add(vo);
        }
        //最后一个部门的数据处理
        tempVo.setFirstTotalBalance(firstDepartmentTotalBalance/10000);
        tempVo.setFirstTotalPerDayBalance(firstDepartmentTotalPerDayBalance/10000);
        tempVo.setSecondTotalBalance(secondDepartmentTotalBalance/10000);
        tempVo.setSecondTotalPerDayBalance(secondDepartmentTotalPerDayBalance/10000);
        tempVo.setTotalDiffBalance(totalDepartmentDiffBalance/10000);
        tempVo.setTotalDiffBalance(totalDepartmentDiffBalance);
        tempVo.setTotalDiffPerDayBalance(totalDepartmentDiffPerDayBalance);

        log.info("{},{},{},{}",firstTotalBalance,firstTotalPerDayBalance,secondTotalBalance,secondTotalPerDayBalance);
        //保存修正后原始数据
        String[] firstnames = firstFile.getName().split("\\.");
        String[] secondnames = secondFile.getName().split("\\.");
        File file = ExcelUtils.writeExcel(result,ReportVo.class,firstnames[0]+"-"+secondnames[0],outPath+firstnames[0]+"_"+secondnames[0]+"_统计结果.xlsx",1,5,7,9,11,13,15);
        log.info("对比报表生产完成 输出到{}路径下{}",file.getPath(),file.getName());
        return "报表生产成功";
    }
}
