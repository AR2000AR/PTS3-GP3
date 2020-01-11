package com.pts3.gp3.dinomap.data;

import android.content.Context;
import android.content.SharedPreferences;

public class GestionnaireDePiece extends Gestionnaire {

    private static final String PIECE = "piece";

    public GestionnaireDePiece(Context context) {
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
