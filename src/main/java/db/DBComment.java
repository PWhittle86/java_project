package db;

import models.Advert;
import models.Comment;
import models.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBComment {
    private static Session session;
    private static Transaction transaction;

    public static void advertComment(Advert advert, User user, Comment comment){
        user.setComments(findUserComments(user));
        user.addComment(comment);
        advert.setComments(findAdvertComments(advert));
        advert.addComment(comment);

        DBHelper.save(user);
        DBHelper.save(advert);
    }


    public static List<Comment> findAdvertComments(Advert advert){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Comment> foundComments = null;
        try{
            Criteria cr = session.createCriteria(Comment.class);
            cr.add(Restrictions.eq("advert.id", advert.getId()));
            foundComments = cr.list();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return foundComments;
    }

    public static List<Comment> findUserComments(User user){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Comment> foundComments = null;
        try{
            Criteria cr = session.createCriteria(Comment.class);
            cr.add(Restrictions.eq("user.id", user.getId()));
            foundComments = cr.list();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return foundComments;
    }
}
