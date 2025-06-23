// Implemented by Mifdzal
// Tested by Amiera

public abstract class Module {
    protected String title;
    protected int duration; // in minutes
    protected String type;

    public Module(String title, int duration) {
        this.title = title;
        this.duration = duration;
        this.type = "Generic";
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getType() {
        return type;
    }

    // Forces subclasses to define how they load content
    public abstract void loadContent();
}
