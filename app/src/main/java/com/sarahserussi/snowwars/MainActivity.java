package com.sarahserussi.snowwars;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;


public class MainActivity extends Activity {

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
        setContentView(gameState);
/*
        ImageView imgView = (ImageView) findViewById(R.id.);
        imgView.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.winterbackground2));

        /* set background image */
       /* View view = getWindow().getDecorView();
        int orientation = getResources().getConfiguration().orientation;
        if(Configuration.ORIENTATION_LANDSCAPE == orientation){
            view.setBackgroundResource(R.drawable.winterbackground2);
        }
        else{
            view.setBackgroundResource(R.drawable.winterbackground2);
        }*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onStop(){
        super.onStop();
    }
}
