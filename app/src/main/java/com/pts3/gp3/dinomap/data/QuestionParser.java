package com.pts3.gp3.dinomap.data;

import org.jdom.Element;
import org.jdom.JDOMException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QuestionParser extends Parser {

    public QuestionParser(InputStream inputStream) throws JDOMException, IOException {
        super(inputStream);
    }

    public QuestionParser() throws NoDatabaseException {
        super();
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        for (Element e : (List<Element>) database.getRootElement().getChildren("Question")) {
            questions.add(makeQuestion(e));
        }
        return questions;
    }

    private Question makeQuestion(Element questionElement) {

        List<String> mrTmp = new ArrayList<>();
        for (Element e : (List<Element>) questionElement.getChildren("mauvaiseReponse")) {
            mrTmp.add(e.getText());
        }
        String[] mauvaisesReponses = mrTmp.toArray(new String[2]);
        String question = questionElement.getChild("Titre").getText();
        String bonneReponse = questionElement.getChild("bonneReponse").getText();

        return new Question(question, mauvaisesReponses, bonneReponse);
    }

}
