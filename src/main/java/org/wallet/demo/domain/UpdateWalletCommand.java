package org.wallet.demo.domain;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class UpdateWalletCommand extends AbstractCommand {

    String userName;
    double balanceChange;

    public UpdateWalletCommand(int transactionId, String userName, double balanceChange) {
        super(CommandType.UPDATE_WALLET, transactionId);
        this.userName = userName;
        this.balanceChange = balanceChange;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getBalanceChange() {
        return balanceChange;
    }

    public void setBalanceChange(double balanceChange) {
        this.balanceChange = balanceChange;
    }

    @Override
    public boolean equals(Object other)
    {
        return (other != null) && ((this == other)
                || (other.getClass().equals(this.getClass())
                && isEquals((UpdateWalletCommand) other)));
    }

    private boolean isEquals(UpdateWalletCommand other)    {
        EqualsBuilder builder = new EqualsBuilder();
        builder.appendSuper(super.equals(other));
        builder.append(getBalanceChange(), other.getBalanceChange())
                .append(getUserName(), other.getUserName());
        return builder.isEquals();
    }


    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.appendSuper(super.hashCode());
        builder.append(getUserName())
                .append(getBalanceChange());

        return builder.toHashCode();
    }
}
