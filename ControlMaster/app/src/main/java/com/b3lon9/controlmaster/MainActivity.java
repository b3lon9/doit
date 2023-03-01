package com.b3lon9.controlmaster;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.b3lon9.controlmaster.constant.Constant;
import com.b3lon9.controlmaster.databinding.ActivityMainBinding;
import com.b3lon9.controlmaster.listener.LevelListener;
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
        finish();
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

        binding.setLightlevel(Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 128));

        binding.btnLightMin.setOnClickListener(v -> levelListener.onLightLevel(Constant.BRIGHT_LEVEL.MIN));
        binding.btnLightMax.setOnClickListener(v -> levelListener.onLightLevel(Constant.BRIGHT_LEVEL.MAX));
        binding.seekbarLight.setOnSeekBarChangeListener(new LightLevelListener(levelListener));
    }

    private final LevelListener levelListener = new LevelListener() {
        @Override
        public void onLightLevel(int level) {
            Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, level);
            binding.seekbarLight.setProgress(level);
        }
    };
}