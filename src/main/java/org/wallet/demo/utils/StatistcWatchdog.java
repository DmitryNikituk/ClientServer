package org.wallet.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wallet.demo.service.StatisticService;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



@Service
public class StatistcWatchdog {

    @Autowired
    private StatisticService statisticService;

    @PostConstruct
    public void init() {
        Runnable job = new Runnable() {
            @Override
            public void run() {
                statisticService.processStatistic();
            }
        };


        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(job, 1, TimeUnit.MINUTES);
    }
}
