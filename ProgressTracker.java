// Implemented by Mifdzal
// Tested by Amiera

/**
 * ProgressTracker provides methods to track, save, and display user quiz progress,
 * including motivational feedback and badges for gamification.
 */
public interface ProgressTracker {
    /**
     * Save the user's score and progress to persistent storage.
     * @param username The user's name.
     */
    void saveScore(String username);

    /**
     * Load and display all previous scores from storage (console or GUI).
     */
    void loadScores();

    /**
     * Generate a motivational message based on the user's latest score.
     * @return A creative, encouraging message.
     */
    String generateMessage();

    /**
     * Get the badge (with emoji) for the user's latest score.
     * @return A badge string, e.g., "🌟 Gold".
     */
    String getBadge();

    /**
     * Get all scores with badges for display in the GUI.
     * @return A formatted string of all scores and their badges.
     */
    String getAllScoresWithBadges();

    /**
     * Get a detailed progress summary for the user, including
     * scores, badges, and motivational messages.
     * @return A creative, multi-line summary string.
     */
    default String getProgressSummary() {
        // This can be overridden for custom summaries in implementing classes
        return "Progress summary not implemented.";
    }
}
