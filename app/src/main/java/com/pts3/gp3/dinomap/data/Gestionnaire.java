package com.pts3.gp3.dinomap.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public abstract class Gestionnaire {


    protected SharedPreferences sp;

    public Gestionnaire(Context context) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
    }
}
