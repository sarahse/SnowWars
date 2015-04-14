package com.sarahserussi.snowwars;

/**
 * Created by sarahserussi on 12.03.15.
 */

public class Score {

    //Player player1;
    //Player player2;
    private int player1Score;
    private int player2Score;

    public Score(){
        player1Score = 0;
        player2Score = 0;
    }

    public void addScoreToPlayer1(){

        player1Score++;
    }

    public void addScoreToPlayer2(){
        player2Score++;
    }

    public int getPlayer1Score(){
        return player1Score;
    }

    public int getPlayer2Score(){
        return player2Score;
    }

    /*
    public Score(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        player1Score = 0;
        player2Score = 0;
    }

    /* add point to player's score */

   /* public void addPoint(Player player){
        if (player == player1){
            player1Score++;
        } else if (player == player2){
            player2Score++;
        }
    }
    public Player whoWon(Player player){
        if (player == player1 && player1Score == 5){
            /* end game and set player1 as winner */
    /*        return player1;
        } else if (player == player2 && player2Score == 5){
            /* end game and set player2 as winner */
    /*        return player2;
        }
        return null;
    }*/



}
