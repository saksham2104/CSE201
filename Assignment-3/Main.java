import java.time.LocalDate;
import java.util.*;
import java.util.ArrayList;


public class Main {

    public static List<Review> reviews = new ArrayList<>();
    public static Queue<Order> pending_orders = new LinkedList<>();
    public static Queue<Order> regular_orders = new LinkedList<>();
    public static Queue<Order> vip_orders = new LinkedList<>();
    static TreeMap<String, String> database = new TreeMap<>();
    public static List<Food_item>menu=new ArrayList<>();
    static  Scanner scanner = new Scanner(System.in);




    public static void main(String[] args) {
         Menu menu1 = new Menu();
        menu1.setList(menu);
        while (true) {
            System.out.println("1.Signup\n" + "2.Login\n" + "3.Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumes the newline character after nextInt
            if (choice == 1) {
                signup();
            } else if (choice == 2) {
                login();
            } else if (choice == 3) {
                System.out.println("Goodbye");
                break;
            }
        }
        Food_item food_1 = new Food_item("Samosa", "Snacks", 10.00, 5);
        Food_item food_2 = new Food_item("Veg Patties", "Snacks", 20.00, 5);
        Food_item food_3 = new Food_item("Honey Chilli Potato", "Snacks", 40.00, 5);
        Food_item food_4 = new Food_item("Coca-Cola", "Beverages", 20.00, 5);
        Food_item food_5 = new Food_item("Limca", "Beverages", 20.00, 5);
        Food_item food_6 = new Food_item("Veg Thali", "Meals", 10.00, 5);
        Food_item food_7 = new Food_item("Non Veg Thali", "Meals", 10.00, 5);
        Food_item food_8 = new Food_item("Cake", "Desserts", 10.00, 5);

        menu.add(food_1);
        menu.add(food_2);
        menu.add(food_3);
        menu.add(food_4);
        menu.add(food_5);
        menu.add(food_6);
        menu.add(food_7);
        menu.add(food_8);

    }


    private static void signup () {
            System.out.println("Are you a:\n 1.Admin\n 2.Customer\n");
            int choice1 = scanner.nextInt();
            scanner.nextLine();
            if (choice1 == 1) {
                String name = scanner.nextLine();
                database.put(name, "Admin");
            } else if (choice1 == 2) {
                //Customer functionalities
                String name = scanner.nextLine();
                database.put(name, "Customer");
            }
    }

    private static void login () {
            String name = scanner.nextLine();
            if (database.containsKey(name)) {
                if (database.get(name).equals("Admin")) {
                    System.out.println("Logged in as Admin");
                    Admin admin=new Admin(name);
                    admin_functionalities(admin);
                }
                else if (database.get(name).equals("Customer")) {
                    System.out.println("Logged in as Customer");
                    List<Food_item>list=new ArrayList<>();
                    Customer customer = new Customer(name,list,0);

                    if(customer.getStatus() == 0){
                        System.out.println("You are a Regular Customer,Upgrade to a VIP Customer by Just 30 Bucks for 3 months");
                        System.out.println("1.Yes\n 2.NO");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        if (choice == 1) {
                            Cart cart =customer.getCart();
                            cart.setTotal(30.00);
                        }
                        else if (choice == 2) {
                            System.out.println("No Worries!");
                        }
                    }
                    customer_functionalities(customer);
                }
            }
    }

    public static Food_item search_item_by_name(String name,Menu menu) {
        for(Food_item item:menu.getList()){
            if(item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }



        public static void customer_functionalities (Customer customer) {
            //customer_functionalities
            Menu menu1=new Menu();
            while(true) {
                System.out.println("Customer Functionalities\n");
                System.out.println("1.View Menu:");
                System.out.println("2.Search an Item:");
                System.out.println("3.Filters");
                System.out.println("4.View order Status:");
                System.out.println("5.View Order History:");
                System.out.println("6.Place Order:");
                System.out.println("7.Exit");
                int choice=scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) {
                    //VIEW MENU
                    for(Food_item item:menu){
                        System.out.println("Name" + item.getName() + "Price:"+item.getPrice()+"\n");

                    }
                }
                else if (choice == 2) {
                    //SEARCH BUTTON
                    System.out.println("Enter Item:");
                    String item = scanner.nextLine();
                    for(Food_item item2:menu){
                        if(item2.getName().equals(item)){
                            System.out.println("Name" + item2.getName() + "Price:"+item2.getPrice()+" "+item2.getStock()+"\n");
                        }
                    }
                }
                else if (choice == 3) {
                    //FILTERS
                    System.out.println("1.Filter by Category:\n");
                    System.out.println("2.Filter by Price(Lowest to Highest\n");
                    System.out.println("3.Filter by Price(Highest to Lowest\n");

                    int choice2 = scanner.nextInt();
                    scanner.nextLine();
                    if (choice2 == 1) {
                        //filter via category
                        //Food_item_sorter.display_sorted_categories();
                    }
                    else if (choice2 == 2) {
                        //sort via prices ascending
                    }
                    else {
                        //sort via prices descending

                    }
                }
                else if (choice == 4) {
                     //VIEW ORDER STATUS
                }
                else if (choice == 5) {
                    //ORDER_HISTORY
                    System.out.println(customer.getOrder_history());
                }
                else if (choice == 6) {
                    //PLACE ORDER
                    System.out.println("What would you like to have Today:\n");
                    Order order=new Order(customer.getName(),customer);
                    Cart cart=customer.getCart();
                    System.out.println("Place Order:");
                    List<Food_item>list=new ArrayList<>();
                    System.out.println("Enter number of Food items you want to have");
                    int number=scanner.nextInt();
                    scanner.nextLine();
                    for(int i=0;i<number;i++){
                        System.out.println("Enter Item:");
                        String item = scanner.nextLine();
                        Food_item food=search_item_by_name(item,menu1);
                        list.add(food);
                    }
                }
                else if (choice == 7) {
                    System.out.println("Goodbye");
                    break;
                }
            }

        }

        public static void admin_functionalities (Admin admin) {
                Menu menu1 = new Menu();
                menu1.setList(menu);
                //admin_functionalities
            while(true){
                System.out.println("Admin Functionalities\n");
                System.out.println("1. Menu Management:");
                System.out.println("2. Order Management:");
                System.out.println("3. Report Generation:");
                System.out.println("4. Exit:");

                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) {
                    //ADD ITEM
                    System.out.println("1.Add new items:");
                    System.out.println("2.Remove items:");
                    System.out.println("3.Update items:");
                    int choice2 = scanner.nextInt();
                    scanner.nextLine();
                    if (choice2 == 1) {
                        System.out.println("Enter item name:");
                        String name = scanner.nextLine();
                        System.out.println("Enter item price:");
                        double price = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("Enter item stock:");
                        int stock = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter item category:");
                        String category = scanner.nextLine();
                        Food_item food=new Food_item(name,category,price,stock);
                        menu1.add_item(food);
                    }
                    else if (choice2 == 2) {
                        //REMOVE ITEM
                        System.out.println("Enter item name:");
                        String name = scanner.nextLine();
                        Food_item food =search_item_by_name(name,menu1);
                        menu1.remove_item(food);
                        //change status of all order to denied iterate through both queues and change all status to denied
                        for(Order order: pending_orders){
                            Customer customer1=order.getCustomer();
                            Cart cart=customer1.getCart();
                            List<Food_item> list=cart.getList();
                            for(Food_item item:list){
                                if(item.getName().equals(name)){
                                    order.setOrder_status("denied");
                                }
                            }
                        }
                    }
                    else if (choice2 == 3) {
                        //UPDATE ITEM
                        System.out.println("Enter item name:");
                        String name = scanner.nextLine();
                        Food_item item = search_item_by_name(name,menu1);
                        System.out.println("What do you want to update:");
                        System.out.println("1.Update item name:");
                        System.out.println("2.Update item price:");
                        System.out.println("3.Update item stock:");
                        System.out.println("4.Update item category:");
                        int choice3 = scanner.nextInt();
                        scanner.nextLine();
                        if (choice3 == 1) {
                            System.out.println("Enter new name for this item:");
                            item.setName(name);
                        }
                        else if (choice3 == 2) {
                            System.out.println("Enter new price for this item:");
                            double price = scanner.nextDouble();
                            scanner.nextLine();
                            item.setPrice(price);
                        }
                        else if (choice3 == 3) {
                            System.out.println("Enter new stock for this item:");
                            int stock = scanner.nextInt();
                            scanner.nextLine();
                            item.setStock(stock);
                        }
                        else if (choice3 == 4) {
                            System.out.println("Enter new category for this item:");
                            String category = scanner.nextLine();
                            item.setCategory(category);
                        }

                    }
                }
                else if (choice == 2) {
                    //ORDER MANAGEMENT
                    System.out.println("1. View Pending Orders:");
                    System.out.println("2. Update Order Status:");
                    System.out.println("3. Process Refunds:");
                    //System.out.println("4. Handle Special Requests");
                    int choice4 = scanner.nextInt();
                    scanner.nextLine();
                    if (choice4 == 1) {
                        //view pending orders
                        System.out.println(pending_orders);
                    }
                    else if (choice4 == 2) {
                        //update order status for a particular order

                    }
                    else if (choice4 == 3) {
                        //process refund,give refund for a particular kind of order say an order with bad feedback
                    }

                }
                else if (choice == 3) {
                    //report generation mei total sales most popular item and total orders
                    System.out.println("Viewing today's Sales Report:");
                    HashMap<Food_item,Integer> map= new HashMap<Food_item,Integer>(); //to track most popular item
                    // display all orders with today's date add their total display the total and most popular items
                    double total=0;
                    for(Order order:pending_orders){
                        if(Objects.equals(order.order_date, LocalDate.now())){
                            Customer customer2=order.getCustomer();
                            Cart cart= customer2.getCart();
                            for(Food_item item: cart.list){
                                map.put(item,map.getOrDefault(item,0)+1);//checking for NPE
                                System.out.println(item.getName()+"\n");
                                total+=item.getPrice();
                            }
                        }
                    }
                    System.out.println("Total Sales:"+total+"\n");

                    Food_item most_popular = null;
                    int mx = 0;
                    for (Map.Entry<Food_item, Integer> entry : map.entrySet()) {
                        if (entry.getValue() > mx) {
                            mx = entry.getValue();
                            most_popular = entry.getKey();
                        }
                    }
                    System.out.println("Most popular Food item today was:"+most_popular.getName()+"which sold"+ mx+"units" +"\n");

                }
                else if (choice == 4) {
                    System.out.println("Goodbye");
                    break;
                }

            }

        }


}
