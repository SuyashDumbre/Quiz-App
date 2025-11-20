package quizpro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReviewPage extends JFrame implements ActionListener {

    String[][] questions, answers, useranswers;
    JButton backBtn;
    String username;
    int score;

    public ReviewPage(String[][] questions, String[][] answers, String[][] useranswers, String username, int score) {
        this.questions = questions;
        this.answers = answers;
        this.useranswers = useranswers;
        this.username = username;
        this.score = score;

        setTitle("Review Answers");
        setBounds(300, 100, 900, 600);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JTextArea reviewArea = new JTextArea();
        reviewArea.setEditable(false);
        reviewArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < questions.length; i++) {
            sb.append((i + 1)).append(". ").append(questions[i][0]).append("\n");
            sb.append("Your Answer: ").append(useranswers[i][0].equals("") ? "Skipped" : useranswers[i][0]).append("\n");
            sb.append("Correct Answer: ").append(answers[i][1]).append("\n\n");
        }

        reviewArea.setText(sb.toString());
        JScrollPane scrollPane = new JScrollPane(reviewArea);
        add(scrollPane, BorderLayout.CENTER);

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backBtn.setBackground(new Color(34, 139, 34));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setPreferredSize(new Dimension(100, 40));
        backBtn.addActionListener(this);
        add(backBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn) {
            setVisible(false);
            new score(username, score, questions, answers, useranswers);
        }
    }
}
