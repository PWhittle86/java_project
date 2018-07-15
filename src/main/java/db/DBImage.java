//package db;
//
//import models.Advert;
//import org.hibernate.Criteria;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import java.io.File;
//import java.io.FileInputStream;
//
//public class DBImage {
//    private static Session session;
//    private static Transaction transaction;
//
//    public static void main(Advert advert, String imagePath ){
//        session = HibernateUtil.getSessionFactory().openSession();
//        File file = new File(imagePath);
//        byte[] bFile = new byte[(int) file.length()];
//        try {
//            session.beginTransaction();
//            FileInputStream fileInputStream = new FileInputStream(file);
//            fileInputStream.read(bFile);
//            fileInputStream.close();
//        } catch (HibernateException e){
//            transaction.rollback();
//            e.printStackTrace();
//        }
//        advert.setImage(bFile);
//        session.save(advert);
//
//    }
//}
