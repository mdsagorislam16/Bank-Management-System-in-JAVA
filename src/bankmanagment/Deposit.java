package bankmanagment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Deposit extends JFrame implements ActionListener {

    JTextField amount, t2;
    JButton deposit, back;
    JLabel text, image;
    String pinnumber;

    Deposit(String pinnumber) {

        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        image = new JLabel(i3);
        image.setBounds(0, 0, 960, 1080);
        add(image);
        
        text = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(190, 350, 400, 35);
        image.add(text);
        
        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(190, 420, 320, 25);
        image.add(amount);
        
        deposit = new JButton("DEPOSIT");
        deposit.setBounds(390, 588, 150, 35);
        image.add(deposit);
        
        back = new JButton("BACK");
        back.setBounds(390, 633, 150, 35);
        image.add(back);
        
        deposit.addActionListener(this);
        back.addActionListener(this);
        
        setSize(960, 1080);
        setUndecorated(true);
        setLocation(500, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String number = amount.getText();
            // FIX: use a short, fixed-length date format instead of Date.toString(),
            // which produces ~28-29 chars and overflows a varchar(20) column
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date());
            if (ae.getSource() == deposit) {
                if (amount.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Deposit");
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
            // FIX: show the real error to the user/developer instead of failing silently
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Deposit("").setVisible(true);
    }
}
