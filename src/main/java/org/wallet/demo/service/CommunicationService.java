package org.wallet.demo.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wallet.demo.connection.JmsCommandSender;
import org.wallet.demo.domain.AbstractCommand;


@Service
public class CommunicationService {

    private  static  final Logger log = Logger.getLogger(CommandService.class.getName());

    @Autowired
    private  JmsCommandSender jmsCommandSender;

    @Autowired
    private  CommandService commandService;

    public  void  sendCommand(AbstractCommand abstractCommand){
        jmsCommandSender.send(abstractCommand);
        log.info("command" + abstractCommand.getCommandType() + "sent to JMS ActiveMQ");
    };

    public  void  onCommandRecieve(AbstractCommand abstractCommand){
            commandService.processCommand(abstractCommand);
            log.info("command " + abstractCommand.getCommandType() + " was read from JMS ActiveMQ");
    };

}
