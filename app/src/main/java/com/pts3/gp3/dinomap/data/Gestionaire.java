package com.pts3.gp3.dinomap.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public abstract class Gestionaire {


    protected SharedPreferences sp;

    public Gestionaire(Context context) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
    }
}
