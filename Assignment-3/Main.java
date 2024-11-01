import java.util.*;

public class Main {
    //List<Review> reviews;
    public static List<Review> reviews = new ArrayList<>();
    public static Queue<Order> regular_order = new LinkedList<>();
    public static Queue<Order> vip_order = new LinkedList<>();
    static TreeMap<String, String> database = new TreeMap<>();
    static  Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Menu menu = new Menu();
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
                    admin_functionalities();
                } else if (database.get(name).equals("Customer")) {
                    System.out.println("Logged in as Customer");
                    customer_functionalities();
                }
            }
        }


        public static void customer_functionalities () {
            //customer_functionalities
        }

        public static void admin_functionalities () {
                //admin_functionalities
        }


}
