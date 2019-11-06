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
}
