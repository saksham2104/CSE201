import java.util.List;
import java.util.Scanner;

public class Admin {
    private String name;
    public Admin(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    Scanner scanner=new Scanner(System.in);

    public void add_item(Food_item food_item,Menu menu){ //has to be done only by admin
        List<Food_item> list=menu.getList();
        list.add(food_item);
    }
    public void remove_item(Food_item food_item,Menu menu){ // has to be done only by admin
        List<Food_item> list=menu.getList();
        list.remove(food_item);
    }
    public void update_item(Food_item food_item,Menu menu){
        List<Food_item> list=menu.getList();
        System.out.println("Update item:\n");
        System.out.println("1.Price");
        System.out.println("2.Category");
        System.out.println("3.Availability");
        int choice=scanner.nextInt();
        if(choice==1){
            System.out.println("Enter new Price:");
            food_item.setPrice(scanner.nextDouble());
        }
        else if(choice==2){
            System.out.println("Enter new Category:");
            food_item.setCategory(scanner.next());
        }
        else if(choice==3){
            System.out.println("Update Availability:");
            food_item.setStock(scanner.nextInt());
        }
    }

}
