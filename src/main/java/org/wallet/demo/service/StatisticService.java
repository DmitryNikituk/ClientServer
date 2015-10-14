package org.wallet.demo.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wallet.demo.dao.PerformanceStatisticDao;
import org.wallet.demo.domain.CommandStatistic;
import org.wallet.demo.domain.PerformanceStatistic;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service
public class StatisticService {

    private  static  final Logger log = Logger.getLogger(CommandService.class.getName());

    @Autowired
    private StatisticResource statisticResource;

    @Autowired
    private PerformanceStatisticDao performanceStatisticDao;

    public void commandExecuted(CommandStatistic commandStatistic) {
        statisticResource.storeData(commandStatistic);
        log.info("statistic about " + commandStatistic.getCommandType() +" was sent in statistic resource ");
    }

    public void processStatistic() {
        Multimap<Integer, CommandStatistic> history = statisticResource.getUnprocessedData(System.currentTimeMillis());

        for(Integer key : history.keySet()) {
            processHistoryForMinute(key, Lists.newArrayList(history.get(key)));
        }
        log.info("history statistic save in data base");
    }

    private void processHistoryForMinute(Integer min, List<CommandStatistic> history) {
        PerformanceStatistic statistic = new PerformanceStatistic();
        statistic.setMinute(min);
        statistic.setNumberOfCommands(history.size());

        Collections.sort(history, new Comparator<CommandStatistic>() {
            @Override
            public int compare(CommandStatistic o1, CommandStatistic o2) {
                return (int) (o1.getQueryTime() - o2.getQueryTime());
            }
        });

        statistic.setMinTime(history.get(0).getQueryTime());
        statistic.setMaxTime(history.get(history.size() - 1).getQueryTime());
//        statistic.setAvgTime(); TODO-impl

        performanceStatisticDao.saveStatistic(statistic);
    }


}
