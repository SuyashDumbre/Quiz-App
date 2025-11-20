package quizpro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MyHistory extends JFrame implements ActionListener {

    JTable table;
    JButton backBtn;
    String username;
    int score;
    String[][] questions, answers, useranswers;

    public MyHistory(String username, int score, String[][] questions, String[][] answers, String[][] useranswers) {
        this.username = username;
        this.score = score;
        this.questions = questions;
        this.answers = answers;
        this.useranswers = useranswers;

        setTitle("My Quiz History - " + username);
        setBounds(400, 200, 700, 500);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("Quiz History of " + username, SwingConstants.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setForeground(new Color(34, 139, 34));
        add(heading, BorderLayout.NORTH);

        String[] columnNames = {"Username", "Score"};
        String[][] data = fetchResults(username);

        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backBtn.setBackground(new Color(70, 130, 180));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setPreferredSize(new Dimension(100, 40));
        backBtn.addActionListener(this);
        add(backBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new score(username, score, questions, answers, useranswers);
    }

    private String[][] fetchResults(String username) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "SELECT username, score FROM quiz_results WHERE username = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            String[][] data = new String[rowCount][2];
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString("username");
                data[i][1] = String.valueOf(rs.getInt("score"));
                i++;
            }
            conn.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[][] {{"Error", "Loading"}};
        }
    }
}
