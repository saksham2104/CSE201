public class Food_item {
    int id;
    String name;
    String category;
    double price;
    int stock;
    int no_of_reviews;//reviews till date for this food item
    int quantity;

    public Food_item(String name,String category,double price,int availability) {
        this.id = Main.menu.size()+1;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = availability;
        this.no_of_reviews = 0;
        this.quantity = 1;
    }
    //getters and setters


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
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

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setNo_of_reviews(int no_of_reviews) {
        this.no_of_reviews = no_of_reviews;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
