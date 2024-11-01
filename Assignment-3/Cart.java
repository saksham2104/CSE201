import java.util.List;
import java.util.Scanner;

public class Cart {
    //cart should store current no of food_items it has, find their total add quantities and stuff
    int counter=0;//stores total no of objects in a cart initialised as 0
    double total=0;//stores total value of the cart initialised as 0
    List<Food_item> list; //stuff in my cart
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

    public void payment(){
        //calling this function should take user to payment getaway
        System.out.println("Choose payment method:\n");
        System.out.println("1.Cash\n");
        System.out.println("2.UPI\n");
        System.out.println("3.Card\n");
        int choice=scanner.nextInt();
        if(choice==1){
            System.out.println("Pay cash amount:"+this.total);
        }
        else if(choice==2){
            System.out.println("Choose among the following:\n");
            System.out.println("1.Paytm \n");
            System.out.println("2.Google Pay \n");
            System.out.println("3.Phone Pe \n");
            System.out.println("4.Others \n");

            System.out.println("Pay total:"+this.total);

        }
        else if(choice==3){
            System.out.println("Choose among the following:\n");
            System.out.println("1.MasterCard \n");
            System.out.println("2.Visa \n");

            System.out.println("Pay total:"+this.total);
        }
    }



}
