package bankmanagment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Transactions extends JFrame implements ActionListener {

    JLabel text;
    JButton deposit, withdrawl, fastcash, ministatement, picchange, balanceenquiry, exit;
    String pinnumber;

    Transactions(String pinnumber) {
        
        setLayout(null);
        
        this.pinnumber = pinnumber;
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 960, 1080);
        add(image);

        text = new JLabel("Please Select Your Transaction");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(235, 400, 700, 35);
        image.add(text);

        deposit = new JButton("DEPOSIT");
        deposit.setBounds(170, 499, 150, 35);
        image.add(deposit);

        withdrawl = new JButton("CASH WITHDRAWL");
        withdrawl.setBounds(390, 499, 150, 35);
        image.add(withdrawl);

        fastcash = new JButton("FAST CASH");
        fastcash.setBounds(170, 543, 150, 35);
        image.add(fastcash);

        ministatement = new JButton("MINI STATEMENT");
        ministatement.setBounds(390, 543, 150, 35);
        image.add(ministatement);

        picchange = new JButton("PIN CHANGE");
        picchange.setBounds(170, 588, 150, 35);
        image.add(picchange);

        balanceenquiry = new JButton("BALANCE ENQUIRY");
        balanceenquiry.setBounds(390, 588, 150, 35);
        image.add(balanceenquiry);

        exit = new JButton("EXIT");
        exit.setBounds(390, 633, 150, 35);
        image.add(exit);

        deposit.addActionListener(this);
        withdrawl.addActionListener(this);
        fastcash.addActionListener(this);
        ministatement.addActionListener(this);
        picchange.addActionListener(this);
        balanceenquiry.addActionListener(this);
        exit.addActionListener(this);

        setSize(960, 1080);
        setLocation(500, 0);
        setUndecorated(true);
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
            new Pin(pinnumber).setVisible(true);
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
