package com.example.servingwebcontent.models;

import java.time.LocalDate;

import com.example.servingwebcontent.models.BorrowTicket;
import com.example.servingwebcontent.models.BorrowTicketUtils;

/** Simple test for {@link BorrowTicketUtils}. */
public class BorrowTicketUtilsTest {
    public static void main(String[] args) {
        try {
            BorrowTicket ticket1 = new BorrowTicket("PM1", "User1", LocalDate.now().plusDays(2));
            boolean result1 = BorrowTicketUtils.isBookNearDueDate(ticket1, 3);
            System.out.println("Test 1 (Trong 3 ngày tới): " + result1);
        } catch (Exception e) {
            System.out.println("Lỗi trong Test 1: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành Test 1");
        }

        try {
            BorrowTicket ticket2 = new BorrowTicket("PM2", "User2", LocalDate.now().plusDays(5));
            boolean result2 = BorrowTicketUtils.isBookNearDueDate(ticket2, 3);
            System.out.println("Test 2 (Hết hạn sau 5 ngày): " + result2);
        } catch (Exception e) {
            System.out.println("Lỗi trong Test 2: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành Test 2");
        }

        try {
            BorrowTicket ticket3 = new BorrowTicket("PM3", "User3", LocalDate.now().minusDays(1));
            boolean result3 = BorrowTicketUtils.isBookNearDueDate(ticket3, 3);
            System.out.println("Test 3 (Đã quá hạn): " + result3);
        } catch (Exception e) {
            System.out.println("Lỗi trong Test 3: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành Test 3");
        }
    }
}