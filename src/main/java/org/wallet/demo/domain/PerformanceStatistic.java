package org.wallet.demo.domain;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class PerformanceStatistic {

    int numberOfCommands;
    long minTime;
    long maxTime;
    long avgTime;
    int minute;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getNumberOfCommands() {
        return numberOfCommands;
    }

    public void setNumberOfCommands(int numberOfCommands) {
        this.numberOfCommands = numberOfCommands;
    }

    public long getMinTime() {
        return minTime;
    }

    public void setMinTime(long minTime) {
        this.minTime = minTime;
    }

    public long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }

    public long getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(long avgTime) {
        this.avgTime = avgTime;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public boolean equals(Object other)
    {
        return (other != null) && ((this == other)
                || (other.getClass().equals(this.getClass())
                && isEquals((PerformanceStatistic) other)));
    }

    private boolean isEquals(PerformanceStatistic other)
    {
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(getMinute(), other.getMinute())
                .append(getMaxTime(), other.getMaxTime())
                .append(getMinTime(), other.getMinTime())
                .append(getAvgTime(), other.getAvgTime())
                .append(getNumberOfCommands(), other.getNumberOfCommands());
        return builder.isEquals();
    }


    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getMinute())
                .append(getMaxTime())
                .append(getMinTime())
                .append(getAvgTime())
                .append(getNumberOfCommands());

        return builder.toHashCode();
    }
}
