# Happiness Project (Java Project – TMF2954)

Happiness Project is a Java Swing GUI application designed to promote mental health awareness for university students. The app offers interactive learning, self-reflection, and quizzes in a friendly, comforting environment.

---

## 💻 How to Run the App

1. Clone the repository: 
git clone https://github.com/adiatulamiera/HappinessProject.git

2. Run the application (ensure JDK 8 or above is installed): 
javac MainGUI.java
java MainGUI

> No external libraries needed.

---

## 🗂️ App Overview

### 🏠 Home Page
- Start Learning → explore mental health content (text, video, image)
- Take Quiz → answer 20 questions with instant feedback
- View Scores → view quiz score history and motivational badges

---

## 📘 Learning Module

- Each page includes:
- 📝 Educational text
- 🖼️ Image illustrations
- ▶️ Video with a “Watch Video” button
- Users navigate with ← Back and Next → buttons
- Finishing all pages shows a completion message

---

## 🧪 Quiz Module

- 20 total questions
- Includes:
- ✅ Multiple Choice
- ✅ True/False
- User receives:
- Real-time feedback (Correct/Incorrect)
- Motivational score at the end
- Badges are given based on performance

---

## 🏅 Gamification

- ScoreManager tracks each quiz
- BadgeSystem gives awards
- User enters their name to save progress
- Viewable under View Scores

---

## 📌 Error Handling

- If the user submits a quiz without selecting an answer → warning popup

---

## 📁 Project Structure (Important Files Only)

- MainGUI.java – main app window & panel switcher
- LearnPanel.java – handles educational content pages
- QuizPanel.java – displays questions & handles answers
- ScorePanel.java – shows final score + badge + leaderboard
- assets/ – image folder (illustrations used in LearnPanel)

---

## 🙋‍♀️ Developed By

- Nur Adiatul Amiera Binti Zulkurnain  
- Mifdzal  

---

## 📣 Note

This project was inspired by the Happiness Project movement — aimed to make mental health learning gentle, positive, and visual.

---