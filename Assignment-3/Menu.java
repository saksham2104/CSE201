import java.util.ArrayList;
import java.util.List;

public class Menu {
    //prepopulate food_items data using ArrayList
    private List<Food_item> list = new ArrayList<Food_item>();

    public Menu(){
        this.list=new ArrayList<>();
        //initialize(); //method to initialize menu
    }

    public void initialize(){
        Food_item food_1 = new Food_item("Samosa", "Snacks", 10.00, 5);
        Food_item food_2 = new Food_item("Veg Patties", "Snacks", 20.00, 5);
        Food_item food_3 = new Food_item("Honey Chilli Potato", "Snacks", 40.00, 5);
        Food_item food_4 = new Food_item("Coca-Cola", "Beverages", 20.00, 5);
        Food_item food_5 = new Food_item("Limca", "Beverages", 20.00, 5);
        Food_item food_6 = new Food_item("Veg Thali", "Meals", 40.00, 5);
        Food_item food_7 = new Food_item("Non Veg Thali", "Meals", 50.00, 5);
        Food_item food_8 = new Food_item("Cake", "Desserts", 15.00, 5);

        this.list.add(food_1);
        this.list.add(food_2);
        this.list.add(food_3);
        this.list.add(food_4);
        this.list.add(food_5);
        this.list.add(food_6);
        this.list.add(food_7);
        this.list.add(food_8);
    }

    public void setList(List<Food_item> list) {
        this.list = list;
    }

    public void add_item(Food_item food_item){ //has to be done only by admin
        this.list.add(food_item);
    }
    public void remove_item(Food_item food_item){ // has to be done only by admin
        this.list.remove(food_item);
    }

    public List<Food_item> getList(){
        return this.list;
    }
}
