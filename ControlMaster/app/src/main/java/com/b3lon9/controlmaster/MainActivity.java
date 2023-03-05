package com.b3lon9.controlmaster;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.b3lon9.controlmaster.constant.Constant;
import com.b3lon9.controlmaster.databinding.ActivityMainBinding;
import com.b3lon9.controlmaster.listener.LevelListener;
import com.b3lon9.controlmaster.listener.LightLevelListener;
import com.b3lon9.controlmaster.work.LightAutoTask;

public class MainActivity extends Activity {
    private final String TAG = "neander";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        permissionCheck();
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

    private void permissionCheck() {
        Log.i(TAG, "PermissionCheck");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.i(TAG, String.format("PermissionCheck Build.VERSION.SDK_INT:{%d} >= Build.VERSION_CODES.M:{%d}", Build.VERSION.SDK_INT, Build.VERSION_CODES.M));
            if (Settings.System.canWrite(this)) {
                Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            } else {                // 권한설정
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                String packageName = "package:" + getPackageName();
                Log.i(TAG, packageName);
                intent.setData(Uri.parse(packageName));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        }
    }

    private void init() {
        // display size
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.width = (int)(getResources().getDisplayMetrics().widthPixels * 0.8);
        binding.seekbarLight.setLayoutParams(layoutParams);
        binding.seekbarLight.setMax(Constant.BRIGHT_LEVEL.MAX);

        binding.setLightlevel(Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, -1));

        binding.btnLightMin.setOnClickListener(v -> levelListener.onLightLevel(Constant.BRIGHT_LEVEL.MIN));
        binding.btnLightAuto.setOnClickListener(v -> new LightAutoTask(this, levelListener));
        binding.btnLightMax.setOnClickListener(v -> levelListener.onLightLevel(Constant.BRIGHT_LEVEL.MAX));
        binding.seekbarLight.setOnSeekBarChangeListener(new LightLevelListener(levelListener));
    }

    // 밝기조절
    private final LevelListener levelListener = new LevelListener() {
        @Override
        public void onLightLevel(int level) {
            Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, level);
            binding.setLightlevel(level);
        }
    };
}