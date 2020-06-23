//package com.iammybest.mrzhang.lzccb;
//
//import org.springframework.beans.BeanUtils;
//
//import java.io.File;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @DESCRIBE:
// * @TIME: 2020/6/21 1:42
// * @AUTHOR: qinghai.deng
// **/
//public class Report {
//    static File firstFile =new File("D:\\lzccb\\20200531_统计结果.xlsx");
//    static File  secondFile=new File("D:\\lzccb\\20200618_统计结果.xlsx");
//    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
//    public static void main(String[] args) {
//        List<RefererBalance> firstDataList = ExcelUtils.readExcel(RefererBalance.class, firstFile);
//        List<RefererBalance> secondDataList = ExcelUtils.readExcel(RefererBalance.class, secondFile);
//
//        if(firstDataList.size()!=secondDataList.size()){
//            System.out.println("两个统计结果数据量不一致");
//            return;
//        }
//
//        List<ReportVo> result = new ArrayList<>();
//        double firstTotalBalance=0D,firstTotalPerDayBalance=0D,secondTotalBalance=0D,
//                secondTotalPerDayBalance=0D,totalDiffBalance=0,totalDiffPerDayBalance=0;
//        ReportVo tempVo = null;
//        for(int index = 0;index<firstDataList.size()&&index<secondDataList.size();index++){
//            RefererBalance first=firstDataList.get(index);
//            RefererBalance second=secondDataList.get(index);
//            ReportVo vo = new ReportVo();
//            vo.setDepartment(first.getDepartment());
//            vo.setName(first.getName());
//            vo.setReferrer(first.getReferrer());
//            vo.setFirstBalance(decimalFormat.format(first.getBalance()));
//            vo.setFirstPerDayBalance(decimalFormat.format(first.getPerDayBalance()));
//            vo.setSecondBalance(decimalFormat.format(second.getBalance()));
//            vo.setSecondPerDayBalance(decimalFormat.format(second.getPerDayBalance()));
//            vo.setDiffBalance(decimalFormat.format(first.getBalance()-second.getBalance()));
//            vo.setDiffPerDayBalance(decimalFormat.format(first.getPerDayBalance()-second.getPerDayBalance()));
//            if(tempVo==null){
//                tempVo = vo;
//            }else if(!tempVo.getDepartment().equalsIgnoreCase(vo.getDepartment())){
//                tempVo.setTotalDiffBalance(decimalFormat.format(totalDiffBalance));
//                tempVo.setTotalDiffPerDayBalance(decimalFormat.format(totalDiffPerDayBalance));
//                totalDiffBalance=0D;
//                totalDiffPerDayBalance=0D;
//                tempVo = vo;
//            }
//            firstTotalBalance=firstTotalBalance+first.getBalance();
//            firstTotalPerDayBalance=firstTotalPerDayBalance+first.getPerDayBalance();
//            secondTotalBalance=secondTotalBalance+second.getBalance();
//            secondTotalPerDayBalance=secondTotalPerDayBalance+first.getPerDayBalance();
//            totalDiffBalance = totalDiffBalance+(first.getBalance()-second.getBalance());
//            totalDiffPerDayBalance = totalDiffPerDayBalance+(first.getPerDayBalance()-second.getPerDayBalance());
//            result.add(vo);
//            System.out.println(vo.toString());
//        }
//        //最后一个部门的数据处理
//        tempVo.setTotalDiffBalance(decimalFormat.format(totalDiffBalance));
//        tempVo.setTotalDiffPerDayBalance(decimalFormat.format(totalDiffPerDayBalance));
//
//        //保存修正后原始数据
//        String[] firstnames = firstFile.getName().split("\\.");
//        String[] secondnames = secondFile.getName().split("\\.");
//        ExcelUtils.writeExcel(result,ReportVo.class,firstnames[0]+"-"+secondnames[0],"D:\\lzccb\\"+firstnames[0]+"_"+secondnames[0]+"_统计结果.xlsx",1,5,7,9,11,13,15);
//    }
//}
