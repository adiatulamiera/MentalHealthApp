import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private MainGUI mainGUI;

    public HomePanel(MainGUI mainGUI) {
        this.mainGUI = mainGUI;

        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 240)); // soft cream background

        // Header
        JLabel titleLabel = new JLabel("Happiness Project", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Center button area
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(255, 255, 240));

        JLabel subtitle = new JLabel("<html><center>Your safe space to learn, reflect,<br>and grow emotionally.</center></html>", SwingConstants.CENTER);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        JButton learnBtn = new JButton("📖 Start Learning");
        JButton quizBtn = new JButton("💡 Take Quiz");
        JButton scoreBtn = new JButton("🏆 View Scores");

        learnBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        learnBtn.setMaximumSize(new Dimension(200, 40));
        quizBtn.setMaximumSize(new Dimension(200, 40));
        scoreBtn.setMaximumSize(new Dimension(200, 40));

        learnBtn.addActionListener(e -> mainGUI.switchTo("learn"));
        quizBtn.addActionListener(e -> mainGUI.switchTo("quiz"));
        scoreBtn.addActionListener(e -> mainGUI.switchTo("scores"));

        // Add components
        buttonPanel.add(subtitle);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(learnBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(quizBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(scoreBtn);

        add(buttonPanel, BorderLayout.CENTER);
    }
}
