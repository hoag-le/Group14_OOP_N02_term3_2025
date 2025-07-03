import com.example.servingwebcontent.database.BookDao;
import com.example.servingwebcontent.database.MemberDao;
import com.example.servingwebcontent.models.Book;
import com.example.servingwebcontent.models.Member;
import com.example.servingwebcontent.models.Library;

public class Main {
    public static void main(String[] args) {
        try {

        BookDao bookDao = new BookDao();
        bookDao.save(new Book(1, "Clean Code", "Robert C. Martin"));
        bookDao.save(new Book(2, "Effective Java", "Joshua Bloch"));
        for (Book b : bookDao.findAll()) {
            System.out.println("Book: " + b.getId() + " - " + b.getTitle());
        }

        MemberDao memberDao = new MemberDao();
        memberDao.save(new Member(1, "Nguyen Van A"));
        memberDao.save(new Member(2, "Tran Thi B"));
        for (Member m : memberDao.findAll()) {
            System.out.println("Member: " + m.getId() + " - " + m.getName());
        }

        Library library = new Library(1, "Thu vien Trung tam");
        library.addBook(new Book(3, "Demo", "Author"));
        library.addMember(new Member(3, "Demo User"));
        for (Library l : java.util.List.of(library)) {
            System.out.println("Library: " + l.getId() + " - " + l.getName());
        }
        TestReturnBook.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}