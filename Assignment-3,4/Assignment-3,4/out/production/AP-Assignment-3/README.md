HOW TO RUN THE CODE:
1)Unzip the zip file
2)Open the main class
3)Compile normal java code on any IDE preferably intellij


FLOW OF THE CODE:
1)In this Assignment I have firstly added basic login and signup methods for both Customer and Admin classes then I
present each of the functionalities to the user based on their login as Customer or Admin

CUSTOMER:
1)For a customer I ask them whether they are a VIP yet or not and I charge them 30 rupees for the same if they agree to by adding a total of 3- to their cart
Next jumping on to each of the functionalities I maintain a global static list for the Menu
2)I do a basic search for any food item based on their name maintaining a Hashmap for logarithmic search
3)Next for filtering via category i just print a simple list whilst comparing the categories
4)For the price sorting i use a food_sorter class implementing the Comparatable Interface in JAVA
5)I maintain another class for  Cart which has its basic attributes like a list storing all the food_items i have ordered yet
6)I support all of the following functionalities:-
    Add items: Customers can add multiple items to their cart, specifying the quantity for each.
    – Modify quantities: Customers can adjust the quantity of items in their cart before checking out.
    – Remove items: Customers can remove items from their cart if they change their mind.
    – View total: Customers can view the total price of all items in their cart before finalizing the order.
    – Checkout process: Customers can complete their order by providing payment and delivery details.
7)I let the user track their order via telling them their order status which is by default order recieved and can be updated only by the admin
I support the following for Order tracking too:
– View order status: Customers can track the status of their orders in real time, from ’order received’
to ’delivered’, or ’denied’.
– Cancel order: Customers can cancel their orders before they are prepared or processed.
– Order history: Customers can view their past orders and re-order previous meals if desired.
8)I let a user add reviews and view reviews left by other customers


ADMIN:

1)An admin can add and remove and update an item from the menu there is some issue with this as menu is getting printed 3-4 times 
2) I have handled order management on a priority basis using Dequeue data structure which allows for the functionality of addfirst andLast in java
3) I handle refunds by setting their carts to negative of order value since that way when they order in future they dont have to worry!
4)I generate a daily report tracking most popular item using a Hashmap and printing the total sales average order value and such

Side Note: The menu error was persistent I have spent a day trying to debugging myself without using any AI tools I hope that fetches me some points for effort :D

Collections used:
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

These are mostly all of the collections I have used in my Assignment.



GUI:
 I display menu and pending orders on a simple GUI display integrate them via a back button 

File I/O :
    I manage carts and Order history via File I/O and serialisation 

Junit Tests:
  I have added basic Junit Tests for Item Out of Stock and Invalid Logins 






