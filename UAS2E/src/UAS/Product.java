package UAS;

public class Product {private int id;
    private String title;
    private int price;
    private double rating;
    public Product(int id, String title, String description, int price, double discountPercentage, double rating, int stock, String brand, String category, String thumbnail, String[] images) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Product: " +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", rating=" + rating;
    }
}
