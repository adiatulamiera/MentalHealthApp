import java.util.*;

public class LearningModule extends Module implements ContentProvider {
    private List<LearningContent> contentPages;
    private int currentIndex;

    public LearningModule(String title, int duration) {
        super(title, duration);
        this.contentPages = new ArrayList<>();
        this.currentIndex = 0;
        loadContent();
    }

    @Override
    public void loadContent() {
        // IMAGE PAGE — Page 1: What Depression Feels Like
        addImagePage(Arrays.asList(
        "assets/page1/1.jpg",
            "assets/page1/2.jpg",
            "assets/page1/3.jpg",
            "assets/page1/4.jpg",
            "assets/page1/5.jpg",
            "assets/page1/6.jpg"
        ));

        // VIDEO PAGES
        addVideoPage("🎬 Mental Health TikTok #1", "https://vt.tiktok.com/ZSkbcXLfN/");
        addVideoPage("🎬 Mental Health TikTok #2", "https://vt.tiktok.com/ZSkbcbj3N/");
        addVideoPage("🎬 Mental Health TikTok #3", "https://vt.tiktok.com/ZSkbcW5A1/");
    }

    public void addVideoPage(String title, String videoLink) {
        contentPages.add(new LearningContent(title, videoLink));
    }

    public void addImagePage(List<String> imagePaths) {
        contentPages.add(new LearningContent(imagePaths));
    }

    public boolean nextPage() {
        if (currentIndex < contentPages.size() - 1) {
            currentIndex++;
            return true;
        } else {
            return false;
        }
    }

    public void previousPage() {
        if (currentIndex > 0) {
            currentIndex--;
        }
    }

    public boolean isFinished() {
        return currentIndex >= contentPages.size() - 1;
    }

    public int getCurrentPageIndex() {
        return currentIndex;
    }

    public LearningContent getCurrentPage() {
        return contentPages.get(currentIndex);
    }

    public LearningContent getPageAt(int index) {
        if (index >= 0 && index < contentPages.size()) {
            return contentPages.get(index);
        }
        return null;
    }

    public int getTotalPages() {
        return contentPages.size();
    }

    public List<LearningContent> getAllPages() {
        return contentPages;
    }
}
