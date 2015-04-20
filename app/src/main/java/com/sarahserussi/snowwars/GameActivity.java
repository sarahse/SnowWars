package com.sarahserussi.snowwars;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Anders H. Gundersen on 14.04.2015.
 * GameActivity class
 */
public class GameActivity extends Activity {

    private GameState gameState;
    private MediaPlayer music;
    private int lastBkgdChecked = MainActivity.bkgdChecked;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gameState = new GameState(this);
        setContentView(gameState);
        gameState.requestFocus();

        if (lastBkgdChecked == 0) {
            music = MediaPlayer.create(GameActivity.this, R.raw.candyvalley);
            music.setLooping(true);
            music.start();
        }


    }

    protected void onPause() {
        if (lastBkgdChecked == 0) {
            music.release();
        }
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}

