package test.models;

import java.util.Calendar;
import java.util.Date;
import com.example.servingwebcontent.models.Library;
import com.example.servingwebcontent.models.Book;
import com.example.servingwebcontent.models.Member;
import com.example.servingwebcontent.BorrowRecord;
import java.time.LocalDate;

public class TestReturnBook {
    public static void main(String[] args) {
        try {
            Library lib = new Library(1, "CNTT Library");
            Book book = new Book(100, "OOP Java", "Nguyen Van A", LocalDate.now().plusDays(7));
            Member mem = new Member(10, "Hoang Le");

            lib.addBook(book);
            lib.addMember(mem);

            lib.borrowBook(10, 100, 3);

            BorrowRecord lastRecord = lib.getBorrowRecords().get(0);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -5);
            lastRecord.getBook().checkOut();
            lastRecord.getMember().borrowBook(lastRecord.getBook());
            lastRecord.setReturned(null);

            lastRecord.setBorrowDate(cal.getTime());
            cal.add(Calendar.DATE, 3);
            lastRecord.setDueDate(cal.getTime());

            String kq = lib.returnBook(10, 100);
            System.out.println(kq);

            String kq2 = lib.returnBook(10, 100);
            System.out.println(kq2);

            String kq3 = lib.returnBook(20, 100);
            System.out.println(kq3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Đã chạy xong TestReturnBook");
        }
    }
}
