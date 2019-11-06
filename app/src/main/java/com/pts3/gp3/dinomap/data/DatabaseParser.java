package com.pts3.gp3.dinomap.data;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class DatabaseParser {
    public static Document database = null;

    public DatabaseParser(InputStream inputStream) throws JDOMException, IOException {
        if (database == null){
            SAXBuilder databaseBuilder = new SAXBuilder();
            database = databaseBuilder.build(inputStream);
        }
    }

    public DatabaseParser() throws NoDatabaseException {
        if(database == null){
            throw new NoDatabaseException();
        }
    }

    public List<String[]> getDinoNameListe(){
        List<String[]> dinoNameListe = new LinkedList<>();
        Element rootElement = database.getRootElement();
        for (Object dino: rootElement.getChildren("Dinosaure")) {
            Element nomElement = ((Element) dino).getChild("Nom");
            Element nomCom = nomElement.getChild("NomCommun");
            Element nomSc = nomElement.getChild("NomScientifique");
            String[] nomDino = {nomCom.getText(),nomSc.getText()};
            dinoNameListe.add(nomDino);
        }
        return dinoNameListe;
    }

    public Dino getDino(String[] nom){
        Element rootElement = database.getRootElement();
        for (Object dino: rootElement.getChildren("Dinosaure")) {
            Element nomElement = ((Element) dino).getChild("Nom");
            Element nomCom = nomElement.getChild("NomCommun");
            Element nomSc = nomElement.getChild("NomScientifique");
            String[] nomDino = {nomCom.getText(),nomSc.getText()};
            if(nomCom.getText().equals(nom[0]) && nomSc.getText().equals(nom[1])){
                return makeDinoObject((Element)dino);
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
        Element descriptionDetailleElement = dino.getChild("DescriptionDetaille");
        Element modeDeVieElement = descriptionDetailleElement.getChild("ModeDeVie");
        Element modeAlimentationElement = descriptionDetailleElement.getChild("ModeAlimentation");
        Element commentaireElement = descriptionDetailleElement.getChild("Commentaire");

        String nomCom = nomCommunElement.getText();
        String nomSc = nomCommunElement.getText();
        String taille = tailleElement.getText();
        String poids = poidsElement.getText();
        String epoque = epoqueElement.getText();
        String lieuDecouverteFossile = lieuDecouverteFossileElement.getText();
        String regimeAlimentaire = regimeAlimentaireElement.getText();
        String modeDeVie = modeDeVieElement.getText();
        String modeAliementation = modeAlimentationElement.getText();
        String commentaire = null;
        if(commentaireElement!=null){
            commentaire = commentaireElement.getText();
        }

        return new Dino(nomCom,nomCom,taille,poids,epoque,lieuDecouverteFossile,regimeAlimentaire,modeDeVie,modeAliementation,commentaire);
    }
}
