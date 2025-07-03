package com.example.servingwebcontent.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Calendar;

public class Library {
    private int id;
    private String name;
    private List<Book> books;
    private List<Member> members;
    private List<BorrowRecord> borrowRecords = new ArrayList<>();
    
    public Library(int id, String name) {
        this.id = id;
        this.name = name;
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String borrowBook(int memberId, int bookId, int borrowDays) {
    try {
        Member member = findMemberById(memberId);
        Book book = findBookById(bookId);
        if (member == null || book == null || !book.isAvailable()) {
            return "Không thể mượn sách";
            }
        Date borrowDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(borrowDate);
        cal.add(Calendar.DATE, borrowDays);
        Date dueDate = cal.getTime();

        BorrowRecord record = new BorrowRecord(book, member, borrowDate, dueDate);
        borrowRecords.add(record);

        member.borrowBook(book);
        return "Mượn sách thành công! Hạn trả: " + dueDate;
    } catch (Exception e) {
        e.printStackTrace();
        return "Không thể mượn sách";
    } finally {
    }
    }

    public String returnBook(int memberId, int bookId) {
    try {
        for (BorrowRecord record : borrowRecords) {
            if (record.getBook().getId() == bookId
                && record.getMember().getId() == memberId
                && !record.isReturned()) {

                record.setReturned(new Date());
                record.getMember().returnBook(record.getBook());

                Date now = record.getReturnDate();
                if (now.after(record.getDueDate())) {
                    long diffMs = now.getTime() - record.getDueDate().getTime();
                    long diffDays = diffMs / (1000 * 60 * 60 * 24);
                    double fine = diffDays * 5000;
                    return "Trả sách thành công. Bạn đã trả trễ " + diffDays + " ngày. Phí phạt: " + fine + " VND.";
                } else {
                    return "Trả sách thành công. Bạn không bị phạt!";
                }
            }
        }
        return "Không tìm thấy giao dịch mượn sách để trả!";
    } catch (Exception e) {
        e.printStackTrace();
        return "Không thể trả sách";
    } finally {
    }
    }

    public void addBook(Book book) {
        try {
            books.add(book);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
    public Book findBookById(int bookId) {
        try {
            for (Book book : books) {
                if (book.getId() == bookId) {
                    return book;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        }
    }

    public void addMember(Member member) {
        try {
            members.add(member);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
    public Member findMemberById(int memberId) {
        try {
            for (Member member : members) {
                if (member.getId() == memberId) {
                    return member;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        }
    }
    
    public boolean updateBook(Book updatedBook) {
        Book existing = findBookById(updatedBook.getId());
        if (existing != null) {
            existing.setTitle(updatedBook.getTitle());
            existing.setAuthor(updatedBook.getAuthor());
            existing.setDueDate(updatedBook.getDueDate());
            return true;
        }
        return false;
    }

    public boolean deleteBook(int bookId) {
        Book existing = findBookById(bookId);
        if (existing != null) {
            books.remove(existing);
            return true;
        }
        return false;
    }

    public boolean updateMember(Member updatedMember) {
        Member existing = findMemberById(updatedMember.getId());
        if (existing != null) {
            existing.setName(updatedMember.getName());
            return true;
        }
        return false;
    }

    public boolean deleteMember(int memberId) {
        Member existing = findMemberById(memberId);
        if (existing != null) {
            members.remove(existing);
            return true;
        }
        return false;
    }

    public List<Book> getBooks() {
        return this.books;
    }
    public List<Member> getMembers() {
        return this.members;
    }
    public List<BorrowRecord> getBorrowRecords() {
    return borrowRecords;
    }
    public void displayAvailableBooks() {
        try {
            System.out.println("Available Books:");
            for (Book book : books) {
                if (book.isAvailable()) {
                    System.out.println(book.getId() + " - " + book.getTitle() + " by " + book.getAuthor());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}