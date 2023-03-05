package com.b3lon9.controlmaster.work;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

import com.b3lon9.controlmaster.listener.LevelListener;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LightAutoTask {
    private final LevelListener levelListener;
    int level = -1;

    @SuppressLint("CheckResult")
    public LightAutoTask(Context context, LevelListener levelListener) {
        this.levelListener = levelListener;

        // Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        // Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, -1)

        // auto
    }

    private void work() {

    }
}
