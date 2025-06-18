import javax.swing.*;
import java.awt.*;

public class QuizPanel extends JPanel {
    private QuizManager quizManager;
    private ScoreManager scoreManager;
    private BadgeSystem badgeSystem;

    private JLabel questionLabel;
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

        questionLabel = new JLabel("Question will appear here");
        questionLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        questionLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(questionLabel);

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
        questionLabel.setText("Q" + (quizIndex + 1) + ": " + q.getText());

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
        for (JRadioButton btn : options) {
            if (btn.isVisible() && btn.isSelected()) {
                selected = btn.getText();
                break;
            }
        }

        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select an answer.");
            return;
        }

        quizManager.checkAnswer(selected);
        scoreManager.incrementScore();
        quizIndex++;
        loadQuestion();
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
