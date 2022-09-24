package com.odde.tdd;

public class Tenis {
    int leftScore = 0;
    int rightScore = 0;

    String score_2_1 = "thirty fifteen";

    private static final String[] DISPLAYS = new String[]{"Love", "fifteen", "thirty"};


    public String getScore() {
        if (leftScore == rightScore) {
            return DISPLAYS[leftScore] + " All";
        }
        return DISPLAYS[leftScore] + " " + DISPLAYS[rightScore];
    }

    public void leftWin() {
        leftScore += 1;
    }

    public void rightWin() {
        rightScore += 1;
    }
}
