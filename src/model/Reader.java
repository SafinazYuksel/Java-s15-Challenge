package model;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {
    private List<Book> borrowedBooks;
    private double totalFine;
    private static final int MAX_BOOKS = 5;

    public Reader(String id, String name, String email) {
        super(id, name, email);
        this.borrowedBooks = new ArrayList<>();
        this.totalFine = 0.0;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public double getTotalFine() {
        return totalFine;
    }

    public boolean canBorrow() {
        return borrowedBooks.size() < MAX_BOOKS;
    }

    public void borrowBook(Book book) {
        if (canBorrow()) {
            borrowedBooks.add(book);
        }
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public void addFine(double amount) {
        this.totalFine += amount;
    }

    public void payFine(double amount) {
        this.totalFine -= amount;
    }

    @Override
    public void whoYouAre() {
        System.out.println("Ben bir okuyucuyum: " + getName());
    }
}