package com.pts3.gp3.dinomap.data;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class Dino {

    public static int LONGUEUR = 0;
    public static int HAUTEUR = 1;

    private String nomCommun,nomScientifique;
    private double[] taille;
    private double poid;
    private String epoque;
    private List<LatLng> lieuDeDecouverte;
    private String regimeAlimentaire;
    private String modeDeVie;
    private String modeAlimentaire;
    private String commentaire;

    public Dino(String nomCommun, String nomScientifique, double[] taille, double poid, String epoque, List<LatLng> lieuDeDecouverte, String regimeAlimentaire, String modeDeVie, String modeAlimentaire, String commentaire) {
        this.nomCommun = nomCommun;
        this.nomScientifique = nomScientifique;
        this.taille = taille;
        this.poid = poid;
        this.epoque = epoque;
        this.lieuDeDecouverte = lieuDeDecouverte;
        this.regimeAlimentaire = regimeAlimentaire;
        this.modeDeVie = modeDeVie;
        this.modeAlimentaire = modeAlimentaire;
        this.commentaire = commentaire;
    }

    public String getNomCommun() {
        return nomCommun;
    }

    public String getNomScientifique() {
        return nomScientifique;
    }

    public double[] getTaille() {
        return taille;
    }

    public double getPoid() {
        return poid;
    }

    public String getEpoque() {
        return epoque;
    }

    public List<LatLng> getLieuDeDecouverte() {
        return lieuDeDecouverte;
    }

    public String getRegimeAlimentaire() {
        return regimeAlimentaire;
    }

    public String getModeDeVie() {
        return modeDeVie;
    }

    public String getModeAlimentaire() {
        return modeAlimentaire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Dino(String nomCommun, String nomScientifique, double[] taille, double poid, String epoque, List<LatLng> lieuDeDecouverte, String regimeAlimentaire, String modeDeVie, String modeAlimentaire) {
        this(nomCommun,nomScientifique,taille,poid,epoque,lieuDeDecouverte,regimeAlimentaire,modeDeVie,modeAlimentaire,null);
    }
}
