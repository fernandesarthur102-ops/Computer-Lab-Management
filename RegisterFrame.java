import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegisterFrame extends JFrame {
    private JTextField txtName;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnRegister;
    private JButton btnClose;

    public RegisterFrame() {
        setUndecorated(true);
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setOpacity(1f);
        setBounds(760, 410, 400, 260);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(50, 60, 100, 30);
        lblName.setFont(new Font("Courier", Font.BOLD, 18));
        lblName.setForeground(Color.WHITE);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(200, 60, 150, 30);
        txtName.setFont(new Font("Courier", Font.PLAIN, 18));
        txtName.setBackground(Color.BLACK);
        txtName.setForeground(Color.WHITE);
        add(txtName);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 110, 100, 30);
        lblUsername.setFont(new Font("Courier", Font.BOLD, 18));
        lblUsername.setForeground(Color.WHITE);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(200, 110, 150, 30);
        txtUsername.setFont(new Font("Courier", Font.PLAIN, 18));
        txtUsername.setBackground(Color.BLACK);
        txtUsername.setForeground(Color.WHITE);
        add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 160, 100, 30);
        lblPassword.setFont(new Font("Courier", Font.BOLD, 18));
        lblPassword.setForeground(Color.WHITE);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(200, 160, 150, 30);
        txtPassword.setFont(new Font("Courier", Font.PLAIN, 18));
        txtPassword.setBackground(Color.BLACK);
        txtPassword.setForeground(Color.WHITE);
        add(txtPassword);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(100, 210, 200, 30);
        btnRegister.setFont(new Font("Courier", Font.BOLD, 18));
        btnRegister.setBackground(Color.DARK_GRAY);
        btnRegister.setForeground(Color.WHITE);
        btnRegister.addActionListener(e -> registerUser());
        add(btnRegister);

        btnClose = new JButton("X");
        btnClose.setBounds(350, 0, 50, 30);
        btnClose.setFont(new Font("Courier", Font.BOLD, 22));
        btnClose.setBackground(Color.RED);
        btnClose.setForeground(Color.WHITE);
        btnClose.addActionListener(e -> dispose());
        add(btnClose);
    }

    private void registerUser() {
        String name = txtName.getText();
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }

        try {
            Conn conn = new Conn();
            String query = "INSERT INTO users (name, username, password) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.getConnection().prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, username);
            pstmt.setString(3, password);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Registration Successful");
                new LoginFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Registration Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RegisterFrame();
    }
}
