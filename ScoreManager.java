// Implemented by Mifdzal
// Tested by Amiera

import java.io.*;
import java.util.*;

public class ScoreManager implements ProgressTracker {
    private int score;
    private int total;
    private String scoreFile;

    public ScoreManager(int total) {
        this.total = total;
        this.score = 0;
        this.scoreFile = "scores.txt";
    }

    // Increment the user's score
    public void incrementScore() {
        score++;
    }

    // Save the user's score to the file
    @Override
    public void saveScore(String user) {
        try (FileWriter fw = new FileWriter(scoreFile, true)) {
            int percent = (int)((score / (double) total) * 100);
            fw.write(user + " - Score: " + score + "/" + total + " (" + percent + "%)\n");
            System.out.println("✅ Score saved.");
        } catch (IOException e) {
            System.out.println("❌ Error saving score: " + e.getMessage());
        }
    }

    // Load and print all previous scores from the file (for console/debug)
    @Override
    public void loadScores() {
        try (Scanner sc = new Scanner(new File(scoreFile))) {
            System.out.println("📊 Previous Scores:");
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (IOException e) {
            System.out.println("❌ Unable to load scores: " + e.getMessage());
        }
    }

    // Generate a motivational message based on the user's score
    @Override
    public String generateMessage() {
        int percent = (int)((score / (double) total) * 100);
        if (percent >= 80) return "🌟 Outstanding!";
        if (percent >= 60) return "👏 That's good!";
        if (percent >= 40) return "👍 Good try!";
        if (percent >= 20) return "💪 You can do better!";
        return "🧠 Don’t give up!";
    }

    // Getters for score and total
    public int getScore() {
        return score;
    }

    public int getTotal() {
        return total;
    }

    // Reset the score for a new quiz
    public void reset() {
        this.score = 0;
    }

    // Get badge for the current score
    @Override
    public String getBadge() {
        int percent = (int)((score / (double) total) * 100);
        if (percent >= 80) return "🌟 Gold";
        if (percent >= 60) return "🥈 Silver";
        if (percent >= 40) return "🥉 Bronze";
        return "🧠 Encouragement";
    }

    // Get all scores with badges for display
    @Override
    public String getAllScoresWithBadges() {
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(new File(scoreFile))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // Try to extract percent from the line
                int percent = 0;
                int idx1 = line.lastIndexOf('(');
                int idx2 = line.lastIndexOf('%');
                if (idx1 != -1 && idx2 != -1 && idx2 > idx1) {
                    try {
                        percent = Integer.parseInt(line.substring(idx1 + 1, idx2));
                    } catch (Exception ignored) {}
                }
                String badge;
                if (percent >= 80) badge = "🌟";
                else if (percent >= 60) badge = "🥈";
                else if (percent >= 40) badge = "🥉";
                else badge = "🧠";
                sb.append(line).append(" ").append(badge).append("\n");
            }
        } catch (IOException e) {
            // No message if no progress
        }
        return sb.toString();
    }

    @Override
    public String getProgressSummary() {
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(new File(scoreFile))) {
            int count = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // Extract percent
                int percent = 0;
                int idx1 = line.lastIndexOf('(');
                int idx2 = line.lastIndexOf('%');
                if (idx1 != -1 && idx2 != -1 && idx2 > idx1) {
                    try {
                        percent = Integer.parseInt(line.substring(idx1 + 1, idx2));
                    } catch (Exception ignored) {}
                }
                // Badge and message
                String badge, message;
                if (percent >= 80) {
                    badge = "Gold";
                    message = "Outstanding!";
                } else if (percent >= 60) {
                    badge = "Silver";
                    message = "Great job!";
                } else if (percent >= 40) {
                    badge = "Bronze";
                    message = "Good try!";
                } else {
                    badge = "Encouragement";
                    message = "Keep going!";
                }
                sb.append(count++)
                  .append(". ")
                  .append(line)
                  .append(" [").append(badge).append("] ")
                  .append("- ").append(message)
                  .append("\n");
            }
        } catch (IOException e) {
            // No summary if no progress
        }
        return sb.toString();
    }
}
