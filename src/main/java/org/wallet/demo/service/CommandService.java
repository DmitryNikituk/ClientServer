package org.wallet.demo.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wallet.demo.domain.*;


@Service
public class CommandService {

    private  static  final Logger log = Logger.getLogger(CommandService.class.getName());


    @Autowired
    private  CommunicationService communicationService;

    @Autowired
    private  WalletService walletService;

    @Autowired
    private StatisticService statisticService;

    public void requestWalletCreate(String username, double balance){
        long currentTime = System.currentTimeMillis();
        CreateWalletCommand createWalletCommand = new CreateWalletCommand(2, username, balance);
        communicationService.sendCommand(createWalletCommand);
        onCommandProcessed(createWalletCommand, currentTime);
        log.info("request for create wallet for" + username + "was sent ");

    }

    public void  requestWalletUpdate(String username, double balanceChange){
        long currentTime = System.currentTimeMillis();
        UpdateWalletCommand updateWalletCommand = new UpdateWalletCommand(3, username, balanceChange);
        communicationService.sendCommand(updateWalletCommand);
        onCommandProcessed(updateWalletCommand, currentTime);
        log.info("request for update wallet for" + username + "was sent ");
    }

    public  void  sendError(ErrorCommand updateWalletErrorCommand){
        long currentTime = System.currentTimeMillis();
        communicationService.sendCommand(updateWalletErrorCommand);
        onCommandProcessed(updateWalletErrorCommand, currentTime);
        log.info("request error was sent");
    }

    public  void  processCommand(AbstractCommand abstractCommand) {
        try {
            long currentTime = System.currentTimeMillis();
            switch (abstractCommand.getCommandType()) {
                case CREATE_WALLET:
                    walletService.createWallet((CreateWalletCommand) abstractCommand);
                    break;
                case UPDATE_WALLET:
                    walletService.updateWallet((UpdateWalletCommand) abstractCommand);
                    break;
                case ERROR:
                    System.out.println("Error recevide : " + ((ErrorCommand) abstractCommand).getMessage());
                    break;

            }
            onCommandProcessed(abstractCommand, currentTime);
        } catch (ApplciationException ex) {
            ErrorCommand error = new ErrorCommand(abstractCommand.getTransactionId(),
                    ex.getErrorCode(),
                    ex.getMessage());
            sendError(error);
            log.warn("error process command " + ex.getMessage());

        }
    }

    public void onCommandProcessed(AbstractCommand command, long startTime) {
        long currentTime = System.currentTimeMillis();
        CommandStatistic statistic = new CommandStatistic(command.getCommandType(), (currentTime - startTime), currentTime);
        statisticService.commandExecuted(statistic);
        log.info("create command statistic for  " + command.getCommandType());
    }

}
