package com.pts3.gp3.dinomap.quizz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.pts3.gp3.dinomap.R;

import androidx.appcompat.app.AppCompatActivity;

public class QuizzActivity extends AppCompatActivity {


    private Button btnRep1, btnRep2, btnRep3, btnRep4;
    private Button listeBtn[] = {btnRep1, btnRep2, btnRep3, btnRep4};
    private Question listeDeQuestion[] = {null, null, null, null, null};  //Ma liste de uestion formant le quizz
    private String mauvaiseReponse[] = {null, null, null};
    private static int numQuestionActuel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_quizz);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //Initialisation des différents bouton de réponse
        btnRep1 = findViewById(R.id.btnRep1);
        btnRep2 = findViewById(R.id.btnRep2);
        btnRep3 = findViewById(R.id.btnRep3);
        btnRep4 = findViewById(R.id.btnRep4);

        btnRep1.setText("");
        btnRep2.setText("");
        btnRep3.setText("");
        btnRep4.setText("");


        initialisationQuizz();
        genererPropositionReponse();


    }

    private void initialisationQuizz() {
        mauvaiseReponse[0] = " 8 mètres";
        mauvaiseReponse[1] = " 12 mètres";
        mauvaiseReponse[2] = " 5 mètres";

        listeDeQuestion[0] = new Question("Le T-Rex mesurait combien de mètre de hauteur ?", mauvaiseReponse, " 4 mètres ");


        mauvaiseReponse[0] = " 1 mètres";
        mauvaiseReponse[1] = " 1.25 mètres";
        mauvaiseReponse[2] = " 0.95 mètres";

        listeDeQuestion[1] = new Question("Le Vélociraptor mesurait combien de mètre de hauteur ?", mauvaiseReponse, " 0.75 mètres ");

        mauvaiseReponse[0] = " 4 mètres";
        mauvaiseReponse[1] = " 4.5 mètres";
        mauvaiseReponse[2] = " 2.5 mètres";

        listeDeQuestion[2] = new Question("Le Tricératops mesurait combien de mètre de hauteur ?", mauvaiseReponse, " 3 mètres ");


        mauvaiseReponse[0] = " 1.8 mètres";
        mauvaiseReponse[1] = " 1.6 mètres";
        mauvaiseReponse[2] = " 1.5 mètres";

        listeDeQuestion[3] = new Question("L' Ankylosaurus mesurait combien de mètre de hauteur ?", mauvaiseReponse, "  1.7 mètres ");
    }


    private void genererPropositionReponse() {
        int i = (int) Math.round(Math.random() * 3); // Inidice du bouton qui a la bonne réponse
        int p = 0;
        listeBtn[i].setText(listeDeQuestion[numQuestionActuel].getReponse());

        for (int j = 0; j < 4; j++) {

            if (listeBtn[j].getText() == "") {
                listeBtn[j].setText(listeDeQuestion[numQuestionActuel].getMauvaiseReponse()[p]);
            }

        }

    }

}
