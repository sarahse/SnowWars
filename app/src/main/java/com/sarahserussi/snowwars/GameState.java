package com.sarahserussi.snowwars;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by sarahserussi on 12.03.15.
 */
public class GameState extends SurfaceView implements SurfaceHolder.Callback {

    private Player player1, player2;
    private Ball ball;
    private GameLogic gameLogic;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;

    /* Where the touch methods go */
     /* init */
    public GameState(Context context) {
        super(context);

        //create player and load bitmap
        player1 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.playerright),
                (getScreenWidth(context) / 40), getScreenHeight(context) / 2); //set player position
        player1.setSpriteWidth(getScreenWidth(context)/5);
        player1.setSpriteHeight(getScreenHeight(context)/3);

        player2 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.playerleft),
                ((getScreenWidth(context) * 31) / 40), getScreenHeight(context) / 2); //set player position
        player2.setSpriteWidth(getScreenWidth(context)/5);
        player2.setSpriteHeight(getScreenHeight(context)/3);

        //create ball and load bitmap
        ball = new Ball(BitmapFactory.decodeResource(getResources(), R.drawable.aquaball),
                getScreenWidth(context) / 5, getScreenHeight(context) / 5); //set ball position
        ball.setSpriteHeight(getScreenHeight(context)/7);
        ball.setSpriteWidth(getScreenHeight(context)/7);

        //make the game focusable so it can handle events
        setFocusable(true);

        //adding the callback to the surface holder to intercept events
        getHolder().addCallback(this);

        // create the game loop thread
        gameLoopThread = new GameLoopThread(this);

        //set servePos
        //add background
        //set new score
    }

    public int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        return width;
    }

    public int getScreenHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int height = metrics.heightPixels;
        return height;
    }

    //update method: updates the gamestate
    public void update(float delta) {
        gameLogic.bounceWall(ball, player1, player2);
    }

    public void render(Canvas canvas) {
        canvas.drawColor(Color.RED);
        player1.draw(canvas);
        player2.draw(canvas);
        ball.draw(canvas);
    }

    /* handles the player's movement
     * if the player is pressed, set touched to true and move the player where it's dragged
     * if the player is released, set touched to false and drop the player */
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {

            if (player1.isTouched()) {
                player1.setTouched(false);
            }

            if (player2.isTouched()) {
                player2.setTouched(false);
            }
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE){
            player1.handleActionDown((int) event.getX(), (int) event.getY());
            if (player1.isTouched()) {
                player1.setPositionX((int) event.getX());
                player1.setPositionY((int) event.getY());
            }

            player2.handleActionDown((int) event.getX(), (int) event.getY());
            if (player2.isTouched()) {
                player2.setPositionX((int) event.getX());
                player2.setPositionY((int) event.getY());
            }
        }
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                gameLoopThread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }
}
