package com.sarahserussi.snowwars;

import android.graphics.Canvas;

/**
 * Created by sarahserussi on 17.03.15.
 */
public class GameLoopThread extends Thread {
    private GameState gameState;
    private boolean running = false;

    public GameLoopThread(GameState gameState){
        this.gameState = gameState;
    }

    public void setRunning(boolean running){
        this.running = running;
    }

    public void run() {
        while (running) {
            Canvas canvas = null;
            try {
                canvas = gameState.getHolder().lockCanvas();
                synchronized (gameState.getHolder()) {
                    gameState.onDraw(canvas);
                }
            } finally {
                if (canvas != null){
                    gameState.getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }
    }

}
