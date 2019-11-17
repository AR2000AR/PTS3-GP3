package com.pts3.gp3.dinomap.data;

import com.google.android.gms.maps.model.LatLng;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class DinoDatabaseParser {

    public static final int NOM_COMMUN = 0;
    public static final int NOM_SCIENTIFIQUE = 1;

    private static Document database = null;

    public DinoDatabaseParser(InputStream inputStream) throws JDOMException, IOException {
        if (database == null) {
            SAXBuilder databaseBuilder = new SAXBuilder();
            database = databaseBuilder.build(inputStream);
        }
    }

    public DinoDatabaseParser() throws NoDatabaseException {
        if (database == null) {
            throw new NoDatabaseException();
        }
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
            String[] nomDino = {nomCom.getText(), nomSc.getText()};
            if (nomCom.getText().equals(nom[0]) && nomSc.getText().equals(nom[1])) {
                return makeDinoObject((Element) dino);
            }
        }
        return null;
    }

    private Dino makeDinoObject(Element dino) {
        Element nomElement = dino.getChild("Nom");
        Element nomCommunElement = nomElement.getChild("NomCommun");
        Element nomScientifique = nomElement.getChild("NomScientifique");
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
        String nomSc = nomCommunElement.getText();
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
            lat = Double.parseDouble(((Element) lieu).getText().split(";")[0]);
            lng = Double.parseDouble(((Element) lieu).getText().split(";")[1]);
            lieus.add(new LatLng(lat, lng));
        }
        double[] tailles = new double[2];
        if (taille.split(";").length == 2) {
            tailles[Dino.LONGUEUR] = Double.parseDouble(taille.split(";")[Dino.LONGUEUR]);
            tailles[Dino.HAUTEUR] = Double.parseDouble(taille.split(";")[Dino.HAUTEUR]);
        } else {
            tailles[Dino.LONGUEUR] = Double.parseDouble(taille);
            tailles[Dino.HAUTEUR] = -1;
        }

        return new Dino(nomCom, nomSc, tailles, Double.parseDouble(poids), epoque, lieus, regimeAlimentaire, modeDeVie, modeAliementation, commentaire);
    }
}
