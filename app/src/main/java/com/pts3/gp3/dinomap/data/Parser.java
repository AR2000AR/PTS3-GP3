package com.pts3.gp3.dinomap.data;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;

public abstract class Parser {

    protected Document database = null;

    public Parser(InputStream inputStream) throws JDOMException, IOException {
        if (database == null) {
            SAXBuilder databaseBuilder = new SAXBuilder();
            database = databaseBuilder.build(inputStream);
        }
    }

    public Parser() throws NoDatabaseException {
        if (database == null) {
            throw new NoDatabaseException();
        }
    }

}
