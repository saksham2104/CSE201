import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Customer {
    private String name;
    List<Food_item> order_history = new ArrayList<Food_item>();
    private int status;// 0 for regular 1 for VIP

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
        Review review1=new Review(name,review);
        Main.reviews.add(review1);
    }
    public void view_reviews(){
        for(Review review:Main.reviews){
            System.out.println(review+"\n");
        }
    }

}
