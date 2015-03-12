package com.sarahserussi.snowwars;

import android.widget.Button;

/**
 * Created by sarahserussi on 12.03.15.
 */
public class EndMenu {

    private Button quitButton;
    private Button playAgainButton;

    protected void onButtonsClicked(){
        if (quitButton.isPressed()){
            /* quit application */
        } else if (playAgainButton.isPressed()){
            new StartMenu();
        }
    }
}
