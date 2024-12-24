import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Customer implements Serializable {
    private String name;
    List<Order> order_history = new ArrayList<>();

    private int status;// 0 for regular 1 for VIP
    private Cart cart;
    public Customer(String name,List<Food_item>list,int status) {
        this.name = name;
        this.order_history = new ArrayList<>();
        this.status = status;
        this.cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Order> getOrder_history() {
        return this.order_history;
    }
    public void setOrder_history(List<Order> order_history) {
        this.order_history = order_history;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    Scanner scanner=new Scanner(System.in);
    public void view_menu(Menu menu){
        List<Food_item> items =menu.getList();
        for(Food_item item:items){
            System.out.println(item+"\n");
        }
    }

    public void search(String search,Menu menu){
        List<Food_item> items =menu.getList();
        for(Food_item item:items){
            if(item.getName().toLowerCase().contains(search.toLowerCase())){
                System.out.println(item+" ");
                System.out.println("availability"+item.stock);
            }
        }
    }

    //cart operations handled via class cart

    public void cancel_order(Order order){
        // cancel current order
        String cancel="cancelled";
        order.setOrder_status(cancel);
        //also remove order from order history and currently making orders
    }

    public void display_order_history() {
        //display order history
        int count = 1;
        System.out.println("Order history: ");
        for (Order order : order_history) {
            List<Food_item> list = order.getCustomer().getCart().getList();
            for (Food_item item : list) {
                System.out.print(count + ":" + " ");
                count++;
                System.out.print(item.name + " ");
                System.out.print("(x"+item.getQuantity()+")"+" ");
                System.out.print(item.price * item.quantity + " " + "\n");
            }
        }
    }

    public void add_review(Food_item item){
        System.out.print("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter review for:"+item.name+":"+"\n");
        String review =scanner.nextLine();
        System.out.println("Rate your order(from 1 to 10):");
        int rating= scanner.nextInt();
        scanner.nextLine();
        //find_customer_by_name and add
        //Review review1=new Review(name,review,rating);
        //Main.reviews.add(review1);
    }
    public void view_reviews(){
        for(Review review:Main.reviews){
            System.out.println(review+"\n");
        }
    }

}
