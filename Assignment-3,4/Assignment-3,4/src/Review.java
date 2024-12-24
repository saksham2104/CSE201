import java.io.Serializable;

public class Review implements Serializable {
    private Customer author;
    private String description;
    private double Rating;

    public Review(Customer author, String content,double Stars) {
        this.Rating = Stars;
        this.author = author;
        this.description = content;
        Main.reviews.add(this);
    }

    public double getRating() {
        return Rating;
    }

    public void setAuthor(Customer author) {
        this.author = author;
    }

    public Customer getCustomer(){
        return author;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setRating(double rating) {
        this.Rating = rating;
    }

    public Customer getAuthor() {
        return author;
    }
    public String getDescription() {
        return description;
    }


}
