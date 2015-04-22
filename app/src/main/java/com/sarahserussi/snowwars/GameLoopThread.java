package com.sarahserussi.snowwars;

import android.graphics.Canvas;

/**
 * Created by sarahserussi on 17.03.15.
 */
public class GameLoopThread extends Thread {

    private GameState gameState;
    private boolean running = false;

    public GameLoopThread(GameState gameState) {
        this.gameState = gameState;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void run() {
        while (running) {
            Canvas canvas = null;
            // try locking the canvas for exclusive pixel editing on the surface
            try {
                canvas = gameState.getHolder().lockCanvas();
                synchronized (gameState.getHolder()) {
                    //draws the canvas on the panel
                    gameState.render(canvas);
                    //update game state
                    gameState.update();
                }
            } finally {
                if (canvas != null) {
                    gameState.getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
