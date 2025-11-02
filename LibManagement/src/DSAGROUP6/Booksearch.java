package DSAGROUP6;

import java.util.LinkedList;

public class Booksearch {

    public static LinkedList<Book> searchByTitle(LinkedList<Book> books, String query) {
        LinkedList<Book> result = new LinkedList<>();
        String q = query.toLowerCase();
        for (Book b : books) {
            if (b.getTitle() != null && b.getTitle().toLowerCase().contains(q)) {
                result.add(b);
            }
        }
        return result;
    }

    public static LinkedList<Book> searchByCategory(LinkedList<Book> books, String category) {
        LinkedList<Book> result = new LinkedList<>();
        String c = category.toLowerCase();
        for (Book b : books) {
            if (b.getCategory() != null && b.getCategory().toLowerCase().contains(c)) {
                result.add(b);
            }
        }
        return result;
    }
}



