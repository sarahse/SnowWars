package com.sarahserussi.snowwars;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by sarahserussi on 12.03.15.
 */
public class Ball {

    private int ballPositionX;
    private int ballPositionY;
    private Speed speed;
    private Bitmap bitmap;
    private int spriteWidth, spriteHeight;
    private Rect sourceRect;

    /* constructor */
    public Ball(Bitmap bitmap, int ballPositionX, int ballPositionY) {
        this.bitmap = bitmap;
        this.ballPositionX = ballPositionX;
        this.ballPositionY = ballPositionY;
        spriteWidth = bitmap.getWidth();
        spriteHeight = bitmap.getHeight();
        sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
    }

    public void draw(Canvas canvas) {
        //where to draw the sprite
        Rect destRect = new Rect(getBallPositionX(), getBallPositionY(), getBallPositionX() + spriteWidth, getBallPositionY() + spriteHeight);
        canvas.drawBitmap(bitmap, sourceRect, destRect, null);
    }

    public void update() {
        ballPositionX += (speed.getxVelocity() * speed.getxDirection());
        ballPositionY += speed.getyVelocity();

        if (getSpeed().getxVelocity() != 0) {
            speed.setyVelocity(getSpeed().getyVelocity() + (float) 0.6);
        }
    }

    public void setServePositionToPlayer1(int screenWidth, int screenHeight) {
        /* need to get screenWidth & screenHeight */
        this.setBallPositionX(screenWidth / 4);
        this.setBallPositionY(screenHeight * 7 / 10);
        this.getSpeed().setxVelocity(0);
        this.getSpeed().setyVelocity(0);
        this.getSpeed().setxDirection(Speed.DIRECTION_LEFT);

    }

    public void setServePositionToPlayer2(int screenWidth, int screenHeight) {
        /* need to get screenWidth & screenHeight */
        this.setBallPositionX(3 * screenWidth / 4);
        this.setBallPositionY(screenHeight * 7 / 10);
        this.getSpeed().setxVelocity(0);
        this.getSpeed().setyVelocity(0);
        this.getSpeed().setxDirection(Speed.DIRECTION_RIGHT);
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

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Rect getSourceRect() {
        return sourceRect;
    }

    public Rect getSpriteRect() {
        return new Rect(getBallPositionX(), getBallPositionY(), getBallPositionX() + spriteWidth, getBallPositionY() + spriteHeight);
    }
}
