package com.pts3.gp3.dinomap.data;

import android.content.Context;
import android.content.SharedPreferences;

public class GestionaireDePiece extends Gestionaire {

    private static final String PIECE = "piece";

    public GestionaireDePiece(Context context) {
        super(context);
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
