package quizpro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Register extends JFrame implements ActionListener {

    JTextField userField;
    JPasswordField passField;
    JButton registerBtn, backBtn;

    public Register() {
        setTitle("Register - QuizPro");
        setBounds(550, 250, 500, 350);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // For clean modern look

        // Title Label
        JLabel title = new JLabel("Create New Account", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(70, 130, 180));
        title.setBounds(0, 20, 500, 40);
        add(title);

        // Username Label
        JLabel userLbl = new JLabel("Username:");
        userLbl.setBounds(80, 90, 100, 25);
        userLbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(userLbl);

        userField = new JTextField();
        userField.setBounds(180, 90, 220, 30);
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        userField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(userField);

        // Password Label
        JLabel passLbl = new JLabel("Password:");
        passLbl.setBounds(80, 140, 100, 25);
        passLbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(passLbl);

        passField = new JPasswordField();
        passField.setBounds(180, 140, 220, 30);
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(passField);

        // Register Button
        registerBtn = new JButton("Register");
        registerBtn.setBounds(100, 200, 120, 40);
        registerBtn.setBackground(new Color(34, 139, 34)); // Green
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        registerBtn.setFocusPainted(false);
        registerBtn.setBorder(BorderFactory.createEmptyBorder());
        registerBtn.addActionListener(this);
        add(registerBtn);

        // Back Button
        backBtn = new JButton("Back");
        backBtn.setBounds(260, 200, 120, 40);
        backBtn.setBackground(new Color(70, 130, 180)); // Blue
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backBtn.setFocusPainted(false);
        backBtn.setBorder(BorderFactory.createEmptyBorder());
        backBtn.addActionListener(this);
        add(backBtn);

        // Exit Button (optional)
        JButton exitBtn = new JButton("X");
        exitBtn.setBounds(460, 10, 30, 30);
        exitBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        exitBtn.setBackground(Color.DARK_GRAY);
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setFocusPainted(false);
        exitBtn.setBorder(BorderFactory.createEmptyBorder());
        exitBtn.addActionListener(e -> System.exit(0));
        add(exitBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == registerBtn) {
            String user = userField.getText().trim();
            String pass = String.valueOf(passField.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
            } else {
                boolean success = DBUtil.registerUser(user, pass);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Registration Successful");
                    setVisible(false);
                    new Login1();
                } else {
                    JOptionPane.showMessageDialog(this, "Username already exists");
                }
            }
        } else if (ae.getSource() == backBtn) {
            setVisible(false);
            new Login1();
        }
    }
}
