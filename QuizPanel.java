import javax.swing.*;
import java.awt.*;

public class QuizPanel extends JPanel {
    private QuizManager quizManager;
    private ScoreManager scoreManager;
    private BadgeSystem badgeSystem;

    private JTextArea questionTextArea;
    private JRadioButton[] options;
    private ButtonGroup optionGroup;
    private JButton submitBtn;

    private int quizIndex = 0;
    private MainGUI mainGUI;

    public QuizPanel(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(245, 255, 250)); // pastel mint green
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        quizManager = new QuizManager("Mental Health Quiz", 20);
        quizManager.loadContent();
        scoreManager = new ScoreManager(quizManager.getTotalQuestions());
        badgeSystem = new BadgeSystem();

        questionTextArea = new JTextArea("Question will appear here");
        questionTextArea.setFont(new Font("SansSerif", Font.BOLD, 16));
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setEditable(false);
        questionTextArea.setOpaque(false);
        questionTextArea.setAlignmentX(CENTER_ALIGNMENT);
        questionTextArea.setMaximumSize(new Dimension(500, 100));
        add(questionTextArea);

        add(Box.createVerticalStrut(20));

        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("SansSerif", Font.PLAIN, 14));
            options[i].setBackground(new Color(245, 255, 250));
            options[i].setAlignmentX(CENTER_ALIGNMENT);
            optionGroup.add(options[i]);
            add(options[i]);
            add(Box.createVerticalStrut(10));
        }

        submitBtn = new JButton("✅ Submit");
        submitBtn.setAlignmentX(CENTER_ALIGNMENT);
        submitBtn.addActionListener(e -> handleSubmit());
        add(submitBtn);

        loadQuestion();
    }

    private void loadQuestion() {
        if (!quizManager.hasMoreQuestions()) {
            showResults();
            return;
        }

        Question q = quizManager.getQuestion(quizIndex);
        questionTextArea.setText("Q" + (quizIndex + 1) + ": " + q.getText());

        String[] opts = q.getOptions();
        for (int i = 0; i < options.length; i++) {
            if (i < opts.length) {
                options[i].setText(opts[i]);
                options[i].setVisible(true);
            } else {
                options[i].setVisible(false);
            }
        }

        optionGroup.clearSelection();
    }

    private void handleSubmit() {
    String selected = null;
    for (JRadioButton option : options) {
        if (option.isSelected()) {
            selected = option.getText();
            break;
        }
    }

    if (selected == null) {
        JOptionPane.showMessageDialog(this, "❗ Please select an answer before submitting.");
        return;
    }

    Question currentQuestion = quizManager.getQuestion(quizIndex);
    boolean isCorrect = quizManager.checkAnswer(selected);

    if (isCorrect) {
        JOptionPane.showMessageDialog(this, "✅ Correct!");
        scoreManager.incrementScore();
    } else {
        JOptionPane.showMessageDialog(this,
            "❌ Incorrect.\nCorrect answer: " + currentQuestion.getAnswer(),
            "Feedback", JOptionPane.INFORMATION_MESSAGE);
    }

    quizIndex++;

    if (!quizManager.hasMoreQuestions()) {
        showResults();
    } else {
        loadQuestion();
    }
}


    private void showResults() {
        String userName = JOptionPane.showInputDialog(this, "Enter your name:");
        if (userName != null && !userName.trim().isEmpty()) {
            scoreManager.saveScore(userName.trim());
        }

        String message = scoreManager.generateMessage();
        int score = scoreManager.getScore();
        int total = scoreManager.getTotal();

        badgeSystem.setScoreData(score, total);
        badgeSystem.evaluateBadge();
        String badge = badgeSystem.getBadge();

        // Show ScorePanel with results
        mainGUI.getScorePanel().displayResults(score, total, message, badge);
        mainGUI.switchTo("scores");
    }
}
