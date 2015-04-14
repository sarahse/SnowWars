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
    private int ballSpeedX;
    private int ballSpeedY;
    private Speed speed;
    private Player player;
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

    public void update(){
        ballPositionX += (speed.getxVelocity() * speed.getxDirection());
        //ballPositionY += (speed.getyVelocity() * speed.getyDirection());
        ballPositionY += speed.getyVelocity();
        //this.setBallPositionX(ballPositionX);
        //this.setBallPositionY(ballPositionY);
        speed.setyVelocity(getSpeed().getyVelocity() + (float)0.6);
    }

    public void setServePositionToPlayer1() {
        /* need to get screenWidth & screenHeight */
        this.setBallPositionX(200);
        this.setBallPositionY(200);
    }

    public void setServePositionToPlayer2() {
        /* need to get screenWidth & screenHeight */
        this.setBallPositionX(400);
        this.setBallPositionY(400);
    }

    /* checks if the ball touches the player, needs editing (bitmapRect) */
    public boolean touchesPlayer(Player player) {
        if (this.getBallPositionX() == player.getPositionX()
                && this.getBallPositionY() == player.getPositionY()) {
            return true;
        } else {
            return false;
        }
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

    public Rect getSourceRect() { return sourceRect; }

    public Rect getSpriteRect(){
        return new Rect(getBallPositionX(), getBallPositionY(), (int)getBallPositionX() + spriteWidth, (int)getBallPositionY() + spriteHeight);
    }
}
