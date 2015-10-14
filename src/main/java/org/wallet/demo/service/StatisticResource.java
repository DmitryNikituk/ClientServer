package org.wallet.demo.service;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.wallet.demo.domain.CommandStatistic;

import java.util.*;


@Service
// Represent temporary storage for comands statistic.
public class StatisticResource {

    private Multimap<Integer, CommandStatistic> store = HashMultimap.create();
    private  static  final Logger log = Logger.getLogger(CommandService.class.getName());


    public void storeData(CommandStatistic commandStatistic) {
        int min = getMinute(commandStatistic.getDate());
        store.put(min, commandStatistic);
        log.info("statistic for " + commandStatistic.getCommandType() + " was put in store data ");
    }

    public Multimap<Integer, CommandStatistic> getUnprocessedData(long currentTime) {
        int currentMin = getMinute(currentTime);
        Multimap<Integer, CommandStatistic> storeCopy = HashMultimap.create();
        for(Integer key : store.keySet()) {
            if(key.equals(currentMin) == false) {
                storeCopy.putAll(key, store.get(key));
                store.get(key).clear();
            }
        }
        log.info("get unprocessed data for time " + currentTime);
        return storeCopy;

    }

    private int getMinute(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.get(Calendar.MINUTE);

    }
}
