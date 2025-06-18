// File: src/Main.java

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        // Tạo sách mẫu (Ví dụ ngày hết hạn là ngày 20/6/2025)
        Book book = new Book("Lập trình Java", LocalDate.of(2025, 6, 20));

        // Gọi hàm thông báo
        notifyUser(book);
    }

    // Phương thức kiểm tra còn bao nhiêu ngày
    public static boolean checkDueDate(Book book) {
        LocalDate today = LocalDate.now(); // Lấy ngày hiện tại
        LocalDate dueDate = book.getDueDate(); // Lấy ngày hết hạn sách

        // Nếu sách còn 3 ngày hoặc ít hơn sẽ thông báo
        return !today.isAfter(dueDate.minusDays(3));
    }

    // Phương thức thông báo tới bạn đọc
    public static void notifyUser(Book book) {
        if (checkDueDate(book)) {
            System.out.println("Thông báo: Sách \"" + book.getTitle() + "\" sắp hết hạn mượn.");
        }
    }
}

// Class Book (viết ngay dưới class Main)
class Book {
    private String title;
    private LocalDate dueDate;

    // Constructor
    public Book(String title, LocalDate dueDate) {
        this.title = title;
        this.dueDate = dueDate;
    }

    // Getter
    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
