import java.util.*;

public class QuizManager extends Module implements QuizFunctions {
    private List<Question> questions;
    private int currentIndex;
    private int score;

    public QuizManager(String title, int duration) {
        super(title, duration);
        this.questions = new ArrayList<>();
        this.currentIndex = 0;
        this.score = 0;
    }

    @Override
    public void loadContent() {
        // Sample 3 questions for demonstration (can be extended to 20)
        questions.add(new Question("What is mental health?", new String[] {
            "A disease", "A lifestyle", "A state of well-being", "A personality type"
        }, "A state of well-being"));

        questions.add(new Question("True or False: Exercise helps mental health.", new String[] {
            "True", "False"
        }, "True"));

        questions.add(new Question("Which of these is a stress management technique?", new String[] {
            "Bottling emotions", "Procrastination", "Deep breathing", "Skipping meals"
        }, "Deep breathing"));
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        if (currentIndex < questions.size()) {
            Question q = questions.get(currentIndex);
            if (q.getAnswer().equalsIgnoreCase(userAnswer.trim())) {
                score++;
            }
            currentIndex++;
            return true;
        }
        return false;
    }

    public Question getQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        }
        return null;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public boolean hasMoreQuestions() {
        return currentIndex < questions.size();
    }
}
