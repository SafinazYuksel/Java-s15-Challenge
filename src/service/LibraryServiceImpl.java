package service;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryServiceImpl implements LibraryService {
    private List<Book> books;
    private List<Reader> readers;
    private List<Invoice> invoices;
    private int nextBookId;
    private int nextInvoiceId;

    public LibraryServiceImpl() {
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
        this.invoices = new ArrayList<>();
        this.nextBookId = 1;
        this.nextInvoiceId = 1;
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public void updateBook(int bookId, Book updatedBook) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                book.setName(updatedBook.getName());
                book.setAuthorName(updatedBook.getAuthorName());
                book.setCategory(updatedBook.getCategory());
                book.setPrice(updatedBook.getPrice());
                book.setEdition(updatedBook.getEdition());
                break;
            }
        }
    }

    @Override
    public void deleteBook(int bookId) {
        books.removeIf(book -> book.getBookId() == bookId);
    }

    @Override
    public Book searchById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> searchByName(String name) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> searchByAuthor(String authorName) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthorName().toLowerCase().contains(authorName.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> getBooksByCategory(String category) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public void borrowBook(int bookId, String readerId) {
        Book book = searchById(bookId);
        Reader reader = findReaderById(readerId);

        if (book == null) {
            System.out.println("Kitap bulunamadı!");
            return;
        }
        if (reader == null) {
            System.out.println("Okuyucu bulunamadı!");
            return;
        }
        if (book.getStatus() != BookStatus.AVAILABLE) {
            System.out.println("Kitap ödünç alınmış!");
            return;
        }
        if (!reader.canBorrow()) {
            System.out.println("5 kitap limitine ulaştınız!");
            return;
        }

        book.setStatus(BookStatus.BORROWED);
        reader.borrowBook(book);

        Invoice invoice = new Invoice(nextInvoiceId++, reader, book, book.getPrice());
        invoices.add(invoice);
        reader.addFine(book.getPrice());

        System.out.println("Kitap ödünç alındı. Fatura kesildi: " + invoice.getInvoiceId());
    }

    @Override
    public void returnBook(int bookId, String readerId) {
        Book book = searchById(bookId);
        Reader reader = findReaderById(readerId);

        if (book == null || reader == null) {
            System.out.println("Kitap veya okuyucu bulunamadı!");
            return;
        }

        book.setStatus(BookStatus.AVAILABLE);
        reader.returnBook(book);
        reader.payFine(book.getPrice());

        for (Invoice invoice : invoices) {
            if (invoice.getBook().getBookId() == bookId && invoice.getReader().getId().equals(readerId) && !invoice.isPaid()) {
                invoice.markAsPaid();
                break;
            }
        }

        System.out.println("Kitap iade edildi. Ücret iade edildi.");
    }

    @Override
    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Kütüphanede kitap yok.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Override
    public void showAllReaders() {
        if (readers.isEmpty()) {
            System.out.println("Kayıtlı okuyucu yok.");
            return;
        }
        for (Reader reader : readers) {
            System.out.println("ID: " + reader.getId() + ", İsim: " + reader.getName() + ", Ödünç kitap: " + reader.getBorrowedBooks().size());
        }
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public Reader findReaderById(String readerId) {
        for (Reader reader : readers) {
            if (reader.getId().equals(readerId)) {
                return reader;
            }
        }
        return null;
    }

    public int getNextBookId() {
        return nextBookId++;
    }
}