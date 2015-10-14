package org.wallet.demo.utils;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.wallet.demo.service.CommandService;

import org.apache.log4j.Logger;


public class ClientRunner {

   private CommandService commandService;
    private  static  final Logger log = Logger.getLogger(ClientRunner.class.getName());

    public static void  main(String[] args){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("client-spring-config.xml");
        ClientRunner clientRunner = new ClientRunner();
        clientRunner.commandService = (CommandService)ctx.getBean("commandService");

        clientRunner.generateCommand();

        ctx.close();
        System.out.println("End.");
    }

    public void generateCommand(){
        try {
            String name[] = {"Dima", "Kola", "Vita", "Nana", "Vera", "Sawa", "Tana", "Vasa", "Roma", "Rita"};
            for (int i = 0; i < name.length; i++) {

                commandService.requestWalletCreate(name[i], 2000);
                commandService.requestWalletUpdate(name[i], 9000);

            }
        } catch (Throwable th) {

            log.error("any warning" + th);


        }

    }




}
