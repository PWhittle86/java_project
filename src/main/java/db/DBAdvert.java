package db;

import models.Advert;
import models.Category;
import models.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
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
            cr.add(Restrictions.ilike("advertTitle", searchCriteria, MatchMode.ANYWHERE));
            foundAdverts = cr.list();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return foundAdverts;
    }

    public static List<Advert> findAdvertsByCategory(Category category){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Advert> foundAdverts = null;

        try{
            Criteria cr = session.createCriteria(Advert.class);
            cr.createAlias("categories", "category");
            cr.add(Restrictions.eq("category.id", category.getId()));
            foundAdverts = cr.list();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return foundAdverts;
    }


    public static List<User> findFavouritedBy(Advert advert) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<User> results = null;
        try {
            Criteria cr = session.createCriteria(User.class);

            cr.createAlias("favouriteAdverts", "advert");
            cr.add(Restrictions.eq("advert.id", advert.getId()));
            results = cr.list();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return results;
    }
}
