package DSAGROUP6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class MainMenu extends JFrame {

    private JTextField searchField;
    private DefaultListModel<String> bookListModel;
    private JList<String> bookList;

    private final Color darkBrown = new Color(101, 67, 33);
    private final Color lightBrown = new Color(205, 133, 63);
    private final Color beige = new Color(245, 222, 179);

    private BookListManager bookManager = BookManager.getBookManager();
    private LinkedList<Book> currentFilteredBooks;
    private BorrowPanel borrowPanel;
    private String username;

    private boolean[] ascending = {true, true, true, true};
    

    public MainMenu(String username) {
        this.username = username;

        setTitle("Library Main Menu");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(beige);

       
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBackground(beige);

        JLabel searchLabel = new JLabel("🔍 Search: ");
        searchLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        searchLabel.setForeground(darkBrown);

        searchField = new JTextField();
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setBackground(Color.WHITE);
        searchField.setForeground(darkBrown);

        JLabel userLabel = new JLabel("👤 Welcome, " + username, SwingConstants.RIGHT);
        userLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        userLabel.setForeground(darkBrown);

        topPanel.add(searchLabel, BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(userLabel, BorderLayout.EAST);

        CategoryPanel categoryPanel = new CategoryPanel(bookManager, this::updateBookList);

        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setBackground(beige);
        topWrapper.add(topPanel, BorderLayout.NORTH);
        topWrapper.add(categoryPanel, BorderLayout.SOUTH);
        add(topWrapper, BorderLayout.NORTH);

        bookListModel = new DefaultListModel<>();
        bookList = new JList<>(bookListModel);
        bookList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        bookList.setBackground(Color.WHITE);
        bookList.setForeground(darkBrown);
        JScrollPane scrollPane = new JScrollPane(bookList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Available Books"));
        scrollPane.getViewport().setBackground(beige);
        add(scrollPane, BorderLayout.CENTER);

        updateBookList(bookManager.getBooks());

        
        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String query = searchField.getText().toLowerCase();
                LinkedList<Book> filtered = Booksearch.searchByTitle(currentFilteredBooks, query);
                updateBookList(filtered);
            }
        });

      
        bookList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = bookList.locationToIndex(e.getPoint());
                if (index >= 0 && index < currentFilteredBooks.size()) {
                    Book selected = currentFilteredBooks.get(index);

                    JPanel detailPanel = new JPanel(new GridLayout(6, 1));
                    detailPanel.setBackground(Color.WHITE);
                    detailPanel.add(new JLabel("📘 " + selected.getTitle()));
                    detailPanel.add(new JLabel("Dewey: " + selected.getDewey()));
                    detailPanel.add(new JLabel("Category: " + selected.getCategory()));
                    detailPanel.add(new JLabel("Year: " + selected.getYear()));
                    detailPanel.add(new JLabel("Pages: " + selected.getPages()));

                    JButton borrowButton = new JButton("Borrow Book");
                    borrowButton.setBackground(new Color(160, 82, 45));
                    borrowButton.setForeground(Color.WHITE);
                    borrowButton.setFont(new Font("SansSerif", Font.BOLD, 12));

                    borrowButton.addActionListener(ev -> {
                        borrowPanel.addBook(selected);
                        Window w = SwingUtilities.getWindowAncestor(borrowButton);
                        if (w != null) w.dispose();
                    });

                    detailPanel.add(borrowButton);

                    JOptionPane.showMessageDialog(
                        MainMenu.this,
                        detailPanel,
                        "Book Details",
                        JOptionPane.PLAIN_MESSAGE
                    );
                }
            }
        });

      
        JPanel sidebarPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        sidebarPanel.setBackground(beige);

        sidebarPanel.add(createSortPanel("Dewey Decimal", 0, () -> currentFilteredBooks.sort((b1, b2) -> b1.getDewey().compareTo(b2.getDewey()))));
        sidebarPanel.add(createSortPanel("Alphabetical", 1, () -> currentFilteredBooks.sort((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()))));
        sidebarPanel.add(createSortPanel("Publication Date", 2, () -> currentFilteredBooks.sort((b1, b2) -> Integer.compare(b1.getYear(), b2.getYear()))));
        sidebarPanel.add(createSortPanel("Page Count", 3, () -> currentFilteredBooks.sort((b1, b2) -> Integer.compare(b1.getPages(), b2.getPages()))));

        add(sidebarPanel, BorderLayout.WEST);

      
        borrowPanel = new BorrowPanel();
        borrowPanel.setPreferredSize(new Dimension(450, 0));
        add(borrowPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private JPanel createSortPanel(String label, int index, Runnable sortMethod) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(beige);

        JButton sortButton = createButton(label);
        JButton toggleButton = new JButton(ascending[index] ? "↑" : "↓");
        toggleButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        toggleButton.setBackground(lightBrown);
        toggleButton.setForeground(Color.WHITE);

        toggleButton.addActionListener(e -> {
            sortMethod.run();
            if (!ascending[index]) reverseList(currentFilteredBooks);
            updateBookList(currentFilteredBooks);
            ascending[index] = !ascending[index];
            toggleButton.setText(ascending[index] ? "↑" : "↓");
        });

        panel.add(sortButton, BorderLayout.CENTER);
        panel.add(toggleButton, BorderLayout.EAST);
        return panel;
    }

    private void reverseList(LinkedList<Book> list) {
        for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
            Book temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(lightBrown);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        return button;
    }
    private void updateBookList(LinkedList<Book> bookData) {
        currentFilteredBooks = new LinkedList<>(bookData);
        bookListModel.clear();
        for (Book b : bookData) {
            bookListModel.addElement(b.getTitle());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu("Brian"));
    }
}








