package repository;

import model.Book;
import model.Reader;
import java.util.*;

public class LibraryRepository {
    private Map<Integer, Book> booksById;
    private List<Book> allBooks;
    private Set<String> categories;
    private Map<Reader, List<Book>> borrowedBooksByReader;

    public LibraryRepository() {
        this.booksById = new HashMap<>();
        this.allBooks = new ArrayList<>();
        this.categories = new HashSet<>();
        this.borrowedBooksByReader = new HashMap<>();
    }

    public void addBook(Book book) {
        booksById.put(book.getBookId(), book);
        allBooks.add(book);
        categories.add(book.getCategory());
    }

    public Book getBookById(int bookId) {
        return booksById.get(bookId);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(allBooks);
    }

    public Set<String> getAllCategories() {
        return new HashSet<>(categories);
    }

    public List<Book> getBooksByCategory(String category) {
        List<Book> result = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                result.add(book);
            }
        }
        return result;
    }

    public void recordBorrow(Reader reader, Book book) {
        borrowedBooksByReader.putIfAbsent(reader, new ArrayList<>());
        borrowedBooksByReader.get(reader).add(book);
    }

    public void recordReturn(Reader reader, Book book) {
        List<Book> books = borrowedBooksByReader.get(reader);
        if (books != null) {
            books.remove(book);
        }
    }

    public Map<Reader, List<Book>> getBorrowedBooksByReader() {
        return borrowedBooksByReader;
    }
}