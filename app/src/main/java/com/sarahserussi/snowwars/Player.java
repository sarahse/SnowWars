package com.sarahserussi.snowwars;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;

/**
 * Created by sarahserussi on 12.03.15.
 */
public class Player {

    private int positionX, positionY;
    private int playerSpeedX;
    private int playerSpeedY;
    private Speed speed;
    private Bitmap bitmap;
    private int spriteWidth, spriteHeight;
    private Rect sourceRect;
    private boolean touched;

    /* Constructor */
    public Player(Bitmap bitmap, int positionX, int positionY) {
        this.bitmap = bitmap;
        this.positionX = positionX;
        this.positionY = positionY;
        spriteWidth = bitmap.getWidth();
        spriteHeight = bitmap.getHeight();
        sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
    }

    public void draw(Canvas canvas) {
        //where to draw the sprite
        Rect destRect = new Rect(getPositionX(), getPositionY(), getPositionX() + spriteWidth, getPositionY() + spriteHeight);
        canvas.drawBitmap(bitmap, sourceRect, destRect, null);
    }

    /* move left and right */
    public void checkPlayerTouched(int eventX, int eventY) {
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

    /* jump */
    public void jump() {

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

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public void setSpriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    public Rect getSourceRect() {
        return sourceRect;
    }

    public void setSourceRect(Rect sourceRect) {
        this.sourceRect = sourceRect;
    }

    public int getScreenWidth() {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        int width = metrics.widthPixels;
        return width;
    }

    public int getScreenHeight() {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        int height = metrics.heightPixels;
        return height;
    }

    public Rect getSpriteRect(){
        return new Rect(getPositionX(), getPositionY(), getPositionX() + spriteWidth, getPositionY() + spriteHeight);
    }

    public Rect getRightSideRect(){
        return new Rect(((getPositionX() + spriteWidth)/2), getPositionY(), (getPositionX() + spriteWidth), getPositionY() + spriteHeight);
    }

    public Rect getLeftSideRect() {
        return new Rect(getPositionX(), getPositionY(), ((getPositionX()+ spriteWidth)/2), getPositionY() + spriteHeight);
    }

    public boolean isPlayer1InLane() {
        if (this.getPositionX() < getScreenWidth()/2 && this.getPositionX() > 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean isPlayer2InLane() {
        if (this.getPositionX() > getScreenWidth()/2 && this.getPositionX() < getScreenWidth()){
            return true;
        } else {
            return false;
        }
    }


}
