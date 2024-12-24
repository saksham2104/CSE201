import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Main implements Serializable {

    public static List<Review> reviews = new ArrayList<>();
    public static Deque<Order> pending_orders = new LinkedList<>();
    public static Deque<Order> regular_orders = new LinkedList<>();
    public static Deque<Order> vip_orders = new LinkedList<>();
    static TreeMap<String, String> database = new TreeMap<>();
    public static List<Food_item>menu=new ArrayList<>();
    private transient static Scanner scanner = new Scanner(System.in);
    public static HashMap<Integer,Order>Orders=new HashMap<>();
    public static HashMap<String,Food_item>FoodItems=new HashMap<>();
    public static HashMap<String,String>verify=new HashMap<>();


    public static void main(String[] args) throws InvalidLoginException {
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
        System.out.println("Current working directory: " + System.getProperty("user.dir"));

        while (true) {
            System.out.println("1.View GUI\n 2.CLI\n 3.Exit");
            int c = scanner.nextInt();

            if (c == 1){
                //GUI
                // Main Frame
                JFrame mainFrame = new JFrame("Canteen Management System");
                mainFrame.setSize(400, 300);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.setLayout(new BorderLayout());

                // Panel for Buttons
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));

                // Buttons
                JButton viewMenuButton = new JButton("View Menu");
                JButton viewPendingOrdersButton = new JButton("View Pending Orders");

                // Add buttons to panel
                buttonPanel.add(viewMenuButton);
                buttonPanel.add(viewPendingOrdersButton);

                // Add panel to frame
                mainFrame.add(buttonPanel, BorderLayout.CENTER);

                // Action Listeners for Buttons
                viewMenuButton.addActionListener(e -> openMenuScreen());
                viewPendingOrdersButton.addActionListener(e -> openPendingOrdersScreen());

                // Display the main frame
                mainFrame.setVisible(true);

            }

            else if(c == 3){
                break;
            }
            else if(c == 2){
                System.out.println("Entering CLI Method");
            }

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
    private static void openMenuScreen () {
        // Create Frame
        JFrame frame = new JFrame("Canteen Menu");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Table to Display Menu
        String[] columns = {"Name", "Category", "Price", "Stock"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable menuTable = new JTable(tableModel);
        menuTable.setEnabled(false); // Make table non-editable

        // Populate Table with Menu Items
        for (Food_item item : Main.menu) { // Use your global `menu` list
            Object[] row = {item.getName(), item.getCategory(), item.getPrice(), item.getStock()};
            tableModel.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(menuTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static void openPendingOrdersScreen () {
        // Create Frame
        JFrame frame = new JFrame("Pending Orders");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        // Table to display pending orders
        String[] columnNames = {"Order ID", "Items Ordered", "Order Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        // Populate table with pending orders data
        for (Order order : Main.pending_orders) { // Use your global `pending_orders`
            String orderNumber = String.valueOf(order.getOrderId());
            StringBuilder itemsOrdered = new StringBuilder(); // Helper to format items
            List<Food_item> list = order.getCustomer().getCart().getList(); // Get items from cart
            for (Food_item item : list) {
                itemsOrdered.append(item.getName()).append(", ");
            }
            String status = order.getOrder_status();
            tableModel.addRow(new String[]{orderNumber, itemsOrdered.toString(), status});
        }

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
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
                // create an order_history_username file
                //create a cart_username  file

                // Create order_history_username and cart_username files
                try {
                    String orderHistoryFileName = "order_history_" + name + ".txt";
                    String cartFileName = "cart_" + name + ".txt";

                    // Create files
                    File orderHistoryFile = new File(orderHistoryFileName);
                    File cartFile = new File(cartFileName);

                    if (orderHistoryFile.createNewFile()) {
                        System.out.println("File created: " + orderHistoryFileName);
                    } else {
                        System.out.println("File already exists: " + orderHistoryFileName);
                    }

                    if (cartFile.createNewFile()) {
                        System.out.println("File created: " + cartFileName);
                    } else {
                        System.out.println("File already exists: " + cartFileName);
                    }
                    // Debugging file creation locations
                    System.out.println("Order history file exists at: " + orderHistoryFile.getAbsolutePath());
                    System.out.println("Cart file exists at: " + cartFile.getAbsolutePath());

                }
                catch (IOException e) {
                    System.out.println("An error occurred while creating files.");
                    e.printStackTrace();
                }

            }
    }

    private static void login() throws InvalidLoginException {
            System.out.println("Enter your Name:");
            String name = scanner.nextLine();
            System.out.println("Enter your Password:");
            String password = scanner.nextLine();

            if(!verify.get(name).equals(password) || !verify.containsKey(name)) {
                throw new InvalidLoginException("Invalid Username or Password");
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

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cart.ser"))) {
                oos.writeObject(customer.getCart()); // Serialize the object
              //  System.out.println("Cart has been serialized to cart.ser");
            } catch (IOException e) {
                e.printStackTrace();
            }

            Menu menu1=new Menu();

            menu1.setList(menu);
            while(true) {

                System.out.println("1.View Menu:");
                System.out.println("2.Search an Item:");
                System.out.println("3.Filters");
                System.out.println( "4.View order Status:");
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
                    System.out.println("2.Filter by Price(Lowest to Highest)\n");

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
                      customer.display_order_history();
                }


                else if (choice == 6) {
                    //PLACE ORDER  , //add delivery address and check out that takes you to the payment method inside cart class
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cart.ser"))) {
                        Cart cart = (Cart) ois.readObject(); // Deserialize the object
                        //write to this user's cart file
                        System.out.println(cart);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }

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

                    String cartFileName="cart_" + customer.getName() + ".txt";
                    List<Food_item>list1=customer.getCart().getList();
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(cartFileName,true))) {
                        writer.write("Cart for user: " + customer.getName() + "\n");
                        writer.write("===========================\n");
                        for(Food_item item:list1){
                            System.out.println(item.getName());
                        }
                        for (Food_item food : list1) {
                            writer.write("Item Name: " + food.getName() + "\n");
                            writer.write("Quantity: " + food.getQuantity() + "\n");
                            writer.write("Price per item: " + food.getPrice() + "\n");
                            writer.write("Total Price: " + (food.getPrice() * food.getQuantity()) + "\n");
                            writer.write("---------------------------\n");
                        }
                        writer.write("Cart Total: " + cart.getTotal() + "\n");
                        writer.flush();
                        System.out.println("Cart details updated in " + cartFileName);
                    } catch (Exception e) {
                        System.out.println("Error writing to cart file.");
                        e.printStackTrace();
                    }

                    if(customer.getStatus() == 0){
                        pending_orders.addLast(order);
                        regular_orders.add(order);
                    }
                    else if(customer.getStatus() == 1){
                        pending_orders.addFirst(order);  //queue principle not being followed well here :D
                        vip_orders.add(order);
                    }

                    //update_cart() i.e modify quantities of existing items or remove some items
                    System.out.println("Would you like to update your Cart ?");
                    System.out.println("1.Update Cart: ");
                    System.out.println("2. Proceed to Checkout: ");
                    int c=scanner.nextInt();
                    // Code for Updating or Removing Items
                    System.out.println("Would you like to update your Cart?");
                    System.out.println("1. Update Cart");
                    System.out.println("2. Proceed to Checkout");
                    int cc = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (cc == 1) {
                        System.out.println("1. Modify Quantity of Existing Item");
                        System.out.println("2. Remove an Existing Item");
                        int ch = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (ch == 1) {
                            System.out.println("Enter Item Name to Modify Quantity:");
                            String itemName = scanner.nextLine();
                            Food_item food = search_item_by_name(itemName);
                            if (food != null) {
                                System.out.println("Enter New Quantity:");
                                int newQuantity = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                int difference = newQuantity - food.getQuantity();
                                food.setQuantity(newQuantity);
                                cart.setTotal(cart.getTotal() + (difference * food.getPrice()));
                                System.out.println("Quantity Updated.");
                                updateCartFile(customer, customer.getName()); // Rewrite the cart file
                            } else {
                                System.out.println("Item not found in cart.");
                            }
                        } else if (ch == 2) {
                            System.out.println("Enter Item Name to Remove:");
                            String itemName = scanner.nextLine();
                            Food_item food = search_item_by_name(itemName);
                            if (food != null) {
                                cart.getList().remove(food);
                                cart.setTotal(cart.getTotal() - (food.getPrice() * food.getQuantity()));
                                System.out.println("Item Removed.");
                                updateCartFile(customer, customer.getName()); // Rewrite the cart file
                            } else {
                                System.out.println("Item not found in cart.");
                            }
                        }
                    }
                    else if ( cc == 2) {

                        System.out.println("Proceeding to Checkout:");
                        System.out.print("Enter Special Requests: ");
                        String special_requests = scanner.nextLine();

                        System.out.print("Add Delivery Address");
                        String address = scanner.nextLine();

                        System.out.println("1.Checkout");
                        System.out.println("2.Exit");
                        int choice1 = scanner.nextInt();
                        scanner.nextLine();
                        if (choice1 == 1) {
                            //Checkout
                            payment(customer, customer.getCart().getTotal());
                            System.out.println("1.Exit");
                            System.out.println("2.Return to Main Menu");
                            int choice2 = scanner.nextInt();
                            scanner.nextLine();
                            if (choice2 == 1) {
                                System.out.println("Your Order has been placed with Order ID:" + order_id);
                                break;
                            }
                            if (choice2 == 2) {
                                System.out.println("Your Order has been placed with Order ID:" + order_id);
                                System.out.println("Returning to Main Menu");
                            }
                        } else if (choice1 == 2) {
                            System.out.println("Sad to see you go :D");
                            cart.setTotal(0);
                        }
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
    private static void updateCartFile(Customer customer, String customerName) {
        Cart cart=customer.getCart();
        String cartFileName = "cart_" + customerName + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cartFileName))) {
            writer.write("Cart for user: " + customerName + "\n");
            writer.write("===========================\n");
            List<Food_item>list=cart.getList();
            for(Food_item item:list){
                System.out.println(item.getName());
            }
            for (Food_item food : list) {
                writer.write("Item Name: " + food.getName() + "\n");
                writer.write("Quantity: " + food.getQuantity() + "\n");
                writer.write("Price per item: " + food.getPrice() + "\n");
                writer.write("Total Price: " + (food.getPrice() * food.getQuantity()) + "\n");
                writer.write("---------------------------\n");
            }
            writer.write("Cart Total: " + cart.getTotal() + "\n");
            writer.flush();
            System.out.println("Cart file rewritten: " + cartFileName);
        } catch (Exception e) {
            System.out.println("Error rewriting cart file.");
            e.printStackTrace();
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
                        //the logic here is slightly off first print all VIP then all normal which is fune but ordering among VIP is not fine

                        JFrame frame = new JFrame("Pending Orders");
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setSize(600, 400);

                        // Table to display pending orders
                        String[] columnNames = {"Order ID", "Items Ordered", "Order Status"};
                        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
                        JTable table = new JTable(tableModel);

                        // Populate table with pending orders data
                        for (Order order : pending_orders) {
                            String orderNumber = String.valueOf(order.getOrderId());
                            String itemsOrdered=""; // Helper function to format items
                            List<Food_item>list=order.getCustomer().getCart().getList();
                            for(Food_item item:list){
                                itemsOrdered+=item.getName()+" , "+"\n";
                            }
                            String status = order.getOrder_status();
                            tableModel.addRow(new String[]{orderNumber, itemsOrdered, status});
                        }

                        // Add table to scroll pane
                        JScrollPane scrollPane = new JScrollPane(table);
                        frame.add(scrollPane, BorderLayout.CENTER);

                        // Show the GUI
                        frame.setVisible(true);

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
                               //add order to order history
                               order.getCustomer().getOrder_history().add(order);
                               Customer customer=order.getCustomer();Cart cart=customer.getCart();
                               String orderHistoryFileName = "order_history_" + customer.getName() + ".txt";

                               try (BufferedWriter writer = new BufferedWriter(new FileWriter(orderHistoryFileName, true))) {
                                   writer.write("Order ID: " + order_id + "\n");
                                   writer.write("Customer Name: " + customer.getName() + "\n");
                                   writer.write("Items Ordered:\n");
                                    List<Food_item>list=cart.getList();
                                   for (Food_item food : list) {
                                       writer.write("  - Item Name: " + food.getName() + "\n");
                                       writer.write("    Quantity: " + food.getQuantity() + "\n");
                                       writer.write("    Price per item: " + food.getPrice() + "\n");
                                       writer.write("    Total Price: " + (food.getPrice() * food.getQuantity()) + "\n");
                                   }

                                   writer.write("Special Requests: " + order.special_requests + "\n");
                                   writer.write("Order Total: " + cart.getTotal() + "\n");
                                   writer.write("-----------------------------\n");
                                   writer.flush();
                                   System.out.println("Order details added to " + orderHistoryFileName);
                               } catch (Exception e) {
                                   System.out.println("Error writing to order history file.");
                                   e.printStackTrace();
                               }

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
                    for(Order order:pending_orders) {
                        if ((!order.getOrder_status().equalsIgnoreCase("denied"))) {
                            count++;
                            Customer customer2 = order.getCustomer();
                            Cart cart = customer2.getCart();
                            for (Food_item item : cart.list) {
                                map.put(item, map.getOrDefault(item, 0) + 1);//checking for NPE
                                System.out.println(item.getName() + "        " + "x" + item.getQuantity());
                                total += item.getPrice() * item.getQuantity();
                            }
                        }

                        System.out.println("Total Sales: " + total);
                        System.out.println("Total Orders: " + count); //make it pretty add avg order value and stuff
                        double avg = total / count;
                        System.out.println("Average Order Value today: " + avg);
                        Food_item most_popular = null;
                        int mx = 0;
                        for (Map.Entry<Food_item, Integer> entry : map.entrySet()) {
                            if (entry.getValue() > mx) {
                                mx = entry.getValue();
                                most_popular = entry.getKey();
                            }
                        }
                        System.out.println("Most popular Food item today was: " + most_popular.getName() + "  which sold " + (mx + 1) + " units" + "\n");
                        //can add try catch block for NPE

                    }
                }
                else if (choice == 4) {
                    System.out.println("Goodbye");
                    break;
                }
            }
        }
}
