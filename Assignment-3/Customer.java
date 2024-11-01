import java.util.ArrayList;
import java.util.List;


public class Customer {
    private String name;
    List<Food_item> order_history = new ArrayList<Food_item>();
    private int status;// 0 for regular 1 for VIP


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
                System.out.println("availability"+item.availability);
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
        // add review
    }

}
