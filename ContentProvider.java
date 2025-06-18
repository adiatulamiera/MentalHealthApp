public interface ContentProvider {
    void loadContent();
    LearningContent getCurrentPage();
    boolean nextPage();
}
