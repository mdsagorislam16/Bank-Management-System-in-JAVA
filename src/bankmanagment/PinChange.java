package bankmanagment;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class PinChange extends JFrame implements ActionListener {
    JPasswordField pin, repin;
    JButton change, back;
    String pinnumber;

    // থিম কালার (Login পেজের সাথে মিল রেখে)
    private final Color PRIMARY = new Color(25, 42, 86);
    private final Color ACCENT  = new Color(0, 173, 181);
    private final Color BG      = new Color(245, 247, 250);

    PinChange(String pinnumber) {
        this.pinnumber = pinnumber;
        setTitle("Change PIN");
        setLayout(null);
        setSize(800, 480);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG);

        // ---------- LEFT SIDE PANEL ----------
        JPanel sidePanel = new JPanel();
        sidePanel.setBounds(0, 0, 300, 480);
        sidePanel.setBackground(PRIMARY);
        sidePanel.setLayout(null);
        add(sidePanel);

        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
            Image i2 = i1.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel logoLabel = new JLabel(i3);
            logoLabel.setBounds(105, 60, 90, 90);
            sidePanel.add(logoLabel);
        } catch (Exception e) {
            // লোগো না থাকলেও ক্র্যাশ করবে না
        }

        JLabel sideText = new JLabel("<html><center>SECURE YOUR<br>ACCOUNT</center></html>");
        sideText.setFont(new Font("SansSerif", Font.BOLD, 24));
        sideText.setForeground(Color.WHITE);
        sideText.setHorizontalAlignment(SwingConstants.CENTER);
        sideText.setBounds(20, 170, 260, 80);
        sidePanel.add(sideText);

        JLabel tagline = new JLabel("<html><center>Choose a PIN only you know.</center></html>");
        tagline.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tagline.setForeground(new Color(200, 210, 230));
        tagline.setHorizontalAlignment(SwingConstants.CENTER);
        tagline.setBounds(20, 250, 260, 30);
        sidePanel.add(tagline);

        // ---------- RIGHT SIDE FORM ----------
        JLabel formTitle = new JLabel("Change PIN");
        formTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
        formTitle.setForeground(PRIMARY);
        formTitle.setBounds(350, 55, 300, 40);
        add(formTitle);

        JLabel subtitle = new JLabel("Enter your new 4-digit PIN below");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(Color.GRAY);
        subtitle.setBounds(350, 95, 350, 20);
        add(subtitle);

        JLabel pinLabel = new JLabel("New PIN");
        pinLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        pinLabel.setForeground(Color.GRAY);
        pinLabel.setBounds(350, 140, 300, 20);
        add(pinLabel);

        pin = new JPasswordField();
        pin.setFont(new Font("Arial", Font.PLAIN, 18));
        pin.setBounds(350, 163, 350, 38);
        pin.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0, 0, 2, 0, ACCENT),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        pin.setBackground(BG);
        add(pin);

        JLabel repinLabel = new JLabel("Re-Enter New PIN");
        repinLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        repinLabel.setForeground(Color.GRAY);
        repinLabel.setBounds(350, 218, 300, 20);
        add(repinLabel);

        repin = new JPasswordField();
        repin.setFont(new Font("Arial", Font.PLAIN, 18));
        repin.setBounds(350, 241, 350, 38);
        repin.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0, 0, 2, 0, ACCENT),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        repin.setBackground(BG);
        add(repin);

        change = new JButton("CHANGE PIN");
        change.setBounds(350, 313, 165, 42);
        change.setBackground(ACCENT);
        change.setForeground(Color.WHITE);
        change.setFont(new Font("SansSerif", Font.BOLD, 14));
        change.setFocusPainted(false);
        change.setBorderPainted(false);
        change.setCursor(new Cursor(Cursor.HAND_CURSOR));
        change.addActionListener(this);
        add(change);

        back = new JButton("BACK");
        back.setBounds(535, 313, 165, 42);
        back.setBackground(new Color(230, 230, 230));
        back.setForeground(Color.DARK_GRAY);
        back.setFont(new Font("SansSerif", Font.BOLD, 14));
        back.setFocusPainted(false);
        back.setBorderPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(this);
        add(back);

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
                String npin = new String(pin.getPassword());
                String rpin = new String(repin.getPassword());

                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter New PIN");
                    return;
                }
                if (rpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Re-Enter new PIN");
                    return;
                }
                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }

                Conn c1 = new Conn();
                String q1 = "update bank set pin = '" + rpin + "' where pin = '" + pinnumber + "' ";
                String q2 = "update login set pin = '" + rpin + "' where pin = '" + pinnumber + "' ";
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
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new PinChange("").setVisible(true);
    }
}