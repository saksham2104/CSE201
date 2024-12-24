import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class LoginSystemTest {

    @Test
    public void testInvalidLogin() {
        // Set up
        HashMap<String, String> verify = new HashMap<>();
        verify.put("admin", "password123"); // Existing user
        verify.put("customer", "pass456");

        String username = "wrongUser";
        String password = "wrongPass";
        String loginError = "";

        // Simulate login attempt
        if (!verify.containsKey(username) || !verify.get(username).equals(password)) {
            loginError = "Invalid username or password!";

        }

        // Assertions
        assertEquals("Invalid username or password!", loginError);
    }

    @Test
    public void testLoginWithIncorrectPassword() {
        // Set up test environment
        HashMap<String, String> verify = new HashMap<>();
        verify.put("admin", "password123"); // Existing user
        verify.put("customer", "pass456");

        String username = "admin";
        String password = "wrongPass";
        String loginError = "";

        // Simulate login attempt
        if (!verify.containsKey(username) || !verify.get(username).equals(password)) {
            loginError = "Invalid username or password!";
        }

        // Assertions
        assertEquals("Invalid username or password!", loginError);
    }
}
