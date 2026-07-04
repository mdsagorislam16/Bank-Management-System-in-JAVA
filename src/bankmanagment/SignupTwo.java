package bankmanagment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {

    JLabel religion, additonalDetails, category, senior, existing, adhar, qualification, occupation, pan, educational, income;
    JTextField panTextField, adharTextField;
    JRadioButton syes, sno, eyes, eno;
    JButton next;
    JComboBox c1, c2, c3, c4, c5;
    String formno;

    SignupTwo(String formno) {
        setLayout(null);
        this.formno = formno;
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");

        additonalDetails = new JLabel("Page 12: Additonal Details");
        additonalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additonalDetails.setBounds(290, 80, 600, 30);
        add(additonalDetails);

        religion = new JLabel("Religion:");
        religion.setFont(new Font("Raleway", Font.BOLD, 20));
        religion.setBounds(100, 140, 100, 30);
        add(religion);

        String valReligion[] = {"Other", "Hindu", "Muslim", "Sikh", "Christian"};
        c1 = new JComboBox(valReligion);
        c1.setBackground(Color.WHITE);
        c1.setFont(new Font("Raleway", Font.BOLD, 14));
        c1.setBounds(350, 120, 320, 30);
        add(c1);

        category = new JLabel("Category:");
        category.setFont(new Font("Raleway", Font.BOLD, 20));
        category.setBounds(100, 190, 200, 30);
        add(category);
        String valCategory[] = {"General", "OBC", "SC", "ST", "Other"};
        c2 = new JComboBox(valCategory);
        c2.setBackground(Color.WHITE);
        c2.setFont(new Font("Raleway", Font.BOLD, 14));
        c2.setBounds(350, 170, 320, 30);
        add(c2);

        income = new JLabel("Income:");
        income.setFont(new Font("Raleway", Font.BOLD, 20));
        income.setBounds(100, 240, 200, 30);
        add(income);
        String valIncome[] = {"Null", "<1,50,000", "<2,50,000", "<5,00,000", "Upto 10,00,000", "Above 10,00,000"};
        c3 = new JComboBox(valIncome);
        c3.setBackground(Color.WHITE);
        c3.setFont(new Font("Raleway", Font.BOLD, 14));
        c3.setBounds(350, 220, 320, 30);
        add(c3);

        educational = new JLabel("Educational:");
        educational.setFont(new Font("Raleway", Font.BOLD, 20));
        educational.setBounds(100, 290, 200, 30);
        add(educational);

        qualification = new JLabel("Qualification:");
        qualification.setFont(new Font("Raleway", Font.BOLD, 20));
        qualification.setBounds(100, 315, 200, 30);
        add(qualification);
        String valEducation[] = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctrate", "Others"};
        c4 = new JComboBox(valEducation);
        c4.setBackground(Color.WHITE);
        c4.setBounds(350, 270, 320, 30);
        c4.setFont(new Font("Raleway", Font.BOLD, 14));
        add(c4);

        occupation = new JLabel("Occupation:");
        occupation.setFont(new Font("Raleway", Font.BOLD, 20));
        occupation.setBounds(100, 390, 200, 30);
        add(occupation);
        String valOccupation[] = {"Salaried", "Self-Employmed", "Business", "Student", "Retired", "Others"};
        c5 = new JComboBox(valOccupation);
        c5.setBackground(Color.WHITE);
        c5.setBounds(350, 340, 320, 30);
        c5.setFont(new Font("Raleway", Font.BOLD, 14));
        add(c5);

        pan = new JLabel("PAN Number:");
        pan.setFont(new Font("Raleway", Font.BOLD, 20));
        pan.setBounds(100, 440, 200, 30);
        add(pan);

        panTextField = new JTextField();
        panTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        panTextField.setBounds(300, 440, 400, 30);
        add(panTextField);

        adhar = new JLabel("Aadhar Number:");
        adhar.setFont(new Font("Raleway", Font.BOLD, 20));
        adhar.setBounds(100, 490, 200, 30);
        add(adhar);

        adharTextField = new JTextField();
        adharTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        adharTextField.setBounds(300, 490, 400, 30);
        add(adharTextField);

        senior = new JLabel("Senior Citizen:");
        senior.setFont(new Font("Raleway", Font.BOLD, 20));
        senior.setBounds(100, 540, 200, 30);
        add(senior);

        syes = new JRadioButton("YES");
        syes.setFont(new Font("Raleway", Font.BOLD, 14));
        syes.setBackground(Color.WHITE);
        syes.setBounds(300, 540, 100, 30);
        add(syes);

        sno = new JRadioButton("NO");
        sno.setFont(new Font("Raleway", Font.BOLD, 14));
        sno.setBackground(Color.WHITE);
        sno.setBounds(450, 540, 100, 30);
        add(sno);

        ButtonGroup groupstatus = new ButtonGroup();
        groupstatus.add(syes);
        groupstatus.add(sno);

        existing = new JLabel("Existing Account:");
        existing.setFont(new Font("Raleway", Font.BOLD, 20));
        existing.setBounds(100, 590, 200, 30);
        add(existing);

        eyes = new JRadioButton("YES");
        eyes.setFont(new Font("Raleway", Font.BOLD, 14));
        eyes.setBackground(Color.WHITE);
        eyes.setBounds(300, 590, 100, 30);
        add(eyes);

        eno = new JRadioButton("NO");
        eno.setFont(new Font("Raleway", Font.BOLD, 14));
        eno.setBackground(Color.WHITE);
        eno.setBounds(450, 590, 100, 30);
        add(eno);

        ButtonGroup egroupstatus = new ButtonGroup();
        egroupstatus.add(eyes);
        egroupstatus.add(eno);

        next = new JButton("Next");
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setBounds(620, 660, 80, 30);

        add(next);

        next.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setTitle("NEW ACCOUNT APPLICATION FORM");
        setSize(850, 750);
        setVisible(true);

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
            // FIX: table name corrected from "signup2" to "signuptwo" to match the CREATE TABLE statement
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