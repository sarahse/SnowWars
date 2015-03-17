package com.sarahserussi.snowwars;

/**
 * Created by sarahserussi on 12.03.15.
 */
public class GameLogic {

    private Ball ball;
    private int screenWidth;
    private int screenHeight;
    private Score score;
    private Player player1; //player on the left side
    private Player player2; //player on the right side
    private Speed speed;

    /* Constructor */
    public GameLogic(){

    }

    /* make draw method */

    /* method that ensures the ball bounces off of the walls, but not the ceiling */
    public void bounceWall(Ball ball, Player player1, Player player2){
        checkLeftWallCollision(ball);
        checkRightWallCollision(ball);
        checkBottomWallCollision(ball, player1, player2);
        checkTopWallCollision(ball);
    }
    /* method that ensures the ball bounces off of the player */
    private void bouncePlayer(Player player, Ball ball){
        checkIntersect(player, ball);
        /* if the player jumps and hits the ball, the ball should get a higher speed */
    }

    /* method that checks if the ball is dropped on the ground and gives point to the winning player*/
    private boolean checkBallDroppedAndGivePoints(){
        if (ball.getBallPositionY() >= screenHeight){
            if (checkSideOfBall() == -1){
                score.addPoint(player2);
            } else if (checkSideOfBall() == 1){
                score.addPoint(player1);
            }
            return true;
        } else {
            return false;
        }
    }

    /* check if a player has won */
    private boolean hasWon(Player player){
        if (player == player1 && score.whoWon(player) == player1) {
            return true;
        } else if (player == player2 && score.whoWon(player) == player2){
            return true;
        }
        else {
            return false;
        }
    }

    /* check which side of the net the ball has dropped on */
    private int checkSideOfBall(){
        if (ball.getBallPositionX() < screenWidth/2){
            return -1;
        } else if (ball.getBallPositionX() > screenWidth/2){
            return 1;
        }
        return 0;
    }

    /* check if the ball collides with the left wall OBS! check logic!*/
    public void checkLeftWallCollision(Ball ball) {
        if (ball.getSpeed().getxDirection() == Speed.DIRECTION_LEFT
                && ball.getBallPositionX() / 2 <= 0) {
            ball.getSpeed().toggleXDirection();
        }
    }

    /* check if the ball collides with the right wall OBS! check logic!*/
    public void checkRightWallCollision(Ball ball) {
        if (ball.getSpeed().getxDirection() == Speed.DIRECTION_RIGHT
                && ball.getBallPositionX() + ball.getBitmap().getWidth() / 2 >= screenWidth) {
            /* need to add the width of the bitmap to check outer position of the ball */
            ball.getSpeed().toggleXDirection();
        }
    }

    /* check if the ball collides with the bottom wall OBS! check logic!*/
    public void checkBottomWallCollision(Ball ball, Player player1, Player player2) {
        if (ball.getSpeed().getyDirection() == Speed.DIRECTION_DOWN
                && ball.getBallPositionY() + ball.getBitmap().getHeight() / 2 >= screenHeight) {
            moveBallToServePosition(player1,player2);
        }
    }

    /* check if the ball collides with the top wall OBS! check logic!*/
    public void checkTopWallCollision(Ball ball){
        if (ball.getSpeed().getyDirection() == Speed.DIRECTION_UP
                && ball.getBallPositionY() + ball.getBitmap().getHeight() / 2 >= screenHeight) {
            /* needs to have a timer function that delays the descent of the ball */
            ball.getSpeed().toggleYDirection();
        }
    }

    /* check if the ball intersects with the player */
    public void checkIntersect(Player player, Ball ball){
        if (ball.touchesPlayer(player)){
            ball.getSpeed().toggleXDirection();
            ball.getSpeed().toggleYDirection();
        }

    }

    public void moveBallToServePosition(Player player1, Player player2){
        if (hasWon(player1)){
            ball.setServePositionToPlayer1();
        } else if (hasWon(player2)){
            ball.setServePositionToPlayer2();
        }
    }
}
