package org.wallet.demo.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.wallet.demo.service.CommandService;


public class ServerRunner {


    private  StatistcWatchdog statistcWatchdog;

    public  static  void  main(String[] args){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("server-spring-config.xml");
        ServerRunner serverRunner = new ServerRunner();
        serverRunner.statistcWatchdog = (StatistcWatchdog)ctx.getBean("statistcWatchdog");
        serverRunner.statistcWatchdog.init();

        sleepAndExit(60*30*1000);
    }

    private static void sleepAndExit(long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
