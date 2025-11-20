package quizpro;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Rules extends JFrame implements ActionListener {

    private final JButton start;
    private final JButton back;
    private final String name;
    private final String level; // added level field
    private final selectlevel prev; // reference to previous SelectLevel window (may be null)

    // Primary constructor accepts the previous selectlevel instance
    public Rules(selectlevel prev, String name, String level) {
        this.prev = prev;
        this.name = name;
        this.level = level;

        setTitle("Quiz Rules");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Heading
        JLabel heading = new JLabel("Welcome, " + name);
        heading.setBounds(50, 40, 700, 40);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(new Color(33, 47, 61));
        add(heading);

        // Subheading
        JLabel subheading = new JLabel("Please read the quiz rules carefully:");
        subheading.setBounds(50, 90, 600, 30);
        subheading.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subheading.setForeground(new Color(90, 90, 90));
        add(subheading);

        // Rules Text
        JLabel rules = new JLabel();
        rules.setBounds(50, 130, 700, 300);
        rules.setFont(new Font("SansSerif", Font.PLAIN, 16));
        rules.setForeground(new Color(44, 62, 80));
        rules.setVerticalAlignment(SwingConstants.TOP);
        rules.setText(
                "<html>" +
                        "1. Participation is open to all persons above 18 years.<br><br>" +
                        "2. There are a total of 10 questions.<br><br>" +
                        "3. You have 15 seconds to answer each question.<br><br>" +
                        "4. No cell phones or devices in the test area.<br><br>" +
                        "5. No talking or disturbances during the quiz.<br><br>" +
                        "6. No one else can be in the room with you.<br>" +
                        "</html>"
        );
        add(rules);

        // Start Quiz Button (rounded style)
        start = new JButton("Start Quiz") {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        start.setBounds(400, 480, 150, 40);
        start.setFont(new Font("Segoe UI", Font.BOLD, 16));
        start.setBackground(new Color(22, 160, 133));
        start.setForeground(Color.WHITE);
        start.setFocusPainted(false);
        start.setBorder(new EmptyBorder(0, 0, 0, 0));
        start.addActionListener(this);
        add(start);

        // Back Button (rounded style)
        back = new JButton("Back") {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        back.setBounds(220, 480, 120, 40);
        back.setFont(new Font("Segoe UI", Font.BOLD, 16));
        back.setBackground(new Color(231, 76, 60));
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.setBorder(new EmptyBorder(0, 0, 0, 0));
        back.addActionListener(this);
        add(back);

        // Background Image (Optional) — guard against missing resource
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/back.png"));
            if (i1.getImageLoadStatus() == MediaTracker.ERRORED) {
                throw new Exception("Image not found or failed to load.");
            }
            Image i = i1.getImage().getScaledInstance(800, 650, Image.SCALE_SMOOTH);
            JLabel image = new JLabel(new ImageIcon(i));
            image.setBounds(0, 0, 800, 650);
            add(image);
        } catch (Exception ex) {
            // If image fails to load, ignore — UI still works
            System.err.println("Background image not loaded: " + ex.getMessage());
        }

        setSize(800, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
    }

    // Convenience constructor for backward compatibility
    public Rules(String name, String level) {
        this(null, name, level);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            // close rules and start quiz
            this.dispose();
            new Quiz(name, level);
        } else if (e.getSource() == back) {
            // Properly return to previous SelectLevel instance if available
            this.dispose();
            if (prev != null) {
                prev.setVisible(true);
            } else {
                // Fallback to Login if no previous screen was provided
                new Login();
            }
        }
    }

    public static void main(String[] args) {
        new Rules("User", "Easy");
    }
}
