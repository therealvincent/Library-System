import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    private boolean isDarkMode = false; // Boolean value for using
    private JPanel backgroundPanel;
    private JPanel mainPanel;
    private JPanel bottomPanel;
    private JLabel titleLabel;
    private JButton logOutButton;
    private JPanel manageUserPage;
    private JPanel viewUserProfilePage;

    public MainGUI() {
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main background panel using CardLayout for page switching
        backgroundPanel = new JPanel(new CardLayout());
        add(backgroundPanel);

        // Initialize main page panel and set it up
        mainPanel = new JPanel(new GridBagLayout());
        setupMainPanel();

        // Add main panel to backgroundPanel with a label for CardLayout
        backgroundPanel.add(mainPanel, "MainPage");

        // Initialize ManageUserPage and add to backgroundPanel
        setupManageUserPage();
        backgroundPanel.add(manageUserPage, "ManageUserPage");

        // Initialize ViewUserProfilePage and add to backgroundPanel
        setUpViewUserProfilePage();
        backgroundPanel.add(viewUserProfilePage, "ViewUserProfilePage");


        // Initialize with light mode
        applyLightMode();

        setVisible(true);
    }

    private void setupMainPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;

        titleLabel = new JLabel("Library Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        mainPanel.add(titleLabel, gbc);

        gbc.gridy++;
        JButton manageUserButton = createStyledButton("Manage User");
        manageUserButton.addActionListener(e -> switchToManageUserPage());
        mainPanel.add(manageUserButton, gbc);

        gbc.gridy++;
        JButton manageBooksButton = createStyledButton("Manage Books");
        mainPanel.add(manageBooksButton, gbc);

        gbc.gridy++;
        JButton viewProfileButton = createStyledButton("View User Profile");
        viewProfileButton.addActionListener(e -> switchToViewUserProfilePage());
        mainPanel.add(viewProfileButton, gbc);

        gbc.gridy++;
        JButton checkBookButton = createStyledButton("Check Book Availability");
        mainPanel.add(checkBookButton, gbc);

        // Add Dark Mode Toggle Icon
        JButton darkModeButton = new JButton("🌙");
        darkModeButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        darkModeButton.setContentAreaFilled(false);
        darkModeButton.setFocusPainted(false);
        darkModeButton.addActionListener(e -> toggleDarkMode());

        // Log Out Button
        logOutButton = new JButton("Log Out");
        logOutButton.setPreferredSize(new Dimension(120, 40));
        logOutButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        logOutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logged Out Successfully!");
            dispose();
            SwingUtilities.invokeLater(LogInGUI::new);
        });

        // Bottom panel setup
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(darkModeButton, BorderLayout.WEST);
        bottomPanel.add(logOutButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void setupManageUserPage() {
        manageUserPage = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Add Book Button
        JButton addBookButton = createStyledButton("Add User");
        buttonPanel.add(addBookButton, gbc);

        gbc.gridy++;
        // Remove Book Button
        JButton removeBookButton = createStyledButton("Remove User");
        buttonPanel.add(removeBookButton, gbc);

        gbc.gridy++;
        // Update Book Button
        JButton updateBookButton = createStyledButton("Update User");
        buttonPanel.add(updateBookButton, gbc);

        manageUserPage.add(buttonPanel, BorderLayout.CENTER);

        // Return to Home Button
        JButton returnButton = new JButton("Return to Home");
        returnButton.setPreferredSize(new Dimension(150, 40));
        returnButton.addActionListener(e -> switchToMainPage());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(returnButton);
        manageUserPage.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void setUpViewUserProfilePage() {
        viewUserProfilePage = new JPanel(new BorderLayout());

        // Creates the title "Library Members" and places it at the top left of the panel.
        JLabel titleLabel = new JLabel("Library Members", SwingConstants.LEFT);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        viewUserProfilePage.add(titleLabel, BorderLayout.NORTH);

        // Creates the search panel with icon and text field used for searching up members.
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchIcon = new JLabel(new ImageIcon("book_icon.png"));
        JTextField searchField = new JTextField("Search", 20);
        searchPanel.add(searchIcon);
        searchPanel.add(searchField);
        viewUserProfilePage.add(searchPanel, BorderLayout.CENTER);

        // Members list panel
        JPanel membersPanel = new JPanel(new GridLayout(4, 1, 0, 10));
        membersPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // This for loop creates the stacks of member row.
        for (int i = 0; i < 4; i++) {
            JPanel memberRow = new JPanel(new BorderLayout());
            JLabel memberLabel = new JLabel("Name of Member", new ImageIcon("user_icon.png"), SwingConstants.LEFT);
            JButton moreInfoButton = createStyledButton("More Info");
            moreInfoButton.setPreferredSize(new Dimension(100, 30));
            memberRow.add(memberLabel, BorderLayout.CENTER);
            memberRow.add(moreInfoButton, BorderLayout.EAST);
            memberRow.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            membersPanel.add(memberRow);
        }

        viewUserProfilePage.add(membersPanel, BorderLayout.SOUTH);

        // Creates the back button
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(e -> switchToMainPage());
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.add(backButton);
        viewUserProfilePage.add(backButtonPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(300, 40));
        button.setFont(new Font("SansSerif", Font.PLAIN, 16));
        button.setFocusPainted(false);
        return button;
    }

    private void toggleDarkMode() {
        if (isDarkMode) {
            applyLightMode();
        } else {
            applyDarkMode();
        }
        isDarkMode = !isDarkMode;
    }

    private void applyLightMode() {
        backgroundPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBackground(new Color(255, 255, 255));
        bottomPanel.setBackground(new Color(240, 240, 240));
        titleLabel.setForeground(Color.BLACK);
        logOutButton.setBackground(Color.BLACK);
        logOutButton.setForeground(Color.WHITE);
    }

    private void applyDarkMode() {
        backgroundPanel.setBackground(new Color(45, 45, 45));
        mainPanel.setBackground(new Color(45, 45, 45));
        bottomPanel.setBackground(new Color(45, 45, 45));
        titleLabel.setForeground(Color.WHITE);
        logOutButton.setBackground(new Color(255, 69, 0));
        logOutButton.setForeground(Color.BLACK);
    }

    public void switchToMainPage() {
        CardLayout cl = (CardLayout) backgroundPanel.getLayout();
        cl.show(backgroundPanel, "MainPage");
    }

    private void switchToManageUserPage() {
        CardLayout cl = (CardLayout) backgroundPanel.getLayout();
        cl.show(backgroundPanel, "ManageUserPage");
    }

    private void switchToViewUserProfilePage() {
        CardLayout cl = (CardLayout) backgroundPanel.getLayout();
        cl.show(backgroundPanel, "ViewUserProfilePage");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
