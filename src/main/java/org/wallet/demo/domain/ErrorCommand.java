package org.wallet.demo.domain;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class ErrorCommand extends  AbstractCommand{

    private int errorCode;
    private String message;

    public ErrorCommand(int transactionId, int errorCode, String message) {
        super(CommandType.ERROR, transactionId);
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public boolean equals(Object other)
    {
        return (other != null) && ((this == other)
                || (other.getClass().equals(this.getClass())
                && isEquals((ErrorCommand) other)));
    }

    private boolean isEquals(ErrorCommand other)    {
        EqualsBuilder builder = new EqualsBuilder();
        builder.appendSuper(super.equals(other));
        builder.append(getErrorCode(), other.getErrorCode());
        return builder.isEquals();
    }


    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.appendSuper(super.hashCode());
        builder.append(getErrorCode());

        return builder.toHashCode();
    }
}
