package com.pts3.gp3.dinomap;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TutorielActivity extends AppCompatActivity {


    ImageView imgTuto;
    Button btnSuivant;
    TextView textInstruction;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoriel);

        btnSuivant = findViewById(R.id.btnSuivant);
        textInstruction = findViewById(R.id.textInstruction);
        imgTuto = findViewById(R.id.imgTuto);

        btnSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                switch (i) {
                    case 1:
                        imgTuto.setImageResource(R.drawable.accueil1);
                        textInstruction.setText("Vous avez le choix de choisir entre explorer la carte," +
                                " faire des quizs, explorer l’encyclopédie et savoir qui a fait l’application.");
                        break;

                    case 2:
                        imgTuto.setImageResource(R.drawable.carte1);
                        textInstruction.setText("Voici la carte, vous pouvez naviguer sur la carte en glissant la carte avec vos doigts, " +
                                "vous pouvez modifier l’époque que vous voulez explorer. " +
                                "En bas de l’écran, il y une frise chronologique que l’on peut faire glisser de gauche à droite le point qui indique l’époque actuelle.");
                        break;

                    case 3:
                        imgTuto.setImageResource(R.drawable.carte2);
                        textInstruction.setText("Les marqueurs représentent l’emplacement d’un fossile de dinosaure trouvé," +
                                " Les marqueurs de la même couleurs représentent le même dinosaure à plusieurs emplacements différentes.\n");
                        break;

                    case 4:
                        imgTuto.setImageResource(R.drawable.carte3);
                        textInstruction.setText("Vous pouvez appuyer sur les marqueurs une seule fois pour savoir le nom scientifique " +
                                "et le nom commun du dinosaure qui correspond au marqueur.");
                        break;

                    case 5:
                        imgTuto.setImageResource(R.drawable.dino1);
                        textInstruction.setText("Puis une seconde fois pour accéder à une représentation visuelle du dinosaure " +
                                "ainsi que des informations comme sa taille, son poids etc …\n");
                        break;

                    case 6:
                        imgTuto.setImageResource(R.drawable.dino2);
                        textInstruction.setText("Vous pouvez débloquer des informations supplémentaires en les échangeant " +
                                "par des points gagnés lors des quiz en appuyant sur le cadenas en bas de page puis en appuyant sur oui.\n");
                        break;

                    case 7:
                        imgTuto.setImageResource(R.drawable.encly1);
                        textInstruction.setText("Vous pouvez faire défiler la liste des dinosaures en la faisant glisser, vous pouvez aussi cliquer sur le nom d’un dinosaure pour accéder aux informations qui sont à propos de lui. \n" +
                                "Vous pouvez voir un cadenas bloqué en face des dinosaures avec les informations supplémentaires non débloquées.\n");
                        break;

                    case 8:
                        imgTuto.setImageResource(R.drawable.encly2);
                        textInstruction.setText("Vous pouvez voir un cadenas débloqué en face des dinosaures avec les informations supplémentaires débloquées.\n");
                        break;

                    case 9:
                        imgTuto.setImageResource(R.drawable.quiz1);
                        textInstruction.setText("Vous devez appuyer sur la proposition par les trois que vous pensez correctes.\n");
                        break;

                    case 10:
                        imgTuto.setImageResource(R.drawable.quiz2);
                        textInstruction.setText("Un message de réussite s'affiche si c’est la bonne réponse avec le montant de pièce gagné.");
                        break;

                    case 11:
                        imgTuto.setImageResource(R.drawable.quiz3);
                        textInstruction.setText("Un message d’erreur avec la bonne réponse s’affiche si c’est la mauvaise réponse.\n");
                        break;

                    case 12:
                        imgTuto.setImageResource(R.drawable.quiz4);
                        textInstruction.setText("Vous pouvez voir le nombre de pièce accumulé ici.\n");
                        break;


                    default:
                        startActivity(new Intent(TutorielActivity.this, MainActivity.class));
                        break;
                }

            }
        });
    }
}



