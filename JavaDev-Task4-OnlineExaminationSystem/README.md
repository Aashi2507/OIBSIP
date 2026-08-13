# Online Examination System – Java Swing

The **Online Examination System** is a GUI-based desktop application developed using **Java and Swing**. It provides a complete examination workflow where students can register, log in, update their profile, select a subject, attempt multiple-choice questions within a fixed time, and view their result.

## Features

- User registration and login
- Username and password validation
- Profile update before starting the examination
- Change display name and password before starting
- Five examination subjects:
  - Java
  - DBMS
  - Data Structures
  - Operating Systems
  - Computer Networks
- 25 MCQs in each subject
- Four radio-button options for every question
- Next and Previous navigation
- Answers remain selected while navigating
- Question progress bar
- Answered-question counter
- 30-minute countdown timer
- Automatic submission when time runs out
- Manual submission with confirmation dialog
- Result screen showing score
- Correct, incorrect and unanswered breakdown
- Time taken displayed after submission
- Quit confirmation while an examination is running
- Logout from the result screen
- Compact and simple Swing interface
- No SQL database required

## Project Structure

```text
OnlineExaminationSystem/
│
├── src/
│   ├── Main.java
│   ├── ExamApplication.java
│   ├── User.java
│   ├── UserStore.java
│   ├── Question.java
│   └── QuestionBank.java
│
├── README.md
└── .gitignore
```

## Technologies Used

- Java
- Java Swing
- AWT
- Object-Oriented Programming
- CardLayout
- JRadioButton
- ButtonGroup
- JProgressBar
- javax.swing.Timer
- Java Collections Framework

## How to Run

### IntelliJ IDEA

1. Open the `OnlineExaminationSystem` folder.
2. Configure a Java JDK.
3. Open `src/Main.java`.
4. Run the `Main` class.

### Command Line

```bash
javac -d out src/*.java
java -cp out Main
```

## Demo Account

```text
Username: student
Password: 1234
```

You can also create a new account from the **Create Account** button on the login screen.

## Examination Flow

```text
Register / Login
       ↓
Profile Update
       ↓
Select Subject
       ↓
25 MCQs
       ↓
Next / Previous
       ↓
Manual Submit / Auto Submit
       ↓
Result
       ↓
Logout
```

## Profile Update

After successful login, the **Update Profile** screen is displayed before subject selection.

The student can:

- Change the display name
- Set a new password

The student must provide both values before continuing to subject selection.

## Examination Details

- Every subject contains 25 questions.
- Each question has four options.
- Only one option can be selected.
- The examination time is 30 minutes.
- The timer remains visible during the examination.
- The exam is automatically submitted when the timer reaches zero.
- Manual submission asks for confirmation.
- The window close button asks for confirmation while an examination is active.
- The result screen displays the final score and answer breakdown.

## Data Management

The project does not use an SQL database because a database is not required for the examination-system specification.

User accounts are maintained in memory while the application is running, and the questions are stored in the Java question bank.

## Screenshots

Recommended screenshots for the GitHub repository:

```text
screenshots/
├── login.png
├── registration.png
├── profile-update.png
├── subject-selection.png
├── examination.png
└── result.png
```
