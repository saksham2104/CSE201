import java.io.Serializable;
import java.time.LocalDate;

public class Order implements Serializable {
    int orderId;
    Customer customer;
    String order_status;
    String special_requests;//extra spice or no onions
    LocalDate order_date;


    public Order(int orderId,String name,Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.order_status = "Order Received";
        this.special_requests = "No Special Requests";
        this.order_date = LocalDate.now(); //today's date
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
    public String getOrder_status() {
        return order_status;
    }
    public void setSpecial_requests(String special_requests) {
        this.special_requests = special_requests;
    }
}
