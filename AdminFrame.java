import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame {
    private JTable studentsTable;
    private DefaultTableModel tableModel;
    private JButton btnClose;

    private JButton btnStudentsRecords;
    private JButton btnItemDetails;
    private JButton btnAddNewItem;
    private JButton btnAddDamageItem;
    private JButton btnAddLostItem;

    AdminFrame() {
        // Frame settings
        setUndecorated(true);
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setOpacity(1f);
        setBounds(200, 100, 1520, 880);

        // Close Button
        btnClose = new JButton("X");
        btnClose.setBounds(1470, 0, 50, 30);
        btnClose.setFont(new Font("Courier", Font.BOLD, 22));
        btnClose.setBackground(Color.RED);
        btnClose.setForeground(Color.WHITE);
        btnClose.setBorderPainted(false);
        btnClose.setFocusPainted(false);
        btnClose.addActionListener(e -> dispose());
        add(btnClose);
        
        btnStudentsRecords = new JButton("Students Records");
        btnStudentsRecords.setBounds(100, 100, 240, 30);
        btnStudentsRecords.setFont(new Font("Courier", Font.BOLD, 18));
        btnStudentsRecords.setBorder(null);
        btnStudentsRecords.setFocusable(false);
        btnStudentsRecords.setBackground(Color.WHITE);
        btnStudentsRecords.setForeground(Color.BLACK);
        btnStudentsRecords.addMouseListener(new ButtonZoomEffect(btnStudentsRecords));
        add(btnStudentsRecords);
        
        btnItemDetails = new JButton("Item Details");
        btnItemDetails.setBounds(370, 100, 240, 30);
        btnItemDetails.setFont(new Font("Courier", Font.BOLD, 18));
        btnItemDetails.setBorder(null);
        btnItemDetails.setFocusable(false);
        btnItemDetails.setBackground(Color.WHITE);
        btnItemDetails.setForeground(Color.BLACK);
        btnItemDetails.addMouseListener(new ButtonZoomEffect(btnItemDetails));
        add(btnItemDetails);
        
        btnAddNewItem = new JButton("Add New Item");
        btnAddNewItem.setBounds(640, 100, 240, 30);
        btnAddNewItem.setFont(new Font("Courier", Font.BOLD, 18));
        btnAddNewItem.setBorder(null);
        btnAddNewItem.setFocusable(false);
        btnAddNewItem.setBackground(Color.WHITE);
        btnAddNewItem.setForeground(Color.BLACK);
        btnAddNewItem.addMouseListener(new ButtonZoomEffect(btnAddNewItem));
        add(btnAddNewItem);
        
        btnAddDamageItem = new JButton("Add Damage Item");
        btnAddDamageItem.setBounds(910, 100, 240, 30);
        btnAddDamageItem.setFont(new Font("Courier", Font.BOLD, 18));
        btnAddDamageItem.setBorder(null);
        btnAddDamageItem.setFocusable(false);
        btnAddDamageItem.setBackground(Color.WHITE);
        btnAddDamageItem.setForeground(Color.BLACK);
        btnAddDamageItem.addMouseListener(new ButtonZoomEffect(btnAddDamageItem));
        add(btnAddDamageItem);
        
        btnAddLostItem = new JButton("Add Lost Item");
        btnAddLostItem.setBounds(1180, 100, 240, 30);
        btnAddLostItem.setFont(new Font("Courier", Font.BOLD, 18));
        btnAddLostItem.setBorder(null);
        btnAddLostItem.setFocusable(false);
        btnAddLostItem.setBackground(Color.WHITE);
        btnAddLostItem.setForeground(Color.BLACK);
        btnAddLostItem.addMouseListener(new ButtonZoomEffect(btnAddLostItem));
        add(btnAddLostItem);
        
        btnStudentsRecords.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTableWithStudentRecords();
            }
        });

        btnItemDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTableWithItemDetails();
            }
        });

        btnAddNewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTableWithNewItemForm();
            }
        });

        btnAddDamageItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTableWithDamageItemForm();
            }
        });

        btnAddLostItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTableWithLostItemForm();
            }
        });
    }

    // Methods to update the table based on button selection
    private void updateTableWithStudentRecords() {
        getContentPane().removeAll();
        repaint(); 

        add(btnClose);
        add(btnStudentsRecords);
        add(btnItemDetails);
        add(btnAddNewItem);
        add(btnAddDamageItem);
        add(btnAddLostItem);
        
        // Table model and setup
        tableModel = new DefaultTableModel(new String[]{"Student ID", "First Name", "Last Name", "Branch", "Year", "XIE ID", "Batch", "PC ID"}, 0);
        studentsTable = new JTable(tableModel);
        studentsTable.setForeground(Color.WHITE);
        studentsTable.setBackground(Color.DARK_GRAY);
        studentsTable.setFont(new Font("Courier", Font.PLAIN, 16));
        studentsTable.setRowHeight(30);

        // Set alternating row colors
        studentsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    cell.setBackground(Color.DARK_GRAY); // Even row color
                } else {
                    cell.setBackground(Color.GRAY); // Odd row color
                }
                return cell;
            }
        });

        // Table header customization
        JTableHeader header = studentsTable.getTableHeader();
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Courier", Font.BOLD, 18));
        header.setReorderingAllowed(false);

        // Scroll Pane for the table
        JScrollPane scrollPane = new JScrollPane(studentsTable);
        scrollPane.setBounds(100, 150, 1320, 630);
        add(scrollPane);

        loadStudentRecords();
    }

    private void updateTableWithItemDetails() {
        getContentPane().removeAll();
        repaint();

        add(btnClose);
        add(btnStudentsRecords);
        add(btnItemDetails);
        add(btnAddNewItem);
        add(btnAddDamageItem);
        add(btnAddLostItem);
        
        // Table model and setup for item details
        tableModel = new DefaultTableModel(new String[]{"Item ID", "Item Type", "Item Name", "Serial Number", "PC ID", "Status"}, 0);
        JTable itemTable = new JTable(tableModel);
        itemTable.setForeground(Color.WHITE);
        itemTable.setBackground(Color.DARK_GRAY);
        itemTable.setFont(new Font("Courier", Font.PLAIN, 16));
        itemTable.setRowHeight(30);
    
        // Set alternating row colors
        itemTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    cell.setBackground(Color.DARK_GRAY); // Even row color
                } else {
                    cell.setBackground(Color.GRAY); // Odd row color
                }
                return cell;
            }
        });
    
        // Table header customization
        JTableHeader header = itemTable.getTableHeader();
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Courier", Font.BOLD, 18));
        header.setReorderingAllowed(false);
    
        // Scroll Pane for the item table
        JScrollPane scrollPane = new JScrollPane(itemTable);
        scrollPane.setBounds(100, 150, 1320, 630);
        add(scrollPane);
    
        loadItemDetails();
    }

    private void updateTableWithNewItemForm() {
        getContentPane().removeAll();
        repaint();

        add(btnClose);
        add(btnStudentsRecords);
        add(btnItemDetails);
        add(btnAddNewItem);
        add(btnAddDamageItem);
        add(btnAddLostItem);

        ImageIcon student = new ImageIcon(ClassLoader.getSystemResource("New Item.jpg"));
        Image set01 = student.getImage().getScaledInstance(820, 630, Image.SCALE_SMOOTH);
        ImageIcon new01 = new ImageIcon(set01);
        JLabel image = new JLabel(new01);
        image.setBounds(100, 150, 820, 630);
        add(image);
    
        JLabel lblItemType = new JLabel("Item Type:");
        lblItemType.setFont(new Font("Courier", Font.BOLD, 18));
        lblItemType.setBounds(1020, 150, 400, 50);
        lblItemType.setForeground(Color.WHITE);
        add(lblItemType);
    
        JTextField txtItemType = new JTextField();
        txtItemType.setFont(new Font("Courier", Font.PLAIN, 16));
        txtItemType.setBounds(1020, 200, 400, 30);
        txtItemType.setBackground(Color.BLACK);
        txtItemType.setForeground(Color.WHITE);
        add(txtItemType);
        
        JLabel lblItemName = new JLabel("Item Name:");
        lblItemName.setFont(new Font("Courier", Font.BOLD, 18));
        lblItemName.setBounds(1020, 240, 400, 50);
        lblItemName.setForeground(Color.WHITE);
        add(lblItemName);
        
        JTextField txtItemName = new JTextField();
        txtItemName.setFont(new Font("Courier", Font.PLAIN, 16));
        txtItemName.setBounds(1020, 290, 400, 30);
        txtItemName.setBackground(Color.BLACK);
        txtItemName.setForeground(Color.WHITE);
        add(txtItemName);
        
        JLabel lblSerialNumber = new JLabel("Serial Number:");
        lblSerialNumber.setFont(new Font("Courier", Font.BOLD, 18));
        lblSerialNumber.setBounds(1020, 330, 400, 50);
        lblSerialNumber.setForeground(Color.WHITE);
        add(lblSerialNumber);
        
        JTextField txtSerialNumber = new JTextField();
        txtSerialNumber.setFont(new Font("Courier", Font.PLAIN, 16));
        txtSerialNumber.setBounds(1020, 380, 400, 30);
        txtSerialNumber.setBackground(Color.BLACK);
        txtSerialNumber.setForeground(Color.WHITE);
        add(txtSerialNumber);
        
        JLabel lblPCID = new JLabel("PC ID:");
        lblPCID.setFont(new Font("Courier", Font.BOLD, 18));
        lblPCID.setBounds(1020, 420, 400, 50);
        lblPCID.setForeground(Color.WHITE);
        add(lblPCID);
    
        JTextField txtPCID = new JTextField();
        txtPCID.setFont(new Font("Courier", Font.PLAIN, 16));
        txtPCID.setBounds(1020, 470, 400, 30);
        txtPCID.setBackground(Color.BLACK);
        txtPCID.setForeground(Color.WHITE);
        add(txtPCID);
        
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(1020, 750, 400, 30);
        btnSubmit.setFont(new Font("Courier", Font.BOLD, 22));
        btnSubmit.setBackground(Color.DARK_GRAY);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.addActionListener(e -> {
            addNewItemToDatabase(
                txtItemType.getText(), 
                txtItemName.getText(), 
                txtSerialNumber.getText(), 
                txtPCID.getText()
            );
        });
        add(btnSubmit);
    
        JButton btnClose = new JButton("X");
        btnClose.setBounds(1470, 0, 50, 30);
        btnClose.setFont(new Font("Courier", Font.BOLD, 22));
        btnClose.setBackground(Color.RED);
        btnClose.setForeground(Color.WHITE);
        btnClose.addActionListener(e -> dispose());
        add(btnClose);
    
        revalidate();
        repaint();
    }

    private void updateTableWithDamageItemForm() {
        getContentPane().removeAll();
        repaint();

        add(btnClose);
        add(btnStudentsRecords);
        add(btnItemDetails);
        add(btnAddNewItem);
        add(btnAddDamageItem);
        add(btnAddLostItem);

        ImageIcon student = new ImageIcon(ClassLoader.getSystemResource("Damage Item.jpg"));
        Image set01 = student.getImage().getScaledInstance(820, 630, Image.SCALE_SMOOTH);
        ImageIcon new01 = new ImageIcon(set01);
        JLabel image = new JLabel(new01);
        image.setBounds(100, 150, 820, 630);
        add(image);
    
        JLabel lblItemType = new JLabel("Item Type:");
        lblItemType.setFont(new Font("Courier", Font.BOLD, 18));
        lblItemType.setBounds(1020, 150, 400, 50);
        lblItemType.setForeground(Color.WHITE);
        add(lblItemType);
    
        JTextField txtItemType = new JTextField();
        txtItemType.setFont(new Font("Courier", Font.PLAIN, 16));
        txtItemType.setBounds(1020, 200, 400, 30);
        txtItemType.setBackground(Color.BLACK);
        txtItemType.setForeground(Color.WHITE);
        add(txtItemType);
        
        JLabel lblItemName = new JLabel("Item Name:");
        lblItemName.setFont(new Font("Courier", Font.BOLD, 18));
        lblItemName.setBounds(1020, 240, 400, 50);
        lblItemName.setForeground(Color.WHITE);
        add(lblItemName);
        
        JTextField txtItemName = new JTextField();
        txtItemName.setFont(new Font("Courier", Font.PLAIN, 16));
        txtItemName.setBounds(1020, 290, 400, 30);
        txtItemName.setBackground(Color.BLACK);
        txtItemName.setForeground(Color.WHITE);
        add(txtItemName);
        
        JLabel lblSerialNumber = new JLabel("Serial Number:");
        lblSerialNumber.setFont(new Font("Courier", Font.BOLD, 18));
        lblSerialNumber.setBounds(1020, 330, 400, 50);
        lblSerialNumber.setForeground(Color.WHITE);
        add(lblSerialNumber);
        
        JTextField txtSerialNumber = new JTextField();
        txtSerialNumber.setFont(new Font("Courier", Font.PLAIN, 16));
        txtSerialNumber.setBounds(1020, 380, 400, 30);
        txtSerialNumber.setBackground(Color.BLACK);
        txtSerialNumber.setForeground(Color.WHITE);
        add(txtSerialNumber);
        
        JLabel lblPCID = new JLabel("PC ID:");
        lblPCID.setFont(new Font("Courier", Font.BOLD, 18));
        lblPCID.setBounds(1020, 420, 400, 50);
        lblPCID.setForeground(Color.WHITE);
        add(lblPCID);
    
        JTextField txtPCID = new JTextField();
        txtPCID.setFont(new Font("Courier", Font.PLAIN, 16));
        txtPCID.setBounds(1020, 470, 400, 30);
        txtPCID.setBackground(Color.BLACK);
        txtPCID.setForeground(Color.WHITE);
        add(txtPCID);
        
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(1020, 750, 400, 30);
        btnSubmit.setFont(new Font("Courier", Font.BOLD, 22));
        btnSubmit.setBackground(Color.DARK_GRAY);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.addActionListener(e -> {
            addDamageItemToDatabase(
                txtItemType.getText(), 
                txtItemName.getText(), 
                txtSerialNumber.getText(), 
                txtPCID.getText()
            );
        });
        add(btnSubmit);
    
        JButton btnClose = new JButton("X");
        btnClose.setBounds(1470, 0, 50, 30);
        btnClose.setFont(new Font("Courier", Font.BOLD, 22));
        btnClose.setBackground(Color.RED);
        btnClose.setForeground(Color.WHITE);
        btnClose.addActionListener(e -> dispose());
        add(btnClose);
    
        revalidate();
        repaint();
    }

    private void updateTableWithLostItemForm() {
        getContentPane().removeAll();
        repaint();

        add(btnClose);
        add(btnStudentsRecords);
        add(btnItemDetails);
        add(btnAddNewItem);
        add(btnAddDamageItem);
        add(btnAddLostItem);

        ImageIcon student = new ImageIcon(ClassLoader.getSystemResource("Lost Item.jpg"));
        Image set01 = student.getImage().getScaledInstance(820, 630, Image.SCALE_SMOOTH);
        ImageIcon new01 = new ImageIcon(set01);
        JLabel image = new JLabel(new01);
        image.setBounds(100, 150, 820, 630);
        add(image);
    
        JLabel lblItemType = new JLabel("Item Type:");
        lblItemType.setFont(new Font("Courier", Font.BOLD, 18));
        lblItemType.setBounds(1020, 150, 400, 50);
        lblItemType.setForeground(Color.WHITE);
        add(lblItemType);
    
        JTextField txtItemType = new JTextField();
        txtItemType.setFont(new Font("Courier", Font.PLAIN, 16));
        txtItemType.setBounds(1020, 200, 400, 30);
        txtItemType.setBackground(Color.BLACK);
        txtItemType.setForeground(Color.WHITE);
        add(txtItemType);
        
        JLabel lblItemName = new JLabel("Item Name:");
        lblItemName.setFont(new Font("Courier", Font.BOLD, 18));
        lblItemName.setBounds(1020, 240, 400, 50);
        lblItemName.setForeground(Color.WHITE);
        add(lblItemName);
        
        JTextField txtItemName = new JTextField();
        txtItemName.setFont(new Font("Courier", Font.PLAIN, 16));
        txtItemName.setBounds(1020, 290, 400, 30);
        txtItemName.setBackground(Color.BLACK);
        txtItemName.setForeground(Color.WHITE);
        add(txtItemName);
        
        JLabel lblSerialNumber = new JLabel("Serial Number:");
        lblSerialNumber.setFont(new Font("Courier", Font.BOLD, 18));
        lblSerialNumber.setBounds(1020, 330, 400, 50);
        lblSerialNumber.setForeground(Color.WHITE);
        add(lblSerialNumber);
        
        JTextField txtSerialNumber = new JTextField();
        txtSerialNumber.setFont(new Font("Courier", Font.PLAIN, 16));
        txtSerialNumber.setBounds(1020, 380, 400, 30);
        txtSerialNumber.setBackground(Color.BLACK);
        txtSerialNumber.setForeground(Color.WHITE);
        add(txtSerialNumber);
        
        JLabel lblPCID = new JLabel("PC ID:");
        lblPCID.setFont(new Font("Courier", Font.BOLD, 18));
        lblPCID.setBounds(1020, 420, 400, 50);
        lblPCID.setForeground(Color.WHITE);
        add(lblPCID);
    
        JTextField txtPCID = new JTextField();
        txtPCID.setFont(new Font("Courier", Font.PLAIN, 16));
        txtPCID.setBounds(1020, 470, 400, 30);
        txtPCID.setBackground(Color.BLACK);
        txtPCID.setForeground(Color.WHITE);
        add(txtPCID);
        
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(1020, 750, 400, 30);
        btnSubmit.setFont(new Font("Courier", Font.BOLD, 22));
        btnSubmit.setBackground(Color.DARK_GRAY);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.addActionListener(e -> {
            addLostItemToDatabase(
                txtItemType.getText(), 
                txtItemName.getText(), 
                txtSerialNumber.getText(), 
                txtPCID.getText()
            );
        });
        add(btnSubmit);
    
        JButton btnClose = new JButton("X");
        btnClose.setBounds(1470, 0, 50, 30);
        btnClose.setFont(new Font("Courier", Font.BOLD, 22));
        btnClose.setBackground(Color.RED);
        btnClose.setForeground(Color.WHITE);
        btnClose.addActionListener(e -> dispose());
        add(btnClose);
    
        revalidate();
        repaint();
    }

    private void loadStudentRecords() {
        // Declare the connection and prepared statement outside the try block
        Conn connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            connection = new Conn();
            String query = "SELECT s.student_id, s.first_name, s.last_name, s.branch, s.year, s.xie_id, s.batch, c.pc_id " +
                           "FROM students s " +
                           "JOIN computers c ON s.pc_id = c.pc_id";
            preparedStatement = connection.getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
    
            // Clear existing rows
            tableModel.setRowCount(0);
    
            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String branch = resultSet.getString("branch");
                String year = resultSet.getString("year");
                String xieID = resultSet.getString("xie_id");
                String batch = resultSet.getString("batch");
                int pcID = resultSet.getInt("pc_id");
    
                // Add row to the table model
                tableModel.addRow(new Object[]{studentId, firstName, lastName, branch, year, xieID, batch, pcID});
            }
    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading student records: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources in the finally block to ensure they are closed
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void loadItemDetails() {
        // Declare the connection and prepared statement outside the try block
        Conn connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            connection = new Conn();
            String query = "SELECT i.item_id, i.item_type, i.item_name, i.serial_number, c.pc_id, i.status " +
                           "FROM inventory i " +
                           "JOIN computers c ON i.pc_id = c.pc_id";
            preparedStatement = connection.getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
    
            // Clear existing rows
            tableModel.setRowCount(0);
    
            while (resultSet.next()) {
                int itemId = resultSet.getInt("item_id");
                String itemType = resultSet.getString("item_type");
                String itemName = resultSet.getString("item_name");
                String serialNumber = resultSet.getString("serial_number");
                int pcID = resultSet.getInt("pc_id");
                String status = resultSet.getString("status");
    
                // Add row to the table model
                tableModel.addRow(new Object[]{itemId, itemType, itemName, serialNumber, pcID, status});
            }
    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading item details: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources in the finally block to ensure they are closed
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void addNewItemToDatabase(String itemType, String itemName, String serialNumber, String pcID) {
        Conn connection = null;
        PreparedStatement preparedStatement = null;
    
        try {
            connection = new Conn();
            String query = "INSERT INTO inventory (item_type, item_name, serial_number, pc_id, status) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, itemType);
            preparedStatement.setString(2, itemName);
            preparedStatement.setString(3, serialNumber);
            preparedStatement.setString(4, pcID);
            preparedStatement.setString(5, "active");
    
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Item added successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add item.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding item: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources in the finally block to ensure they are closed
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void addDamageItemToDatabase(String itemType, String itemName, String serialNumber, String pcID) {
        Conn connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = new Conn();
            // Update the existing item's status to 'damaged'
            String query = "UPDATE inventory SET status = ? WHERE serial_number = ?";
            preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, "damaged");
            preparedStatement.setString(2, serialNumber);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Item status updated to 'damaged' successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Item not found or failed to update.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating item status: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources in the finally block to ensure they are closed
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void addLostItemToDatabase(String itemType, String itemName, String serialNumber, String pcID) {
        Conn connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = new Conn();
            // Update the existing item's status to 'damaged'
            String query = "UPDATE inventory SET status = ? WHERE serial_number = ?";
            preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, "lost");
            preparedStatement.setString(2, serialNumber);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Item status updated to 'lost' successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Item not found or failed to update.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating item status: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources in the finally block to ensure they are closed
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    

    public static void main(String[] args) {
        new AdminFrame();
    }
}
