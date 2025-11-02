package DSAGROUP6;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.function.Consumer;

public class CategoryPanel extends JPanel {

    private final Color lightBrown = new Color(205, 133, 63);
    private final Color beige = new Color(245, 222, 179);
    private final Color highlight = new Color(160, 82, 45);

    private JButton activeButton = null;

    public CategoryPanel(BookListManager manager, Consumer<LinkedList<Book>> updateCallback) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        setBackground(beige);

        String[] categories = {"Show All", "Children", "Comics", "Fiction", "Non-Fiction", "Educational", "Novels"};

        for (String cat : categories) {
            JButton button = new JButton(cat);
            button.setBackground(lightBrown);
            button.setForeground(Color.WHITE);
            button.setFont(new Font("SansSerif", Font.BOLD, 12));

            button.addActionListener(e -> {
                highlightButton(button);
                LinkedList<Book> filtered;
                if (cat.equals("Show All")) {
                    filtered = manager.getBooks();
                } else {
                    filtered = Booksearch.searchByCategory(manager.getBooks(), cat);
                }
                updateCallback.accept(filtered);
            });

            add(button);
        }
    }

    private void highlightButton(JButton selected) {
        if (activeButton != null) {
            activeButton.setBackground(lightBrown);
        }
        selected.setBackground(highlight);
        activeButton = selected;
    }
}

