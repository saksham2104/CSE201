import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Food_item_sorter implements Comparator<Food_item>{
    Scanner scanner =new Scanner(System.in);
    @Override
    public int compare(Food_item f1, Food_item f2) {
        return Double.compare(f1.getPrice(), f2.getPrice());
    }

    public void display_sorted_prices(Menu menu){
        //display food items sorted via price
        List<Food_item> items=menu.getList();
        Collections.sort(items,this);
    }

    public void display_sorted_categories(Menu menu){
        String choice=scanner.nextLine();
        List<Food_item> items=menu.getList();
        for(Food_item item:items){
            if(item.category.equals(choice)){
                System.out.println(item +"\n");
            }
        }
    }
    
}
