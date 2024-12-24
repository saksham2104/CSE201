import java.io.Serializable;
import java.util.HashMap;

class User implements Serializable {
    private static HashMap<String, Customer> customers = new HashMap<>();
    private static HashMap<String, Admin> admins = new HashMap<>();

    public static Customer getCustomer(String name) {
        return customers.get(name);
    }

    public static Admin getAdmin(String name) {
        return admins.get(name);
    }

    public static void addCustomer(String name, Customer customer) {
        customers.put(name, customer);
    }

    public static void addAdmin(String name, Admin admin) {
        admins.put(name, admin);
    }
}
