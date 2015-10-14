package org.wallet.demo.domain;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Wallet {
    int id;
    String userName;
    int balanceVersion;
    double balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getBalanceVersion() {
        return balanceVersion;
    }

    public void setBalanceVersion(int balanceVersion) {
        this.balanceVersion = balanceVersion;
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
                && isEquals((Wallet) other)));
    }

    private boolean isEquals(Wallet other)
    {
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(getBalance(), other.getBalance())
                .append(getBalanceVersion(), other.getBalanceVersion())
                .append(getUserName(), other.getUserName());
        return builder.isEquals();
    }


    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getBalance())
                .append(getBalanceVersion())
                .append(getUserName());

        return builder.toHashCode();
    }
}
