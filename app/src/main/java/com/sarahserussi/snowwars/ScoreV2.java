package com.sarahserussi.snowwars;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by FinkenLaptop on 09.04.2015.
 */
public class ScoreV2 implements Observer {

    private float x;
    private float y;
    private int score = 0;
    private Paint paint;
    private String player;
    private boolean isRight;


    public ScoreV2(boolean b,float x, float y) {
        if(b) {
            player = "Player 2: ";
        } else {
            player = "Player 1: ";
        }
        paint = new Paint();
        this.x = x;
        this.y = y;
        paint.setTextSize(40);
        paint.setColor(Color.BLACK);
        isRight = b;
    }

    public void draw(Canvas canvas) {
        canvas.drawText(this.player + String.valueOf(score), x, y, this.paint);
    }


    public int getScore() {
        return this.score;
    }


    @Override
    public void update(int player1, int player2) {
        if(this.isRight)
            score = player2;
        else
            score = player1;
    }
}
