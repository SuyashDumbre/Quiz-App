// All code remains same — only Hint logic is added

package quizpro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.Collections; // ✅ Needed for shuffle
import java.util.Arrays;      // ✅ Needed for Arrays.asList
import java.util.List;        // ✅ Needed for List<JRadioButton>

public class Quizpro {
    public static void main(String[] args) {
        new Login1();
//        new LevelSelection();
    }
}

class LevelSelection extends JFrame implements ActionListener {
    JButton easy, medium, hard;

    LevelSelection() {
        setTitle("Select Difficulty Level");
        setBounds(600, 300, 400, 200);
        setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Choose Difficulty Level:", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(label);

        easy = new JButton("Easy");
        medium = new JButton("Medium");
        hard = new JButton("Hard");

        easy.addActionListener(this);
        medium.addActionListener(this);
        hard.addActionListener(this);

        add(easy);
        add(medium);
        add(hard);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String level = e.getActionCommand();
        setVisible(false);
        new Quiz("User", level);
    }
}

class Quiz extends JFrame implements ActionListener {
    String[][] questions = new String[10][5];
    String[][] answers = new String[10][2];
    String[][] useranswers = new String[10][1];

    JLabel qno, question, timerLabel;
    JProgressBar progressBar;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JButton next, submit, lifeline, quit;
    Timer countdownTimer;

    int timer = 15;
    int count = 0;
    int score = 0;
    String name;
    boolean answered = false;

    // NEW: hint tracking
    private int hintsRemaining = 0;
    private JLabel lblHintsRemaining; // shows "Hints: N"

    Quiz(String name, String level) {
        this.name = name;
        setBounds(50, 0, 1440, 850);
        getContentPane().setBackground(new Color(245, 245, 245));
        setUndecorated(true);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/quiz.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1440, 300);
        add(image);

        qno = new JLabel();
        qno.setBounds(100, 330, 50, 30);
        qno.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(qno);

        question = new JLabel();
        question.setBounds(150, 330, 1000, 30);
        question.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        add(question);

        timerLabel = new JLabel("Time left: 15s");
        timerLabel.setBounds(1100, 330, 200, 30);
        timerLabel.setFont(new Font("Consolas", Font.BOLD, 22));
        timerLabel.setForeground(Color.RED);
        add(timerLabel);

        progressBar = new JProgressBar(0, 10);
        progressBar.setBounds(100, 370, 1200, 25);
        progressBar.setValue(1);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        progressBar.setForeground(new Color(22, 99, 54));
        add(progressBar);

        opt1 = new JRadioButton();
        opt2 = new JRadioButton();
        opt3 = new JRadioButton();
        opt4 = new JRadioButton();

        JRadioButton[] options = {opt1, opt2, opt3, opt4};
        int y = 420;
        for (JRadioButton opt : options) {
            opt.setBounds(170, y, 1000, 30);
            opt.setBackground(Color.WHITE);
            opt.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            opt.addActionListener(this);
            add(opt);
            y += 40;
        }

        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);

        next = createButton("Next", 540, 680, new Color(34, 139, 34), this);
        lifeline = createButton("Hint", 710, 680, new Color(70, 130, 180), this);
        submit = createButton("Submit", 880, 680, new Color(255, 165, 0), this);
        submit.setEnabled(false);

        quit = createButton("Quit", 40, 680, Color.DARK_GRAY, e -> {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) System.exit(0);
        });

        add(next); add(lifeline); add(submit); add(quit);

        // NEW: Hints remaining label (placed near hint button)
        lblHintsRemaining = new JLabel();
        lblHintsRemaining.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblHintsRemaining.setForeground(new Color(60, 60, 60));
        lblHintsRemaining.setBounds(710, 650, 150, 20);
        add(lblHintsRemaining);

        loadQuestions(level);

        // NEW: initialize hints based on level
        if ("Easy".equalsIgnoreCase(level)) {
            hintsRemaining = 6;
        } else if ("Medium".equalsIgnoreCase(level)) {
            hintsRemaining = 4;
        } else {
            hintsRemaining = 3; // Hard or default
        }
        updateHintUI(); // update label and lifeline enabled state

        start(count);
        startTimer();

        setVisible(true);
    }

    private JButton createButton(String text, int x, int y, Color color, ActionListener al) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 150, 40);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.addActionListener(al);
        return btn;
    }

    public void loadQuestions(String level) {
        String[][] easy = {
            {"Number of primitive data types in Java?", "6", "7", "8", "9"},
            {"Size of float and double in Java?", "32 and 64", "32 and 32", "64 and 64", "64 and 32"},
            {"Automatic type conversion possible in?", "Byte to int", "Int to Long", "Long to int", "Short to int"},
            {"When array passed to method, what is received?", "The reference of the array", "A copy of the array", "Length of the array", "Copy of first element"},
            {"Arrays in Java are?", "Object References", "Objects", "Primitive data type", "None"},
            {"When is object created with new keyword?", "At run time", "At compile time", "Depends on code", "None"},
            {"Which is a valid keyword in Java?", "interface", "string", "Float", "unsigned"},
            {"compareTo() returns?", "True", "False", "An int value", "None"},
            {"String class belongs to?", "java.lang", "java.awt", "java.applet", "java.String"},
            {"String class has how many constructors?", "3", "7", "13", "20"}
        };

        String[][] medium = {
            {"What is JVM responsible for?", "Compiling code", "Loading class files", "Executing bytecode", "All"},
            {"Java is platform independent because of?", "Compiler", "JDK", "JVM", "Bytecode"},
            {"Access specifier for inheritance in different package?", "private", "protected", "default", "final"},
            {"What is final keyword used for?", "Constant variable", "Overridable method", "Object reference", "None"},
            {"Which keyword is used for interface?", "class", "interface", "extends", "implements"},
            {"Which is not a primitive type?", "int", "boolean", "String", "char"},
            {"Which package is automatically imported?", "java.util", "java.lang", "java.io", "java.net"},
            {"What is method overloading?", "Multiple methods with same name and params", "Same name, different params", "Same return type", "None"},
            {"Which is not a loop in Java?", "for", "while", "loop", "do-while"},
            {"Java uses which memory model?", "Stack", "Heap", "Both", "None"}
        };

        String[][] hard = {
            {"What is reflection in Java?", "Debugging", "Runtime inspection", "Compile-time logic", "None"},
            {"JVM consists of?", "Loader", "Runtime Data Area", "Bytecode Verifier", "All"},
            {"JIT stands for?", "Jump Into Time", "Just-In-Time", "Java Interpreted Type", "None"},
            {"Which is not thread state?", "Runnable", "Dead", "Waiting", "Frozen"},
            {"Java 8 introduced?", "Pointers", "Goto", "Lambdas", "Macros"},
            {"Which collection is synchronized?", "ArrayList", "Vector", "HashSet", "TreeSet"},
            {"What is ClassLoader?", "Loads libraries", "Loads files", "Loads classes", "All"},
            {"Java memory leak can be caused by?", "Static references", "Garbage Collector", "Final methods", "None"},
            {"Thread priority range in Java?", "0-10", "1-5", "1-10", "0-5"},
            {"Default thread priority?", "0", "5", "1", "10"}
        };

        String[] easyAnswers = {"8", "32 and 64", "Int to Long", "The reference of the array", "Objects", "At run time", "interface", "An int value", "java.lang", "13"};
        String[] mediumAnswers = {"All", "Bytecode", "protected", "Constant variable", "interface", "String", "java.lang", "Same name, different params", "loop", "Both"};
        String[] hardAnswers = {"Runtime inspection", "All", "Just-In-Time", "Frozen", "Lambdas", "Vector", "All", "Static references", "1-10", "5"};

        String[][] levelQuestions;
        String[] levelAnswers;

        switch (level.toLowerCase()) {
            case "medium":
                levelQuestions = medium;
                levelAnswers = mediumAnswers;
                break;
            case "hard":
                levelQuestions = hard;
                levelAnswers = hardAnswers;
                break;
            default:
                levelQuestions = easy;
                levelAnswers = easyAnswers;
                break;
        }

        for (int i = 0; i < 10; i++) {
            questions[i] = levelQuestions[i];
            answers[i][1] = levelAnswers[i];
        }

    }

    public void actionPerformed(ActionEvent ae) {
        Object src = ae.getSource();

        if (src instanceof JRadioButton && !answered) {
            saveAnswer();
            String selected = groupoptions.getSelection().getActionCommand();
            String correct = answers[count][1];
            answered = true;
            enableOptions(false);
            if (selected.equals(correct)) {
                JOptionPane.showMessageDialog(this, "Correct!", "Answer Feedback", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Wrong! Correct Answer: " + correct, "Answer Feedback", JOptionPane.ERROR_MESSAGE);
            }
        } else if (src == next) {
            count++;
            if (count == 9) next.setEnabled(false);
            if (count == 9) submit.setEnabled(true);
            start(count);
        } else if (src == lifeline) {
            giveHint();
        } else if (src == submit) {
            calculateScore();
            setVisible(false);
            new score(name, score, questions, answers, useranswers);
        }
    }

    public void giveHint() {
        // Check if any hints remain
        if (hintsRemaining <= 0) {
            JOptionPane.showMessageDialog(this, "No hints left!", "Hint", JOptionPane.INFORMATION_MESSAGE);
            lifeline.setEnabled(false);
            return;
        }

        String correct = answers[count][1];
        List<JRadioButton> options = Arrays.asList(opt1, opt2, opt3, opt4);
        Collections.shuffle(options);

        JRadioButton correctBtn = null, randomWrongBtn = null;

        for (JRadioButton btn : options) {
            if (btn.getText().equals(correct)) {
                correctBtn = btn;
            }
        }
        for (JRadioButton btn : options) {
            if (!btn.getText().equals(correct)) {
                randomWrongBtn = btn;
                break;
            }
        }

        for (JRadioButton btn : options) {
            btn.setEnabled(false);
        }

        if (correctBtn != null) correctBtn.setEnabled(true);
        if (randomWrongBtn != null) randomWrongBtn.setEnabled(true);

        // Decrement hint count and update UI
        hintsRemaining--;
        updateHintUI();

        // If no hints remain, disable lifeline permanently for the session
        if (hintsRemaining <= 0) {
            lifeline.setEnabled(false);
        }
    }

    // NEW: update hint label and button state
    private void updateHintUI() {
        lblHintsRemaining.setText("Hints: " + hintsRemaining);
        lifeline.setEnabled(hintsRemaining > 0);
    }

    public void start(int count) {
        // ... no changes
        qno.setText((count + 1) + ". ");
        question.setText(questions[count][0]);
        opt1.setText(questions[count][1]); opt1.setActionCommand(questions[count][1]);
        opt2.setText(questions[count][2]); opt2.setActionCommand(questions[count][2]);
        opt3.setText(questions[count][3]); opt3.setActionCommand(questions[count][3]);
        opt4.setText(questions[count][4]); opt4.setActionCommand(questions[count][4]);
        groupoptions.clearSelection();

        timer = 15;
        timerLabel.setText("Time left: " + timer + "s");
        progressBar.setValue(count + 1);
        enableOptions(true);

        // Respect global hintsRemaining — only enable lifeline if there are hints left
        lifeline.setEnabled(hintsRemaining > 0);
        lblHintsRemaining.setText("Hints: " + hintsRemaining);

        answered = false;
    }

    public void saveAnswer() {
        if (groupoptions.getSelection() != null) {
            useranswers[count][0] = groupoptions.getSelection().getActionCommand();
        } else {
            useranswers[count][0] = "";
        }
    }

    public void calculateScore() {
        for (int i = 0; i < useranswers.length; i++) {
            if (useranswers[i][0].equals(answers[i][1])) {
                score += 10;
            }
        }
    }

    public void enableOptions(boolean status) {
        opt1.setEnabled(status);
        opt2.setEnabled(status);
        opt3.setEnabled(status);
        opt4.setEnabled(status);
    }

    public void startTimer() {
        if (countdownTimer != null && countdownTimer.isRunning()) countdownTimer.stop();

        countdownTimer = new Timer(1000, e -> {
            timer--;
            timerLabel.setText("Time left: " + timer + "s");

            if (timer <= 0) {
                countdownTimer.stop();
                saveAnswer();

                if (count == 9) {
                    calculateScore();
                    setVisible(false);
                    new score(name, score, questions, answers, useranswers);
                } else {
                    count++;
                    if (count == 9) {
                        next.setEnabled(false);
                        submit.setEnabled(true);
                    }
                    start(count);
                    startTimer();
                }
            }
        });
        countdownTimer.start();
    }
}
