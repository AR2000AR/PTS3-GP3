package com.pts3.gp3.dinomap.quizz;

public class Question {

    private String question,reponse;
    private String mauvaiseReponse[] = {null,null,null};


    public Question(String question,String[]mauvaiseReponse,String reponse){

        this.question = question;
        this.mauvaiseReponse = mauvaiseReponse;
        this.reponse = reponse;

    }

    public String getReponse() {
        return reponse;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getMauvaiseReponse() {
        return mauvaiseReponse;
    }

}
