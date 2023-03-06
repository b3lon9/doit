package com.b3lon9.controlmaster.work;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import com.b3lon9.controlmaster.listener.LevelListener;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Single;

public class LightAutoTask {
    private final LevelListener levelListener;
    private Context mContext;
    int level = -1;

    @SuppressLint("CheckResult")
    public LightAutoTask(Context context, LevelListener levelListener) {
        this.mContext = context;
        this.levelListener = levelListener;

        // Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        // Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, -1)

        // auto
    }

    private Callable<Boolean> callable = () -> {
        // Thread.sleep(1000);
        return Settings.System.putInt(mContext.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
    };

    @SuppressLint("CheckResult")
    public void work() {
        Observable<Boolean> source = Observable.fromCallable(callable);
        source.subscribe(result -> {
            Log.d("neander", "RxJava Result : " + result);
            if (result) {
                Observable<Integer> bright = Observable.just(Settings.System.getInt(mContext.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, -1));
                Single.fromObservable(bright).subscribe(levelListener::onLightLevel);
            }
        });
    }
}
