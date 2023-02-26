package com.b3lon9.controlmaster.listener;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;

public class LightButton extends AppCompatButton implements View.OnClickListener {
    private int level = 0;
    private final LightLevelListener lightLevelListener;

    public LightButton(Context context, Button button, int level, LightLevelListener lightLevelListener) {
        super(context);
        this.level = level;
        this.lightLevelListener = lightLevelListener;

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        lightLevelListener.setLightLevel(level);
    }
}
