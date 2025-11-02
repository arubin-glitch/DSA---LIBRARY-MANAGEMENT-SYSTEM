package DSAGROUP6;

public class Book {
    private String title;
    private String dewey;
    private String category;
    private int year;
    private int pages;

    public Book(String title, String dewey, String category, int year, int pages) {
        this.title = title;
        this.dewey = dewey;
        this.category = category;
        this.year = year;
        this.pages = pages;
    }

    public String getTitle() { return title; }
    public String getDewey() { return dewey; }
    public String getCategory() { return category; }
    public int getYear() { return year; }
    public int getPages() { return pages; }

    @Override
    public String toString() {
        return title + " (" + dewey + ", " + category + ", " + year + ", " + pages + " pages)";
    }
}

