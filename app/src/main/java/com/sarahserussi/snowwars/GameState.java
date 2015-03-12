package com.sarahserussi.snowwars;

import android.view.MotionEvent;

/**
 * Created by sarahserussi on 12.03.15.
 */
public class GameState {

    float eX, eY;
    /* Where the touch methods go */
     /* init */
    public GameState () {
        //add player
        //set playerPos
        //add ball
        //set servePos
        //add background
        //set new score

    }

    public void update() {
        //if event.getX is left of playerPos - move player left
        //move player right
        //player jump

    }

    public boolean onTouchEvent(MotionEvent event) {
        eX = event.getX();
        eY = event.getY();

        update();
        return true;
    }
}
