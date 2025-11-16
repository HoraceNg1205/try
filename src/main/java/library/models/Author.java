package library.models;

import java.util.ArrayList;
import java.util.List;

public class Author extends User {
    private int authorId;
    private List<Book> books;

    public Author(int userId, String username, String password, String fullName, int authorId) {
        super(userId, username, password, fullName);
        this.authorId = authorId;
        this.books = new ArrayList<>();
    }

    public void publishBook(Book book) {
        books.add(book);
    }
}