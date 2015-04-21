package com.sarahserussi.snowwars;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by sarahserussi on 12.03.15.
 */
public class GameState extends SurfaceView implements SurfaceHolder.Callback {

    private Player player1, player2;
    private Ball ball;
    private Line line;
    private GameLoopThread gameLoopThread;
    private Score score;
    private ArrayList<Score> observerList;
    private int screenWidth, screenHeight;
    private boolean endGame, player1Wins, player2Wins;

    public GameState(Context context) {
        super(context);

        //create player and load bitmap
        player1 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.figure1),
                (getScreenWidth(context) / 40), getScreenHeight(context) * 6 / 8); //set player position
        player1.setSpriteWidth(getScreenWidth(context) / 8);
        player1.setSpriteHeight(getScreenHeight(context) / 5);

        player2 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.figure2),
                ((getScreenWidth(context) * 31) / 40), getScreenHeight(context) * 6 / 8); //set player position
        player2.setSpriteWidth(getScreenWidth(context) / 8);
        player2.setSpriteHeight(getScreenHeight(context) / 5);

        //create ball and load bitmap
        ball = new Ball(BitmapFactory.decodeResource(getResources(), R.drawable.snowball_icon),
                getScreenWidth(context) / 5, getScreenHeight(context) / 5); //set ball position
        ball.setSpriteHeight(getScreenHeight(context) / 10);
        ball.setSpriteWidth(getScreenHeight(context) / 10);
        ball.setSpeed(new Speed(0, 0));
        ball.setServePositionToPlayer1(getScreenWidth(context), getScreenHeight(context));
        update();

        //create line
        line = new Line(BitmapFactory.decodeResource(getResources(), R.drawable.wood),
                (getScreenWidth(context) / 2) - 30, getScreenHeight(context) * 3 / 5);
        line.setSpriteWidth(60);
        line.setSpriteHeight(getScreenHeight() * 4 / 5);
        //line.setSpriteHeight((getScreenWidth()/2)-(line.getSpriteWidth()/2));

        //make the game focusable so it can handle events
        setFocusable(true);

        //adding the callback to the surface holder to intercept events
        getHolder().addCallback(this);

        // create the game loop thread
        gameLoopThread = new GameLoopThread(this);

        // set new score
        score = new Score();
        observerList = new ArrayList<Score>();
        observerList.add(score);
        screenWidth = getScreenWidth(context);
        screenHeight = getScreenHeight(context);

    }

    // get current thread so we can stop it when backButton is pressed
    public GameLoopThread getGameLoopThread() {
        return this.gameLoopThread;
    }

    // update method: updates the gamestate
    public void update() {

        ball.update();

        checkLeftWallCollision();

        checkRightWallCollision();

        checkBottomWallCollision();

        checkTopWallCollision();

        checkIntersect();

    }

    public void render(Canvas canvas) {

        Drawable d = getResources().getDrawable(R.drawable.winterbackground2);
        d.setBounds(getLeft(), getTop(), getRight(), getBottom());
        d.draw(canvas);

        player1.draw(canvas);
        player2.draw(canvas);
        ball.draw(canvas);
        line.draw(canvas);
        drawText(canvas);

        if (endGame && player1Wins) {
            endText(canvas, "Player 1");
            gameLoopThread.setRunning(false);

        }
        if (endGame && player2Wins) {
            endText(canvas, "Player 2");
            gameLoopThread.setRunning(false);

        }
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
        if (ball.getBallPositionY() + ball.getBitmap().getHeight() / 3.5 >= getScreenHeight()) {

            // ball hits left side of net - give point to player 2
            if (ball.getBallPositionX() < line.getLinePositionX()) {
                notifyObserver(2);
                ball.setServePositionToPlayer2(screenWidth, screenHeight);
            }
            // ball hits right side of net - give point to player 1
            else if (ball.getBallPositionX() > line.getLinePositionX()) {
                notifyObserver(1);
                ball.setServePositionToPlayer1(screenWidth, screenHeight);
            }
        }
    }

    /* check if the ball collides with the top wall */
    public void checkTopWallCollision() {
        if (ball.getSpeed().getyDirection() == Speed.DIRECTION_UP
                && ball.getBallPositionY() + ball.getBitmap().getHeight() <= 0) {
            /* needs to have a timer function that delays the descent of the ball */
            ball.getSpeed().toggleYDirection();
        }
    }

    private int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    /* check if the ball intersects with player or web */
    public void checkIntersect() {

        if (ball.getSpriteRect().intersect(player1.getSpriteRect())) {

            Rect player1RightSide = player1.getRightSideRect();
            Rect player1LeftSide = player1.getLeftSideRect();

            if (ball.getSpriteRect().intersect(player1LeftSide)) {
                ball.getSpeed().setxDirection(Speed.DIRECTION_LEFT);
            }

            if (ball.getSpriteRect().intersect(player1RightSide)) {
                ball.getSpeed().setxDirection(Speed.DIRECTION_RIGHT);
            }

            //the ball gets a random speed
            ball.getSpeed().setyVelocity(-randomWithRange(getScreenHeight() / 40, getScreenHeight() / 35));
            ball.getSpeed().setxVelocity(randomWithRange(getScreenHeight() / 30, getScreenHeight() / 25));
        }
        if (ball.getSpriteRect().intersect(player2.getSpriteRect())) {

            Rect player2RightSide = player2.getRightSideRect();
            Rect player2LeftSide = player2.getLeftSideRect();

            if (ball.getSpriteRect().intersect(player2LeftSide)) {
                ball.getSpeed().setxDirection(Speed.DIRECTION_RIGHT);
            }

            if (ball.getSpriteRect().intersect(player2RightSide)) {
                ball.getSpeed().setxDirection(Speed.DIRECTION_LEFT);
            }

            ball.getSpeed().setyVelocity(-randomWithRange(getScreenHeight() / 40, getScreenHeight() / 35));
            ball.getSpeed().setxVelocity(randomWithRange(getScreenHeight() / 30, getScreenHeight() / 25));
        }

        try {
            if (ball.getSpriteRect().intersect(line.getSpriteRect())) {

                if (ball.getBallPositionX() <= getScreenWidth() / 2) {
                    ball.getSpeed().setyVelocity(-randomWithRange(getScreenHeight() / 60, getScreenHeight() / 70));
                    ball.getSpeed().setxVelocity(-randomWithRange(getScreenHeight() / 60, getScreenHeight() / 70));
                } else {
                    ball.getSpeed().setyVelocity(-randomWithRange(getScreenHeight() / 60, getScreenHeight() / 70));
                    ball.getSpeed().setxVelocity(randomWithRange(getScreenHeight() / 60, getScreenHeight() / 70));
                }

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Error: nullpointer");
        }
    }

    /* handles the player's movement
     * if the player is pressed, set touched to true and move the player where it's dragged
     * if the player is released, set touched to false and drop the player */
    @Override
    public boolean onTouchEvent(MotionEvent event) {


        int action = event.getAction() & MotionEvent.ACTION_MASK;
        int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        int pointerId = event.getPointerId(pointerIndex);

        switch (action) {
            /*case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:


                player1.checkPlayerTouched((int) event.getX(pointerIndex), (int) event.getY(pointerIndex));
                player2.checkPlayerTouched((int) event.getX(pointerIndex), (int) event.getY(pointerIndex));

                if (pointerId == 0) {

                    checkPlayer1TouchedAndSetPosition(player1, event, pointerIndex);
                    checkPlayer2TouchedAndSetPosition(player2, event, pointerIndex);
                }
                if (pointerId == 1) {

                    checkPlayer1TouchedAndSetPosition(player1, event, pointerIndex);
                    checkPlayer2TouchedAndSetPosition(player2, event, pointerIndex);

                }

                break; */

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:

                if (pointerId == 0) {
                    if (player1.isTouched()) {
                        player1.setTouched(false);
                    }
                    if (player2.isTouched()) {
                        player2.setTouched(false);
                    }
                }
                if (pointerId == 1) {
                    if (player1.isTouched()) {
                        player1.setTouched(false);
                    }
                    if (player2.isTouched()) {
                        player2.setTouched(false);
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:

                int pointerCount = event.getPointerCount();
                for (int i = 0; i < pointerCount; i++) {
                    pointerIndex = i;
                    pointerId = event.getPointerId(pointerIndex);

                    player1.checkPlayerTouched((int) event.getX(pointerIndex), (int) event.getY(pointerIndex));
                    player2.checkPlayerTouched((int) event.getX(pointerIndex), (int) event.getY(pointerIndex));

                    if (pointerId == 0) {

                        checkPlayer1TouchedAndSetPosition(player1, event, pointerIndex);
                        checkPlayer2TouchedAndSetPosition(player2, event, pointerIndex);
                    }
                    if (pointerId == 1) {

                        checkPlayer1TouchedAndSetPosition(player1, event, pointerIndex);
                        checkPlayer2TouchedAndSetPosition(player2, event, pointerIndex);
                    }
                }
                break;
        }
        return true;
    }

    private void checkPlayer2TouchedAndSetPosition(Player player2, MotionEvent event, int pointerIndex) {

        if (player2.isTouched()) {
            player2.setPositionX((int) event.getX(pointerIndex));
            if (player2.getSpriteRect().intersect(line.getSpriteRect())) {
                player2.setPositionX((getScreenWidth() / 2 + (line.getSpriteWidth())));
            }
            if (player2.isPlayer2InLane()) {
                if ((player2.getPositionX() + player2.getSpriteRect().width()) >= getScreenWidth()) {
                    player2.setPositionX(getScreenWidth() - player2.getSpriteRect().width());
                }
            } else {
                player2.setPositionX(getScreenWidth() / 2 + (line.getSpriteWidth()));
            }
        }
    }


    private void checkPlayer1TouchedAndSetPosition(Player player1, MotionEvent event, int pointerIndex) {

        if (player1.isTouched()) {
            player1.setPositionX((int) event.getX(pointerIndex));
            if (player1.getSpriteRect().intersect(line.getSpriteRect())) {
                player1.setPositionX((getScreenWidth() / 2 - player1.getSpriteRect().width() - (line.getSpriteWidth() / 2) - 10));
            }
            if (player1.isPlayer1InLane()){
                if ((player1.getPositionX() < 0)) {
                    player1.setPositionX(player1.getSpriteRect().width()/8);
                }
            }
        }
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

    // Notify observer about the score
    public void notifyObserver(int player) {
        if (player == 1) {
            score.addScoreToPlayer1();
            if (score.getPlayer1Score() == 3) {
                //stop game

                endGame = true;
                player1Wins = true;
            }
        }
        if (player == 2) {
            score.addScoreToPlayer2();
            if (score.getPlayer2Score() == 3) {

                endGame = true;
                player2Wins = true;
            }

        }
    }


    private void drawText(Canvas can) {
        Paint paintPlayerTxt = new Paint();
        paintPlayerTxt.setColor(Color.BLACK);
        paintPlayerTxt.setTextSize(getScreenWidth() / 40);
        paintPlayerTxt.setTypeface(Typeface.DEFAULT_BOLD);

        can.drawText("Player 1", getScreenWidth() / 3, getScreenHeight() / 8, paintPlayerTxt);
        can.drawText("Player 2", getScreenWidth() / 2 + getScreenWidth() / 10, getScreenHeight() / 8, paintPlayerTxt);

        Paint scoreTxt = new Paint();
        scoreTxt.setColor(Color.BLACK);
        scoreTxt.setTextSize(getScreenWidth() / 50);
        scoreTxt.setTypeface(Typeface.DEFAULT_BOLD);
        can.drawText("" + score.getPlayer1Score(), getScreenWidth() / 3, getScreenHeight() / 6, scoreTxt);
        can.drawText("" + score.getPlayer2Score(), getScreenWidth() / 2 + getScreenWidth() / 10, getScreenHeight() / 6, scoreTxt);
    }

    private void endText(Canvas can, String player) {
        Paint txt = new Paint();
        Paint text2 = new Paint();
        txt.setColor(Color.BLACK);
        text2.setColor(Color.BLACK);
        txt.setTextSize(getScreenWidth() / 20);
        text2.setTextSize(getScreenWidth() / 30);
        can.drawText("The winner is " + player + " !", getScreenWidth() / 5, getScreenHeight() / 3, txt);
        can.drawText("Press the back button in the navigation bar to exit", getScreenWidth() / 7, getScreenHeight() / 2, text2);
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
