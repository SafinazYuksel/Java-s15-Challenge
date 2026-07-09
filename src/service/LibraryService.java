package service;

import model.Book;
import java.util.List;

public interface LibraryService {
    void addBook(Book book);
    void updateBook(int bookId, Book updatedBook);
    void deleteBook(int bookId);
    Book searchById(int bookId);
    List<Book> searchByName(String name);
    List<Book> searchByAuthor(String authorName);
    List<Book> getBooksByCategory(String category);
    void borrowBook(int bookId, String readerId);
    void returnBook(int bookId, String readerId);
    void showAllBooks();
    void showAllReaders();
}