// Implemented by Amiera
// Tested by Mifdzal

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.CardLayout; 

public class MainGUI extends JFrame {
    private CardLayout layout;
    private JPanel cardPanel;

    private ScorePanel scorePanel;

    public MainGUI() {
        setTitle("Happiness Project");
        setSize(400, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        layout = new CardLayout();
        cardPanel = new JPanel(layout);

        // Add modular panels
        cardPanel.add(new HomePanel(this), "home");
        cardPanel.add(new LearnPanel(this), "learn");
        cardPanel.add(new QuizPanel(this), "quiz");
        cardPanel.add(new ScorePanel(this), "scores");

        setContentPane(cardPanel); // ✅ use the full card panel
        layout.show(cardPanel, "home"); // show first screen
    }

    public void switchTo(String name) {
        layout.show(cardPanel, name);
    }

    // ⭐ Access to ScorePanel for result display
    public ScorePanel getScorePanel() {
        return scorePanel;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}
