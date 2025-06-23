// Implemented by Mifdzal
// Tested by  Amiera

public class BadgeSystem implements Gamification {
    private int score;
    private int total;
    private String badge;

    // Set the score and total for badge evaluation
    @Override
    public void setScoreData(int score, int total) {
        this.score = score;
        this.total = total;
    }

    // Evaluate which badge to assign based on percentage
    @Override
    public void evaluateBadge() {
        int percent = (int)((score / (double) total) * 100);

        if (percent >= 80) badge = "🌟 Gold Badge - Outstanding!";
        else if (percent >= 60) badge = "🥈 Silver Badge - Great job!";
        else if (percent >= 40) badge = "🥉 Bronze Badge - Good try!";
        else badge = "🧠 No badge, but don’t give up!";
    }

    // Display badge in the console (optional, for debugging)
    @Override
    public void displayBadge() {
        System.out.println("🏅 Your Reward: " + badge);
    }

    // Get the badge string for GUI display
    public String getBadge() {
        return badge;
    }
}
