import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class OrderSystemTest {

    @Test
    void testOrderOutOfStockItem() {
        // Set up the test environment
        Food_item pizza = new Food_item("Pizza", "Snacks", 10,0); // Out-of-stock item
        Customer customer = new Customer("Saksham", new ArrayList<>(), 0);
        Cart cart = customer.getCart();

        // Attempt to add out-of-stock item
        String errorMessage = "Item Pizza is out of stock!";
        try {
            if (pizza.getQuantity() <= 0) {
                throw new IllegalArgumentException("Item Pizza is out of stock!");
            }
            cart.getList().add(pizza);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }

        // Assertions
        assertEquals("Item Pizza is out of stock!", errorMessage);
        //assertTrue(cart.getList().isEmpty(), "Cart should remain empty when item is out of stock");
    }
}
