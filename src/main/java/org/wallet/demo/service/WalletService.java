package org.wallet.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wallet.demo.dao.WalletDao;
import org.wallet.demo.domain.*;


import org.apache.log4j.Logger;


@Service
public class WalletService {

    private  static final  Logger log = Logger.getLogger(WalletService.class.toString());

    @Autowired
    private WalletDao walletDao;

    public void createWallet(CreateWalletCommand createWalletCommand){
        int balanceVersion = 0;
        Wallet wallet = new Wallet();
        wallet.setBalance(createWalletCommand.getBalance());
        wallet.setUserName(createWalletCommand.getUserName());
        wallet.setBalanceVersion(balanceVersion);

        walletDao.createWallet(wallet);
        log.debug("create wallet for " + createWalletCommand.getUserName());
    }

    public void updateWallet(UpdateWalletCommand updadeWalletCommand ){
        Wallet wallet = walletDao.getWallet(updadeWalletCommand.getUserName());
        if (wallet == null) {
            log.info("wallet for" + updadeWalletCommand.getUserName() + " not found");
            throw new ApplciationException(ApplciationException.WALLET_NOT_FOUND_ERROR_CODE, updadeWalletCommand, updadeWalletCommand, updadeWalletCommand.getUserName());
        }


        int balanceVersion = wallet.getBalanceVersion();
        double oldBalance = wallet.getBalance();
        double newBalance = oldBalance + updadeWalletCommand.getBalanceChange();
        wallet.setBalanceVersion(balanceVersion + 1);
        wallet.setBalance(newBalance);

        validateWallet(wallet, updadeWalletCommand);

        walletDao.updateWallet(wallet);
        log.info("update wallet for " + updadeWalletCommand.getUserName());

    }

    private void validateWallet(Wallet wallet, AbstractCommand command) {
        if(wallet.getBalance() < 0) {
            log.info("balance user" + wallet.getUserName() + " was negative");
            throw new ApplciationException(ApplciationException.NEGATIVE_BALANCE_ERROR_CODE, command, wallet.getUserName(), wallet.getBalance());
        }
    }

}
