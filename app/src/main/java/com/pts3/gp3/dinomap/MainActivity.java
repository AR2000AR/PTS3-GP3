package com.pts3.gp3.dinomap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pts3.gp3.dinomap.encyclopedia.EncyclopedieMenu;
import com.pts3.gp3.dinomap.quizz.QuizzActivity;

public class MainActivity extends AppCompatActivity {

    TextView texteNbPiece;
    static int nbPiece = 0;

    public static void gagnerNbPiece(int pieceGagner) {
        nbPiece += pieceGagner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        LinearLayout quizLayout = findViewById(R.id.quizLayout);
        ImageView mapIcone = findViewById(R.id.mapIcone);
        ImageView encycloIcone = findViewById(R.id.encycloIcone);
        ImageView quizIcone = findViewById(R.id.quizIcone);
        ImageView btnCredit = findViewById(R.id.btnCredit);


        View.OnClickListener mapClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lauchIntent = new Intent(getBaseContext(), MapActivity.class);
                startActivity(lauchIntent);
            }
        };
        mapIcone.setOnClickListener(mapClickListener);

        View.OnClickListener encycloClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lauchIntent = new Intent(getBaseContext(), EncyclopedieMenu.class);
                startActivity(lauchIntent);

            }
        };
        encycloIcone.setOnClickListener(encycloClickListener);

        View.OnClickListener quizClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lauchIntent = new Intent(getBaseContext(), QuizzActivity.class);
                startActivity(lauchIntent);
            }
        };
        quizLayout.setOnClickListener(quizClickListener);
        quizIcone.setOnClickListener(quizClickListener);



        btnCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lauchIntent = new Intent(getBaseContext(), CreditActivity.class);
                startActivity(lauchIntent);
            }
        });


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        texteNbPiece.setText(""+nbPiece);
    }
}
