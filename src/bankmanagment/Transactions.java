package bankmanagment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener {
    JButton deposit, withdrawl, fastcash, ministatement, picchange, balanceenquiry, exit;
    String pinnumber;

    // থিম কালার (Login / Deposit / Withdrawl এর সাথে মিল রেখে)
    private final Color PRIMARY = new Color(25, 42, 86);
    private final Color ACCENT  = new Color(0, 173, 181);
    private final Color BG      = new Color(245, 247, 250);

    Transactions(String pinnumber) {
        this.pinnumber = pinnumber;
        setTitle("Transactions");
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

        JLabel sideText = new JLabel("<html><center>WHAT WOULD YOU<br>LIKE TO DO?</center></html>");
        sideText.setFont(new Font("SansSerif", Font.BOLD, 22));
        sideText.setForeground(Color.WHITE);
        sideText.setHorizontalAlignment(SwingConstants.CENTER);
        sideText.setBounds(20, 170, 260, 80);
        sidePanel.add(sideText);

        JLabel tagline = new JLabel("<html><center>Choose a transaction below.</center></html>");
        tagline.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tagline.setForeground(new Color(200, 210, 230));
        tagline.setHorizontalAlignment(SwingConstants.CENTER);
        tagline.setBounds(20, 250, 260, 30);
        sidePanel.add(tagline);

        // ---------- RIGHT SIDE: MENU GRID ----------
        JLabel formTitle = new JLabel("Select Transaction");
        formTitle.setFont(new Font("SansSerif", Font.BOLD, 26));
        formTitle.setForeground(PRIMARY);
        formTitle.setBounds(350, 40, 400, 40);
        add(formTitle);

        String[] labels = {
            "DEPOSIT", "CASH WITHDRAWAL", "FAST CASH",
            "MINI STATEMENT", "PIN CHANGE", "BALANCE ENQUIRY"
        };
        JButton[] buttons = new JButton[6];

        int startX = 350, startY = 90, w = 165, h = 55, gapX = 20, gapY = 15;
        for (int i = 0; i < 6; i++) {
            int row = i / 2;
            int col = i % 2;
            JButton menuBtn = new JButton("<html><center>" + labels[i] + "</center></html>");
            menuBtn.setBounds(startX + col * (w + gapX), startY + row * (h + gapY), w, h);
            menuBtn.setBackground(Color.WHITE);
            menuBtn.setForeground(PRIMARY);
            menuBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
            menuBtn.setFocusPainted(false);
            menuBtn.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230), 2, true));
            menuBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            menuBtn.addActionListener(this);
            add(menuBtn);
            buttons[i] = menuBtn;
        }
        deposit = buttons[0];
        withdrawl = buttons[1];
        fastcash = buttons[2];
        ministatement = buttons[3];
        picchange = buttons[4];
        balanceenquiry = buttons[5];

        exit = new JButton("EXIT");
        exit.setBounds(350, 315, 350, 42);
        exit.setBackground(ACCENT);
        exit.setForeground(Color.WHITE);
        exit.setFont(new Font("SansSerif", Font.BOLD, 14));
        exit.setFocusPainted(false);
        exit.setBorderPainted(false);
        exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exit.addActionListener(this);
        add(exit);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deposit) {
            setVisible(false);
            new Deposit(pinnumber).setVisible(true);
        } else if (ae.getSource() == withdrawl) {
            setVisible(false);
            new Withdrawl(pinnumber).setVisible(true);
        } else if (ae.getSource() == fastcash) {
            setVisible(false);
            new FastCash(pinnumber).setVisible(true);
        } else if (ae.getSource() == ministatement) {
            new MiniStatement(pinnumber).setVisible(true);
        } else if (ae.getSource() == picchange) {
            setVisible(false);
            new PinChange(pinnumber).setVisible(true);
        } else if (ae.getSource() == balanceenquiry) {
            this.setVisible(false);
            new BalanceEnquiry(pinnumber).setVisible(true);
        } else if (ae.getSource() == exit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Transactions("").setVisible(true);
    }
}