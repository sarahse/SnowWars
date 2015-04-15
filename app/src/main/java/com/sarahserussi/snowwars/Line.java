package com.sarahserussi.snowwars;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by sarahserussi on 15.04.15.
 */
public class Line {

    private int linePositionX;
    private int linePositionY;
    private Bitmap bitmap;
    private int spriteWidth, spriteHeight;
    private Rect sourceRect;

    public Line(Bitmap bitmap, int linePositionX, int linePositionY){
        this.bitmap = bitmap;
        this.linePositionX = linePositionX;
        this.linePositionY = linePositionY;
        spriteHeight = bitmap.getHeight();
        spriteWidth = bitmap.getWidth();
        sourceRect = new Rect(0,0,spriteWidth,spriteHeight);
        this.setLinePositionX(960);
        this.setLinePositionY(540);
    }

    public void draw(Canvas canvas) {
        //where to draw the sprite
        Rect destRect = new Rect(getLinePositionX(), getLinePositionY(), getLinePositionX() + spriteWidth, getLinePositionY() + spriteHeight);
        canvas.drawBitmap(bitmap, sourceRect, destRect, null);
    }

    public int getLinePositionX() {
        return linePositionX;
    }

    public void setLinePositionX(int linePositionX) {
        this.linePositionX = linePositionX;
    }

    public int getLinePositionY() {
        return linePositionY;
    }

    public void setLinePositionY(int linePositionY) {
        this.linePositionY = linePositionY;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
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

    public Rect getSpriteRect() {

        return new Rect(getLinePositionX(), getLinePositionY(), getLinePositionX() + spriteWidth, getLinePositionY() + spriteHeight);

    }

    public void setSourceRect(Rect sourceRect) {
        this.sourceRect = sourceRect;
    }

}
