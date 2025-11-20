package quizpro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    JTextField text;
    JButton next, back;

    Login() {
        
        setTitle("Login - Quiz Test");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Image Section
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.png"));
        Image i = i1.getImage().getScaledInstance(450, 500, Image.SCALE_SMOOTH);
        JLabel image = new JLabel(new ImageIcon(i));
        image.setBounds(550, 0, 450, 500);
        add(image);

        // Heading
        JLabel heading = new JLabel("Welcome to Quiz Test");
        heading.setBounds(80, 50, 400, 40);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(new Color(33, 47, 61));
        add(heading);

        // Name label
        JLabel name = new JLabel("Enter your name");
        name.setBounds(80, 130, 300, 20);
        name.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        name.setForeground(new Color(90, 90, 90));
        add(name);

        // Input field
        text = new JTextField();
        text.setBounds(80, 160, 360, 30);
        text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        text.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        add(text);

        // Buttons
        next = new JButton("Start Quiz");
        next.setBounds(80, 220, 150, 35);
        next.setBackground(new Color(22, 160, 133));
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        next.setFocusPainted(false);
        next.setBorder(BorderFactory.createEmptyBorder());
        next.addActionListener(this);
        add(next);

        back = new JButton("Exit");
        back.setBounds(250, 220, 100, 35);
        back.setBackground(new Color(231, 76, 60));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        back.setFocusPainted(false);
        back.setBorder(BorderFactory.createEmptyBorder());
        back.addActionListener(this);
        add(back);

        // Frame settings
        setSize(1000, 500);
        setLocationRelativeTo(null); // center screen
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            String name = text.getText().trim();
            if (!name.equals("")) {
                setVisible(false);
                //new Rules(name);
//                new selectlevel();
                new LevelSelection();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter your name to proceed.");
            }
        } else if (e.getSource() == back) {
            System.exit(0);
        }
    }
}

