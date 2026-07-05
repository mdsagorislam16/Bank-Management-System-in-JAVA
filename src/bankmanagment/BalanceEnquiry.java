package bankmanagment;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.border.*;

class BalanceEnquiry extends JFrame implements ActionListener {
    JButton back;
    JLabel amountLabel;
    String pinnumber;

    // থিম কালার (Login / PinChange পেজের সাথে মিল রেখে)
    private final Color PRIMARY  = new Color(25, 42, 86);
    private final Color ACCENT   = new Color(0, 173, 181);
    private final Color BG       = new Color(245, 247, 250);
    private final Color POSITIVE = new Color(0, 150, 90);
    private final Color NEGATIVE = new Color(200, 50, 50);

    BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;
        setTitle("Balance Enquiry");
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

        JLabel sideText = new JLabel("<html><center>YOUR ACCOUNT<br>AT A GLANCE</center></html>");
        sideText.setFont(new Font("SansSerif", Font.BOLD, 22));
        sideText.setForeground(Color.WHITE);
        sideText.setHorizontalAlignment(SwingConstants.CENTER);
        sideText.setBounds(20, 170, 260, 80);
        sidePanel.add(sideText);

        JLabel tagline = new JLabel("<html><center>Real-time balance check.</center></html>");
        tagline.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tagline.setForeground(new Color(200, 210, 230));
        tagline.setHorizontalAlignment(SwingConstants.CENTER);
        tagline.setBounds(20, 250, 260, 30);
        sidePanel.add(tagline);

        // ---------- RIGHT SIDE: BALANCE CARD ----------
        JLabel formTitle = new JLabel("Account Balance");
        formTitle.setFont(new Font("SansSerif", Font.BOLD, 28));
        formTitle.setForeground(PRIMARY);
        formTitle.setBounds(350, 55, 400, 40);
        add(formTitle);

        JLabel subtitle = new JLabel("Your current available balance");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(Color.GRAY);
        subtitle.setBounds(350, 95, 350, 20);
        add(subtitle);

        // Card panel holding the big balance number
        JPanel balanceCard = new JPanel();
        balanceCard.setLayout(null);
        balanceCard.setBackground(Color.WHITE);
        balanceCard.setBounds(350, 140, 380, 150);
        balanceCard.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0, 0, 0, 0, BG),
                BorderFactory.createEmptyBorder()));
        add(balanceCard);

        JLabel currencyLabel = new JLabel("TAKA");
        currencyLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        currencyLabel.setForeground(Color.GRAY);
        currencyLabel.setBounds(20, 15, 340, 20);
        balanceCard.add(currencyLabel);

        amountLabel = new JLabel("0");
        amountLabel.setFont(new Font("SansSerif", Font.BOLD, 56));
        amountLabel.setForeground(PRIMARY);
        amountLabel.setBounds(15, 40, 350, 80);
        balanceCard.add(amountLabel);

        // subtle accent underline beneath the card
        JPanel underline = new JPanel();
        underline.setBackground(ACCENT);
        underline.setBounds(350, 300, 60, 4);
        add(underline);

        back = new JButton("BACK");
        back.setBounds(350, 330, 165, 42);
        back.setBackground(ACCENT);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("SansSerif", Font.BOLD, 14));
        back.setFocusPainted(false);
        back.setBorderPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(this);
        add(back);

        // ---------- FETCH BALANCE ----------
        int balance = 0;
        try {
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from bank where pin = '" + pinnumber + "'");
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching balance: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        amountLabel.setText(String.valueOf(balance));
        amountLabel.setForeground(balance >= 0 ? POSITIVE : NEGATIVE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("").setVisible(true);
    }
}