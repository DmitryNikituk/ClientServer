package org.wallet.demo.domain;

import java.text.MessageFormat;


public class ApplciationException extends  RuntimeException{

    public static final int NEGATIVE_BALANCE_ERROR_CODE = 1;
    public static final int WALLET_NOT_FOUND_ERROR_CODE = 2;

    private static final String NEGATIVE_BALANCE_MESSAGE = "There is not enough money on {0}'s balance to change it. Current balance is {1}";
    private static final String WALLET_NOT_FOUND_MESSAGE = "Wallet was not found for user {0}.";
    private static final String UNEXPECTED_MESSAGE = "All operators are busy. You call is very important for us, please try again latter.";

    private String message;
    private AbstractCommand command;
    private int errorCode;

    public ApplciationException(int erroCode, AbstractCommand command, Object... params) {
        super();
        this.message = MessageFormat.format(getMessageByErrorCode(erroCode), params);
        this.command = command;
        this.errorCode = erroCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public int getErrorCode() {
        return errorCode;
    }

    private static String getMessageByErrorCode(int code) {
        switch (code) {
            case 1:
                return NEGATIVE_BALANCE_MESSAGE;
            case 2:
                return WALLET_NOT_FOUND_MESSAGE;
            default:
                return UNEXPECTED_MESSAGE;
        }
    }
}
