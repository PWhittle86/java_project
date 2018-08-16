package db;


import models.*;

public class Seeds {
    public static void seedData() {
        DBHelper.deleteAll(Advert.class);
        DBHelper.deleteAll(User.class);
        DBHelper.deleteAll(Category.class);
        DBHelper.deleteAll(Comment.class);

        User user = new User("Jack", "/seedImages/jack.png");
        DBHelper.save(user);
        User user1 = new User("Pete", "/seedImages/pete.png");
        DBHelper.save(user1);
        User user2 = new User("Adri", "/seedImages/adri.png");
        DBHelper.save(user2);

        Advert advert = new Advert("Shoes", "New shoes. Never used!", 10.0, user, "/seedImages/Shoes.jpg");
        user.getUserAdverts().add(advert);
        Advert advert1 = new Advert("Ps4", "PS4 with two controllers, 3 games.", 100.05, user, "/seedImages/PS4.png");
        Advert advert2 = new Advert("New Bike", "Bike for sale!",76.80, user1, "/seedImages/Bike.png");
        Advert advert3 = new Advert("Car", "Sadly needs to go to a new home. Please get in touch!", 500.00, user1, "/seedImages/Corsa.jpg");
        Advert advert4 = new Advert("Fancy Boat", "Very nice boat, need a lake.", 2000.00, user2, "/seedImages/Boat.jpg");
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

        Comment comment = new Comment(advert, user2,"Hi there! Looks really good, but could you do it for £10 cheaper?");
        Comment comment1 = new Comment(advert, user,"Hmm, how about £5?");
        DBHelper.save(comment);
        DBHelper.save(comment1);
        DBComment.advertComment(advert, user2, comment);
        DBComment.advertComment(advert, user, comment1);


    }
}
