// Implemented by Amiera
// Tested by Mifdzal

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
        questions.add(new Question("What is mental health?", new String[] {
            "A disease", "A lifestyle", "A state of well-being", "A personality type"
        }, "A state of well-being"));

        questions.add(new Question("True or False: Exercise helps mental health.", new String[] {
            "True", "False"
        }, "True"));

        questions.add(new Question("Which of these is a stress management technique?", new String[] {
            "Bottling emotions", "Procrastination", "Deep breathing", "Skipping meals"
        }, "Deep breathing"));

        questions.add(new Question("Which hormone is often called the 'feel good' chemical?", new String[] {
            "Adrenaline", "Serotonin", "Cortisol", "Insulin"
        }, "Serotonin"));

        questions.add(new Question("True or False: Talking to a friend about your feelings is unhealthy.", new String[] {
            "True", "False"
        }, "False"));

        questions.add(new Question("What is a common symptom of burnout?", new String[] {
            "Increased energy", "Improved memory", "Emotional exhaustion", "Better sleep"
        }, "Emotional exhaustion"));

        questions.add(new Question("True or False: Mental health affects physical health.", new String[] {
            "True", "False"
        }, "True"));

        questions.add(new Question("Which of these can help improve mood?", new String[] {
            "Junk food binge", "Ignoring problems", "Regular exercise", "Oversleeping"
        }, "Regular exercise"));

        questions.add(new Question("True or False: Only people with diagnosed disorders should care about mental health.", new String[] {
            "True", "False"
        }, "False"));

        questions.add(new Question("Which of these is a grounding technique?", new String[] {
            "Hyperventilating", "5-4-3-2-1 method", "Skipping meals", "Avoiding everyone"
        }, "5-4-3-2-1 method"));

        questions.add(new Question("True or False: It’s okay to take breaks when you feel overwhelmed.", new String[] {
            "True", "False"
        }, "True"));

        questions.add(new Question("What can trigger anxiety?", new String[] {
            "Deadlines", "Lack of sleep", "Conflict", "All of the above"
        }, "All of the above"));

        questions.add(new Question("True or False: Crying is a sign of weakness.", new String[] {
            "True", "False"
        }, "False"));

        questions.add(new Question("Which activity helps release endorphins?", new String[] {
            "Watching sad news", "Regular exercise", "Skipping meals", "Staying indoors all day"
        }, "Regular exercise"));

        questions.add(new Question("What is self-care?", new String[] {
            "Being selfish", "Avoiding responsibilities", "Taking care of your well-being", "Wasting time"
        }, "Taking care of your well-being"));

        questions.add(new Question("True or False: You can always tell when someone is struggling mentally.", new String[] {
            "True", "False"
        }, "False"));

        questions.add(new Question("What should you do if you're feeling persistently low?", new String[] {
            "Ignore it", "Reach out for help", "Blame yourself", "Hide it"
        }, "Reach out for help"));

        questions.add(new Question("True or False: Mental illness is rare.", new String[] {
            "True", "False"
        }, "False"));

        questions.add(new Question("Which of the following is NOT a healthy coping mechanism?", new String[] {
            "Journaling", "Talking to someone", "Drinking excessively", "Mindful breathing"
        }, "Drinking excessively"));

        questions.add(new Question("True or False: Seeking therapy means you’re weak.", new String[] {
            "True", "False"
        }, "False"));
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        if (currentIndex < questions.size()) {
            Question q = questions.get(currentIndex);
            boolean isCorrect = q.getAnswer().equalsIgnoreCase(userAnswer.trim());
            if (isCorrect) score++;
            currentIndex++;
            return isCorrect;
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
