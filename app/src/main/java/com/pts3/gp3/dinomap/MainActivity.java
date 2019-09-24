package com.pts3.gp3.dinomap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_quizz);

        final ImageView imV = (ImageView) findViewById(R.id.imageView7);
            imV.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),"TEST",Toast.LENGTH_LONG);
                }
            });
    }
}
