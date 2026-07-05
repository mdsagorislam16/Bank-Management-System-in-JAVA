package bankmanagment;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SignupThree extends JFrame implements ActionListener {

    JRadioButton r1, r2, r3, r4;
    JButton submit, cancel;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    String formno;

    // থিম কালার (SignupOne / SignupTwo এর সাথে মিল রেখে)
    private final Color PRIMARY = new Color(25, 42, 86);
    private final Color ACCENT  = new Color(0, 173, 181);
    private final Color BG      = new Color(245, 247, 250);
    private final Color LABEL_COLOR = new Color(50, 50, 50);

    SignupThree(String formno) {
        this.formno = formno;
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 3");
        setLayout(null);
        setSize(880, 900);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG);

        // ---- টপ হেডার ব্যান্ড ----
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 880, 95);
        headerPanel.setBackground(PRIMARY);
        headerPanel.setLayout(null);
        add(headerPanel);

        JLabel formLabel = new JLabel("APPLICATION FORM NO. " + formno);
        formLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        formLabel.setForeground(Color.WHITE);
        formLabel.setBounds(30, 15, 650, 32);
        headerPanel.add(formLabel);

        JLabel pageInfo = new JLabel("Page 3 of 3  —  Account Details");
        pageInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        pageInfo.setForeground(new Color(200, 210, 230));
        pageInfo.setBounds(30, 52, 650, 28);
        headerPanel.add(pageInfo);

        // ---- অ্যাকাউন্ট টাইপ ----
        JLabel type = new JLabel("Account Type");
        styleLabel(type);
        type.setBounds(100, 130, 300, 28);
        add(type);

        r1 = new JRadioButton("Saving Account");
        styleRadio(r1);
        r1.setBounds(100, 165, 250, 34);
        add(r1);

        r2 = new JRadioButton("Fixed Deposit Account");
        styleRadio(r2);
        r2.setBounds(420, 165, 300, 34);
        add(r2);

        r3 = new JRadioButton("Current Account");
        styleRadio(r3);
        r3.setBounds(100, 205, 300, 34);
        add(r3);

        r4 = new JRadioButton("Recurring Deposit Account");
        styleRadio(r4);
        r4.setBounds(420, 205, 300, 34);
        add(r4);

        ButtonGroup groupaccount = new ButtonGroup();
        groupaccount.add(r1);
        groupaccount.add(r2);
        groupaccount.add(r3);
        groupaccount.add(r4);

        // ---- কার্ড / পিন প্রিভিউ কার্ড ----
        JPanel previewCard = new JPanel();
        previewCard.setLayout(null);
        previewCard.setBackground(Color.WHITE);
        previewCard.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230), 2, true));
        previewCard.setBounds(100, 265, 650, 150);
        add(previewCard);

        JLabel card = new JLabel("Card Number:");
        styleLabel(card);
        card.setBounds(25, 20, 220, 28);
        previewCard.add(card);

        JLabel number = new JLabel("XXXX - XXXX - XXXX - XXXX");
        number.setFont(new Font("Monospaced", Font.BOLD, 20));
        number.setForeground(PRIMARY);
        number.setBounds(260, 18, 350, 32);
        previewCard.add(number);

        JLabel carddetail = new JLabel("Auto-generated 16-digit card number");
        carddetail.setFont(new Font("SansSerif", Font.PLAIN, 12));
        carddetail.setForeground(Color.GRAY);
        carddetail.setBounds(25, 50, 400, 20);
        previewCard.add(carddetail);

        JLabel pin = new JLabel("PIN:");
        styleLabel(pin);
        pin.setBounds(25, 90, 220, 28);
        previewCard.add(pin);

        JLabel pnumber = new JLabel("XXXX");
        pnumber.setFont(new Font("Monospaced", Font.BOLD, 20));
        pnumber.setForeground(PRIMARY);
        pnumber.setBounds(260, 88, 200, 32);
        previewCard.add(pnumber);

        JLabel pindetail = new JLabel("Auto-generated 4-digit PIN — shown after submit");
        pindetail.setFont(new Font("SansSerif", Font.PLAIN, 12));
        pindetail.setForeground(Color.GRAY);
        pindetail.setBounds(25, 120, 400, 20);
        previewCard.add(pindetail);

        // ---- সার্ভিস চেকবক্স ----
        JLabel services = new JLabel("Services Required");
        styleLabel(services);
        services.setBounds(100, 440, 300, 28);
        add(services);

        c1 = new JCheckBox("ATM Card");
        styleCheck(c1);
        c1.setBounds(100, 478, 250, 32);
        add(c1);

        c2 = new JCheckBox("Internet Banking");
        styleCheck(c2);
        c2.setBounds(420, 478, 250, 32);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        styleCheck(c3);
        c3.setBounds(100, 518, 250, 32);
        add(c3);

        c4 = new JCheckBox("Email & SMS Alerts");
        styleCheck(c4);
        c4.setBounds(420, 518, 250, 32);
        add(c4);

        c5 = new JCheckBox("Cheque Book");
        styleCheck(c5);
        c5.setBounds(100, 558, 250, 32);
        add(c5);

        c6 = new JCheckBox("E-Statement");
        styleCheck(c6);
        c6.setBounds(420, 558, 250, 32);
        add(c6);

        c7 = new JCheckBox("<html>I hereby declare that the above entered details are correct to the best of my knowledge.</html>", true);
        c7.setFont(new Font("SansSerif", Font.PLAIN, 13));
        c7.setBackground(BG);
        c7.setForeground(LABEL_COLOR);
        c7.setFocusPainted(false);
        c7.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c7.setBounds(100, 660, 650, 40);
        add(c7);

        // ---- বাটন ----
        submit = new JButton("SUBMIT");
        submit.setFont(new Font("SansSerif", Font.BOLD, 16));
        submit.setBackground(ACCENT);
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        submit.setBorderPainted(false);
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit.setBounds(390, 800, 175, 46);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("CANCEL");
        cancel.setFont(new Font("SansSerif", Font.BOLD, 16));
        cancel.setBackground(new Color(230, 230, 230));
        cancel.setForeground(Color.DARK_GRAY);
        cancel.setFocusPainted(false);
        cancel.setBorderPainted(false);
        cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancel.setBounds(575, 800, 175, 46);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    private void styleLabel(JLabel label) {
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setForeground(LABEL_COLOR);
    }

    private void styleRadio(JRadioButton radio) {
        radio.setFont(new Font("SansSerif", Font.PLAIN, 15));
        radio.setBackground(BG);
        radio.setForeground(LABEL_COLOR);
        radio.setFocusPainted(false);
        radio.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void styleCheck(JCheckBox check) {
        check.setFont(new Font("SansSerif", Font.PLAIN, 15));
        check.setBackground(BG);
        check.setForeground(LABEL_COLOR);
        check.setFocusPainted(false);
        check.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
            return;
        }

        if (ae.getSource() == submit) {

            String accountType = null;
            if (r1.isSelected()) {
                accountType = "Saving Account";
            } else if (r2.isSelected()) {
                accountType = "Fixed Deposit Account";
            } else if (r3.isSelected()) {
                accountType = "Current Account";
            } else if (r4.isSelected()) {
                accountType = "Recurring Deposit Account";
            }

            if (accountType == null) {
                JOptionPane.showMessageDialog(null, "Please select an Account Type",
                        "Missing Info", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!c7.isSelected()) {
                JOptionPane.showMessageDialog(null, "You must accept the declaration to continue",
                        "Declaration Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Random ran = new Random();
            long cardnumber = Math.abs((ran.nextLong() % 9000000000000000L)) + 1000000000000000L;
            String cardno = "" + cardnumber;
            if (cardno.length() > 16) {
                cardno = cardno.substring(0, 16);
            }

            long pinnumber = Math.abs((ran.nextLong() % 9000L)) + 1000L;
            String pinno = "" + pinnumber;

            String facility = "";
            if (c1.isSelected()) facility += " ATM Card";
            if (c2.isSelected()) facility += " Internet Banking";
            if (c3.isSelected()) facility += " Mobile Banking";
            if (c4.isSelected()) facility += " EMAIL Alerts";
            if (c5.isSelected()) facility += " Cheque Book";
            if (c6.isSelected()) facility += " E-Statement";

            try {
                Conn conn = new Conn();
                String query1 = "insert into signupthree values('" + formno + "','" + accountType + "','" + cardno + "','" + pinno + "','" + facility + "')";
                String query2 = "insert into login values('" + formno + "','" + cardno + "','" + pinno + "')";
                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null, "Account created successfully!\nCard Number: " + cardno + "\nPin: " + pinno);

                setVisible(false);
                new Deposit(pinno).setVisible(true);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Database Error: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new SignupThree("").setVisible(true);
    }
}