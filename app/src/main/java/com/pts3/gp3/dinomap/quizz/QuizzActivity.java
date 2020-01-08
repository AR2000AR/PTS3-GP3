package com.pts3.gp3.dinomap.quizz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pts3.gp3.dinomap.MainActivity;
import com.pts3.gp3.dinomap.R;
import com.pts3.gp3.dinomap.data.DinoDatabaseParser;
import com.pts3.gp3.dinomap.data.GestionaireDePiece;
import com.pts3.gp3.dinomap.data.Question;
import com.pts3.gp3.dinomap.data.QuestionParser;

import org.jdom.JDOMException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QuizzActivity extends AppCompatActivity {

    private QuestionParser database;
    private List<Question> questions = new ArrayList<>();

    private Button[] boutons = new Button[3];

    private int piecesGagnees = 0;

    private TextView t;
    private int numQuestionActuelle = 1;
    private TextView textQuestion;
    private int idQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        boutons[0] = findViewById(R.id.btnRep1);
        boutons[1] = findViewById(R.id.btnRep2);
        boutons[2] = findViewById(R.id.btnRep3);
        for(Button b : boutons){
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    afficherStatutReponse(view);
                }
            });
        }

        t = findViewById(R.id.textQuestionActuelle);

        InputStream inputStream = getResources().openRawResource(R.raw.quiz);

        try {
            database = new QuestionParser(inputStream);
            questions = database.getAllQuestions();
            Log.i("", "onCreate: " + questions.size());
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }

        idQuestion = genererReponse();
    }

    public boolean testerLaProposition(View v){
        for (int i = 0; i < 3; i++) {
            if (v == boutons[i]) {
                if (boutons[i].getText() == questions.get(idQuestion).getReponse()) {
                    piecesGagnees += 10;
                    return true;
                }
            }
        }
        return false;
    }

    private void afficherStatutReponse(View v) {
        String result = "";
        Log.i("", "afficherStatutReponse: " + testerLaProposition(v));
        if(testerLaProposition(v)){
            result = "Réponse correct vous avez gagné 10 pièces";
        }else {
            result = "Réponse incorrect la réponse était " + questions.get(idQuestion).getReponse();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(result);
       // LinearLayout linearLayout = new LinearLayout(this);


        builder.setPositiveButton("Question suivante", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GestionaireDePiece gp = new GestionaireDePiece(QuizzActivity.this);
                gp.setNbPiece(gp.getNbPiece()+10);
                quetionSuivante();
            }
        });

        builder.create().show();
    }


    private void quetionSuivante() {
        numQuestionActuelle++;
        idQuestion = genererReponse();
    }


    private int genererReponse() {

        //Test de fin de quizz
        if (numQuestionActuelle == questions.size() - 1) {
            /*
            Creation de la boite de dialogue de fin
             */
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("Vous avez gagné " + piecesGagnees + " pièce(s)", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.gagnerNbPiece(piecesGagnees);
                    startActivity(new Intent(QuizzActivity.this, MainActivity.class));
                }
            });

            builder.create().show();
            return -1;
        } else {
            t.setText("Question n°" + numQuestionActuelle);
            int i = (int) Math.round(Math.random() * 30); // Indice du bouton qui a la bonne réponse
            int p = 0;
            int val = (int) Math.round(Math.random() * 2);
            boutons[val].setText(questions.get(i).getReponse());
            textQuestion.setText(questions.get(i).getQuestion());


            for (int j = 0; j < 3; j++) {
                if (boutons[j].getText() != questions.get(i).getReponse()) {
                    boutons[j].setText(questions.get(i).getMauvaiseReponse()[p]);
                    p++;
                }

            }
            return i;
        }
    }

}
