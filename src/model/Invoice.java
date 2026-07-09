package model;

public class Invoice {
    private int invoiceId;
    private Reader reader;
    private Book book;
    private double amount;
    private boolean isPaid;

    public Invoice(int invoiceId, Reader reader, Book book, double amount) {
        this.invoiceId = invoiceId;
        this.reader = reader;
        this.book = book;
        this.amount = amount;
        this.isPaid = false;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public Reader getReader() {
        return reader;
    }

    public Book getBook() {
        return book;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void markAsPaid() {
        this.isPaid = true;
    }

    @Override
    public String toString() {
        return "Fatura ID: " + invoiceId + ", Okuyucu: " + reader.getName() + ", Kitap: " + book.getName() + ", Tutar: " + amount + ", Ödendi: " + isPaid;
    }
}