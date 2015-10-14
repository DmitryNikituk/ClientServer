package org.wallet.demo.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.wallet.demo.domain.AbstractCommand;


public class CreateWalletCommand extends AbstractCommand {

    String userName;
    double balance;

    public CreateWalletCommand(int transactionId, String userName, double balance) {
        super(CommandType.CREATE_WALLET, transactionId);
        this.userName = userName;
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object other)
    {
        return (other != null) && ((this == other)
                || (other.getClass().equals(this.getClass())
                && isEquals((CreateWalletCommand) other)));
    }

    private boolean isEquals(CreateWalletCommand other)    {
        EqualsBuilder builder = new EqualsBuilder();
        builder.appendSuper(super.equals(other));
        builder.append(getBalance(), other.getBalance())
                .append(getUserName(), other.getUserName());
        return builder.isEquals();
    }


    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.appendSuper(super.hashCode());
        builder.append(getUserName())
                .append(getBalance());

        return builder.toHashCode();
    }
}
