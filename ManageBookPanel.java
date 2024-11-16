import javax.swing.*;
import java.awt.*;

public class ManageBookPanel extends JPanel {
    private MainGUI mainGUI;

    public ManageBookPanel(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        setLayout(new BorderLayout());

        // Create the title label
        JLabel titleLabel = new JLabel("Book Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 45)); // Set the font to 36 and make it bold
        titleLabel.setForeground(Color.BLACK);

        // Set the title's position using setBounds(x, y, width, height)
        titleLabel.setBounds(150, 90, 500, 50); // Example: position at X=150, Y=30, width=500, height=50

        add(titleLabel); // Add the label to the panel


        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Adding space between buttons

        // Add Book Button (Row 0, Column 0)
        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton addBookButton = createStyledButton("Add Book");
        buttonPanel.add(addBookButton, gbc);

        // Remove Book Button (Row 0, Column 1)
        gbc.gridx = 1;
        gbc.gridy = 0;
        JButton removeBookButton = createStyledButton("Remove Book");
        buttonPanel.add(removeBookButton, gbc);

        // Update Book Button (Row 1, Column 0)
        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton updateBookButton = createStyledButton("Update Book");
        buttonPanel.add(updateBookButton, gbc);

        // Book Inventory Button (Row 1, Column 1)
        gbc.gridx = 1;
        gbc.gridy = 1;
        JButton bookInventoryButton = createStyledButton("Book Inventory");
        buttonPanel.add(bookInventoryButton, gbc);

        add(buttonPanel, BorderLayout.CENTER);

        // Return Button
        JButton returnButton = new JButton("Return to Home");
        returnButton.setPreferredSize(new Dimension(150, 40));
        returnButton.addActionListener(e -> mainGUI.switchToMainPage());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(returnButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(300, 40));
        button.setFont(new Font("SansSerif", Font.PLAIN, 16));
        button.setFocusPainted(false);
        return button;
    }
}
