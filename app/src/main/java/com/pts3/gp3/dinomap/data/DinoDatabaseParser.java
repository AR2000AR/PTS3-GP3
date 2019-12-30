package com.pts3.gp3.dinomap.data;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.jdom.Element;
import org.jdom.JDOMException;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class DinoDatabaseParser extends Parser {

    public static final int NOM_COMMUN = 0;
    public static final int NOM_SCIENTIFIQUE = 1;

    public DinoDatabaseParser(InputStream inputStream) throws JDOMException, IOException {
        super(inputStream);
    }

    public DinoDatabaseParser() throws NoDatabaseException {
        super();
    }

    public List<String[]> getDinoNameListe() {
        List<String[]> dinoNameListe = new LinkedList<>();
        Element rootElement = database.getRootElement();
        for (Object dino : rootElement.getChildren("Dinosaure")) {
            Element nomElement = ((Element) dino).getChild("Nom");
            Element nomCom = nomElement.getChild("NomCommun");
            Element nomSc = nomElement.getChild("NomScientifique");
            String[] nomDino = {nomCom.getText(), nomSc.getText()};
            dinoNameListe.add(nomDino);
        }
        return dinoNameListe;
    }

    public Dino getDino(String[] nom) {
        Element rootElement = database.getRootElement();
        for (Object dino : rootElement.getChildren("Dinosaure")) {
            Element nomElement = ((Element) dino).getChild("Nom");
            Element nomCom = nomElement.getChild("NomCommun");
            Element nomSc = nomElement.getChild("NomScientifique");
            if (nomCom.getText().equals(nom[0]) && nomSc.getText().equals(nom[1])) {
                return makeDinoObject((Element) dino);
            }
        }
        return null;
    }

    public Dino getDino(String nomScientifique) {
        Log.d("DinoDatabaseParser", nomScientifique);
        Element rootElement = database.getRootElement();
        for (Object dino : rootElement.getChildren("Dinosaure")) {
            Element nomElement = ((Element) dino).getChild("Nom");
            Element nomSc = nomElement.getChild("NomScientifique");
            if (nomSc.getText().equals(nomScientifique)) {
                return makeDinoObject((Element) dino);
            }
        }
        return null;
    }

    private Dino makeDinoObject(Element dino) {
        Element nomElement = dino.getChild("Nom");
        Element nomCommunElement = nomElement.getChild("NomCommun");
        Element nomScientifiqueElement = nomElement.getChild("NomScientifique");
        Element tailleElement = dino.getChild("Taille");
        Element poidsElement = dino.getChild("Poids");
        Element epoqueElement = dino.getChild("Epoque");
        Element lieuDecouverteFossileElement = dino.getChild("LieuDecouverteFossile");
        Element regimeAlimentaireElement = dino.getChild("RegimeAlimentaire");
        Element descriptionDetailleeElement = dino.getChild("DescriptionDetaillee");
        Element modeDeVieElement = descriptionDetailleeElement.getChild("ModeDeVie");
        Element modeAlimentationElement = descriptionDetailleeElement.getChild("ModeAlimentation");
        Element commentaireElement = descriptionDetailleeElement.getChild("Commentaire");

        String nomCom = nomCommunElement.getText();
        String nomSc = nomScientifiqueElement.getText();
        String taille = tailleElement.getText();
        String poids = poidsElement.getText();
        String epoque = epoqueElement.getText();
        String regimeAlimentaire = regimeAlimentaireElement.getText();
        String modeDeVie = modeDeVieElement.getText();
        String modeAliementation = modeAlimentationElement.getText();
        String commentaire = null;
        if (commentaireElement != null) {
            commentaire = commentaireElement.getText();
        }

        List<LatLng> lieus = new LinkedList<>();
        for (Object lieu : lieuDecouverteFossileElement.getChildren("Lieu")) {
            Element lieuElement = (Element) lieu;
            double lat;
            double lng;
            lat = Double.parseDouble(lieuElement.getText().split(";")[0]);
            lng = Double.parseDouble(lieuElement.getText().split(";")[1]);
            lieus.add(new LatLng(lat, lng));
        }

        List<Epoque> epoques = new LinkedList<>();
        Log.d("DinoDatabaseParser", "Epoques " + nomSc + "===========");
        Log.d("DinoDatabaseParser", epoque);
        for (String epoqueStr : epoque.split(";")) {
            epoques.add(Epoque.valueOf(epoqueStr.toUpperCase()));
            Log.d("DinoDatabaseParser", Epoque.valueOf(epoqueStr.toUpperCase()).name());
        }

        double[] tailles = new double[2];
        if (taille.split(";").length == 2) {
            tailles[Dino.LONGUEUR] = Double.parseDouble(taille.split(";")[Dino.LONGUEUR]);
            tailles[Dino.HAUTEUR] = Double.parseDouble(taille.split(";")[Dino.HAUTEUR]);
        } else {
            if (taille.equals("")) {
                tailles[Dino.LONGUEUR] = -1;
            } else {
                tailles[Dino.LONGUEUR] = Double.parseDouble(taille);
            }
            tailles[Dino.HAUTEUR] = -1;
        }

        if (poids.equals("")) {
            poids = "-1";
        }

        return new Dino(nomCom, nomSc, tailles, Double.parseDouble(poids), epoques, lieus, regimeAlimentaire, modeDeVie, modeAliementation, commentaire);
    }
}
