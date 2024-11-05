import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Customer {
    private String name;
    List<Food_item> order_history = new ArrayList<Food_item>();

    private int status;// 0 for regular 1 for VIP
    private Cart cart;
    public Customer(String name,List<Food_item>list,int status) {
        this.name = name;
        this.order_history = list;
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
    public List<Food_item> getOrder_history() {
        return order_history;
    }
    public void setOrder_history(List<Food_item> order_history) {
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

    public void order_history(){
        //display order history
        int count=1;
        for(Food_item item: order_history){
            System.out.println("Order history:\n");
            System.out.println(count+":"+" ");
            count++;
            System.out.println(item.name+" ");
            System.out.println(item.price+" "+"\n");
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
