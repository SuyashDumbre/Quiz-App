Quiz Application 

This Quiz Application is a Java Swing desktop project created for learning purposes.
It focuses on understanding Java GUI design, event handling, MySQL database integration, and building a complete working application with login, registration, quiz logic, timer, hints, score calculation, history, and review page.

This project is suitable for students who want to learn Java and JDBC through a real application.

About the Project

The application provides a complete quiz-taking experience.
A user can register, login, choose difficulty level, attempt questions, use hints, see the score, review the answers, and view their previous quiz attempts stored in a database.

The purpose of this project is to help students understand:

Java Swing UI components

Frame navigation and event listeners

Timer handling using javax.swing.Timer

Using arrays to store questions and answers

Storing and retrieving data with JDBC (MySQL)

Writing a clean, structured Java application

Main Features (Explained)
1. Registration and Login

Users can create an account by entering a username and password.
The username is checked for duplicates in the database.
After successful registration, the user can log in.
Both actions are done through MySQL using JDBC.

2. Difficulty Level Selection

After login, the user can choose between Easy, Medium, and Hard levels.
Each level loads a different set of 10 questions and correct answers.
The number of hints also depends on the selected level.

3. Timed Questions

Each question has a 15-second countdown timer.
If the time finishes, the app automatically moves to the next question.
This helps students learn how timers work in Java Swing.

4. Hint / Lifeline System

Users get hints depending on the chosen difficulty:

Easy: 6 hints

Medium: 4 hints

Hard: 3 hints

When a hint is used, the app will disable all incorrect options except one, and keep the correct answer enabled. This helps in understanding logic and randomization in Java.

5. Score Calculation

Each correct answer gives 10 marks.
Skipped or wrong answers give 0.
A final score screen shows the total marks.

6. Review Page

After the quiz ends, users can review all the questions.
For each question, it displays:

Question

User's answer (or "Skipped")

Correct answer

This helps students validate their performance.

7. History Page

The application stores the user's score in a MySQL table.
Users can view all their past quiz attempts along with the scores.
This demonstrates how to fetch data from MySQL and display it in a JTable.

MySQL Database Setup

Run these commands in MySQL Workbench or Command Prompt:

CREATE DATABASE quizapp;
USE quizapp;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    password VARCHAR(100)
);

CREATE TABLE quiz_results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100),
    score INT,
    taken_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


This creates two tables:

users → stores usernames and passwords

quiz_results → stores the quiz scores along with timestamp

DBUtil Configuration

The project uses a DBUtil class to connect Java with MySQL.
For local use, update the connection details:

static final String URL = "jdbc:mysql://localhost:3306/quizapp";
static final String USER = "root";
static final String PASSWORD = "your_mysql_password";


For GitHub, always remove your real credentials and keep placeholders like:

static final String USER = "yourusername";
static final String PASSWORD = "yourpassword";

How to Run the Project in NetBeans

Install Java JDK (8 or above).

Install Apache NetBeans IDE.

Install MySQL Server and MySQL Workbench.

Import the project folder into NetBeans.

Add the MySQL JDBC Connector JAR in project libraries.

Update DBUtil.java with your real MySQL username and password.

Run the project from Quizpro.java (contains the main method).

Project File Structure
src/quizpro/
    DBUtil.java
    Login1.java
    Register.java
    LevelSelection.java
    Quiz.java
    score.java
    ReviewPage.java
    MyHistory.java
    selectlevel.java
    Quizpro.java


Each class represents a specific screen or feature:

Login and Register ➝ authentication

LevelSelection ➝ choose difficulty

Quiz ➝ main quiz logic, timer, hints

score ➝ final score screen

ReviewPage ➝ review answers

MyHistory ➝ shows previous scores

DBUtil ➝ MySQL connection

Quizpro.java ➝ main entry point

Purpose of This Project

This project was created as a part of learning:

Java Swing UI

Object-oriented programming

Events and timers

Database connectivity using JDBC

Organizing a multi-file Java application

It is a student-level project but demonstrates practical concepts used in real applications.
