import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class HomeFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
    private AdminFrame adminFrame;
    private StudentsRecordFrame studentsRecordFrame;
    private LoginFrame loginFrame;
    private RegisterFrame registerFrame;

    private JButton button01;
    private JButton button02;
    private JButton button03;
    private JButton button04;

    HomeFrame() {

        adminFrame = new AdminFrame();
        studentsRecordFrame = new StudentsRecordFrame();
        loginFrame = new LoginFrame();
        registerFrame = new RegisterFrame();
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
        ImageIcon image01 = new ImageIcon(ClassLoader.getSystemResource("Lab2.jpg"));
        Image set01 = image01.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        ImageIcon new01 = new ImageIcon(set01);
        JLabel image = new JLabel(new01);
        image.setBounds(0, 0, 1920, 1080);
        add(image);
        
        JLabel text01 = new JLabel("L A B - L O G I X");
        text01.setBounds(150, 400, 1000, 60);
        text01.setFont(new Font("Courier", Font.BOLD, 60));
        text01.setForeground(Color.BLACK);
        image.add(text01);
        
        JLabel text02 = new JLabel("Smart Solution for Lab Assistants");
        text02.setBounds(150, 450, 1000, 50);
        text02.setFont(new Font("Courier", Font.BOLD, 22));
        text02.setForeground(Color.BLACK);
        image.add(text02);

        button01 = new JButton("Admin");
        button01.setBounds(30, 10, 120, 30);
        button01.setFont(new Font("Courier", Font.BOLD, 18));
        button01.setContentAreaFilled(false);
        button01.setBorder(null);
        button01.setFocusable(false);
        button01.setForeground(Color.BLACK);
        button01.addMouseListener(new ButtonZoomEffect(button01));
        button01.addActionListener(this);
        image.add(button01);

        button02 = new JButton("Students Record");
        button02.setBounds(180, 10, 200, 30);
        button02.setFont(new Font("Courier", Font.BOLD, 18));
        button02.setContentAreaFilled(false);
        button02.setBorder(null);
        button02.setFocusable(false);
        button02.setForeground(Color.BLACK);
        button02.addMouseListener(new ButtonZoomEffect(button02));
        button02.addActionListener(this);
        image.add(button02);

        button03 = new JButton("Login");
        button03.setBounds(1620, 10, 120, 30);
        button03.setFont(new Font("Courier", Font.BOLD, 18));
        button03.setContentAreaFilled(false);
        button03.setBorder(null);
        button03.setFocusable(false);
        button03.setForeground(Color.BLACK);
        button03.addMouseListener(new ButtonZoomEffect(button03));
        button03.addActionListener(this);
        image.add(button03);

        button04 = new JButton("Register");
        button04.setBounds(1770, 10, 120, 30);
        button04.setFont(new Font("Courier", Font.BOLD, 18));
        button04.setContentAreaFilled(false);
        button04.setBorder(null);
        button04.setFocusable(false);
        button04.setForeground(Color.BLACK);
        button04.addMouseListener(new ButtonZoomEffect(button04));
        button04.addActionListener(this);
        image.add(button04);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == button01) {
            adminFrame.setVisible(true);
        }
        if(ae.getSource() == button02) {
            studentsRecordFrame.setVisible(true);
        }
        if(ae.getSource() == button03) {
            loginFrame.setVisible(true);
        }
        if(ae.getSource() == button04) {
            registerFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new HomeFrame();
    }

}