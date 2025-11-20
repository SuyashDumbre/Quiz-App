package quizpro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class selectlevel extends JFrame implements ActionListener {

    JButton easyBtn, mediumBtn, hardBtn, backBtn;
    String username;

    public selectlevel(String username) {
        this.username = username;

        setTitle("Select Difficulty Level - QuizPro");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setUndecorated(true);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(null);

        JLabel heading = new JLabel("Welcome, " + username);
        heading.setBounds(60, 40, 600, 40);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(new Color(34, 139, 34));
        add(heading);

        JLabel sub = new JLabel("Select your difficulty level");
        sub.setBounds(60, 90, 400, 25);
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        sub.setForeground(new Color(90, 90, 90));
        add(sub);

        // Easy Button
        easyBtn = createLevelButton("Easy", new Color(102, 204, 0), 60, 150);
        add(easyBtn);

        // Medium Button
        mediumBtn = createLevelButton("Medium", new Color(255, 153, 0), 300, 150);
        add(mediumBtn);

        // Hard Button
        hardBtn = createLevelButton("Hard", new Color(255, 51, 51), 540, 150);
        add(hardBtn);

        // Back Button
        backBtn = new JButton("Exit");
        backBtn.setBounds(650, 420, 100, 35);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setBackground(new Color(231, 76, 60));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setBorder(BorderFactory.createEmptyBorder());
        backBtn.addActionListener(this);
        add(backBtn);

        setVisible(true);
    }

    private JButton createLevelButton(String text, Color borderColor, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 180, 100);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setBackground(new Color(230, 230, 230));
        btn.setForeground(borderColor);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(borderColor, 3));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(this);
        return btn;
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == backBtn) {
            System.exit(0);
        } else if (src instanceof JButton) {
            JButton selected = (JButton) src;
            String level = selected.getText(); // Easy, Medium, or Hard

            highlightSelected(selected); // Optional visual effect

            setVisible(false);
            new Rules(this,username, level);  // ✅ FIXED: now goes to Rules first
        }
    }

    private void highlightSelected(JButton selected) {
        JButton[] buttons = {easyBtn, mediumBtn, hardBtn};

        for (JButton btn : buttons) {
            if (btn == selected) {
                btn.setBackground(btn.getForeground().brighter());
                btn.setForeground(Color.WHITE);
            } else {
                btn.setBackground(new Color(230, 230, 230));
                if (btn.getBorder() instanceof javax.swing.border.LineBorder) {
                    Color borderColor = ((javax.swing.border.LineBorder) btn.getBorder()).getLineColor();
                    btn.setForeground(borderColor);
                }
            }
        }
    }
}
