package quizpro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login1 extends JFrame implements ActionListener {

    JTextField userField;
    JPasswordField passField;
    JButton loginBtn, registerBtn;

    public Login1() {
        setTitle("Login - QuizPro");
        setBounds(550, 250, 500, 360);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);

        // Title Label
        JLabel title = new JLabel("Login to QuizPro", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(new Color(34, 139, 34));
        title.setBounds(0, 20, 500, 40);
        add(title);

        // Username Label
        JLabel userLbl = new JLabel("Username:");
        userLbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        userLbl.setBounds(80, 90, 100, 25);
        add(userLbl);

        userField = new JTextField();
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        userField.setBounds(180, 90, 220, 30);
        userField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(userField);

        // Password Label
        JLabel passLbl = new JLabel("Password:");
        passLbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passLbl.setBounds(80, 140, 100, 25);
        add(passLbl);

        passField = new JPasswordField();
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passField.setBounds(180, 140, 220, 30);
        passField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(passField);

        // Login Button
        loginBtn = new JButton("Login");
        styleButton(loginBtn, 100, 200, new Color(34, 139, 34));
        add(loginBtn);

        // Register Button
        registerBtn = new JButton("Register");
        styleButton(registerBtn, 260, 200, new Color(70, 130, 180));
        add(registerBtn);

        // Exit Button
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

    private void styleButton(JButton btn, int x, int y, Color color) {
        btn.setBounds(x, y, 120, 40);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        String user = userField.getText().trim();
        String pass = String.valueOf(passField.getPassword()).trim();

        if (ae.getSource() == loginBtn) {
            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            if (DBUtil.loginUser(user, pass)) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                setVisible(false);
                new selectlevel(user); 
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials");
            }
        } else if (ae.getSource() == registerBtn) {
            setVisible(false);
            new Register();
        }
    }
}
