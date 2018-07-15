package db;

import models.Advert;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBAdvert {

    private static Session session;
    private static Transaction transaction;

    public static List<Advert> findAdvertsByName(String searchCriteria){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Advert> foundAdverts = null;

        try{
            Criteria cr = session.createCriteria(Advert.class);
            cr.add(Restrictions.like("advertTitle", searchCriteria));
            foundAdverts = cr.list();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return foundAdverts;
    }

}
