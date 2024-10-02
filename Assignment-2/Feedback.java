public class Feedback<T, U> {
    private T description; // Use generic type for description
    private U rating; // Use generic type for rating

    public Feedback(T description, U rating) {
        this.description = description;
        this.rating = rating;
    }

    public T getDescription() {
        return description;
    }

    public U getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "description=" + description +
                ", rating=" + rating +
                '}';
    }
}
