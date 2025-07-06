package com.example.servingwebcontent.models;

import com.example.servingwebcontent.service.BorrowService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BorrowLimitTest {
    @Test
    public void memberCannotBorrowMoreThanLimit() {
        Library lib = new Library(1, "L");
        Member m = new Member(1, "A");
        lib.addMember(m);
        for (int i = 1; i <= BorrowService.MAX_BORROW_LIMIT + 1; i++) {
            Book b = new Book(i, "B" + i, "Auth");
            lib.addBook(b);
            String msg = lib.borrowBook(1, i, 1);
            if (i <= BorrowService.MAX_BORROW_LIMIT) {
                assertTrue(msg.startsWith("Mượn sách thành công"));
            } else {
                assertEquals("Bạn đã mượn quá số lượng sách cho phép", msg);
            }
        }
    }
}