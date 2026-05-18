import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnClose;

    public LoginFrame() {

        setUndecorated(true);
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setOpacity(1f);
        setBounds(760, 435, 400, 210);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 60, 100, 30);
        lblUsername.setFont(new Font("Courier", Font.BOLD, 18));
        lblUsername.setForeground(Color.WHITE);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(200, 60, 150, 30);
        txtUsername.setFont(new Font("Courier", Font.PLAIN, 18));
        txtUsername.setBackground(Color.BLACK);
        txtUsername.setForeground(Color.WHITE);
        add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 110, 100, 30);
        lblPassword.setFont(new Font("Courier", Font.BOLD, 18));
        lblPassword.setForeground(Color.WHITE);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(200, 110, 150, 30);
        txtPassword.setFont(new Font("Courier", Font.PLAIN, 18));
        txtPassword.setBackground(Color.BLACK);
        txtPassword.setForeground(Color.WHITE);
        add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 160, 200, 30);
        btnLogin.setFont(new Font("Courier", Font.BOLD, 22));
        btnLogin.setBackground(Color.DARK_GRAY);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.addActionListener(e -> loginUser());
        add(btnLogin);

        btnClose = new JButton("X");
        btnClose.setBounds(350, 0, 50, 30);
        btnClose.setFont(new Font("Courier", Font.BOLD, 22));
        btnClose.setBackground(Color.RED);
        btnClose.setForeground(Color.WHITE);
        btnClose.addActionListener(e -> dispose());
        add(btnClose);
    }

    private void loginUser() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement pstmt = conn.getConnection().prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Login Successful");
                new StudentsRecordFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
