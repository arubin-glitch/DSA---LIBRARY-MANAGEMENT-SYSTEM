package DSAGROUP6;

import java.util.LinkedList;

public class BookListManager {
    private LinkedList<Book> books;

    public BookListManager() {
        books = new LinkedList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public LinkedList<Book> getBooks() {
        return books;
    }

    public void sortByPages() {
        books.sort((b1, b2) -> Integer.compare(b1.getPages(), b2.getPages()));
    }

    public void sortByTitle() {
        books.sort((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));
    }

    public void sortByDewey() {
        books.sort((b1, b2) -> b1.getDewey().compareTo(b2.getDewey()));
    }

    public void sortByCategory() {
        books.sort((b1, b2) -> b1.getCategory().compareToIgnoreCase(b2.getCategory()));
    }

    public void sortByYear() {
        books.sort((b1, b2) -> Integer.compare(b1.getYear(), b2.getYear()));
    }
}






