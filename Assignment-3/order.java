import java.time.LocalDate;

public class Order {
    String name;//name of the person ordering
    Cart cart;
    String order_status;
    String special_requests;//extra spice or no onions
    LocalDate order_date;

    public Order(String name,  Cart cart,String order_status, String special_requests, LocalDate order_date) {
        this.name = name;
        this.cart = cart;
        this.order_status = "Order Received";
        this.special_requests = "No Special Requests";
        this.order_date = LocalDate.now();//today's date
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
    public void setSpecial_requests(String special_requests) {
        this.special_requests = special_requests;
    }

}
