package com.b3lon9.controlmaster.model;

import androidx.lifecycle.MutableLiveData;

public class VolumeLevelModel {
    private MutableLiveData<Integer> volumeLevel;

    public VolumeLevelModel() {
        volumeLevel = new MutableLiveData<>();
    }
}
