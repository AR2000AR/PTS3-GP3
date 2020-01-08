package com.pts3.gp3.dinomap.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class GestionaireDePiece {
    public static final String PIECE = "piece";
    SharedPreferences sp;

    public GestionaireDePiece(Context context) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public int getNbPiece() {
        return sp.getInt(PIECE, 0);
    }

    public void setNbPiece(int nb) {
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt(PIECE, nb);
        ed.apply();
    }
}
