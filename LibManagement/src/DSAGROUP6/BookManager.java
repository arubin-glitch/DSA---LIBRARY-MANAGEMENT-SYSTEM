package DSAGROUP6;

public class BookManager {

    public static BookListManager getBookManager() {
        BookListManager manager = new BookListManager();

        manager.addBook(new Book("Introduction to Algorithms", "005.1", "Educational", 2009, 1312));
        manager.addBook(new Book("The Great Gatsby", "813.52", "Fiction", 1925, 180));
        manager.addBook(new Book("Database Systems", "005.74", "Educational", 2015, 850));
        manager.addBook(new Book("Human Anatomy", "611", "Non-Fiction", 2012, 600));
        manager.addBook(new Book("World History", "909", "Non-Fiction", 2018, 720));
        manager.addBook(new Book("Spider-Man Adventures", "741.5", "Comics", 2010, 120));
        manager.addBook(new Book("Harry Potter and the Sorcerer's Stone", "823", "Children", 1997, 309));
        manager.addBook(new Book("The Hobbit", "823", "Novels", 1937, 310));

        return manager;
    }
}


