package com.sarahserussi.snowwars;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Anders H. Gundersen on 14.04.2015.
 */
public class GameActivity extends Activity {

    private GameState gameState;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gameState = new GameState(this);
        setContentView(gameState);
        gameState.requestFocus();
    }
}

