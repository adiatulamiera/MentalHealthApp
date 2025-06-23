// Implemented by Mifdzal
// Tested by Amiera

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class ScorePanel extends JPanel {
    private MainGUI mainGUI;
    private JLabel summaryLabel;
    private JPanel historyPanel;
    private JScrollPane scrollPane;
    private JButton homeButton;
    private JProgressBar avgProgressBar;

    public ScorePanel(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        setLayout(new BorderLayout());
        setBackground(new Color(245, 255, 250)); // pastel mint

        // Card panel for summary
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(255, 255, 240));
        card.setBorder(new CompoundBorder(
            new EmptyBorder(20, 20, 20, 20),
            new LineBorder(new Color(200, 220, 200), 2, true)
        ));

        // Change the title label (remove emoji)
        JLabel title = new JLabel("Progress Tracker", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        summaryLabel = new JLabel();
        summaryLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        summaryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        summaryLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        avgProgressBar = new JProgressBar(0, 100);
        avgProgressBar.setStringPainted(true);
        avgProgressBar.setPreferredSize(new Dimension(250, 22));
        avgProgressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        avgProgressBar.setForeground(new Color(120, 180, 120));
        avgProgressBar.setBackground(new Color(230, 240, 230));
        avgProgressBar.setBorder(new LineBorder(new Color(200, 220, 200), 1, true));

        card.add(title);
        card.add(summaryLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(avgProgressBar);

        // History panel
        historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        historyPanel.setOpaque(false);

        scrollPane = new JScrollPane(historyPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Quiz History"));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setPreferredSize(new Dimension(340, 350));
        scrollPane.setBackground(new Color(245, 255, 250));

        // Home button
        homeButton = new JButton("🏠 Return to Home");
        homeButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        homeButton.setBackground(new Color(220, 240, 220));
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeButton.addActionListener(e -> mainGUI.switchTo("home"));

        // Layout
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(card);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(scrollPane);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(homeButton);

        add(centerPanel, BorderLayout.CENTER);

        refreshProgress();
    }

    // Call this when switching to this panel
    public void displayResults(int score, int total) {
        refreshProgress();
    }

    public void refreshProgress() {
        // Read scores.txt and analyze
        java.util.List<ScoreEntry> entries = new ArrayList<>();
        int best = 0, sum = 0;
        try (Scanner sc = new Scanner(new File("scores.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                ScoreEntry entry = ScoreEntry.parse(line);
                if (entry != null) {
                    entries.add(entry);
                    best = Math.max(best, entry.percent);
                    sum += entry.percent;
                }
            }
        } catch (IOException e) {
            // No scores yet
        }

        int totalQuizzes = entries.size();
        int avg = totalQuizzes > 0 ? sum / totalQuizzes : 0;
        String badgeStr = "None";
        if (!entries.isEmpty()) {
            badgeStr = entries.get(entries.size() - 1).badgeEmoji();
        }

        summaryLabel.setText(String.format(
            "<html><center>"
            + "<b>Total:</b> %d &nbsp; "
            + "<b>Best:</b> %d%% &nbsp; "
            + "<b>Badge:</b> %s"
            + "</center></html>",
            totalQuizzes, best, badgeStr
        ));
        avgProgressBar.setValue(avg);
        avgProgressBar.setString("Average: " + avg + "%");

        // History
        historyPanel.removeAll();
        if (entries.isEmpty()) {
            JLabel none = new JLabel("No quiz attempts yet. Try a quiz!");
            none.setFont(new Font("SansSerif", Font.ITALIC, 14));
            none.setForeground(Color.GRAY);
            none.setAlignmentX(Component.CENTER_ALIGNMENT);
            historyPanel.add(Box.createVerticalStrut(30));
            historyPanel.add(none);
        } else {
            // Sort entries by percent descending, then by score descending
            entries.sort((a, b) -> {
                if (b.percent != a.percent) return b.percent - a.percent;
                return b.score - a.score;
            });

            String[] medals = {"🥇", "🥈", "🥉"};
            int idx = 1;
            for (ScoreEntry entry : entries) {
                JPanel card = new JPanel();
                card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
                card.setBackground(new Color(255, 255, 250));
                card.setBorder(new CompoundBorder(
                    new LineBorder(new Color(200, 220, 200), 1, true),
                    new EmptyBorder(10, 10, 10, 10)
                ));

                String rank = idx <= 3 ? medals[idx - 1] : String.valueOf(idx);

                JLabel top = new JLabel(
                    String.format(
                        "<html><b>%s %s</b></html>",
                        rank, entry.user
                    )
                );
                top.setFont(new Font("SansSerif", Font.BOLD, 14));

                JLabel score = new JLabel(
                    String.format("Score: %d/%d (%d%%)", entry.score, entry.total, entry.percent)
                );
                score.setFont(new Font("Monospaced", Font.PLAIN, 13));
                score.setForeground(new Color(80, 120, 80));

                JLabel badgeLabel = new JLabel("Badge: " + entry.badgeEmoji());
                badgeLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
                badgeLabel.setForeground(new Color(100, 140, 200));

                JLabel msg = new JLabel(entry.motivation());
                msg.setFont(new Font("SansSerif", Font.ITALIC, 12));
                msg.setForeground(new Color(100, 100, 100));

                card.add(top);
                card.add(Box.createVerticalStrut(3));
                card.add(score);
                card.add(Box.createVerticalStrut(3));
                card.add(badgeLabel);
                card.add(Box.createVerticalStrut(3));
                card.add(msg);

                card.setAlignmentX(Component.LEFT_ALIGNMENT);
                card.setMaximumSize(new Dimension(380, 120));
                card.setPreferredSize(new Dimension(380, 120));
                historyPanel.add(card);
                historyPanel.add(Box.createVerticalStrut(8));
                idx++;
            }
        }
        historyPanel.revalidate();
        historyPanel.repaint();
    }

    // Helper class for parsing score lines
    static class ScoreEntry {
        String user;
        int score, total, percent;

        static ScoreEntry parse(String line) {
            try {
                // Example: "Miera - Score: 0/20 (0%)"
                int dash = line.indexOf(" - ");
                int slash = line.indexOf("/", dash);
                int paren = line.indexOf("(", slash);
                int percentIdx = line.indexOf("%", paren);
                if (dash < 0 || slash < 0 || paren < 0 || percentIdx < 0) return null;
                String user = line.substring(0, dash).trim();
                int score = Integer.parseInt(line.substring(dash + 9, slash).trim());
                int total = Integer.parseInt(line.substring(slash + 1, paren).trim());
                int percent = Integer.parseInt(line.substring(paren + 1, percentIdx).trim());
                ScoreEntry e = new ScoreEntry();
                e.user = user;
                e.score = score;
                e.total = total;
                e.percent = percent;
                return e;
            } catch (Exception ex) {
                return null;
            }
        }

        // In ScoreEntry, return badge name only (no emoji)
        String badgeEmoji() {
            if (percent >= 80) return "Gold";
            if (percent >= 60) return "Silver";
            if (percent >= 40) return "Bronze";
            return "Encouragement";
        }

        String motivation() {
            if (percent >= 80) return "Outstanding!";
            if (percent >= 60) return "Great job!";
            if (percent >= 40) return "Good try!";
            if (percent >= 20) return "Keep going!";
            return "Don't give up!";
        }
    }
}
