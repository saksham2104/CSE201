public class Review {
    private String author;
    private String description;

    public Review(String author, String content) {
        this.author = author;
        this.description = content;
        Main.reviews.add(this);
    }
    public String getAuthor() {
        return author;
    }
    public String getDescription() {
        return description;
    }
  

}
