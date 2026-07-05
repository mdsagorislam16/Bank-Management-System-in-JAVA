package bankmanagment;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Deposit extends JFrame implements ActionListener {
    JTextField amount;
    JButton deposit, back;
    String pinnumber;

    // থিম কালার (Login / PinChange / BalanceEnquiry এর সাথে মিল রেখে)
    private final Color PRIMARY = new Color(25, 42, 86);
    private final Color ACCENT  = new Color(0, 173, 181);
    private final Color BG      = new Color(245, 247, 250);

    Deposit(String pinnumber) {
        this.pinnumber = pinnumber;
        setTitle("Deposit");
        setLayout(null);
        setSize(800, 480);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG);

        // ---------- LEFT SIDE PANEL ----------
        JPanel sidePanel = new JPanel();
        sidePanel.setBounds(0, 0, 300, 480);
        sidePanel.setBackground(PRIMARY);
        sidePanel.setLayout(null);
        add(sidePanel);

        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
            Image i2 = i1.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel logoLabel = new JLabel(i3);
            logoLabel.setBounds(105, 60, 90, 90);
            sidePanel.add(logoLabel);
        } catch (Exception e) {
            // লোগো না থাকলেও ক্র্যাশ করবে না
        }

        JLabel sideText = new JLabel("<html><center>ADD MONEY TO<br>YOUR ACCOUNT</center></html>");
        sideText.setFont(new Font("SansSerif", Font.BOLD, 22));
        sideText.setForeground(Color.WHITE);
        sideText.setHorizontalAlignment(SwingConstants.CENTER);
        sideText.setBounds(20, 170, 260, 80);
        sidePanel.add(sideText);

        JLabel tagline = new JLabel("<html><center>Fast, secure deposits.</center></html>");
        tagline.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tagline.setForeground(new Color(200, 210, 230));
        tagline.setHorizontalAlignment(SwingConstants.CENTER);
        tagline.setBounds(20, 250, 260, 30);
        sidePanel.add(tagline);

        // ---------- RIGHT SIDE FORM ----------
        JLabel formTitle = new JLabel("Deposit Amount");
        formTitle.setFont(new Font("SansSerif", Font.BOLD, 28));
        formTitle.setForeground(PRIMARY);
        formTitle.setBounds(350, 55, 350, 40);
        add(formTitle);

        JLabel subtitle = new JLabel("Enter the amount you want to deposit");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(Color.GRAY);
        subtitle.setBounds(350, 95, 350, 20);
        add(subtitle);

        JLabel amountLabel = new JLabel("Amount (TAKA)");
        amountLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        amountLabel.setForeground(Color.GRAY);
        amountLabel.setBounds(350, 150, 300, 20);
        add(amountLabel);

        amount = new JTextField();
        amount.setFont(new Font("Arial", Font.PLAIN, 22));
        amount.setBounds(350, 173, 350, 42);
        amount.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0, 0, 2, 0, ACCENT),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        amount.setBackground(BG);
        add(amount);

        deposit = new JButton("DEPOSIT");
        deposit.setBounds(350, 250, 165, 42);
        deposit.setBackground(ACCENT);
        deposit.setForeground(Color.WHITE);
        deposit.setFont(new Font("SansSerif", Font.BOLD, 14));
        deposit.setFocusPainted(false);
        deposit.setBorderPainted(false);
        deposit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deposit.addActionListener(this);
        add(deposit);

        back = new JButton("BACK");
        back.setBounds(535, 250, 165, 42);
        back.setBackground(new Color(230, 230, 230));
        back.setForeground(Color.DARK_GRAY);
        back.setFont(new Font("SansSerif", Font.BOLD, 14));
        back.setFocusPainted(false);
        back.setBorderPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String number = amount.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date());
            if (ae.getSource() == deposit) {
                if (amount.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Deposit");
                } else {
                    Conn conn = new Conn();
                    conn.s.executeUpdate("insert into bank values('" + pinnumber + "', '" + date + "', 'Deposit', '" + number + "')");
                    JOptionPane.showMessageDialog(null, "TAKA. " + number + " Deposited Successfully");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }
            } else if (ae.getSource() == back) {
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Deposit("").setVisible(true);
    }
}