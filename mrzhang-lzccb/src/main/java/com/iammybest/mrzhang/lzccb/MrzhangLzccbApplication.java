package com.iammybest.mrzhang.lzccb;

//@SpringBootApplication
public class MrzhangLzccbApplication {

    public static void main(String[] args) {
<<<<<<< HEAD
//        System.out.println("系统输出");
//        SpringApplication.run(MrzhangLzccbApplication.class, args);
        StatisticsHandler statisticsHandler = new StatisticsHandler();
        statisticsHandler.setCorrectAccountFileName("D:\\lzccb\\id_referer_correct.xlsx");
        statisticsHandler.setCorrectRefererFileName("D:\\lzccb\\referer_correct.xlsx");
        statisticsHandler.setRefererFileName("D:\\lzccb\\referers.xlsx");
        statisticsHandler.setOutPath("D:\\lzccb\\");
        statisticsHandler.statistics("D:\\lzccb\\20201031.xlsx");
//        statisticsHandler.statistics("D:\\lzccb\\20200831.xlsx");
        statisticsHandler.report("D:\\lzccb\\20201031_统计结果.xlsx","D:\\lzccb\\20200930_统计结果.xlsx");
=======
        System.out.println("系统输出");
//        SpringApplication.run(MrzhangLzccbApplication.class, args);
//        StatisticsHandler statisticsHandler = new StatisticsHandler();
//        statisticsHandler.setCorrectAccountFileName("D:\\lzccb\\id_referer_correct.xlsx");
//        statisticsHandler.setCorrectRefererFileName("D:\\lzccb\\referer_correct.xlsx");
//        statisticsHandler.setRefererFileName("D:\\lzccb\\referers.xlsx");
//        statisticsHandler.setOutPath("D:\\lzccb\\");
//        statisticsHandler.statistics("D:\\lzccb\\20200910.xlsx");
//        statisticsHandler.statistics("D:\\lzccb\\20200831.xlsx");
//        statisticsHandler.report("D:\\lzccb\\20200910_统计结果.xlsx","D:\\lzccb\\20200831_统计结果.xlsx");
>>>>>>> 7af9c3ef8b116229dac193ce3de8bf20e8df57ad
    }

}
