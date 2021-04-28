package arlauskas.lukas.quayzy;

import com.google.gson.annotations.SerializedName;

public class UserScores {
    @SerializedName("username")
    public String Name;
    @SerializedName("scoresum")
    public int Score;

    public UserScores(String name, int score) {
        Name = name;
        Score = score;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
