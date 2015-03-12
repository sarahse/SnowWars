package com.sarahserussi.snowwars;

/**
 * Created by sarahserussi on 12.03.15.
 */
public class Ball {

    private int ballPositionX;
    private int ballPositionY;
    private int ballSpeedX;
    private int ballSpeedY;
    private Speed speed;
    private int testHakon;
    private Player player;

    /* constructor */
    public Ball (){

    }

    public int getBallPositionX() {
        return ballPositionX;
    }

    public void setBallPositionX(int ballPositionX) {
        this.ballPositionX = ballPositionX;
    }

    public int getBallPositionY() {
        return ballPositionY;
    }

    public void setBallPositionY(int ballPositionY) {
        this.ballPositionY = ballPositionY;
    }

    public Speed getSpeed() { return speed; }

    public void setSpeed(Speed speed) { this.speed = speed; }

    public void setServePositionToPlayer1(){
        /* need to get screenWidth & screenHeight */
        this.setBallPositionX(200);
        this.setBallPositionY(200);
    }

    public void setServePositionToPlayer2(){
        /* need to get screenWidth & screenHeight */
        this.setBallPositionX(400);
        this.setBallPositionY(400);
    }

    /* checks if the ball touches the player, needs editing (bitmapRect) */
    public boolean touchesPlayer(Player player){
        if(this.getBallPositionX() == player.getPositionX()
                && this.getBallPositionY() == player.getPositionY()){
            return true;
        } else {
            return false;
        }
    }
}
