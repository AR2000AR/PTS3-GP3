package com.pts3.gp3.dinomap.quizz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pts3.gp3.dinomap.MainActivity;
import com.pts3.gp3.dinomap.R;
import com.pts3.gp3.dinomap.data.Question;

public class QuizzActivity extends AppCompatActivity {


    private Button btnRep1, btnRep2, btnRep3, btnRep4;
    private Button[] listeBtn = {null, null, null, null};
    private Question[] listeDeQuestion = {null, null, null, null, null};  //Ma liste de uestion formant le quizz
    private String[] mauvaiseReponse1 = {null, null, null};
    private String[] mauvaiseReponse2 = {null, null, null};
    private String[] mauvaiseReponse3 = {null, null, null};
    private String[] mauvaiseReponse4 = {null, null, null};

    private int pieceGagner = 0;

    private TextView t;
    private int numQuestionActuel = 0;
    private TextView textQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_quizz);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        textQuestion = findViewById(R.id.textQuestion);

        //Initialisation des différents bouton de réponse
        btnRep1 = findViewById(R.id.btnRep1);
        btnRep2 = findViewById(R.id.btnRep2);
        btnRep3 = findViewById(R.id.btnRep3);

        t = findViewById(R.id.textQuestionActuelle);

        btnRep1.setText("");
        btnRep2.setText("");
        btnRep3.setText("");
//        btnRep4.setText(""); // a enlever

        listeBtn[0] = btnRep1;
        listeBtn[1] = btnRep2;
        listeBtn[2] = btnRep3;



        initialisationQuizz();
        genererPropositionReponse();


        for (int i = 0; i < 3; i++) {
            listeBtn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    testerLaProposition(view);
                }
            });
        }
    }

    private void testerLaProposition(View view) {

        boolean test = false;
        for (int i = 0; i < 3 && !test; i++) {
            if (view == listeBtn[i]) {
                if (listeBtn[i].getText() == listeDeQuestion[numQuestionActuel].getReponse()) {
                    test = true;
                }
            }
        }
        String result;
        if (test) {
            result = "Réponse correct vous avez gagné 10 pièces";
            pieceGagner += 10;
        } else {
            result = "Réponse incorrect la réponse était " + listeDeQuestion[numQuestionActuel].getReponse();

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(result);
        LinearLayout linearLayout = new LinearLayout(this);


        builder.setPositiveButton("Question suivante", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                quetionSuivante();
            }
        });

        builder.create().show();


    }


    private void quetionSuivante() {
        numQuestionActuel++;
        genererPropositionReponse();
    }

    private void initialisationQuizz() {
        mauvaiseReponse1[0] = " 8 mètres";
        mauvaiseReponse1[1] = " 12 mètres";
        mauvaiseReponse1[2] = " 5 mètres";

        listeDeQuestion[0] = new Question("Le T-Rex mesurait combien de mètre de hauteur ?", mauvaiseReponse1, " 4 mètres ");


        mauvaiseReponse2[0] = " 1 mètres";
        mauvaiseReponse2[1] = " 1.25 mètres";
        mauvaiseReponse2[2] = " 0.95 mètres";

        listeDeQuestion[1] = new Question("Le Vélociraptor mesurait combien de mètre de hauteur ?", mauvaiseReponse2, " 0.75 mètres ");

        mauvaiseReponse3[0] = " 4 mètres";
        mauvaiseReponse3[1] = " 4.5 mètres";
        mauvaiseReponse3[2] = " 2.5 mètres";

        listeDeQuestion[2] = new Question("Le Tricératops mesurait combien de mètre de hauteur ?", mauvaiseReponse3, " 3 mètres ");


        mauvaiseReponse4[0] = " 1.8 mètres";
        mauvaiseReponse4[1] = " 1.6 mètres";
        mauvaiseReponse4[2] = " 1.5 mètres";

        listeDeQuestion[3] = new Question("L' Ankylosaurus mesurait combien de mètre de hauteur ?", mauvaiseReponse4, "  1.7 mètres ");


    }


    private void genererPropositionReponse() {

        t.setText("Question N° " + (numQuestionActuel + 1));
        //Test de fin de quizz
        if (numQuestionActuel == listeDeQuestion.length - 1) {

            /*
            Creation de la boite de dialogue de fin
             */
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("Vous avez gagné " + pieceGagner + " pièce(s)", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.gagnerNbPiece(pieceGagner);
                    startActivity(new Intent(QuizzActivity.this, MainActivity.class));
                }
            });

            builder.create().show();
        } else {

            int i = (int) Math.round(Math.random() * 3); // Inidice du bouton qui a la bonne réponse
            int p = 0;
            listeBtn[i].setText(listeDeQuestion[numQuestionActuel].getReponse());

            textQuestion.setText(listeDeQuestion[numQuestionActuel].getQuestion());


            for (int j = 0; j < 3; j++) {
                if (listeBtn[j].getText() != listeDeQuestion[numQuestionActuel].getReponse()) {
                    listeBtn[j].setText(listeDeQuestion[numQuestionActuel].getMauvaiseReponse()[p]);
                    p++;
                }

            }

        }
    }

}
