import repository.GenericRepository;
import com.example.servingwebcontent.models.Book;
import com.example.servingwebcontent.models.Member;
import com.example.servingwebcontent.models.Library;

public class Main {
    public static void main(String[] args) {
        try {

        GenericRepository<Book> bookRepo = new GenericRepository<>(Book::getId);
        bookRepo.create(new Book(1, "Clean Code", "Robert C. Martin"));
        bookRepo.create(new Book(2, "Effective Java", "Joshua Bloch"));
        for (Book b : bookRepo.listAll()) {
            System.out.println("Book: " + b.getId() + " - " + b.getTitle());
        }

        GenericRepository<Member> memberRepo = new GenericRepository<>(Member::getId);
        memberRepo.create(new Member(1, "Nguyen Van A"));
        memberRepo.create(new Member(2, "Tran Thi B"));
        for (Member m : memberRepo.listAll()) {
            System.out.println("Member: " + m.getId() + " - " + m.getName());
        }

        GenericRepository<Library> libraryRepo = new GenericRepository<>(Library::getId);
        libraryRepo.create(new Library(1, "Thu vien Trung tam"));
        libraryRepo.create(new Library(2, "Thu vien Co So 2"));
        for (Library l : libraryRepo.listAll()) {
            System.out.println("Library: " + l.getId() + " - " + l.getName());
        }
        TestReturnBook.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}