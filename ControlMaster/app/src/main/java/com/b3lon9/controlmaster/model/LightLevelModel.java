package com.b3lon9.controlmaster.model;

import androidx.lifecycle.MutableLiveData;

public class LightLevelModel {
    private MutableLiveData<Integer> lightLevel;

    public LightLevelModel(int level) {
        lightLevel = new MutableLiveData<>();
        this.lightLevel.setValue(level);
    }

    public MutableLiveData<Integer> getLightLevel() {
        return lightLevel;
    }

    public void setLightLevel(MutableLiveData<Integer> lightLevel) {
        this.lightLevel = lightLevel;
    }
}
