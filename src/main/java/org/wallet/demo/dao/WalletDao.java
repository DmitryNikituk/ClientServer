package org.wallet.demo.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wallet.demo.domain.Wallet;

@Service
@Transactional
public class WalletDao extends AbstractDao{

    public void createWallet(Wallet wallet){
        getSession().save(wallet);
    };

    public void updateWallet(Wallet wallet){
        getSession().update(wallet);
    };

    public Wallet getWallet(String userName){
        Criteria criteria = getSession().createCriteria(Wallet.class);
        Wallet wallet = (Wallet)criteria.add(Restrictions.eq("userName", userName)).uniqueResult();
        return wallet;
    };
}
