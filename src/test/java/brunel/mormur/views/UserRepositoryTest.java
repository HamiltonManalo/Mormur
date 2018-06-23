//package brunel.mormur.views;
//import org.junit.*;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//
//public class UserRepositoryTest {
//    @Autowired
//    private IUserRepository IuserRepistory;
//
//    @Before
//    public void setUp() {
//        User user1 = new User("Hamilton", "Manalo", "hmA@gm.cm");
//        User user2 = new User("Hamilton1", "Man2alo", "h123mA@gm.cm");
//        assertNull(user1.getId());
//        assertNull(user2.getId());
//        this.IuserRepistory.save(user1);
//        this.IuserRepistory.save(user2);
//        assertNotNull(user1.getId());
//        assertNotNull(user2.getId());
//    }
//
//    @Test
//    public void testFetchData() {
//        User userA = IuserRepistory.findByName("Hamilton");
//        User userB = IuserRepistory.findByName("Hamilton1");
//        assertNotNull(userA);
//        Iterable<User> users = IuserRepistory.findAll();
//        int count = 0;
//        for(User p : users) {
//            count++;
//        }
//        assertEquals(count,2);
//    }
//}
