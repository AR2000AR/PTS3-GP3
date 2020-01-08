package com.pts3.gp3.dinomap.data;

import android.content.Context;
import android.content.SharedPreferences;

public class GestionaireAchat extends Gestionaire {


    public GestionaireAchat(Context context) {
        super(context);
    }

    public boolean isUnlocked(Dino dino) {
        return sp.getBoolean(dino.getNomScientifique(), false);
    }

    public void setUnlocked(Dino dino, boolean unlocked) {
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean(dino.getNomScientifique(), unlocked);
        ed.apply();
    }
}
