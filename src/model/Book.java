package model;

public class Book {
    private int bookId;
    private String name;
    private String authorName;
    private String category;
    private double price;
    private BookStatus status;
    private String edition;

    public Book(int bookId, String name, String authorName, String category, double price, String edition) {
        this.bookId = bookId;
        this.name = name;
        this.authorName = authorName;
        this.category = category;
        this.price = price;
        this.status = BookStatus.AVAILABLE;
        this.edition = edition;
    }

    public int getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public BookStatus getStatus() {
        return status;
    }

    public String getEdition() {
        return edition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    @Override
    public String toString() {
        return "Kitap ID: " + bookId + ", İsim: " + name + ", Yazar: " + authorName + ", Kategori: " + category + ", Fiyat: " + price + ", Durum: " + status;
    }
}