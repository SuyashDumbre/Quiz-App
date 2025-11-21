# 🧠 Quiz Application (QuizApp)

A fully functional **MCQ-based desktop quiz system** built using **Java Swing** and **Core Java**, designed to provide a real exam-like experience.  
The application includes **login**, **difficulty levels**, **15-second timer**, **progress bar**, **hint feature**, **result screen**,  
**answer review**, and **quiz history tracking using MySQL**.

---

## 📌 1. Project Description

**QuizApp** allows users to log in, select a difficulty level, answer timed multiple-choice questions, and view results instantly.  
All quiz attempts are stored in a **MySQL database**, enabling users to track their historical performance.

This project demonstrates:

- Building UI using **Java Swing**
- Implementing **timed questions**
- Using **JProgressBar** for tracking progress
- JDBC connectivity with **MySQL**
- Managing multi-screen workflows
- Applying clean object-oriented programming principles

---

## 🚀 2. Features

### 🔐 User Login
- Username + password authentication  
- Validates credentials using the `users` table  
- Only authenticated users can proceed to the quiz  

---

### 🎚 Difficulty Selection
- Choose between:
  - **Easy**
  - **Medium**
  - **Hard**
- Determines the question set loaded from the `questions` table  

---

### ⏱ 15-Second Countdown Timer
- Every question has **exactly 15 seconds**
- Timer resets automatically on each new question  
- Auto-navigates to the next question when time expires  
- Built using `javax.swing.Timer`

---

### 📊 Progress Bar
- Visual indicator of quiz completion  
- Progress increases with each question  
- Enhances user experience  

---

### 🧩 Main Quiz Screen
Includes:

- Question text  
- Four MCQ options  
- 15-second timer  
- Progress bar  
- Buttons:
  - **Next**
  - **Hint**
  - **Submit**
  - **Quit**

User answers are stored for evaluation at the end.

---

### 💡 Hint Feature
- Usable once per question  
- Reveals:
  - **One correct option**
  - **One incorrect option**
- Helps users make an informed choice  

---

### 🏁 Result Screen
Displays:

- Final score  
- Correct vs incorrect answers  
- Accuracy  
- Performance summary  

---

### 🔍 Review Answers Screen
Shows:

- All questions  
- Correct answers  
- User’s selected answers  
- Clean, color-coded layout for analysis  

---

### 📘 History Page
Allows users to view all past quiz attempts fetched from MySQL, including:

- Score  
- Date & time  
- Username  

---

## ⚙️ 3. Application Flow

1. **Login** → Validate user  
2. **Select Difficulty Level**  
3. **View Rules**  
4. **Start Quiz** (Timer + Progress Bar + Hint + Navigation)  
5. **Submit Quiz** → Score generated  
6. **View Result**  
7. **Review Answers**  
8. **View History (MySQL)**  

---

## 🧩 4. Technologies Used

- **Java Swing** (JFrame, JPanel, JLabel, JButton, JProgressBar, JTextArea, JRadioButton)  
- **Core Java OOP**  
- **javax.swing.Timer**  
- **Event Handling (ActionListener)**  
- **JDBC**  
- **MySQL Database**  
- **NetBeans IDE**

---

# 🧠 Key Learning Outcomes (Short & Professional)

- Building desktop UI using **Java Swing** (frames, buttons, labels, progress bar).  
- Implementing a **15-second countdown timer** with `javax.swing.Timer`.  
- Using **event-driven programming** and handling button actions.  
- Applying **Core Java OOP** concepts to structure a multi-screen application.  
- Implementing **Hint logic** (show 1 correct + 1 wrong option).  
- Updating and syncing a **progress bar** during quiz flow.  
- Connecting Java to **MySQL using JDBC** for login, questions, and history.  
- Designing **database tables** (`users`, `questions`, `quiz_results`).  
- Calculating scores and building a **result + review answers system**.  
- Running a full workflow in **NetBeans** from setup → connect DB → run quiz.  

---

# 📚 Quiz Application Project Setup Guide

This section provides the **database schema** and **setup steps** needed to run the Quiz Application.

---

# 💾 Database Schema (MySQL)

The Quiz Application requires **three** tables:

- **users** – stores login credentials  
- **quiz_results** – stores user quiz history  
---

## 🗄️ Database Tables (Users + Quiz Results)

```sql
-- Create the database
CREATE DATABASE IF NOT EXISTS quizapp;
USE quizapp;

-- Users Table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Quiz Results Table (History)
CREATE TABLE IF NOT EXISTS quiz_results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    score INT NOT NULL,
    date_taken TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
# 🏁 How to Run the Project (NetBeans)

## 1️⃣ Open in NetBeans
- Import the project folder  
- Ensure all `.java` files compile correctly  
- Add **MySQL Connector/J**:
  - Right-click project → **Properties**
  - Go to **Libraries**
  - Click **Add JAR/Folder**
  - Select the MySQL JDBC `.jar` file

------------------------------------------------------------

## 2️⃣ Update Database Connection

Modify the database connection details in your Java code:
```java
String url = "jdbc:mysql://localhost:3306/quizapp";
String user = "root";
String password = "your_password";
```



