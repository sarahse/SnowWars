package com.sarahserussi.snowwars;

/**
 * Created by sarahserussi on 12.03.15.
 */
public class Score {

    Player player1;
    Player player2;
    private int player1Score;
    private int player2Score;

    public Score(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        player1Score = 0;
        player2Score = 0;
    }

    /* add point to player's score */
    public void addPoint(Player player){
        if (player == player1){
            player1Score++;
        } else if (player == player2){
            player2Score++;
        }
    }

    public void hasWon(Player player){
        if (player == player1 && player1Score == 5){
            /* end game and set player1 as winner */
        } else if (player == player2 && player2Score == 5){
            /* end game and set player2 as winner */
        }
    }
}
