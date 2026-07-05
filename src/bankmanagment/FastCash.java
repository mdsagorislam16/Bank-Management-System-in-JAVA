package bankmanagment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4, b5, b6, back;
    String pinnumber;

    // থিম কালার (Login / Deposit / BalanceEnquiry এর সাথে মিল রেখে)
    private final Color PRIMARY = new Color(25, 42, 86);
    private final Color ACCENT  = new Color(0, 173, 181);
    private final Color BG      = new Color(245, 247, 250);

    FastCash(String pinnumber) {
        this.pinnumber = pinnumber;
        setTitle("Fast Cash");
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

        JLabel sideText = new JLabel("<html><center>QUICK CASH<br>WITHDRAWAL</center></html>");
        sideText.setFont(new Font("SansSerif", Font.BOLD, 22));
        sideText.setForeground(Color.WHITE);
        sideText.setHorizontalAlignment(SwingConstants.CENTER);
        sideText.setBounds(20, 170, 260, 80);
        sidePanel.add(sideText);

        JLabel tagline = new JLabel("<html><center>Pick an amount below.</center></html>");
        tagline.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tagline.setForeground(new Color(200, 210, 230));
        tagline.setHorizontalAlignment(SwingConstants.CENTER);
        tagline.setBounds(20, 250, 260, 30);
        sidePanel.add(tagline);

        // ---------- RIGHT SIDE: AMOUNT GRID ----------
        JLabel formTitle = new JLabel("Select Amount");
        formTitle.setFont(new Font("SansSerif", Font.BOLD, 28));
        formTitle.setForeground(PRIMARY);
        formTitle.setBounds(350, 45, 350, 40);
        add(formTitle);

        JLabel subtitle = new JLabel("Choose how much you want to withdraw");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(Color.GRAY);
        subtitle.setBounds(350, 85, 350, 20);
        add(subtitle);

        String[] labels = {"TAKA 100", "TAKA 500", "TAKA 1000", "TAKA 2000", "TAKA 5000", "TAKA 10000"};
        JButton[] buttons = new JButton[6];

        int startX = 350, startY = 125, w = 165, h = 55, gapX = 20, gapY = 15;
        for (int i = 0; i < 6; i++) {
            int row = i / 2;
            int col = i % 2;
            JButton amtBtn = new JButton(labels[i]);
            amtBtn.setBounds(startX + col * (w + gapX), startY + row * (h + gapY), w, h);
            amtBtn.setBackground(Color.WHITE);
            amtBtn.setForeground(PRIMARY);
            amtBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
            amtBtn.setFocusPainted(false);
            amtBtn.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230), 2, true));
            amtBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            amtBtn.addActionListener(this);
            add(amtBtn);
            buttons[i] = amtBtn;
        }
        b1 = buttons[0]; b2 = buttons[1]; b3 = buttons[2];
        b4 = buttons[3]; b5 = buttons[4]; b6 = buttons[5];

        back = new JButton("BACK");
        back.setBounds(350, 350, 350, 42);
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
            if (ae.getSource() == back) {
                this.setVisible(false);
                new Transactions(pinnumber).setVisible(true);
                return;
            }
            String amount = ((JButton) ae.getSource()).getText().substring(5); //k
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + pinnumber + "'");
            int balance = 0;
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            if (balance < Integer.parseInt(amount)) {
                JOptionPane.showMessageDialog(null, "Insuffient Balance");
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date());
            c.s.executeUpdate("insert into bank values('" + pinnumber + "', '" + date + "', 'Withdrawl', '" + amount + "')");
            JOptionPane.showMessageDialog(null, "TAKA. " + amount + " Debited Successfully");
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new FastCash("").setVisible(true);
    }
}