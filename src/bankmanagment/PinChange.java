package bankmanagment;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PinChange extends JFrame implements ActionListener {
    JPasswordField pin, repin;
    JButton change, back;
    JLabel text, pintext, repintext;
    String pinnumber;
    PinChange(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 960, 1080);
        add(image);
        text = new JLabel("CHANGE YOUR PIN");
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setForeground(Color.WHITE);
        text.setBounds(280, 330, 800, 35);
        image.add(text);
        pintext = new JLabel("New PIN:");
        pintext.setFont(new Font("System", Font.BOLD, 16));
        pintext.setForeground(Color.WHITE);
        pintext.setBounds(180, 390, 150, 35);
        image.add(pintext);
        pin = new JPasswordField();
        pin.setFont(new Font("Raleway", Font.BOLD, 25));
        pin.setBounds(350, 390, 180, 25);
        image.add(pin);
        repintext = new JLabel("Re-Enter New PIN:");
        repintext.setFont(new Font("System", Font.BOLD, 16));
        repintext.setForeground(Color.WHITE);
        repintext.setBounds(180, 440, 200, 35);
        image.add(repintext);
        repin = new JPasswordField();
        repin.setFont(new Font("Raleway", Font.BOLD, 25));
        repin.setBounds(350, 440, 180, 25);
        image.add(repin);
        change = new JButton("CHANGE");
        change.setBounds(390, 588, 150, 35);
        image.add(change);
        back = new JButton("BACK");
        back.setBounds(390, 633, 150, 35);
        image.add(back);
        change.addActionListener(this);
        back.addActionListener(this);
        setSize(960, 1080);
        setLocation(500, 0);
        setUndecorated(true);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == back) {
                new Transactions(pinnumber).setVisible(true);
                setVisible(false);
                return;
            }

            if (ae.getSource() == change) {
                // FIX: use getPassword() (char[]) instead of the deprecated getText() on JPasswordField
                String npin = new String(pin.getPassword());
                String rpin = new String(repin.getPassword());

                // FIX: check for empty fields FIRST, and return immediately so the
                // update queries never run with blank data
                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter New PIN");
                    return;
                }
                if (rpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Re-Enter new PIN");
                    return;
                }
                // FIX: match-check moved after the empty-check, so two blank fields
                // can no longer sneak through as a "match"
                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }

                Conn c1 = new Conn();
                String q1 = "update bank set pin = '" + rpin + "' where pin = '" + pinnumber + "' ";
                String q2 = "update login set pin = '" + rpin + "' where pin = '" + pinnumber + "' ";
                // FIX: table name corrected from "signup3" to "signupthree" to match the CREATE TABLE statement
                String q3 = "update signupthree set pin = '" + rpin + "' where pin = '" + pinnumber + "' ";
                c1.s.executeUpdate(q1);
                c1.s.executeUpdate(q2);
                c1.s.executeUpdate(q3);
                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transactions(rpin).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // FIX: surface the real error instead of failing silently
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        new PinChange("").setVisible(true);
    }
}