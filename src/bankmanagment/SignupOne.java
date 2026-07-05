package bankmanagment;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class SignupOne extends JFrame implements ActionListener {

    JLabel formno, personalDetails, name, pinCode, state, city, email, marital, address, gender, dob, father;
    JTextField nameTextField, fnameTextField, emailTextField, addressTextField, cityTextField, stateTextField, pinTextField;
    JDateChooser dateChooser;
    JRadioButton male, female, married, unmarried, other;
    JButton next;

    Random ran = new Random();
    long random = Math.abs((ran.nextLong() % 9000L) + 1000L);
    String first = "" + Math.abs(random);

    // থিম কালার
    private final Color PRIMARY = new Color(25, 42, 86);
    private final Color ACCENT  = new Color(0, 173, 181);
    private final Color BG      = new Color(245, 247, 250);
    private final Color LABEL_COLOR = new Color(50, 50, 50);

    SignupOne() {

        setTitle("NEW ACCOUNT APPLICATION FORM");
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

        formno = new JLabel("APPLICATION FORM NO. " + first);
        formno.setFont(new Font("SansSerif", Font.BOLD, 26));
        formno.setForeground(Color.WHITE);
        formno.setBounds(30, 15, 650, 32);
        headerPanel.add(formno);

        personalDetails = new JLabel("Page 1 of 3  —  Personal Details");
        personalDetails.setFont(new Font("SansSerif", Font.PLAIN, 16));
        personalDetails.setForeground(new Color(200, 210, 230));
        personalDetails.setBounds(30, 52, 650, 28);
        headerPanel.add(personalDetails);

        // ---- ফর্ম ফিল্ডস ----
        name = new JLabel("Name");
        styleLabel(name);
        name.setBounds(100, 135, 300, 28);
        add(name);

        nameTextField = new JTextField();
        styleField(nameTextField);
        nameTextField.setBounds(100, 163, 650, 42);
        add(nameTextField);

        father = new JLabel("Father's Name");
        styleLabel(father);
        father.setBounds(100, 218, 300, 28);
        add(father);

        fnameTextField = new JTextField();
        styleField(fnameTextField);
        fnameTextField.setBounds(100, 246, 650, 42);
        add(fnameTextField);

        dob = new JLabel("Date of Birth");
        styleLabel(dob);
        dob.setBounds(100, 301, 300, 28);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setForeground(new Color(50, 50, 50));
        dateChooser.setFont(new Font("SansSerif", Font.PLAIN, 16));
        dateChooser.setBounds(100, 329, 650, 42);
        add(dateChooser);

        gender = new JLabel("Gender");
        styleLabel(gender);
        gender.setBounds(100, 384, 300, 28);
        add(gender);

        male = new JRadioButton("Male");
        styleRadio(male);
        male.setBounds(100, 412, 100, 34);
        add(male);

        female = new JRadioButton("Female");
        styleRadio(female);
        female.setBounds(220, 412, 120, 34);
        add(female);

        ButtonGroup groupgender = new ButtonGroup();
        groupgender.add(male);
        groupgender.add(female);

        email = new JLabel("Email Address");
        styleLabel(email);
        email.setBounds(100, 460, 300, 28);
        add(email);

        emailTextField = new JTextField();
        styleField(emailTextField);
        emailTextField.setBounds(100, 488, 650, 42);
        add(emailTextField);

        marital = new JLabel("Marital Status");
        styleLabel(marital);
        marital.setBounds(100, 543, 300, 28);
        add(marital);

        married = new JRadioButton("Married");
        styleRadio(married);
        married.setBounds(100, 571, 120, 34);
        add(married);

        unmarried = new JRadioButton("Unmarried");
        styleRadio(unmarried);
        unmarried.setBounds(230, 571, 140, 34);
        add(unmarried);

        other = new JRadioButton("Other");
        styleRadio(other);
        other.setBounds(380, 571, 120, 34);
        add(other);

        ButtonGroup groupstatus = new ButtonGroup();
        groupstatus.add(married);
        groupstatus.add(unmarried);
        groupstatus.add(other);

        address = new JLabel("Address");
        styleLabel(address);
        address.setBounds(100, 619, 300, 28);
        add(address);

        addressTextField = new JTextField();
        styleField(addressTextField);
        addressTextField.setBounds(100, 647, 650, 42);
        add(addressTextField);

        city = new JLabel("City");
        styleLabel(city);
        city.setBounds(100, 702, 200, 28);
        add(city);

        cityTextField = new JTextField();
        styleField(cityTextField);
        cityTextField.setBounds(100, 730, 200, 42);
        add(cityTextField);

        pinCode = new JLabel("Pin Code");
        styleLabel(pinCode);
        pinCode.setBounds(340, 702, 200, 28);
        add(pinCode);

        pinTextField = new JTextField();
        styleField(pinTextField);
        pinTextField.setBounds(340, 730, 200, 42);
        add(pinTextField);

        state = new JLabel("State");
        styleLabel(state);
        state.setBounds(580, 702, 170, 28);
        add(state);

        stateTextField = new JTextField();
        styleField(stateTextField);
        stateTextField.setBounds(580, 730, 170, 42);
        add(stateTextField);

        next = new JButton("NEXT  →");
        next.setFont(new Font("SansSerif", Font.BOLD, 16));
        next.setBackground(ACCENT);
        next.setForeground(Color.WHITE);
        next.setFocusPainted(false);
        next.setBorderPainted(false);
        next.setCursor(new Cursor(Cursor.HAND_CURSOR));
        next.setBounds(570, 800, 180, 46);
        next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                next.setBackground(ACCENT.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                next.setBackground(ACCENT);
            }
        });
        add(next);

        next.addActionListener(this);

        setVisible(true);

    }

    private void styleLabel(JLabel label) {
        label.setFont(new Font("SansSerif", Font.BOLD, 15));
        label.setForeground(LABEL_COLOR);
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 17));
        field.setBackground(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0, 0, 2, 0, ACCENT),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)));
    }

    private void styleRadio(JRadioButton radio) {
        radio.setFont(new Font("SansSerif", Font.PLAIN, 15));
        radio.setBackground(BG);
        radio.setForeground(LABEL_COLOR);
        radio.setFocusPainted(false);
        radio.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void actionPerformed(ActionEvent ae) {

        String formno = "" + random;
        String name = nameTextField.getText();
        String fname = fnameTextField.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        }

        String email = emailTextField.getText();
        String marital = null;
        if (married.isSelected()) {
            marital = "Married";
        } else if (unmarried.isSelected()) {
            marital = "Unmarried";
        } else if (other.isSelected()) {
            marital = "Other";
        }

        String address = addressTextField.getText();
        String city = cityTextField.getText();
        String pincode = pinTextField.getText();
        String state = stateTextField.getText();

        try {

            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Fill all the required fields");
            } else {
                Conn c = new Conn();
                String query = "insert into signup values('" + formno + "','" + name + "','" + fname + "','" + dob + "','" + gender + "','" + email + "','" + marital + "','" + address + "','" + city + "','" + pincode + "','" + state + "')";
                c.s.executeUpdate(query);

                new SignupTwo(formno).setVisible(true);
                setVisible(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void main(String[] args) {
        new SignupOne();
    }
}