public interface ProgressTracker {
    void saveScore(String username);
    void loadScores();
    String generateMessage();
}
