package quizpro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class score extends JFrame implements ActionListener {

    JButton playAgain, exit, review, history;
    String name;
    int score;
    String[][] questions;
    String[][] answers;
    String[][] useranswers;

    public score(String name, int score, String[][] questions, String[][] answers, String[][] useranswers) {
        this.name = name;
        this.score = score;
        this.questions = questions;
        this.answers = answers;
        this.useranswers = useranswers;

        DBUtil.saveResult(this.name, score);

        setTitle("Quiz Completed - Score Summary");
        setBounds(450, 150, 800, 500);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(null);
        setUndecorated(true);

        // Title
        JLabel title = new JLabel("🎉 QUIZ COMPLETED 🎉", SwingConstants.CENTER);
        title.setBounds(0, 20, 800, 40);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(new Color(34, 139, 34));
        add(title);

        // Greeting
        JLabel greeting = new JLabel("Well done, " + name + "!", SwingConstants.CENTER);
        greeting.setBounds(0, 80, 800, 30);
        greeting.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        greeting.setForeground(new Color(70, 130, 180));
        add(greeting);

        // Score
        JLabel scoreLabel = new JLabel("Your Score: " + score + " / 100", SwingConstants.CENTER);
        scoreLabel.setBounds(0, 120, 800, 28);
        scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        scoreLabel.setForeground(new Color(0, 102, 204));
        add(scoreLabel);

        // Feedback Message
        String message;
        if (score >= 80) {
            message = "\u2728 Excellent! You're a Java Champ!";
        } else if (score >= 50) {
            message = "\uD83D\uDC4F Good Job! Keep practicing!";
        } else {
            message = "\uD83D\uDCA1 Don't give up! Keep learning!";
        }

        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setBounds(0, 160, 800, 30);
        messageLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        messageLabel.setForeground(new Color(128, 0, 128));
        add(messageLabel);

        // Review Button
review = createButton("Review Answers", 200, 220, new Color(100, 149, 237));
review.addActionListener(e -> {
    setVisible(false);
    new ReviewPage(questions, answers, useranswers, name, score); // ✅ Pass all 5
});
add(review);

// History Button
history = createButton("View History", 420, 220, new Color(100, 149, 237));
history.addActionListener(e -> {
    setVisible(false);
    new MyHistory(name, score, questions, answers, useranswers); // ✅ Pass all 5
});
add(history);

        // Play Again Button
        playAgain = createButton("Play Again", 200, 300, new Color(22, 99, 54));
        playAgain.addActionListener(this);
        add(playAgain);

        // Exit Button
        exit = createButton("Exit", 450, 300, new Color(255, 69, 58));
        exit.addActionListener(e -> System.exit(0));
        add(exit);

        // Credits
        JLabel credits = new JLabel(" PGDIT | Powered by Java Swing", SwingConstants.CENTER);
        credits.setBounds(0, 450, 800, 20);
        credits.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        credits.setForeground(Color.GRAY);
        add(credits);

        setVisible(true);
    }

    private JButton createButton(String text, int x, int y, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 180, 45);
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        return btn;
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Login1(); // Go back to login screen
    }
}
