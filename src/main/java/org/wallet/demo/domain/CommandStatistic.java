package org.wallet.demo.domain;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class CommandStatistic {
    private CommandType commandType;
    private long queryTime;
    private long date;

    public CommandStatistic(CommandType commandType, long queryTime, long date) {
        this.commandType = commandType;
        this.queryTime = queryTime;
        this.date = date;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public long getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(long queryTime) {
        this.queryTime = queryTime;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public final boolean equals(Object other)
    {
        return (other != null) && ((this == other)
                || (other.getClass().equals(this.getClass())
                && isEquals((CommandStatistic) other)));
    }

    private boolean isEquals(CommandStatistic other)
    {
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(getDate(), other.getDate())
                .append(getQueryTime(), other.getQueryTime())
                .append(getCommandType(), other.getCommandType());
        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getDate())
                .append(getQueryTime())
                .append(getCommandType());

        return builder.toHashCode();
    }
}
