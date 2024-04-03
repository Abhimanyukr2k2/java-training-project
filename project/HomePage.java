import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;













public class HomePage extends JFrame implements ActionListener {
    public HomePage() {
        setTitle("Home Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
         // Set background image
         try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\ak745\\Desktop\\project\\Mainscreen.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Create buttons
        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");
        JButton aboutUsButton = new JButton("About Us");

        // Set button positions
        loginButton.setBounds(50, 50, 100, 30);
        signUpButton.setBounds(50, 100, 100, 30);
        aboutUsButton.setBounds(50, 150, 100, 30);

        // Add action signup
        loginButton.addActionListener(this);
        signUpButton.addActionListener(this);
        aboutUsButton.addActionListener(this);

        // Add buttons to frame
        add(loginButton);
        add(signUpButton);
        add(aboutUsButton);

        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            // Open the login frame
            new LoginFrame();
            this.setVisible(false); // Hide the home page
        } else if (e.getActionCommand().equals("Sign Up")) {
            // Open the sign-up frame
            new SignUpFrame();
            this.setVisible(false); // Hide the home page
        } else if (e.getActionCommand().equals("About Us")) {
            // Open the about us page
            // Add code to open the about us page here
            new AboutUsFrame();
        }
    }

    public static void main(String[] args) {
        new HomePage();
    }
}



















 // Inner class for the sign-up frame
 class SignUpFrame extends JFrame implements ActionListener {
    JTextField nameField;
    JTextField emailField;
    JPasswordField passwordField;
    JPasswordField confirmPasswordField;

    public SignUpFrame() {
        setLayout(null);
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);

          // Set background image
          try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\ak745\\Desktop\\project\\Mainscreen.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel nameLabel = new JLabel("Enter user Name:");
        nameLabel.setBounds(50, 50, 100, 30);
        nameLabel.setForeground(Color.green);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 30);
        add(nameField);

        JLabel emailLabel = new JLabel("Enter Email:");
        emailLabel.setBounds(50, 100, 100, 30);
        emailLabel.setForeground(Color.green);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 100, 200, 30);
        add(emailField);

        JLabel passwordLabel = new JLabel("Enter Password:");
        passwordLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setForeground(Color.green);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 150, 200, 30);
        add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(50, 200, 120, 30);
        confirmPasswordLabel.setForeground(Color.green);
        add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(180, 200, 200, 30);
        add(confirmPasswordField);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(150, 250, 100, 30);
        signUpButton.addActionListener(this);
        add(signUpButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Perform signup functionality here
        String name = nameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // Validate password confirmation
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match. Please try again.");
            return;
        }

        // Insert data into the database
        if (insertUserData(name, email, password)) {
            JOptionPane.showMessageDialog(null, "Sign up successful!");
            // Redirect to the login page or perform any other action after signup
            new LoginFrame();
            this.setVisible(false); // Hide the sign-up framed
        } else {
            JOptionPane.showMessageDialog(null, "Failed to sign up. Please try again.");
        }
    }

    private boolean insertUserData(String name, String email, String password) {
        try {
            // Establish database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/venuedatabase", "root", "Qwertyuiop@123.");
            System.out.println("Connection Established");

            // Prepare SQL statement
            String sql = "INSERT INTO customerlogin (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);

            // Execute the query
            int rowsInserted = statement.executeUpdate();

            // Check if the insertion was successful
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

 




















//////loginframe/////



class LoginFrame extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;

    public LoginFrame() {
        setLayout(null);
        setTitle("Login - Venvault");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
          // Set background image
          try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\ak745\\Desktop\\project\\Mainscreen.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }


        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 80, 30);
        usernameLabel.setForeground(Color.green);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 30);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 80, 30);
        passwordLabel.setForeground(Color.green);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 30);
        add(loginButton);
        loginButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (isValidLogin(username, password)) {
            JOptionPane.showMessageDialog(null, "Login successful!");
            // Open the main application window or perform other actions here
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password");
        }
    }

    private boolean isValidLogin(String username, String password) {
        try {
            // Establish database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/venuedatabase", "root", "Qwertyuiop@123.");
            System.out.println("Connection Established");

            // Prepare SQL statement
            String sql = "SELECT * FROM customerlogin WHERE name = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Check if any rows are returned
            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}






//***------------about us-----***-/***** */




class AboutUsFrame extends JFrame {
    public AboutUsFrame() {
        setTitle("About Us");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the frame when closed
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        

        
          // Set background image
          try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\ak745\\Desktop\\project\\Mainscreen.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        JLabel aboutLabel = new JLabel("About Us");
        aboutLabel.setHorizontalAlignment(SwingConstants.CENTER);
        aboutLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(aboutLabel, BorderLayout.NORTH);

        JTextArea aboutTextArea = new JTextArea();
        aboutTextArea.setEditable(false);
        aboutTextArea.setText("This is a summary about our venue.");
        add(new JScrollPane(aboutTextArea), BorderLayout.CENTER);

        setVisible(true);
    }
}