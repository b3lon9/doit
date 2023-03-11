package com.b3lon9.controlmaster.work;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.widget.Toast;

import com.b3lon9.controlmaster.listener.LevelListener;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LightAutoTask {
    private final LevelListener levelListener;
    private Context mContext;

    @SuppressLint("CheckResult")
    public LightAutoTask(Context context, LevelListener levelListener) {
        this.mContext = context;
        this.levelListener = levelListener;
    }

    private Callable<Boolean> callable = () -> {
        // Thread.sleep(1000);
        return Settings.System.putInt(mContext.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
    };

    @SuppressLint("CheckResult")
    public void work(int manualLevel) {
        Observable.fromCallable(callable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {

                    if (result) {
                        int count = 10;
                        int autoLevel = Settings.System.getInt(mContext.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);


                        while (true) {
                            if (manualLevel != autoLevel || count < 0) {
                                break;
                            }
                            Thread.sleep(50);
                            count--;
                            autoLevel = Settings.System.getInt(mContext.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
                        }

                        // Manual Mode Setting
                        Settings.System.putInt(mContext.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);

                        levelListener.onLightLevel(autoLevel);
                    } else {
                        Toast.makeText(mContext, "다시 클릭해주세요", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
