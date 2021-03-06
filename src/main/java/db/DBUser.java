package db;

import models.Advert;
import models.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBUser {
    private static Session session;

    public static List<Advert> getUsersAdverts(User user){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Advert> result = null;
        try {
            Criteria cr = session.createCriteria(Advert.class);
            cr.add(Restrictions.eq("advertOwner", user));
            result = cr.list();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return result;
    }

    public static List<Advert> getUsersFavAdverts(User user){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Advert> result = null;
        try {
            Criteria cr = session.createCriteria(Advert.class);
            cr.createAlias("favouritedBy", "user");
            cr.add(Restrictions.eq("user.id", user.getId()));
            result = cr.list();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return result;
    }

    public static void addToUserFavourites(User user, Advert advert){
        user.setFavouriteAdverts(getUsersFavAdverts(user));
        user.addFavouriteAdvert(advert);

        advert.setFavouritedBy(DBAdvert.findFavouritedBy(advert));
        advert.addUserFavourite(user);

        DBHelper.save(user);
        DBHelper.save(advert);

    }

}
