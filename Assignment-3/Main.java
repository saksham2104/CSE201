import java.time.LocalDate;
import java.util.*;
import java.util.ArrayList;


public class Main {

    public static List<Review> reviews = new ArrayList<>();
    public static Deque<Order> pending_orders = new LinkedList<>();
    public static Deque<Order> regular_orders = new LinkedList<>();
    public static Deque<Order> vip_orders = new LinkedList<>();
    static TreeMap<String, String> database = new TreeMap<>();
    public static List<Food_item>menu=new ArrayList<>();
    static  Scanner scanner = new Scanner(System.in);
    public static HashMap<Integer,Order>Orders=new HashMap<>();
    public static HashMap<String,Food_item>FoodItems=new HashMap<>();
    public static HashMap<String,String>verify=new HashMap<>();


    public static void main(String[] args) {
        Menu menu1 = new Menu();

        Food_item food_1 = new Food_item("Samosa", "Snacks", 10.00, 5);
        menu.add(food_1);
        FoodItems.put(food_1.getName(), food_1);
        Food_item food_2 = new Food_item("Veg Patties", "Snacks", 20.00, 5);
        menu.add(food_2);
        FoodItems.put(food_2.getName(), food_2);
        Food_item food_3 = new Food_item("Honey Chilli Potato", "Snacks", 40.00, 5);
        menu.add(food_3);
        FoodItems.put(food_3.getName(), food_3);
        Food_item food_4 = new Food_item("Coca-Cola", "Beverages", 20.00, 5);
        menu.add(food_4);
        FoodItems.put(food_4.getName(), food_4);
        Food_item food_5 = new Food_item("Limca", "Beverages", 20.00, 5);
        menu.add(food_5);
        FoodItems.put(food_5.getName(), food_5);
        Food_item food_6 = new Food_item("Veg Thali", "Meals", 40.00, 5);
        menu.add(food_6);
        FoodItems.put(food_6.getName(), food_6);
        Food_item food_7 = new Food_item("Non Veg Thali", "Meals", 50.00, 5);
        menu.add(food_7);
        FoodItems.put(food_7.getName(), food_7);
        Food_item food_8 = new Food_item("Cake", "Desserts", 15.00, 5);
        menu.add(food_8);
        FoodItems.put(food_8.getName(), food_8);

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
    }

    private static void signup () {
            System.out.println("Are you a:\n 1.Admin\n 2.Customer\n");
            int choice1 = scanner.nextInt();
            scanner.nextLine();
            if (choice1 == 1) {
                System.out.println("Enter your Name:");
                String name = scanner.nextLine();
                System.out.println("Enter your Password:");
                String password = scanner.nextLine();
                verify.put(name,password);
                database.put(name, "Admin");
                Admin admin = new Admin(name);
                User.addAdmin(name, admin);
            } else if (choice1 == 2) {
                System.out.println("Enter your Name:");
                String name = scanner.nextLine();
                System.out.println("Enter your Password:");
                String password = scanner.nextLine();
                verify.put(name,password);
                database.put(name, "Customer");
                List<Food_item> list = new ArrayList<>();
                Customer customer = new Customer(name, list, 0);
                User.addCustomer(name, customer);
            }
    }

    private static void login () {
            System.out.println("Enter your Name:");
            String name = scanner.nextLine();
            System.out.println("Enter your Password:");
            String password = scanner.nextLine();
            if(!verify.get(name).equals(password)) {
                System.out.println("Invalid Login");
                System.out.println("Try Again:");
                return;
            }
            if ((verify.get(name).equals(password)) && database.containsKey(name)) {
                if (database.get(name).equals("Admin")) {
                    System.out.println("Logged in as Admin");
                    Admin admin=User.getAdmin(name);
                    admin_functionalities(admin);
                }
                else if (database.get(name).equals("Customer")) {
                    System.out.println("Logged in as Customer");
                    List<Food_item>list=new ArrayList<>();
                    Customer customer=User.getCustomer(name);

                    if(customer.getStatus() == 0){
                        System.out.println("You are a Regular Customer,Upgrade to a VIP Customer by Just 30 Bucks for 3 months");
                        System.out.println("1.Yes\n2.NO");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        if (choice == 1) {
                            System.out.println("Great Choice !");
                            customer.setStatus(1);
                            Cart cart =customer.getCart();
                            cart.setTotal(30.00);
                        }
                        else if (choice == 2) {
                            System.out.println("No Worries !");
                        }
                    }
                    customer_functionalities(customer);
                }
            }
    }

    public static Food_item search_item_by_name(String name) {
        if(FoodItems.containsKey(name)){
            return FoodItems.get(name);
        }
        return null;
    }

    public static Order search_order_by_id(int order_id){
        if(Orders.containsKey(order_id)){
            return Orders.get(order_id);
        }
        return null;
    }


    public static void payment(Customer customer,double amount){

        //calling this function should take user to payment getaway

        Cart cart=customer.getCart();
        if(cart.getTotal() < 0){
            cart.setTotal(cart.getTotal()+amount);
            System.out.println("You were refunded by the admin for a previous bad experience this one is free for YOU!");
            return;
        }
        if(cart.getTotal() == 0){
            System.out.println("Your cart Total is 0.00 !");
            return;
        }

        System.out.println("Choose payment method:\n");
        System.out.println("1.Cash\n");
        System.out.println("2.UPI\n");
        System.out.println("3.Card\n");
        int choice=scanner.nextInt();

        if(choice==1){
            System.out.println("Pay cash amount:"+cart.total);
        }
        else if(choice==2){
            System.out.println("Choose among the following:\n");
            System.out.println("1.Paytm \n");
            System.out.println("2.Google Pay \n");
            System.out.println("3.Phone Pe \n");
            System.out.println("4.Others \n");

            int cho= scanner.nextInt();
            scanner.nextLine();
            System.out.println("Pay total:"+cart.total);
        }
        else if(choice==3){
            System.out.println("Choose among the following:\n");
            System.out.println("1.MasterCard \n");
            System.out.println("2.Visa \n");
            int cho= scanner.nextInt();
            scanner.nextLine();
            System.out.println("Pay total:"+cart.total);
        }

    }


        public static void customer_functionalities (Customer customer) {
            //customer_functionalities

            Menu menu1=new Menu();

            menu1.setList(menu);
            while(true) {

                System.out.println("1.View Menu:");
                System.out.println("2.Search an Item:");
                System.out.println("3.Filters");
                System.out.println("4.View order Status:");
                System.out.println("5.View Order History:");
                System.out.println("6.Place Order:");
                System.out.println("7.Cancel Order:");
                System.out.println("8.Add reviews");
                System.out.println("9.Exit");
                int choice=scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) {
                    //VIEW MENU
                    for(Food_item item:menu){
                        System.out.println("Item Name: "+ item.getName()+"     "+"Price: "+item.getPrice()+ "    "+   "Availability:"+item.getStock()+"\n");
                    }
                }
                else if (choice == 2) {
                    //SEARCH BUTTON
                    System.out.println("Enter Item:");
                    String item = scanner.nextLine();
                    int flag=1;
                    for(Food_item item2:menu){
                        if(item2.getName().equalsIgnoreCase(item)){
                            System.out.println("Item Name: " + item2.getName()+"   " + "Price:"+item2.getPrice()+"  "+"Availability: "+item2.getStock()+"\n");
                            flag=0;
                            break;
                        }
                    }
                    if(flag == 1){
                        System.out.println("Item not found");
                    }
                }
                else if (choice == 3) {
                    //FILTERS
                    System.out.println("1.Filter by Category:\n");
                    System.out.println("2.Filter by Price(Lowest to Highest\n");

                    int choice2 = scanner.nextInt();
                    scanner.nextLine();
                   if (choice2 == 1) {
                      //filter via category
                        Food_item_sorter.display_sorted_categories(menu1);
                    }
                   else {
                        //sort via prices ascending
                        Food_item_sorter.display_sorted_prices_descending(menu1);
                   }
                }
                else if (choice == 4) {
                     //VIEW ORDER STATUS
                    for(Order order:pending_orders){
                        Customer customer1=order.getCustomer();
                        if(customer1.getName().equals(customer.getName())){
                            System.out.println("Order Status:"+order.getOrder_status());
                        }
                    }
                }
                else if (choice == 5) {
                    //ORDER_HISTORY
                    for(Food_item item: customer.getOrder_history()){
                        System.out.println("Item Name: " +item.getName() + " Item Price: "+item.getPrice()+ "   "+  "Quantity: "+item.getQuantity());
                    }
                }


                else if (choice == 6) {
                    //PLACE ORDER  , //add delivery address and check out that takes you to the payment method inside cart class
                    System.out.println("What would you like to have Today:");
                    int order_id=pending_orders.size()+1;
                    Order order=new Order(order_id,customer.getName(),customer);
                    Cart cart=customer.getCart();
                    System.out.println("Place Order:");
                    List<Food_item>list=new ArrayList<>();
                    System.out.println("Enter number of Food items you want to have");
                    int number=scanner.nextInt();
                    scanner.nextLine();
                    for(int i=0;i<number;i++){
                        System.out.println("Enter Item Name:");
                        String item = scanner.nextLine();
                        System.out.println("Enter Item Quantity:");
                        int quantity=scanner.nextInt();
                        scanner.nextLine();
                        Food_item food=search_item_by_name(item);
                        list.add(food);
                        cart.setTotal(cart.getTotal()+food.getPrice()*quantity);
                        food.setQuantity(quantity);
                    }
                    cart.setList(list);
                    Orders.put(order_id,order);

                    if(customer.getStatus() == 0){
                        pending_orders.addLast(order);
                        regular_orders.add(order);
                    }
                    else if(customer.getStatus() == 1){
                        pending_orders.addFirst(order);
                        vip_orders.add(order);
                    }

                    System.out.println("Add Delivery Address");
                    String address=scanner.nextLine();

                    System.out.println("Proceed to Checkout:");
                    System.out.println("1.Checkout");
                    System.out.println("2.Exit");
                    int choice1=scanner.nextInt();
                    scanner.nextLine();
                    if(choice1 == 1){
                        //Checkout
                        payment(customer,customer.getCart().getTotal());
                        System.out.println("1.Exit");
                        System.out.println("2.Return to Main Menu");
                        int choice2=scanner.nextInt();
                        scanner.nextLine();
                        if(choice2 == 1){
                            System.out.println("Your Order has been placed with Order ID:"+order_id);
                            break;
                        }
                        if(choice2 == 2){
                            System.out.println("Your Order has been placed with Order ID:"+order_id);
                            System.out.println("Returning to Main Menu");
                        }
                    }
                    else if(choice1 == 2){
                        System.out.println("Sad to see you go :D");
                        cart.setTotal(0);
                    }
                }
                else if(choice == 7){
                    //CANCEL ORDER
                    System.out.println("Cancelling Order:");
                    System.out.println("Enter Order ID:");
                    int order_id=scanner.nextInt();
                    scanner.nextLine();
                    Order order =search_order_by_id(order_id);
                    pending_orders.remove(order);
                    //remove form vip and regular queue as well(slightly complicated)
                    if(order.getCustomer().getStatus() == 0){
                        //regular se hatao
                        regular_orders.remove(order);
                    }
                    else if(order.getCustomer().getStatus() == 1){
                        //remove from vip
                        vip_orders.remove(order);
                    }
                    System.out.println("Your Order with Order ID "+order_id+" has been cancelled");
                    System.out.println("Returning to Main Menu");
                }
                else if(choice == 8){
                    //Add review by customer
                    System.out.println("1.Add review for a previous item you ordered");
                    System.out.println("2.View Other's Reviews");

                    int choice1=scanner.nextInt();
                    scanner.nextLine();
                    if(choice1 == 1){
                        System.out.println("Enter a food item : ");
                        String item=scanner.nextLine();
                        //leave a review
                        System.out.println("Give Review Description:");
                        String description=scanner.nextLine();
                        System.out.println("Add Rating (1-10)");
                        double rating=scanner.nextDouble();
                        scanner.nextLine();
                        Review review=new Review(customer,description,rating);
                    }


                    else if(choice1 == 2){
                        System.out.println("View All Reviews: ");
                        for(Review review:reviews){
                            System.out.println("Name of the Customer: "+ review.getCustomer().getName());
                            System.out.println("Description: "+review.getDescription());
                            System.out.println("Rating: "+review.getRating());
                        }
                    }

            }

                else if (choice == 9) {
                    System.out.println("Goodbye");
                    break;
                }
            }

        }

        public static void admin_functionalities (Admin admin) {
                //admin_functionalities
            Menu menu1=new Menu();
            menu1.setList(menu);

            while(true){
                System.out.println("Admin Functionalities\n");
                System.out.println("1. Menu Management:");
                System.out.println("2. Order Management:");
                System.out.println("3. Report Generation:");
                System.out.println("4. Exit:");

                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) {
                    //MENU MANAGEMENT
                    System.out.println("1.Add new items:");
                    System.out.println("2.Remove items:");
                    System.out.println("3.Update items:");
                    int choice2 = scanner.nextInt();
                    scanner.nextLine();
                    if (choice2 == 1) {
                        //ADD ITEM
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
                        FoodItems.put(food.getName(),food);
                        menu=menu1.getList();
                    }
                    else if (choice2 == 2) {
                        //REMOVE ITEM
                        System.out.println("Enter item name:");
                        String name = scanner.nextLine();
                        Food_item food =search_item_by_name(name); //can improve search_by_name to hashmap for logarithmic
                        menu1.remove_item(food);
                        menu=menu1.getList();
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
                        Food_item item = search_item_by_name(name);
                        System.out.println("What do you want to update:");
                        System.out.println("1.Update item name:");
                        System.out.println("2.Update item price:");
                        System.out.println("3.Update item stock:");
                        System.out.println("4.Update item category:");
                        int choice3 = scanner.nextInt();
                        scanner.nextLine();
                        if (choice3 == 1) {
                            System.out.println("Enter new name for this item:");
                            String namee=scanner.nextLine();
                            item.setName(namee);
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
                         //make it pretty like customer name : cart contains blah blah total value of order and so on
                        int sz= pending_orders.size();
                        for(Order order: pending_orders){
                            System.out.println("Name of the Customer: "+order.getCustomer().getName() +" Order ID: "+order.getOrderId()+" Status: " + order.getOrder_status());
                        }
                    }
                    else if (choice4 == 2) {
                        //update order status for a particular order
                        System.out.println("Enter Order ID:");
                        int order_id = scanner.nextInt();
                        scanner.nextLine();
                        Order order = search_order_by_id(order_id);
                           System.out.println("Enter new order status");
                           String order_status=scanner.nextLine();

                           order.setOrder_status(order_status);
                           List<Food_item>items=order.getCustomer().getCart().getList();
                           if(order.getOrder_status().equalsIgnoreCase("Delivered")){
                               order.getCustomer().setOrder_history(items);
                           }
                    }
                    else if (choice4 == 3) {
                        //process refund,give refund for a particular kind of order say an order with bad feedback
                        for(Review review:reviews){
                            if(review.getRating()<= 1){
                                Customer customer=review.getCustomer();
                                Cart cart=customer.getCart();
                                cart.setTotal(-cart.getTotal()); //since this is there total it is equivalent to being refunded
                            }
                        }
                    }
                }
                else if (choice == 3) {
                    //report generation mei total sales most popular item and total orders
                    System.out.println("Viewing today's Sales Report:");
                    HashMap<Food_item,Integer> map= new HashMap<Food_item,Integer>(); //to track most popular item
                    // display all orders with today's date add their total display the total and most popular items
                    double total=0; int count=0;
                    for(Order order:pending_orders){
                        if(Objects.equals(order.order_date, LocalDate.now()) && !order.getOrder_status().equalsIgnoreCase("denied")){
                            count++;
                            Customer customer2=order.getCustomer();
                            Cart cart= customer2.getCart();
                            for(Food_item item: cart.list){
                                map.put(item,map.getOrDefault(item,0)+1);//checking for NPE
                                System.out.println(item.getName()+"        "+"x"+item.getQuantity());
                                total+=item.getPrice()* item.getQuantity();
                            }
                        }
                    }
                    System.out.println("Total Sales: "+total);
                    System.out.println("Total Orders: "+count); //make it pretty add avg order value and stuff
                    double avg=total/count;
                    System.out.println("Average Order Value today: "+avg);
                    Food_item most_popular = null;
                    int mx = 0;
                    for (Map.Entry<Food_item, Integer> entry : map.entrySet()) {
                        if (entry.getValue() > mx) {
                            mx = entry.getValue();
                            most_popular = entry.getKey();
                        }
                    }
                    System.out.println("Most popular Food item today was: "+most_popular.getName()+"  which sold "+(mx+1)+" units" +"\n");
                    //can add try catch block for NPE

                }
                else if (choice == 4) {
                    System.out.println("Goodbye");
                    break;
                }
            }
        }
}
