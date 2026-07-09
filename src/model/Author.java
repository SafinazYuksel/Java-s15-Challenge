package model;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person {
    private List<Book> books;

    public Author(String id, String name, String email) {
        super(id, name, email);
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public void whoYouAre() {
        System.out.println("Ben bir yazarım: " + getName());
    }
}