package com.pts3.gp3.dinomap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        LinearLayout mapLayout = findViewById(R.id.mapLayout);
        LinearLayout encycloLayout = findViewById(R.id.encycloLayout);
        LinearLayout quizLayout = findViewById(R.id.quizLayout);
        ImageButton mapIcone = findViewById(R.id.mapIcone);
        ImageButton encycloIcone = findViewById(R.id.encycloIcone);
        ImageButton quizIcone = findViewById(R.id.quizIcone);

        /*mapLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lauchIntent = new Intent(getBaseContext(),);
                startActivity(lauchIntent);
            }
        });*/

        View.OnClickListener encycloClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lauchIntent = new Intent(getBaseContext(),EncyclopediaActivity.class);
                startActivity(lauchIntent);

            }
        };
        encycloLayout.setOnClickListener(encycloClickListener);
        encycloIcone.setOnClickListener(encycloClickListener);

        View.OnClickListener quizClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lauchIntent = new Intent(getBaseContext(),QuizActivity.class);
                startActivity(lauchIntent);
            }
        };
        quizLayout.setOnClickListener(quizClickListener);
        quizIcone.setOnClickListener(quizClickListener);


    }
}
