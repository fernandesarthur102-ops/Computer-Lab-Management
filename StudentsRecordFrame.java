import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StudentsRecordFrame extends JFrame {
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtBranch;
    private JTextField txtYear;
    private JTextField txtXIEID;
    private JTextField txtBatch;
    private JTextField txtPCID;
    private JButton btnSubmit;
    private JButton btnClose;

    StudentsRecordFrame() {
        
        setUndecorated(true);
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setOpacity(1f);
        setBounds(200, 100, 1520, 880);

        ImageIcon student = new ImageIcon(ClassLoader.getSystemResource("student.jpg"));
        Image set01 = student.getImage().getScaledInstance(820, 680, Image.SCALE_SMOOTH);
        ImageIcon new01 = new ImageIcon(set01);
        JLabel image = new JLabel(new01);
        image.setBounds(100, 100, 820, 680);
        add(image);

        JLabel text01 = new JLabel("Enter First Name: ");
        text01.setBounds(1020, 100, 400, 50);
        text01.setFont(new Font("Courier", Font.BOLD, 22));
        text01.setForeground(Color.WHITE);
        add(text01);

        txtFirstName = new JTextField();
        txtFirstName.setBounds(1020, 150, 400, 30);
        txtFirstName.setFont(new Font("Courier", Font.BOLD, 22));
        txtFirstName.setBackground(Color.BLACK);
        txtFirstName.setForeground(Color.WHITE);
        add(txtFirstName);

        JLabel text02 = new JLabel("Enter Last Name: ");
        text02.setBounds(1020, 190, 400, 50);
        text02.setFont(new Font("Courier", Font.BOLD, 22));
        text02.setForeground(Color.WHITE);
        add(text02);

        txtLastName = new JTextField();
        txtLastName.setBounds(1020, 240, 400, 30);
        txtLastName.setFont(new Font("Courier", Font.BOLD, 22));
        txtLastName.setBackground(Color.BLACK);
        txtLastName.setForeground(Color.WHITE);
        add(txtLastName);

        JLabel text03 = new JLabel("Enter Your Branch: ");
        text03.setBounds(1020, 280, 400, 50);
        text03.setFont(new Font("Courier", Font.BOLD, 22));
        text03.setForeground(Color.WHITE);
        add(text03);

        txtBranch = new JTextField();
        txtBranch.setBounds(1020, 330, 400, 30);
        txtBranch.setFont(new Font("Courier", Font.BOLD, 22));
        txtBranch.setBackground(Color.BLACK);
        txtBranch.setForeground(Color.WHITE);
        add(txtBranch);

        JLabel text04 = new JLabel("Enter Your Year: ");
        text04.setBounds(1020, 370, 400, 50);
        text04.setFont(new Font("Courier", Font.BOLD, 22));
        text04.setForeground(Color.WHITE);
        add(text04);

        txtYear = new JTextField();
        txtYear.setBounds(1020, 420, 400, 30);
        txtYear.setFont(new Font("Courier", Font.BOLD, 22));
        txtYear.setBackground(Color.BLACK);
        txtYear.setForeground(Color.WHITE);
        add(txtYear);

        JLabel text05 = new JLabel("Enter Your XIE ID: ");
        text05.setBounds(1020, 460, 400, 50);
        text05.setFont(new Font("Courier", Font.BOLD, 22));
        text05.setForeground(Color.WHITE);
        add(text05);

        txtXIEID = new JTextField();
        txtXIEID.setBounds(1020, 510, 400, 30);
        txtXIEID.setFont(new Font("Courier", Font.BOLD, 22));
        txtXIEID.setBackground(Color.BLACK);
        txtXIEID.setForeground(Color.WHITE);
        add(txtXIEID);

        JLabel text06 = new JLabel("Enter Your Batch: ");
        text06.setBounds(1020, 550, 400, 50);
        text06.setFont(new Font("Courier", Font.BOLD, 22));
        text06.setForeground(Color.WHITE);
        add(text06);

        txtBatch = new JTextField();
        txtBatch.setBounds(1020, 600, 400, 30);
        txtBatch.setFont(new Font("Courier", Font.BOLD, 22));
        txtBatch.setBackground(Color.BLACK);
        txtBatch.setForeground(Color.WHITE);
        add(txtBatch);

        JLabel text07 = new JLabel("Enter Available PC Number: ");
        text07.setBounds(1020, 640, 400, 50);
        text07.setFont(new Font("Courier", Font.BOLD, 22));
        text07.setForeground(Color.WHITE);
        add(text07);

        txtPCID = new JTextField();
        txtPCID.setBounds(1020, 690, 400, 30);
        txtPCID.setFont(new Font("Courier", Font.BOLD, 22));
        txtPCID.setBackground(Color.BLACK);
        txtPCID.setForeground(Color.WHITE);
        add(txtPCID);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(1020, 750, 400, 30);
        btnSubmit.setFont(new Font("Courier", Font.BOLD, 22));
        btnSubmit.setBackground(Color.DARK_GRAY);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.addActionListener(e -> submitRecord());
        add(btnSubmit);

        btnClose = new JButton("X");
        btnClose.setBounds(1470, 0, 50, 30);
        btnClose.setFont(new Font("Courier", Font.BOLD, 22));
        btnClose.setBackground(Color.RED);
        btnClose.setForeground(Color.WHITE);
        btnClose.addActionListener(e -> {dispose();});
        add(btnClose);
    }

    private void submitRecord() {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String branch = txtBranch.getText();
        String year = txtYear.getText();
        String xieID = txtXIEID.getText();
        String batch = txtBatch.getText();
        String pcNumber = txtPCID.getText().trim();  // Trim leading/trailing spaces
        
        if (firstName.isEmpty() || lastName.isEmpty() || branch.isEmpty() || year.isEmpty() || 
            xieID.isEmpty() || batch.isEmpty() || pcNumber.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }
    
        System.out.println("PC Number Entered: " + pcNumber);  // Log PC number for debugging
    
        try {
            Conn connection = new Conn();
            String fetchPCIDQuery = "SELECT pc_id FROM computers WHERE pc_number = ?";
            PreparedStatement fetchPCIDStmt = connection.getConnection().prepareStatement(fetchPCIDQuery);
            fetchPCIDStmt.setString(1, pcNumber);
    
            System.out.println(fetchPCIDStmt);  // Log the query for debugging
    
            ResultSet resultSet = fetchPCIDStmt.executeQuery();
    
            if (!resultSet.next()) {  // No PC found
                JOptionPane.showMessageDialog(null, "PC Number does not exist. Please enter a valid PC Number.");
                return;
            }
    
            int pcID = resultSet.getInt("pc_id");
    
            String query = "INSERT INTO students (first_name, last_name, branch, year, xie_id, batch, pc_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, branch);
            preparedStatement.setString(4, year);
            preparedStatement.setString(5, xieID);
            preparedStatement.setString(6, batch);
            preparedStatement.setInt(7, pcID);  
    
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Record Submitted Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to Submit Record");
            }
    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        new StudentsRecordFrame();
    }
}
