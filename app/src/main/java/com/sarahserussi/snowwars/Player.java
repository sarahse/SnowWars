package com.sarahserussi.snowwars;

/**
 * Created by sarahserussi on 12.03.15.
 */
public class Player {

    private int positionX, positionY;
    private int playerSpeedX;
    private int playerSpeedY;
    private Speed speed;

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPlayerSpeedX() {
        return playerSpeedX;
    }

    public void setPlayerSpeedX(int playerSpeedX) {
        this.playerSpeedX = playerSpeedX;
    }

    public int getPlayerSpeedY() {
        return playerSpeedY;
    }

    public void setPlayerSpeedY(int playerSpeedY) {
        this.playerSpeedY = playerSpeedY;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    /* jump */
    public void jump(){

    }


    /* move left and right */

    /* Constructor */
    public Player(){

    }
}
