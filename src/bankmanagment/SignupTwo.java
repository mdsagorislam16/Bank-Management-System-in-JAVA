package bankmanagment;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {

    JLabel religion, additonalDetails, category, senior, existing, adhar, qualification, occupation, pan, income;
    JTextField panTextField, adharTextField;
    JRadioButton syes, sno, eyes, eno;
    JButton next;
    JComboBox<String> c1, c2, c3, c4, c5;
    String formno;

    // থিম কালার (SignupOne এর সাথে মিল রেখে)
    private final Color PRIMARY = new Color(25, 42, 86);
    private final Color ACCENT  = new Color(0, 173, 181);
    private final Color BG      = new Color(245, 247, 250);
    private final Color LABEL_COLOR = new Color(50, 50, 50);

    SignupTwo(String formno) {
        this.formno = formno;
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");
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

        additonalDetails = new JLabel("Page 2 of 3  —  Additional Details");
        additonalDetails.setFont(new Font("SansSerif", Font.PLAIN, 16));
        additonalDetails.setForeground(new Color(200, 210, 230));
        additonalDetails.setBounds(30, 52, 650, 28);
        headerPanel.add(additonalDetails);

        // ---- ফর্ম ফিল্ডস ----
        religion = new JLabel("Religion");
        styleLabel(religion);
        religion.setBounds(100, 130, 300, 28);
        add(religion);

        String[] valReligion = {"Other", "Hindu", "Muslim", "Sikh", "Christian"};
        c1 = new JComboBox<>(valReligion);
        styleCombo(c1);
        c1.setBounds(100, 158, 650, 42);
        add(c1);

        category = new JLabel("Category");
        styleLabel(category);
        category.setBounds(100, 213, 300, 28);
        add(category);

        String[] valCategory = {"General", "OBC", "SC", "ST", "Other"};
        c2 = new JComboBox<>(valCategory);
        styleCombo(c2);
        c2.setBounds(100, 241, 650, 42);
        add(c2);

        income = new JLabel("Income");
        styleLabel(income);
        income.setBounds(100, 296, 300, 28);
        add(income);

        String[] valIncome = {"Null", "<1,50,000", "<2,50,000", "<5,00,000", "Upto 10,00,000", "Above 10,00,000"};
        c3 = new JComboBox<>(valIncome);
        styleCombo(c3);
        c3.setBounds(100, 324, 650, 42);
        add(c3);

        qualification = new JLabel("Educational Qualification");
        styleLabel(qualification);
        qualification.setBounds(100, 379, 400, 28);
        add(qualification);

        String[] valEducation = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Others"};
        c4 = new JComboBox<>(valEducation);
        styleCombo(c4);
        c4.setBounds(100, 407, 650, 42);
        add(c4);

        occupation = new JLabel("Occupation");
        styleLabel(occupation);
        occupation.setBounds(100, 462, 300, 28);
        add(occupation);

        String[] valOccupation = {"Salaried", "Self-Employed", "Business", "Student", "Retired", "Others"};
        c5 = new JComboBox<>(valOccupation);
        styleCombo(c5);
        c5.setBounds(100, 490, 650, 42);
        add(c5);

        pan = new JLabel("PHONE Number");
        styleLabel(pan);
        pan.setBounds(100, 545, 300, 28);
        add(pan);

        panTextField = new JTextField();
        styleField(panTextField);
        panTextField.setBounds(100, 573, 650, 42);
        add(panTextField);

        adhar = new JLabel("NID Number");
        styleLabel(adhar);
        adhar.setBounds(100, 628, 300, 28);
        add(adhar);

        adharTextField = new JTextField();
        styleField(adharTextField);
        adharTextField.setBounds(100, 656, 650, 42);
        add(adharTextField);

        senior = new JLabel("Senior Citizen");
        styleLabel(senior);
        senior.setBounds(100, 711, 300, 28);
        add(senior);

        syes = new JRadioButton("YES");
        styleRadio(syes);
        syes.setBounds(100, 739, 100, 34);
        add(syes);

        sno = new JRadioButton("NO");
        styleRadio(sno);
        sno.setBounds(220, 739, 100, 34);
        add(sno);

        ButtonGroup groupstatus = new ButtonGroup();
        groupstatus.add(syes);
        groupstatus.add(sno);

        existing = new JLabel("Existing Account Holder");
        styleLabel(existing);
        existing.setBounds(400, 711, 350, 28);
        add(existing);

        eyes = new JRadioButton("YES");
        styleRadio(eyes);
        eyes.setBounds(400, 739, 100, 34);
        add(eyes);

        eno = new JRadioButton("NO");
        styleRadio(eno);
        eno.setBounds(520, 739, 100, 34);
        add(eno);

        ButtonGroup egroupstatus = new ButtonGroup();
        egroupstatus.add(eyes);
        egroupstatus.add(eno);

        next = new JButton("NEXT  →");
        next.setFont(new Font("SansSerif", Font.BOLD, 16));
        next.setBackground(ACCENT);
        next.setForeground(Color.WHITE);
        next.setFocusPainted(false);
        next.setBorderPainted(false);
        next.setCursor(new Cursor(Cursor.HAND_CURSOR));
        next.setBounds(570, 805, 180, 46);
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

    private void styleCombo(JComboBox<String> combo) {
        combo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0, 0, 2, 0, ACCENT),
                BorderFactory.createEmptyBorder(2, 6, 2, 6)));
    }

    private void styleRadio(JRadioButton radio) {
        radio.setFont(new Font("SansSerif", Font.PLAIN, 15));
        radio.setBackground(BG);
        radio.setForeground(LABEL_COLOR);
        radio.setFocusPainted(false);
        radio.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void actionPerformed(ActionEvent ae) {

        String sreligion = (String) c1.getSelectedItem();
        String scategory = (String) c2.getSelectedItem();
        String sincome = (String) c3.getSelectedItem();
        String seducation = (String) c4.getSelectedItem();
        String soccupation = (String) c5.getSelectedItem();

        String span = panTextField.getText().trim();
        String sadhar = adharTextField.getText().trim();

        String scitizen = "";
        if (syes.isSelected()) {
            scitizen = "Yes";
        } else if (sno.isSelected()) {
            scitizen = "No";
        }

        String saccount = "";
        if (eyes.isSelected()) {
            saccount = "Yes";
        } else if (eno.isSelected()) {
            saccount = "No";
        }

        // ---- সব required field validation ----
        if (span.equals("") || sadhar.equals("") || scitizen.equals("") || saccount.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Fill all the required fields (PAN, Aadhar, Senior Citizen, Existing Account)",
                    "Missing Info", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Conn conn = new Conn();
            String q1 = "insert into signuptwo values('" + formno + "','" + sreligion + "','" + scategory + "','" + sincome + "','" + seducation + "','" + soccupation + "','" + span + "','" + sadhar + "','" + scitizen + "','" + saccount + "')";
            conn.s.executeUpdate(q1);

            new SignupThree(formno).setVisible(true);
            setVisible(false);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Database Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void main(String[] args) {
        new SignupTwo("").setVisible(true);
    }
}