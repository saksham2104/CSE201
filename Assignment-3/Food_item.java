import java.util.Comparator;

public class Food_item {
    String name;
    String category;
    double price;
    int availability;
    int no_of_reviews;//reviews till date for this food item
    int quantity;

    public Food_item(String name, String category, double price, int availability, int no_of_reviews) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.availability = availability;
        this.no_of_reviews = no_of_reviews;
        this.quantity = 1;
    }
    //getters and setters

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailability() {
        return availability;
    }

    public int getNo_of_reviews() {
        return no_of_reviews;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public void setNo_of_reviews(int no_of_reviews) {
        this.no_of_reviews = no_of_reviews;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
