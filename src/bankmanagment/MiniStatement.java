package bankmanagment;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener {

    JButton exitBtn;
    JLabel cardLabel, balanceLabel;
    JTable table;
    String pinnumber;

    // থিম কালার (Login পেজের সাথে মিল রেখে)
    private final Color PRIMARY = new Color(25, 42, 86);
    private final Color ACCENT  = new Color(0, 173, 181);
    private final Color BG      = new Color(245, 247, 250);
    private final Color POSITIVE = new Color(0, 150, 90);
    private final Color NEGATIVE = new Color(200, 50, 50);

    MiniStatement(String pinnumber) {
        super("Mini Statement");
        this.pinnumber = pinnumber;

        setLayout(new BorderLayout());
        setSize(430, 620);
        setLocation(20, 20);
        getContentPane().setBackground(BG);

        // ---------- TOP HEADER PANEL ----------
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBackground(PRIMARY);
        header.setPreferredSize(new Dimension(430, 110));

        JLabel bankName = new JLabel("Prime University Bank");
        bankName.setFont(new Font("SansSerif", Font.BOLD, 20));
        bankName.setForeground(Color.WHITE);
        bankName.setBounds(20, 12, 350, 28);
        header.add(bankName);

        JLabel subtitle = new JLabel("MINI STATEMENT");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(new Color(200, 210, 230));
        subtitle.setBounds(20, 40, 350, 20);
        header.add(subtitle);

        cardLabel = new JLabel("Card Number: N/A");
        cardLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
        cardLabel.setForeground(ACCENT);
        cardLabel.setBounds(20, 68, 380, 24);
        header.add(cardLabel);

        add(header, BorderLayout.NORTH);

        // ---------- CENTER: TRANSACTION TABLE ----------
        String[] columns = {"Date", "Type", "Amount (TAKA)"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setRowHeight(28);
        table.setForeground(new Color(40, 40, 40));
        table.setGridColor(new Color(225, 228, 232));
        table.setShowGrid(true);
        table.setSelectionBackground(new Color(220, 240, 240));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        table.getTableHeader().setBackground(PRIMARY);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setPreferredSize(new Dimension(0, 32));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        // ---------- BOTTOM: BALANCE + EXIT ----------
        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        bottom.setBackground(BG);
        bottom.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(1, 0, 0, 0, new Color(225, 228, 232)),
                BorderFactory.createEmptyBorder(14, 20, 14, 20)));

        balanceLabel = new JLabel("Your Total Balance is TAKA 0");
        balanceLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        balanceLabel.setForeground(PRIMARY);
        bottom.add(balanceLabel, BorderLayout.WEST);

        exitBtn = new JButton("EXIT");
        exitBtn.setBackground(ACCENT);
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
        exitBtn.setFocusPainted(false);
        exitBtn.setBorderPainted(false);
        exitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitBtn.setPreferredSize(new Dimension(90, 34));
        exitBtn.addActionListener(this);
        bottom.add(exitBtn, BorderLayout.EAST);

        add(bottom, BorderLayout.SOUTH);

        // ---------- LOAD DATA ----------
        loadCardNumber();
        loadTransactions(model);

        setVisible(true);
    }

    private void loadCardNumber() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from login where pin = '" + pinnumber + "'");
            while (rs.next()) {
                String cardnumber = rs.getString("cardnumber");
                cardLabel.setText("Card Number:  " + cardnumber.substring(0, 4) + " XXXX XXXX " + cardnumber.substring(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
            cardLabel.setText("Card Number: N/A");
        }
    }

    private void loadTransactions(DefaultTableModel model) {
        try {
            int balance = 0;
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("SELECT * FROM bank where pin = '" + pinnumber + "'");
            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("type");
                String amount = rs.getString("amount");
                model.addRow(new Object[]{date, type, amount});

                if (type.equals("Deposit")) {
                    balance += Integer.parseInt(amount);
                } else {
                    balance -= Integer.parseInt(amount);
                }
            }
            balanceLabel.setText("Your Total Balance is TAKA " + balance);
            balanceLabel.setForeground(balance >= 0 ? POSITIVE : NEGATIVE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        this.setVisible(false);
    }

    public static void main(String[] args) {
        new MiniStatement("").setVisible(true);
    }
}