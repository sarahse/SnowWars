package com.sarahserussi.snowwars;

import android.widget.Button;

/**
 * Created by sarahserussi on 12.03.15.
 */
public class StartMenu {

    private Button rulesButton;
    private Button connplayButton;
    private Button quitButton;

    /* Constructor that creates new startMenu */
    public StartMenu(){

    }

    protected void onButtonsClicked(){
        if (rulesButton.isPressed()){
            //new RulesView();
        } else if (connplayButton.isPressed()){
            //new GameState();
        } else if (quitButton.isPressed()){
            /* quit the application */
        }
    }
}
