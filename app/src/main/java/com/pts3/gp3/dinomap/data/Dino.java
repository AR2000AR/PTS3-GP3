package com.pts3.gp3.dinomap.data;

public class Dino {
    private String nomCommun,nomScientifique;
    private String taille;
    private String poid;
    private String epoque;
    private String lieuDeDecouverte;
    private String regimeAlimentaire;
    private String modeDeVie;
    private String modeAlimentaire;
    private String commentaire;

    public Dino(String nomCommun, String nomScientifique, String taille, String poid, String epoque, String lieuDeDecouverte, String regimeAlimentaire, String modeDeVie, String modeAlimentaire, String commentaire) {
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

    public String getTaille() {
        return taille;
    }

    public String getPoid() {
        return poid;
    }

    public String getEpoque() {
        return epoque;
    }

    public String getLieuDeDecouverte() {
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

    public Dino(String nomCommun, String nomScientifique, String taille, String poid, String epoque, String lieuDeDecouverte, String regimeAlimentaire, String modeDeVie, String modeAlimentaire) {
        this(nomCommun,nomScientifique,taille,poid,epoque,lieuDeDecouverte,regimeAlimentaire,modeDeVie,modeAlimentaire,null);
    }
}
