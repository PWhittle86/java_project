import models.Advert;
import models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdvertTest {
    User user;
    Advert advert;

    @Before
    public void setUp() throws Exception {
        user = new User("jack", "/public");
        advert = new Advert("test", "test2", 10.0, user, "/public");
    }

    @Test
    public void hasTitle(){
        assertEquals("test", advert.getAdvertTitle());
    }

    @Test
    public void hasImagePath(){
        assertEquals("/public", advert.getImageLocation());
    }

    @Test
    public void hasOwner(){
        assertEquals(user, advert.getAdvertOwner());
    }

    @Test
    public void hasDescription(){
        assertEquals("test2", advert.getAdvertDescription());
    }

    @Test
    public void hasPrice(){
        assertEquals(10.0, advert.getAskingPrice(), 0);
    }
}
