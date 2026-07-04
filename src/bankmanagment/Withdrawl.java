package bankmanagment;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
public class Withdrawl extends JFrame implements ActionListener {
    JTextField amount;
    JButton withdraw, back;
    JLabel text, text2, image;
    String pinnumber;
    Withdrawl(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);
        // FIX: corrected resource path from "ASimulatorSystem/icons/atm.jpg" to "icons/atm.jpg"
        // (the wrong path made getSystemResource() return null, which crashed the ImageIcon
        // constructor with a NullPointerException before the window could ever show)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        image = new JLabel(i3);
        image.setBounds(0, 0, 960, 1080);
        add(image);
        text = new JLabel("MAXIMUM WITHDRAWAL IS TAKA.5,00");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(190, 350, 400, 20);
        image.add(text);
        text2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        text2.setForeground(Color.WHITE);
        text2.setFont(new Font("System", Font.BOLD, 16));
        text2.setBounds(190, 400, 400, 20);
        image.add(text2);
        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 25));
        amount.setBounds(190, 450, 330, 30);
        image.add(amount);
        withdraw = new JButton("WITHDRAW");
        withdraw.setBounds(390, 588, 150, 35);
        image.add(withdraw);
       back = new JButton("BACK");
        back.setBounds(390, 633, 150, 35);
        image.add(back);

        withdraw.addActionListener(this);
        back.addActionListener(this);
        setSize(960, 1080);
        setLocation(500, 0);
        setUndecorated(true);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        try {
            String number = amount.getText();
            // FIX: use a short fixed-length date format instead of Date.toString(),
            // which can overflow a varchar(20)/varchar(50) date column
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date());
            if (ae.getSource() == withdraw) {
                if (amount.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Withdraw");
                } else {
                    Conn c1 = new Conn();
                    ResultSet rs = c1.s.executeQuery("select * from bank where pin = '" + pinnumber + "'");
                    int balance = 0;
                    while (rs.next()) {
                        // FIX: column name corrected from "mode" to "type" to match the CREATE TABLE statement
                        if (rs.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(rs.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(rs.getString("amount"));
                        }
                    }
                    if (balance < Integer.parseInt(number)) {
                        JOptionPane.showMessageDialog(null, "Insuffient Balance");
                        return;
                    }
                    c1.s.executeUpdate("insert into bank values('" + pinnumber + "', '" + date + "', 'Withdrawl', '" + number + "')");
                    JOptionPane.showMessageDialog(null, "TAKA. " + number + " Debited Successfully");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }
            } else if (ae.getSource() == back) {
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // FIX: surface the real error instead of only printing to console
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        new Withdrawl("").setVisible(true);
    }
}