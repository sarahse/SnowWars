package com.sarahserussi.snowwars;

import android.content.Context;
import android.content.res.Resources;
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
    private Speed speed;
    private Bitmap verticalLine;

    /* Where the touch methods go */
     /* init */
    public GameState(Context context) {
        super(context);

        //create player and load bitmap
        player1 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.playerright),
                (getScreenWidth(context) / 40), getScreenHeight(context) * 5 / 8); //set player position
        player1.setSpriteWidth(getScreenWidth(context)/5);
        player1.setSpriteHeight(getScreenHeight(context)/3);

        player2 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.playerleft),
                ((getScreenWidth(context) * 31) / 40), getScreenHeight(context) * 5 / 8); //set player position
        player2.setSpriteWidth(getScreenWidth(context)/5);
        player2.setSpriteHeight(getScreenHeight(context)/3);

        //create ball and load bitmap
        ball = new Ball(BitmapFactory.decodeResource(getResources(), R.drawable.aquaball),
                getScreenWidth(context) / 5, getScreenHeight(context) / 5); //set ball position
        ball.setSpriteHeight(getScreenHeight(context)/7);
        ball.setSpriteWidth(getScreenHeight(context)/7);
        ball.setSpeed(new Speed(30,30));
        update();

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
    public void update() {

        ball.update();

        checkLeftWallCollision();

        checkRightWallCollision();

        checkBottomWallCollision();

        checkTopWallCollision();

        checkIntersect();
    }

    public void render(Canvas canvas) {

        verticalLine = BitmapFactory.decodeResource(getResources(), R.drawable.verticalline2);

        //canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.winterbackground2), 0, 0, null);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.verticalline2), (getScreenWidth()/2-verticalLine.getWidth()/2), (getScreenHeight()-verticalLine.getHeight()), null);
        player1.draw(canvas);
        player2.draw(canvas);
        ball.draw(canvas);
    }

    /* check if the ball collides with the left wall */
    private void checkLeftWallCollision() {
        if (ball.getSpeed().getxDirection() == Speed.DIRECTION_LEFT
                && ball.getBallPositionX() <= 0) {
            ball.getSpeed().toggleXDirection();
        }
    }

    /* check if the ball collides with the right wall */
    public void checkRightWallCollision() {
        if (ball.getSpeed().getxDirection() == Speed.DIRECTION_RIGHT
                && ball.getBallPositionX() + ball.getBitmap().getWidth() / 3.5 >= getScreenWidth()) {
            /* need to add the width of the bitmap to check outer position of the ball */
            ball.getSpeed().toggleXDirection();
        }
    }

    /* check if the ball collides with the bottom wall */
    public void checkBottomWallCollision() {
        if (ball.getSpeed().getyDirection() == Speed.DIRECTION_DOWN
                && ball.getBallPositionY() + ball.getBitmap().getHeight() / 3.5 >= getScreenHeight()) {
            //moveBallToServePosition(player1,player2);
            ball.getSpeed().toggleYDirection();
        }
    }

    /* check if the ball collides with the top wall */
    public void checkTopWallCollision(){
        if (ball.getSpeed().getyDirection() == Speed.DIRECTION_UP
                && ball.getBallPositionY() + ball.getBitmap().getHeight() <= 0) {
            /* needs to have a timer function that delays the descent of the ball */
            ball.getSpeed().toggleYDirection();
        }
    }

    /* check if the ball intersects with player or web */
    public void checkIntersect(){
        if (ball.getSpriteRect().intersect(player1.getSpriteRect())){
            ball.getSpeed().toggleXDirection();
            ball.getSpeed().toggleYDirection();
        }
        if (ball.getSpriteRect().intersect(player2.getSpriteRect())){

            ball.getSpeed().toggleXDirection();
            ball.getSpeed().toggleYDirection();
        }
        /*if (ball.getSpriteRect().intersect(getSpriteLineRect())){
            ball.getSpeed().toggleXDirection();
            ball.getSpeed().toggleYDirection();
        }*/
    }

    public Rect getSpriteLineRect(){
        return new Rect(getLinePositionX(), getLinePositionY(), (int)getLinePositionX() + verticalLine.getWidth(), (int)getLinePositionY() + verticalLine.getHeight());
    }

    public int getLinePositionX(){
        return (getScreenWidth()/2);
    }

    public int getLinePositionY(){
        return (getScreenHeight()-verticalLine.getHeight());
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
        if (event.getAction() == MotionEvent.ACTION_DOWN){
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            player1.handleActionDown((int) event.getX(), (int) event.getY());
            if (player1.isTouched()) {
                player1.setPositionX((int) event.getX());
                /* checks if the player touches the net */
                if (player1.getSpriteRect().intersect(getSpriteLineRect())) {
                    player1.setPositionX((getScreenWidth() / 2 - player1.getSpriteRect().width() - (verticalLine.getWidth() / 2)));
                }
            }

            player2.handleActionDown((int) event.getX(), (int) event.getY());
            if (player2.isTouched()) {
                player2.setPositionX((int) event.getX());
                /* checks if the player touches the net */
                if (player2.getSpriteRect().intersect(getSpriteLineRect())) {
                    player2.setPositionX((getScreenWidth() / 2) + (verticalLine.getWidth() / 2));
                }
                /* checks if the player goes out of the screen */
                if ((player2.getPositionX() + player2.getSpriteRect().width()) >= getScreenWidth()) {
                    player2.setPositionX(getScreenWidth() - player2.getSpriteRect().width());
                }
            }
        }
        }

        if (event.getAction() == MotionEvent.ACTION_POINTER_DOWN){
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                player1.handleActionPointerDown((int) event.getX(), (int) event.getY());
                if (player1.isTouched()) {
                    player1.setPositionX((int) event.getX());
                /* checks if the player touches the net */
                    if (player1.getSpriteRect().intersect(getSpriteLineRect())) {
                        player1.setPositionX((getScreenWidth() / 2 - player1.getSpriteRect().width() - (verticalLine.getWidth() / 2)));
                    }
                }


                player2.handleActionPointerDown((int) event.getX(), (int) event.getY());
                if (player2.isTouched()) {
                    player2.setPositionX((int) event.getX());
                /* checks if the player touches the net */
                    if (player2.getSpriteRect().intersect(getSpriteLineRect())) {
                        player2.setPositionX((getScreenWidth() / 2) + (verticalLine.getWidth() / 2));
                    }
                /* checks if the player goes out of the screen */
                    if ((player2.getPositionX() + player2.getSpriteRect().width()) >= getScreenWidth()) {
                        player2.setPositionX(getScreenWidth() - player2.getSpriteRect().width());
                    }
                }
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
