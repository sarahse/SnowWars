package com.sarahserussi.snowwars;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by sarahserussi on 12.03.15.
 */
public class GameState extends SurfaceView implements SurfaceHolder.Callback {

    private Player player1, player2;
    private Ball ball;
    private Line line;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private Speed speed;
    private Bitmap verticalLine;
    private Score score;
    private ArrayList<Score> observerList;
    private ImageView myImageView;

    /* Where the touch methods go */
     /* init */
    public GameState(Context context) {
        super(context);

        // TODO: Set background
        // Bind xml file
        //Activity a = (Activity) context;
        //a.setContentView(R.layout.activity_game);

        // Using ImageView
        // myImageView = (ImageView)findViewById(R.id.background);
        //myImageView.setImageResource(R.drawable.winterbackground2);
        // myImageView.setImageDrawable(getResources().getDrawable(R.drawable.winterbackground2));


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
        ball.setSpriteHeight(getScreenHeight(context) / 7);
        ball.setSpriteWidth(getScreenHeight(context) / 7);
        ball.setSpeed(new Speed(16, 0));
        update();

        //create line
        line = new Line(BitmapFactory.decodeResource(getResources(), R.drawable.verticalbar),
                getScreenWidth(context)/2, getScreenHeight(context)/2);
        line.setSpriteWidth(30);
        line.setSpriteHeight((getScreenWidth()/2)-(line.getSpriteWidth()/2));


        //make the game focusable so it can handle events
        setFocusable(true);

        //adding the callback to the surface holder to intercept events
        getHolder().addCallback(this);

        // create the game loop thread
        gameLoopThread = new GameLoopThread(this);

        //set servePos
        //add background
        //set new score
        score = new Score();
        observerList = new ArrayList<Score>();
        observerList.add(score);

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

        //canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.winterbackground2), 0, 0, null);
        canvas.drawColor(Color.YELLOW);

        player1.draw(canvas);
        player2.draw(canvas);
        ball.draw(canvas);
        line.draw(canvas);
        drawText(canvas);
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
        if(ball.getSpeed().getyDirection() == Speed.DIRECTION_DOWN && ball.getBallPositionY() + ball.getBitmap().getHeight() / 3.5 >= getScreenHeight()){

            // ball hits left side of net - give point to player 2
            if(ball.getBallPositionX()+ball.getBitmap().getWidth() < (getScreenWidth()/2)){
                notifyObserver(2);
                ball.setBallPositionY(getScreenHeight()/3);
                ball.setBallPositionX(getScreenWidth()/4);
            }
            // ball hits right side of net - give point to player 1
            else if(ball.getBallPositionX()+ball.getBitmap().getWidth()>(getScreenWidth()/2)){
                notifyObserver(1);
                ball.setBallPositionX(getScreenWidth()*3/4);
                ball.setBallPositionY(getScreenHeight()/3);
            }


            //ball.setBallPositionY(20);
        }

            //ball.getSpeed().toggleYDirection();

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
            // TODO: Check if ball hits left or right side of player
            ball.getSpeed().toggleXDirection();
            //ball.getSpeed().toggleYDirection();
            ball.getSpeed().setyVelocity(-15);
        }
        if (ball.getSpriteRect().intersect(player2.getSpriteRect())){

            ball.getSpeed().toggleXDirection();
            //ball.getSpeed().toggleYDirection();
            ball.getSpeed().setyVelocity(-15);
        }

        try {
            if (ball.getSpriteRect().intersect(line.getSpriteRect())){
                ball.getSpeed().toggleXDirection();
                ball.getSpeed().toggleYDirection();
            }
        } catch (NullPointerException e){
            e.printStackTrace();
            System.out.println ("Error: nullpointer");
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
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:

                player1.checkPlayerTouched((int)event.getX(pointerIndex), (int)event.getY(pointerIndex));
                player2.checkPlayerTouched((int)event.getX(pointerIndex), (int)event.getY(pointerIndex));

                if (pointerId == 0){

                    checkPlayer1TouchedAndSetPosition(player1, event, pointerIndex);
                    checkPlayer2TouchedAndSetPosition(player2, event, pointerIndex);
                }
                if (pointerId == 1){

                    checkPlayer1TouchedAndSetPosition(player1, event, pointerIndex);
                    checkPlayer2TouchedAndSetPosition(player2, event, pointerIndex);
                }

                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:

                player1.checkPlayerTouched((int)event.getX(pointerIndex), (int)event.getY(pointerIndex));
                player2.checkPlayerTouched((int)event.getX(pointerIndex), (int)event.getY(pointerIndex));

                if (pointerId == 0){
                    if (player1.isTouched()){
                        player1.setTouched(false);
                        player1.setPositionX((int)event.getX(pointerIndex));
                    }
                    if (player2.isTouched()){
                        player2.setTouched(false);
                        player2.setPositionX((int)event.getX(pointerIndex));
                    }
                }
                if (pointerId == 1){
                    if (player1.isTouched()){
                        player1.setTouched(false);
                        player1.setPositionX((int)event.getX(pointerIndex));
                    }
                    if (player2.isTouched()){
                        player2.setTouched(false);
                        player2.setPositionX((int)event.getX(pointerIndex));
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:

                int pointerCount = event.getPointerCount();
                for(int i = 0; i < pointerCount; i++) {
                    pointerIndex = i;
                    pointerId = event.getPointerId(pointerIndex);

                    player1.checkPlayerTouched((int)event.getX(pointerIndex), (int)event.getY(pointerIndex));
                    player2.checkPlayerTouched((int)event.getX(pointerIndex), (int)event.getY(pointerIndex));

                    if (pointerId == 0){

                        checkPlayer1TouchedAndSetPosition(player1, event, pointerIndex);
                        checkPlayer2TouchedAndSetPosition(player2, event, pointerIndex);
                    }
                    if (pointerId == 1){

                        checkPlayer1TouchedAndSetPosition(player1, event, pointerIndex);
                        checkPlayer2TouchedAndSetPosition(player2, event, pointerIndex);
                    }
                }
                break;
        }
        return true;
    }

    private void checkPlayer2TouchedAndSetPosition(Player player2, MotionEvent event, int pointerIndex) {

        if (player2.isPlayer2InLane()){
            if (player2.isTouched()){
                player2.setPositionX((int)event.getX(pointerIndex)-100);
                if (player2.getSpriteRect().intersect(line.getSpriteRect())) {
                    player2.setPositionX((getScreenWidth() / 2 +  (line.getSpriteWidth()) + 20));
                }
                if ((player2.getPositionX() + player2.getSpriteRect().width()) >= getScreenWidth()) {
                    player2.setPositionX(getScreenWidth() - player2.getSpriteRect().width());
                }
            }
        } else {
            player2.setPositionX((getScreenWidth() / 2 +  (line.getSpriteWidth()) + 20));
        }

    }

    private void checkPlayer1TouchedAndSetPosition(Player player1, MotionEvent event, int pointerIndex) {

        if (player1.isPlayer1InLane()){
            if (player1.isTouched()){
                player1.setPositionX((int)event.getX(pointerIndex)-100);
                if (player1.getSpriteRect().intersect(line.getSpriteRect())) {
                    player1.setPositionX((getScreenWidth() / 2 - player1.getSpriteRect().width() - (line.getSpriteWidth() / 2) - 10));
                }
            }
        } else {
            player1.setPositionX(0);
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
    public void notifyObserver(int player){
        if(player == 1){
            score.addScoreToPlayer1();

        }
        if(player == 2){
            score.addScoreToPlayer2();

        }
    }

    private void drawText(Canvas can){
        Paint paintPlayerTxt = new Paint();
        paintPlayerTxt.setColor(Color.BLACK);
        paintPlayerTxt.setTextSize(20);
        can.drawText("Player1", getScreenWidth()/3, getScreenHeight()/8,paintPlayerTxt);
        can.drawText("Player2", getScreenWidth()/2+getScreenWidth()/10, getScreenHeight()/8, paintPlayerTxt);

        Paint scoreTxt = new Paint();
        scoreTxt.setColor(Color.BLACK);
        scoreTxt.setTextSize(15);
        can.drawText(""+score.getPlayer1Score(), getScreenWidth()/3, getScreenHeight()/6,scoreTxt);
        can.drawText(""+score.getPlayer2Score(), getScreenWidth()/2+getScreenWidth()/10, getScreenHeight()/6,scoreTxt);
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
