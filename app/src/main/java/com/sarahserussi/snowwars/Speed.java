package com.sarahserussi.snowwars;

/**
 * Created by sarahserussi on 12.03.15.
 */
public class Speed {

    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_LEFT = -1;
    public static final int DIRECTION_UP = -1;
    public static final int DIRECTION_DOWN = 1;

    private float xVelocity = 5;
    private float yVelocity = 5;

    private int xDirection = DIRECTION_RIGHT;
    private int yDirection = DIRECTION_DOWN;

    public Speed(){
        this.xVelocity = 5;
        this.yVelocity = 5;
    }

    public Speed(float xVelocity, float yVelocity){
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public int getxDirection() {
        return xDirection;
    }

    public void setxDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public void setyDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    public float getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    public float getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    //changes the direction on the x axis
    public void toggleXDirection(){
        xDirection = xDirection * -1;
    }

    //changes the direction on the y axis
    public void toggleYDirection(){
        yDirection = yDirection * -1;
    }
}
