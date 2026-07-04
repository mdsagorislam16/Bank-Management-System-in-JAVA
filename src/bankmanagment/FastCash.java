package bankmanagment;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class FastCash extends JFrame implements ActionListener {
    JLabel text, l2;
    JButton b1, b2, b3, b4, b5, b6, b7, b8;
    JTextField t1;
    String pinnumber;
    FastCash(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 960, 1080);
        add(image);
        text = new JLabel("SELECT WITHDRAWL AMOUNT");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(235, 400, 700, 35);
        image.add(text);
        b1 = new JButton("TAKA 100");
        b1.setBounds(170, 499, 150, 35);
        image.add(b1);
        b2 = new JButton("TAKA 500");
        b2.setBounds(390, 499, 150, 35);
        image.add(b2);
        b3 = new JButton("TAKA 1000");
        b3.setBounds(170, 543, 150, 35);
        image.add(b3);
        b4 = new JButton("TAKA 2000");
        b4.setBounds(390, 543, 150, 35);
        image.add(b4);
        b5 = new JButton("TAKA 5000");
        b5.setBounds(170, 588, 150, 35);
        image.add(b5);
        b6 = new JButton("TAKA 10000");
        b6.setBounds(390, 588, 150, 35);
        image.add(b6);
        b7 = new JButton("BACK");
        b7.setBounds(390, 633, 150, 35);
        image.add(b7);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        setSize(960, 1080);
        setLocation(500, 0);
        setUndecorated(true);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b7) {
                this.setVisible(false);
                new Transactions(pinnumber).setVisible(true);
                return;
            }
            String amount = ((JButton) ae.getSource()).getText().substring(5); //k
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + pinnumber + "'");
            int balance = 0;
            while (rs.next()) {
                // FIX: column name corrected from "mode" to "type" to match the CREATE TABLE statement
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
            // FIX: use a short fixed-length date format instead of Date.toString(),
            // which can overflow the varchar date column
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date());
            c.s.executeUpdate("insert into bank values('" + pinnumber + "', '" + date + "', 'Withdrawl', '" + amount + "')");
            JOptionPane.showMessageDialog(null, "TAKA. " + amount + " Debited Successfully");
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            // FIX: surface the real error instead of failing silently
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        new FastCash("").setVisible(true);
    }
}