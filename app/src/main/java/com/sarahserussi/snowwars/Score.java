package com.sarahserussi.snowwars;

/**
 * Created by sarahserussi on 12.03.15.
 */

public class Score {

    private int player1Score;
    private int player2Score;

    public Score() {
        player1Score = 0;
        player2Score = 0;
    }

    public void addScoreToPlayer1() {

        player1Score++;
    }

    public void addScoreToPlayer2() {
        player2Score++;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

}
