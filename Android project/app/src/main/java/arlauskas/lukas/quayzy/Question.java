package arlauskas.lukas.quayzy;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("response")
    private String response;

    @SerializedName("question")
    public String question;

    @SerializedName("answer1")
    public String answer1;

    @SerializedName("answer2")
    public String answer2;

    @SerializedName("answer3")
    public String answer3;

    @SerializedName("answer4")
    public String answer4;

    @SerializedName("correctanswer")
    public String correactanswer;

    public String getResponse() {
        return response;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public String getCorreactanswer() {
        return correactanswer;
    }
}
