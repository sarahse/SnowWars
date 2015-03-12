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

    public void update(MotionEvent e) {
        //if event.getX is left of playerPos - move player left
        //move player right
        //player jump g

        int a = e.getActionMasked();
        int aIndex = e.getActionIndex();
        int x,y;
        switch(a) {
            //case MotionEvent.ACTION_DOWN:
            //case MotionEvent.ACTION_POINTER_DOWN:
            /* Action between touched down and up */
            case MotionEvent.ACTION_MOVE:
                x = (int) e.getX(aIndex);
                y = (int) e.getY(aIndex);

                /*if(m.leftPressed(x, y)) {
                    movingLeft = true;
                    movingRight = false;
                    }
                  */
        }

    }

    public boolean onTouchEvent(MotionEvent event) {
        eX = event.getX();
        eY = event.getY();

        update(event);
        return true;
    }
}
