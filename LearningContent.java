import java.util.List;

public class LearningContent {
    public enum Type {
        TEXT, VIDEO, IMAGE
    }

    private Type type;
    private String textContent;
    private String videoLink;
    private List<String> imagePaths;

    // Constructor for text
    public LearningContent(String textContent) {
        this.type = Type.TEXT;
        this.textContent = textContent;
    }

    // Constructor for video
    public LearningContent(String textContent, String videoLink) {
        this.type = Type.VIDEO;
        this.textContent = textContent;
        this.videoLink = videoLink;
    }

    // Constructor for image-based content
    public LearningContent(List<String> imagePaths) {
        this.type = Type.IMAGE;
        this.imagePaths = imagePaths;
    }

    public Type getType() {
        return type;
    }

    public String getTextContent() {
        return textContent;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }
}
