import java.util.List;

public class LearningContent {
    public enum Type {
        TEXT, IMAGE, VIDEO
    }

    private String textContent;
    private String videoLink;
    private List<String> imagePaths;
    private Type type;

    // Constructor for plain text page
    public LearningContent(String textContent) {
        this.textContent = textContent;
        this.type = Type.TEXT;
    }

    // Constructor for text + video link (1-page video entry)
    public LearningContent(String textContent, String videoLink) {
        this.textContent = textContent;
        this.videoLink = videoLink;
        this.type = Type.TEXT; // still rendered as a text page
    }

    // Constructor for image page
    public LearningContent(List<String> imagePaths) {
        this.imagePaths = imagePaths;
        this.type = Type.IMAGE;
    }

    // Getters
    public String getTextContent() {
        return textContent;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public Type getType() {
        return type;
    }
}
