import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart {
    //cart should store current no of food_items it has, find their total add quantities and stuff
    int counter=0;//stores total no of objects in a cart initialised as 0
    double total=0;//stores total value of the cart initialised as 0
    public List<Food_item> list; //stuff in my cart
    public Cart() {
        list = new ArrayList<Food_item>();
        this.counter=0;
        this.total=0;
    }

    public void setList(List<Food_item> list) {
        this.list = list;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List getList(){
        return this.list;
    }

    public double getTotal() {
        return total;
    }

    public int getCounter() {
        return counter;
    }

    Scanner scanner=new Scanner(System.in);
    public void add_items(Food_item item){
        //add food item increase counter by 1
        this.counter+=1;
        this.total+=item.getPrice();
        this.list.add(item);
    }

    public void add_quantity(Food_item item){
        System.out.print("Enter the quantity of"+item.getName()+":");
        int choice=scanner.nextInt();
        item.setQuantity(choice);
        this.total+=item.getPrice()*choice;
        this.counter+= item.quantity-1;
    }

    public void remove_items(Food_item item){
            this.counter-=1;
            this.total-=item.getPrice();
            this.list.remove(item);
    }




}
