package org.wallet.demo.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;


public class AbstractCommand implements Serializable {

    private CommandType commandType;
    private int transactionId;

    public AbstractCommand(CommandType commandType, int transactionId) {
        this.commandType = commandType;
        this.transactionId = transactionId;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public boolean equals(Object other)
    {
        return (other != null) && ((this == other)
                || (other.getClass().equals(this.getClass())
                && isEquals((AbstractCommand) other)));
    }

    private boolean isEquals(AbstractCommand other)
    {
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(getTransactionId(), other.getTransactionId())
                .append(getCommandType(), other.getCommandType());
        return builder.isEquals();
    }


    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getTransactionId())
                .append(getCommandType());

        return builder.toHashCode();
    }
}
