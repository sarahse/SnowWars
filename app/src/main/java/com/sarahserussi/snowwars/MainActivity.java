package com.sarahserussi.snowwars;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class MainActivity extends Activity implements View.OnClickListener {

    public static int bkgdChecked = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* turn the title of the project off */
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        /* Set fullscreen */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        setMusicOff();

        View startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(this);
        View rulesButton = findViewById(R.id.rulesButton);
        rulesButton.setOnClickListener(this);
        View exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onStop(){
        super.onStop();
    }



    /* start game */
    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.startButton:
                //View textLoading = findViewById(R.id.textLoading);
                //textLoading.setVisibility(View.VISIBLE);
                Intent intentStart = new Intent(this, GameActivity.class);
                startActivity(intentStart);
                break;
            case R.id.rulesButton:
                Intent intentRule = new Intent(this, RulesActivity.class);
                startActivity(intentRule);
                break;
            case R.id.exitButton:
                finish();
                break;
        }
    }

    /* Function for turning off background music */
    public void setMusicOff() {
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox);
        checkBox1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    bkgdChecked = 1;
                } else {
                    bkgdChecked = 0;
                }
            }
        });
        //bkgdChecked = 0;
    }
}
