package com.pts3.gp3.dinomap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CreditActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        button = findViewById(R.id.button_tutoriel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreditActivity.this, TutorielActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
            }
        });
    }
}
