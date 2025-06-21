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

    // One-page video (description + button)
    private void addVideoPageWithDescription(String videoLink, String description) {
        contentPages.add(new LearningContent(description, videoLink));
    }

    // Title + content for learning text
    private void addTextPage(String title, String textContent) {
        contentPages.add(new LearningContent(title + "\n\n" + textContent));
    }

    @Override
    public void loadContent() {
        // PAGE 1: TEXT
        addTextPage("Understanding Mental Health",
            "Mental health is about how we feel, think, and cope with life. It affects how we handle stress, relate to others, and make choices. "
            + "Just like our physical health, our mind deserves care too. 💛\n\n"
            + "📚 University life can be overwhelming — deadlines, expectations, and uncertainty. "
            + "Remember, taking care of your mental well-being isn't a luxury — it's a necessity. 🌱"
        );

        // PAGE 2: IMAGE — What Depression Feels Like
        addImagePage(List.of(
            "assets/page2/1.jpg",
            "assets/page2/2.jpg",
            "assets/page2/3.jpg",
            "assets/page2/6.jpg"
        ));

        // PAGE 3: TEXT
        addTextPage("Burnout Isn't Laziness",
            "Burnout is what happens when you've been strong for too long without rest. It's not laziness, its exhaustion. It's your mind and body saying: ‘I can’t keep doing this.’ 😞\n\n"
            + "You might feel tired even after sleeping, struggle to focus, feel disconnected from your work or studies, or like you're just going through the motions. These are all signs of burnout.\n\n"
            + "💡 Recovery starts with acknowledging you’re burnt out — and giving yourself permission to slow down. Here's what might help:\n"
            + "😊 Take guilt-free breaks (real ones, not scrolling stressfully)\n"
            + "📅 Set boundaries — say no without overexplaining\n"
            + "☕ Talk to someone you trust or seek campus support\n"
            + "💛 Reconnect with activities that make you feel alive, not just productive\n\n"
            + "You don’t need to earn rest. You’re allowed to pause, reset, and come back when you’re ready. Burnout isn’t the end — it’s your body asking for kindness."
        );


        // PAGE 4: TEXT
        addTextPage("You're Not Alone",
            "😔 It's okay to feel anxious, burnt out, or overwhelmed — many students go through this too. "
            + "You might feel like you're the only one struggling, but you're not. 💬\n\n"
            + "These feelings are valid, and more common than you think. "
            + "You deserve support, and it's okay to ask for help. 🤝"
        );

        // PAGE 5: VIDEO
        addVideoPageWithDescription("https://www.tiktok.com/embed/7070996316066876718",
        "Feeling overwhelmed? This gentle breathing guide invites you to pause and reconnect with your body. "
        + "Inhale... hold... exhale... hold. Just one mindful breath can bring you back to the present. 💛");

        // PAGE 6: TEXT
        addTextPage("Healthy Coping Strategies",
            "🌙 Get enough sleep and eat regular, nourishing meals\n"
            + "🗣️ Talk to someone you trust — you're not a burden\n"
            + "🌿 Take breaks and get some fresh air, even for a few minutes\n"
            + "📵 Limit comparing yourself to others on social media\n"
            + "📝 Try journaling your thoughts or practicing mindfulness — even small steps help 💛"
        );

        // PAGE 7: IMAGE
        addImagePage(List.of(
            "assets/page7/1.png",
            "assets/page7/2.png"
        ));

        // PAGE 8: VIDEO
        addVideoPageWithDescription("https://www.tiktok.com/embed/7173077691812384046", 
            "Have you ever felt like no matter how hard you try, it’s still not enough? Maybe it’s the pressure from social media, where everyone seems to be succeeding faster than you. "
            + "Maybe it’s expectations from family or comparisons with others. Or maybe it’s your own voice whispering that you should be doing more. 💭\n\n"

            + "This video reminds us of something important: the solution isn’t always to push harder or do more. In fact, constantly chasing productivity can wear us down. "
            + "Sometimes, what we really need is to pause, take a breath, and reflect on what actually matters to us. 🌱\n\n"

            + "You don’t have to be everything to everyone. You don’t need to earn rest, love, or value through constant hustle. "
            + "You’re allowed to grow at your own pace. You’re allowed to take breaks. You are enough — even when you’re still figuring things out. 💛"
        );

        // PAGE 9: TEXT
        addTextPage("You Are Worth Helping",
            "💛 It's okay to not be okay. Life isn't always kind, and some days may feel heavier than others. Struggling doesn't mean you're failing — it means you're carrying more than most people can see.\n\n"
            + "🌧️ Sometimes we gaslight ourselves into thinking, \"Others have it worse\" or \"I'm just being sensitive.\" But the truth is: pain is not a competition. Your experiences are real. Your emotions are valid. You deserve care just as much as anyone else.\n\n"
            + "✨ You are not defined by your worst days. You are not alone in this journey — even when it feels like it. There is space for your story. You matter. You are enough. And most importantly, you are worth helping. Always."
        );

        // PAGE 10: VIDEO
        addVideoPageWithDescription(
            "https://www.tiktok.com/embed/7177131311646199083",
            "Have you ever told yourself things like \"I'm just being dramatic\" or \"Other people have it worse, I shouldn't feel this way\"? "
            + "That’s called self-gaslighting — when we minimize, dismiss, or deny our own feelings. 😞\n\n"
            + "It often comes from a place of wanting to stay strong or not cause trouble, but over time, it teaches us to silence our pain instead of healing it. "
            + "This video reminds us how easy it is to fall into that trap — and how important it is to talk to yourself with kindness. 💛\n\n"
            + "You are allowed to feel what you feel. Your emotions are valid, and your experience matters. 🌱"
        );
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

    public void resetModule() {
        currentIndex = 0;
    }
}
