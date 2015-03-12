package com.sarahserussi.snowwars;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.view.SurfaceView;

/**
 * Created by sarahserussi on 12.03.15.
 */
public class GameState extends SurfaceView {

    private Player player1, player2;
    private Ball ball;

    float eX, eY;
    /* Where the touch methods go */
     /* init */
    public GameState (Context context) {
        super(context);
        //create player and load bitmap
        player1 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.playerright),
                200,200, //set player position
                64,64); //set size of bitmap

        player2 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.playerleft),
                600,200, //set player position
                64,64); //set size of bitmap

        //create ball and load bitmap
        ball = new Ball(BitmapFactory.decodeResource(getResources(), R.drawable.aquaball),
                300,300, //set ball position
                20,20);

        //make the game focusable so it can handle events
        setFocusable(true);
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
