package org.example.repository;

import org.example.entity.Sell;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class SellRepository extends BaseRepository<Sell>{
    @Override
    public Sell findById(int id) {
        session = sessionFactory.openSession();
        Sell sell = session.get(Sell.class,id);
        session.close();
        return sell;
    }

    @Override
    public List<Sell> findAll() {
        List<Sell> sellList = null;
        session = sessionFactory.openSession();
        Query<Sell> sellQuery = session.createQuery("from Sell",Sell.class);
        sellList = sellQuery.list();
        session.close();
        return sellList;
    }

    public List<Sell> findByDate (LocalDate dateStart,LocalDate dateStop){
        List<Sell> sellList = null;
        Query<Sell> sellQuery;
        session = sessionFactory.openSession();
        if(dateStop != null){
            sellQuery = session.createQuery("from Sell where sellDate > :datestart AND sellDate < :datestop", Sell.class);
            sellQuery.setParameter("datestart",dateStart);
            sellQuery.setParameter("datestop",dateStop);
        }else{
            sellQuery = session.createQuery("from Sell where sellDate > :datestart ",Sell.class);
            sellQuery.setParameter("datestart",dateStart);
        }

        sellList = sellQuery.list();
        session.close();
        return sellList;
    }
}
