import java.util.List;

public class Question {

    public String prompt;
    public int correctIndex;
    public List<String> options;
    public int answer;

    public Question(String prompt, List<String> options, int correctIndex) {
        this.prompt = prompt;
        this.correctIndex = correctIndex;
        this.options = options;
        this.answer = -1;
    }

    public boolean isCorrect() {
        return (this.answer == this.correctIndex);
    }

    public String toString() {

        return "{" + prompt + ":" + options + "::" + correctIndex + "|" + answer + "}";

    }

}
