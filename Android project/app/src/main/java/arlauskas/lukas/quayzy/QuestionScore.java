package arlauskas.lukas.quayzy;

import java.io.Serializable;

public class QuestionScore implements Serializable {
    public String question;
    public String score;

    public QuestionScore(String question, String score) {
        this.question = question;
        this.score = score;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
