package bankmanagment;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton login, clear, singup;
    JTextField cardtextfield;
    JPasswordField pintextfield;

    // থিম কালার
    private final Color PRIMARY = new Color(25, 42, 86);
    private final Color ACCENT  = new Color(0, 173, 181);
    private final Color BG      = new Color(245, 247, 250);

    Login() {
        setTitle("AUTOMATED BANKING SYSTEM");
        setLayout(null);
        setSize(800, 480);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG);

        // ---- বাম পাশের কালার্ড সাইড প্যানেল ----
        JPanel sidePanel = new JPanel();
        sidePanel.setBounds(0, 0, 300, 480);
        sidePanel.setBackground(PRIMARY);
        sidePanel.setLayout(null);
        add(sidePanel);

        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
            Image i2 = i1.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel label = new JLabel(i3);
            label.setBounds(105, 60, 90, 90);
            sidePanel.add(label);
        } catch (Exception e) {
            // লোগো না থাকলেও ক্র্যাশ করবে না
        }

        JLabel text = new JLabel("<html><center>WELCOME TO<br>SMART ATM</center></html>");
        text.setFont(new Font("SansSerif", Font.BOLD, 24));
        text.setForeground(Color.WHITE);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setBounds(20, 170, 260, 80);
        sidePanel.add(text);

        JLabel tagline = new JLabel("<html><center>Fast. Secure. Reliable.</center></html>");
        tagline.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tagline.setForeground(new Color(200, 210, 230));
        tagline.setHorizontalAlignment(SwingConstants.CENTER);
        tagline.setBounds(20, 250, 260, 30);
        sidePanel.add(tagline);

        // ---- ডান পাশের ফর্ম ----
        JLabel formTitle = new JLabel("Sign In");
        formTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
        formTitle.setForeground(PRIMARY);
        formTitle.setBounds(350, 55, 300, 40);
        add(formTitle);

        JLabel cardno = new JLabel("Card Number");
        cardno.setFont(new Font("SansSerif", Font.PLAIN, 14));
        cardno.setForeground(Color.GRAY);
        cardno.setBounds(350, 125, 300, 20);
        add(cardno);

        cardtextfield = new JTextField(15);
        cardtextfield.setBounds(350, 148, 350, 38);
        cardtextfield.setFont(new Font("Arial", Font.PLAIN, 16));
        cardtextfield.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0, 0, 2, 0, ACCENT),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        cardtextfield.setBackground(BG);
        add(cardtextfield);

        JLabel pin = new JLabel("PIN");
        pin.setFont(new Font("SansSerif", Font.PLAIN, 14));
        pin.setForeground(Color.GRAY);
        pin.setBounds(350, 200, 300, 20);
        add(pin);

        pintextfield = new JPasswordField(15);
        pintextfield.setFont(new Font("Arial", Font.PLAIN, 16));
        pintextfield.setBounds(350, 223, 350, 38);
        pintextfield.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0, 0, 2, 0, ACCENT),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        pintextfield.setBackground(BG);
        add(pintextfield);

        login = new JButton("SIGN IN");
        login.setBounds(350, 295, 165, 42);
        login.setBackground(ACCENT);
        login.setForeground(Color.WHITE);
        login.setFont(new Font("SansSerif", Font.BOLD, 14));
        login.setFocusPainted(false);
        login.setBorderPainted(false);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.addActionListener(this);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(535, 295, 165, 42);
        clear.setBackground(new Color(230, 230, 230));
        clear.setForeground(Color.DARK_GRAY);
        clear.setFont(new Font("SansSerif", Font.BOLD, 14));
        clear.setFocusPainted(false);
        clear.setBorderPainted(false);
        clear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clear.addActionListener(this);
        add(clear);

        singup = new JButton("CREATE NEW ACCOUNT");
        singup.setBounds(350, 355, 350, 42);
        singup.setBackground(PRIMARY);
        singup.setForeground(Color.WHITE);
        singup.setFont(new Font("SansSerif", Font.BOLD, 14));
        singup.setFocusPainted(false);
        singup.setBorderPainted(false);
        singup.setCursor(new Cursor(Cursor.HAND_CURSOR));
        singup.addActionListener(this);
        add(singup);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == login) {
                Conn conn = new Conn();
                String cardnumber = cardtextfield.getText();
                String pinnumber = pintextfield.getText();
                // FIX: column name corrected from "cardno" to "cardnumber" to match the CREATE TABLE statement
                String query = "select * from login where cardnumber = '" + cardnumber + "' and pin = '" + pinnumber + "'";
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } else if (ae.getSource() == clear) {
                cardtextfield.setText("");
                pintextfield.setText("");
            } else if (ae.getSource() == singup) {
                setVisible(false);
                new SignupOne().setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}