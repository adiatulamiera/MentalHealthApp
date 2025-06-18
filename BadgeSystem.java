public class BadgeSystem implements Gamification {
    private int score;
    private int total;
    private String badge;

    public void setScoreData(int score, int total) {
        this.score = score;
        this.total = total;
    }

    @Override
    public void evaluateBadge() {
        int percent = (int)((score / (double) total) * 100);

        if (percent >= 80) badge = "🌟 Gold Badge - Outstanding!";
        else if (percent >= 60) badge = "🥈 Silver Badge - Great job!";
        else if (percent >= 40) badge = "🥉 Bronze Badge - Good try!";
        else badge = "🧠 No badge, but don’t give up!";
    }

    @Override
    public void displayBadge() {
        System.out.println("🏅 Your Reward: " + badge);
    }

    public String getBadge() {
        return badge;
    }
}
