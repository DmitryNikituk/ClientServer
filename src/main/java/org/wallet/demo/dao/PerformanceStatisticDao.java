package org.wallet.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wallet.demo.domain.PerformanceStatistic;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
@Transactional
public class PerformanceStatisticDao extends AbstractDao{

    public void saveStatistic(PerformanceStatistic performanceStatistics){
        getSession().save(performanceStatistics);
    }

    public void findStatisticForPerId(int startTime, int endTime){
        //TODO - implement later.
        throw new NotImplementedException();
    }
}
