package com.b3lon9.controlmaster.listener;

import android.util.Log;
import android.widget.SeekBar;

public class LightLevelListener implements SeekBar.OnSeekBarChangeListener {
    private SeekBar seekBar;
    public LightLevelListener(SeekBar seekBar) {
        this.seekBar = seekBar;
    }

    public void setLightLevel(int level) {
        Log.d("neander", "light level : " + level);
        this.seekBar.setProgress(level);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (b) {

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
