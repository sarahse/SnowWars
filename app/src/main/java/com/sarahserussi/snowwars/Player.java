package com.sarahserussi.snowwars;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by sarahserussi on 12.03.15.
 */
public class Player {

    private int positionX, positionY;
    private int playerSpeedX;
    private int playerSpeedY;
    private Speed speed;
    private Bitmap bitmap;
    private int width, height, spriteWidth, spriteHeight;
    private Rect sourceRect;
    private boolean touched;

    /* Constructor */
    public Player(Bitmap bitmap, int positionX, int positionY, int width, int height ){
        this.bitmap = bitmap;
        this.positionX = positionX;
        this.positionY = positionY;
        this.spriteWidth = width;
        this.spriteHeight = height;
        spriteWidth = bitmap.getWidth();
        spriteHeight= bitmap.getHeight();
        sourceRect = new Rect(0,0,spriteWidth,spriteHeight);
    }

    public void draw(Canvas canvas){
        //where to draw the sprite
        Rect destRect = new Rect(getPositionX(), getPositionY(), getPositionX() + spriteWidth, getPositionY() + spriteHeight);
        canvas.drawBitmap(bitmap, sourceRect, destRect, null);
    }

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

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean touched){
        this.touched = touched;
    }

    public Rect getSourceRect() {
        return sourceRect;
    }

    public void setSourceRect(Rect sourceRect) {
        this.sourceRect = sourceRect;
    }

    public void handleActionDown(int eventX, int eventY){
        if (eventX >= (positionX - bitmap.getWidth() / 2)

                && (eventX <= (positionX + bitmap.getHeight() / 2))) {
            if (eventY >= (positionY - bitmap.getHeight() / 2)
                    && (positionY <= (positionY + bitmap.getHeight() / 2))) {
                setTouched(true);
            } else {
                setTouched(false);
            }
        } else {
            setTouched(false);
        }

    }


    /* move left and right */


}
