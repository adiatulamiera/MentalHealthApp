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

    public void incrementScore() {
        score++;
    }

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

    public String generateMessage() {
        int percent = (int)((score / (double) total) * 100);
        if (percent >= 80) return "🌟 Outstanding!";
        if (percent >= 60) return "👏 That's good!";
        if (percent >= 40) return "👍 Good try!";
        if (percent >= 20) return "💪 You can do better!";
        return "🧠 Don’t give up!";
    }

    public int getScore() {
        return score;
    }

    public int getTotal() {
        return total;
    }

    public void reset() {
    this.score = 0;
    }

}
