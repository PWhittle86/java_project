package db;

import models.Advert;
import models.Category;
import models.ShopFront;
import models.User;

public class Seeds {
    public static void seedData() {
        DBHelper.deleteAll(ShopFront.class);
        DBHelper.deleteAll(Advert.class);
        DBHelper.deleteAll(User.class);
        DBHelper.deleteAll(Category.class);

        ShopFront shopFront = new ShopFront();

        User user = new User("Jack");
        DBHelper.save(user);
        User user1 = new User("Pete");
        DBHelper.save(user1);
        User user2 = new User("Adri");
        DBHelper.save(user2);

        Advert advert = new Advert("old shoes", "old nikes mate, worn 600 times", 10.0, user, "/images/bobplace.jpg");
        user.getUserAdverts().add(advert);
        Advert advert1 = new Advert("Ps4", "ps4 with 2 controllers, 3 games", 100.05, user, "/images/bobplace.jpg");
        Advert advert2 = new Advert("BIKE", "old fixy in good nick, one flat tire",76.80, user1, "/images/bobplace.jpg");
        Advert advert3 = new Advert("03 Corsa 150,000 miles", "good run around", 500.00, user1, "/images/bobplace.jpg");
        Advert advert4 = new Advert("Old Boat", "very nice boat, need a lake", 2000.00, user2, "/images/bobplace.jpg");
        DBHelper.save(advert);
        DBHelper.save(advert1);
        DBHelper.save(advert2);
        DBHelper.save(advert3);
        DBHelper.save(advert4);

        Category category = new Category("Fashion");
        Category category1 = new Category("Home & Garden");
        Category category2 = new Category("Electronics");
        Category category3 = new Category("Sports & Leisure");
        Category category4 = new Category("Health & Beauty");
        DBHelper.save(category);
        DBHelper.save(category1);
        DBHelper.save(category2);
        DBHelper.save(category3);
        DBHelper.save(category4);

        DBHelper.addCategoryToAdvert(category, advert);
    }
}
