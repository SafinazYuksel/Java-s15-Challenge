import model.*;
import service.LibraryServiceImpl;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryServiceImpl library = new LibraryServiceImpl();
        Scanner scanner = new Scanner(System.in);

        // Örnek okuyucular ekle
        library.addReader(new Reader("R1", "Ahmet Yılmaz", "ahmet@mail.com"));
        library.addReader(new Reader("R2", "Ayşe Demir", "ayse@mail.com"));

        // Örnek kitaplar ekle
        library.addBook(new Book(library.getNextBookId(), "Java Programlama", "Ali Veli", "Bilgisayar", 50.0, "1. Baskı"));
        library.addBook(new Book(library.getNextBookId(), "Python Temelleri", "Mehmet Can", "Bilgisayar", 40.0, "2. Baskı"));
        library.addBook(new Book(library.getNextBookId(), "Tarih 101", "Fatma Kaya", "Tarih", 30.0, "1. Baskı"));

        while (true) {
            System.out.println("\n=== KÜTÜPHANE SİSTEMİ ===");
            System.out.println("1. Yeni Kitap Ekle");
            System.out.println("2. Kitap Ara (ID)");
            System.out.println("3. Kitap Ara (İsim)");
            System.out.println("4. Kitap Ara (Yazar)");
            System.out.println("5. Kitap Güncelle");
            System.out.println("6. Kitap Sil");
            System.out.println("7. Kategoriye Göre Listele");
            System.out.println("8. Yazara Göre Listele");
            System.out.println("9. Tüm Kitapları Göster");
            System.out.println("10. Kitap Ödünç Al");
            System.out.println("11. Kitap İade Et");
            System.out.println("12. Tüm Okuyucuları Göster");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Kitap İsmi: ");
                    String name = scanner.nextLine();
                    System.out.print("Yazar: ");
                    String author = scanner.nextLine();
                    System.out.print("Kategori: ");
                    String category = scanner.nextLine();
                    System.out.print("Fiyat: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Baskı: ");
                    String edition = scanner.nextLine();
                    library.addBook(new Book(library.getNextBookId(), name, author, category, price, edition));
                    System.out.println("Kitap eklendi!");
                    break;

                case 2:
                    System.out.print("Kitap ID: ");
                    int id = scanner.nextInt();
                    Book book = library.searchById(id);
                    System.out.println(book != null ? book : "Kitap bulunamadı!");
                    break;

                case 3:
                    System.out.print("Aranacak İsim: ");
                    String searchName = scanner.nextLine();
                    System.out.println(library.searchByName(searchName));
                    break;

                case 4:
                    System.out.print("Aranacak Yazar: ");
                    String searchAuthor = scanner.nextLine();
                    System.out.println(library.searchByAuthor(searchAuthor));
                    break;

                case 5:
                    System.out.print("Güncellenecek Kitap ID: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Yeni İsim: ");
                    String newName = scanner.nextLine();
                    System.out.print("Yeni Yazar: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Yeni Kategori: ");
                    String newCategory = scanner.nextLine();
                    System.out.print("Yeni Fiyat: ");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Yeni Baskı: ");
                    String newEdition = scanner.nextLine();
                    library.updateBook(updateId, new Book(0, newName, newAuthor, newCategory, newPrice, newEdition));
                    System.out.println("Kitap güncellendi!");
                    break;

                case 6:
                    System.out.print("Silinecek Kitap ID: ");
                    int deleteId = scanner.nextInt();
                    library.deleteBook(deleteId);
                    System.out.println("Kitap silindi!");
                    break;

                case 7:
                    System.out.print("Kategori: ");
                    String cat = scanner.nextLine();
                    System.out.println(library.getBooksByCategory(cat));
                    break;

                case 8:
                    System.out.print("Yazar: ");
                    String auth = scanner.nextLine();
                    System.out.println(library.searchByAuthor(auth));
                    break;

                case 9:
                    library.showAllBooks();
                    break;

                case 10:
                    System.out.print("Kitap ID: ");
                    int borrowId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Okuyucu ID: ");
                    String readerId = scanner.nextLine();
                    library.borrowBook(borrowId, readerId);
                    break;

                case 11:
                    System.out.print("Kitap ID: ");
                    int returnId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Okuyucu ID: ");
                    String returnReaderId = scanner.nextLine();
                    library.returnBook(returnId, returnReaderId);
                    break;

                case 12:
                    library.showAllReaders();
                    break;

                case 0:
                    System.out.println("Çıkış yapılıyor...");
                    return;

                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }
}