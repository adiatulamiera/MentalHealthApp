import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private MainGUI mainGUI;
    private JLabel scoreLabel;
    private JLabel messageLabel;
    private JLabel badgeLabel;
    private JButton returnButton;

    public ScorePanel(MainGUI mainGUI) {
        this.mainGUI = mainGUI;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(40, 30, 30, 30));

        JLabel title = new JLabel("📊 Your Quiz Results", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setAlignmentX(CENTER_ALIGNMENT);

        scoreLabel = new JLabel("Score: ?", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        messageLabel.setAlignmentX(CENTER_ALIGNMENT);
        messageLabel.setForeground(new Color(80, 80, 80));

        badgeLabel = new JLabel("🏅 Badge: None", SwingConstants.CENTER);
        badgeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        badgeLabel.setAlignmentX(CENTER_ALIGNMENT);

        returnButton = new JButton("🏠 Return to Home");
        returnButton.setAlignmentX(CENTER_ALIGNMENT);
        returnButton.addActionListener(e -> mainGUI.switchTo("home"));

        add(title);
        add(Box.createVerticalStrut(20));
        add(scoreLabel);
        add(Box.createVerticalStrut(10));
        add(messageLabel);
        add(Box.createVerticalStrut(10));
        add(badgeLabel);
        add(Box.createVerticalStrut(30));
        add(returnButton);
    }

    // Call this from MainGUI or QuizPanel when switching to this panel
    public void displayResults(int score, int total, String message, String badge) {
        scoreLabel.setText("Score: " + score + " / " + total);
        messageLabel.setText("<html><div style='text-align: center;'>" + message + "</div></html>");
        badgeLabel.setText("🏅 Badge: " + badge);
    }
}
