package com.sarahserussi.snowwars;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends Activity implements View.OnClickListener {

    private GameState gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* turn the title of the project off */
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        /* Set fullscreen */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gameState = new GameState(this);

        setContentView(R.layout.activity_main);
        //setContentView(gameState);

        View startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.startButton:
                Intent intentStart = new Intent(this, GameActivity.class);
                startActivity(intentStart);
                break;
            case R.id.rulesButton:
                break;
            case R.id.settingsButton:
                break;
        }
    }
}
