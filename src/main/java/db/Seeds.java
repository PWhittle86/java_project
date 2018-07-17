package db;

import models.*;

public class Seeds {
    public static void seedData() {
        DBHelper.deleteAll(ShopFront.class);
        DBHelper.deleteAll(Advert.class);
        DBHelper.deleteAll(User.class);
        DBHelper.deleteAll(Category.class);
        DBHelper.deleteAll(Comment.class);

        ShopFront shopFront = new ShopFront();

        User user = new User("Jack");
        DBHelper.save(user);
        User user1 = new User("Pete");
        DBHelper.save(user1);
        User user2 = new User("Adri");
        DBHelper.save(user2);

        Advert advert = new Advert("old shoes", "old nikes mate, worn 600 times", 10.0, user);
        user.getUserAdverts().add(advert);
        Advert advert1 = new Advert("Ps4", "ps4 with 2 controllers, 3 games", 100.05, user);
        Advert advert2 = new Advert("BIKE", "old fixy in good nick, one flat tire",76.80, user1 );
        Advert advert3 = new Advert("03 Corsa 150,000 miles", "good run around", 500.00, user1);
        Advert advert4 = new Advert("Old Boat", "very nice boat, need a lake", 2000.00, user2);
        DBHelper.save(advert);
        DBHelper.save(advert1);
        DBHelper.save(advert2);
        DBHelper.save(advert3);
        DBHelper.save(advert4);

        Category fashion = new Category("Fashion");
        Category homeAndGarden = new Category("Home & Garden");
        Category electronics = new Category("Electronics");
        Category sportsAndLeisure = new Category("Sports & Leisure");
        Category healthAndBeauty = new Category("Health & Beauty");
        DBHelper.save(fashion);
        DBHelper.save(homeAndGarden);
        DBHelper.save(electronics);
        DBHelper.save(sportsAndLeisure);
        DBHelper.save(healthAndBeauty);

        DBHelper.addCategoryToAdvert(fashion, advert);
        DBHelper.addCategoryToAdvert(electronics, advert1);
        DBHelper.addCategoryToAdvert(sportsAndLeisure, advert2);
        DBHelper.addCategoryToAdvert(homeAndGarden, advert3);
        DBHelper.addCategoryToAdvert(sportsAndLeisure, advert4);

        Comment comment = new Comment(advert, user2,"looks really good, do it for £10 cheaper");
        Comment comment1 = new Comment(advert, user,"Aw hell nah");
        DBHelper.save(comment);
        DBHelper.save(comment1);
        DBComment.advertComment(advert, user2, comment);
        DBComment.advertComment(advert, user, comment1);


    }
}
