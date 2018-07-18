import models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    User user;

    @Before
    public void setUp() throws Exception {
        user = new User("jack", "/public");
    }

    @Test
    public void hasUsername(){
        assertEquals("jack", user.getUsername());
    }

    @Test
    public void hasImagePath(){
        assertEquals("/public", user.getUserImage());
    }


}
