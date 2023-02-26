package com.b3lon9.controlmaster;

import android.app.Activity;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.b3lon9.controlmaster.constant.Constant;
import com.b3lon9.controlmaster.databinding.ActivityMainBinding;
import com.b3lon9.controlmaster.listener.LightButton;
import com.b3lon9.controlmaster.listener.LightLevelListener;

public class MainActivity extends Activity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // setContentView(R.layout.activity_main);

        init();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void init() {
        // display size
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.width = (int)(getResources().getDisplayMetrics().widthPixels * 0.8);
        binding.seekbarLight.setLayoutParams(layoutParams);

        LightLevelListener lightLevelListener = new LightLevelListener(binding.seekbarLight);

        new LightButton(this, binding.btnLightMin, Constant.BRIGHT_LEVEL.MIN, lightLevelListener);
        new LightButton(this, binding.btnLightMax, Constant.BRIGHT_LEVEL.MAX, lightLevelListener);

        binding.seekbarLight.setOnSeekBarChangeListener(lightLevelListener);
    }
}