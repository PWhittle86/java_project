package db;

import models.Advert;
import models.Comment;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DBComment {
    private static Session session;
    private static Transaction transaction;

    public static void advertComment(Advert advert, User user, Comment comment){
        user.addComment(comment);
        advert.addComment(comment);

        DBHelper.save(user);
        DBHelper.save(advert);
    }
}
