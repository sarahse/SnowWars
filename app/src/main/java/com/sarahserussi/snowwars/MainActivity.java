package com.sarahserussi.snowwars;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* turn the title of the project off */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /* Set fullscreen */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new GameState(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
