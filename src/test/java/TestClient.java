import Entities.Client;
import Entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test the Client with different input cases
 *
 * downloading functions are not fully tested because they require image files
 */

public class TestClient {
    @Test
    public void testDelete() throws Exception {
        //set up a user
        User user = new User();
        user.setUsername("ABC");
        user.setPassword("123");
        user.hashcode();

        //test
        Client client=new Client();
        String res=client.delete(user);

        //output should be
        String res_should_be="Deleted Successfully";

        Assert.assertEquals(res_should_be,res);
    }
    @Test
    public void testRegister1() throws Exception {
        //set up a user
        User user = new User();
        user.setUsername("ABC");
        user.setPassword("123");
        user.hashcode();

        //test
        Client client=new Client();
        client.delete(user);
        String res=client.register(user);

        //output should be
        String res_should_be="Registered Successfully";

        Assert.assertEquals(res_should_be,res);
    }
    @Test
    public void testRegister2() throws Exception {
        //set up a user
        User user = new User();
        user.setUsername("ABC");
        user.setPassword("123");
        user.hashcode();

        //test
        Client client=new Client();
        client.register(user);
        String res=client.register(user);

        //output should be
        String res_should_be="Username Already Exists";

        Assert.assertEquals(res_should_be,res);
    }
    @Test
    public void testLogin1() throws Exception {
        //set up a user
        User user = new User();
        user.setUsername("ABC");
        user.setPassword("123");
        user.hashcode();

        //test
        Client client=new Client();
        client.register(user);
        String res=client.login(user);

        //output should be
        String res_should_be="Login Successful";

        Assert.assertEquals(res_should_be,res);
    }
    @Test
    public void testLogin2() throws Exception {
        //set up a user
        User user1 = new User();
        user1.setUsername("ABC");
        user1.setPassword("12");
        user1.hashcode();

        User user2 = new User();
        user2.setUsername("ABC");
        user2.setPassword("123");
        user2.hashcode();

        //test
        Client client=new Client();
        client.register(user2);
        String res=client.login(user1);

        //output should be
        String res_should_be="Wrong Password";

        Assert.assertEquals(res_should_be,res);
    }
    @Test
    public void testLogin3() throws Exception {
        //set up a user
        User user = new User();
        user.setUsername("TestOnly");
        user.setPassword("123");
        user.hashcode();

        //test
        Client client=new Client();
        String res=client.login(user);

        //output should be
        String res_should_be="Unknown Username";

        Assert.assertEquals(res_should_be,res);
    }
}
