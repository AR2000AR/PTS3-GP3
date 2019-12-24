package com.pts3.gp3.dinomap.data;

import com.pts3.gp3.dinomap.R;

public enum Epoque {

    CAMBRIEN(R.string.cambrien),
    ORDOVICIEN(R.string.ordovicien),
    SILURIEN(R.string.silurien),
    DEVONIEN(R.string.devonien),
    CARBONIFERE(R.string.carbonifere),
    PERMIEN(R.string.permien),
    TRIAS(R.string.trias),
    JURASSIQUE(R.string.jurassique),
    CRETACE(R.string.cretace);

    private int stringId;

    Epoque(int stringId) {
        this.stringId = stringId;
    }

    public int getStringId() {
        return stringId;
    }
}
