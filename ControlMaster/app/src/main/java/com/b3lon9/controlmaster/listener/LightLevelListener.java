package com.b3lon9.controlmaster.listener;

import android.widget.SeekBar;

public class LightLevelListener implements SeekBar.OnSeekBarChangeListener {
    private final LevelListener levelListener;
    public LightLevelListener(LevelListener listener) {
        this.levelListener = listener;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (b) {
            this.levelListener.onLightLevel(i);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
