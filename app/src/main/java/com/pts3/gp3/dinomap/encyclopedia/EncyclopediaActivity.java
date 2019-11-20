package com.pts3.gp3.dinomap.encyclopedia;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pts3.gp3.dinomap.R;
import com.pts3.gp3.dinomap.data.Dino;
import com.pts3.gp3.dinomap.data.DinoDatabaseParser;

import org.jdom.JDOMException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EncyclopediaActivity extends AppCompatActivity {

    private DinoDatabaseParser database;

    private Dino dino;

    private TextView text_nomSDino;
    private TextView text_nomCDino;
    private TextView text_TailleDino;
    private TextView text_PoidsDino;
    private TextView text_EpoqueDino;
    private TextView text_localisationDino;
    private TextView text_RegimeDino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia);

        Bundle extras = getIntent().getExtras();
        String nom[] = null;

        if(extras.getStringArray("nomDino") != null){
            nom = extras.getStringArray("nomDino");
            Log.i("Je suis passée par là", "merde ca marhce pas");
        }

        text_nomCDino = findViewById(R.id.text_nomCDino);
        text_nomCDino.setText(nom[0]);

        text_nomSDino = findViewById(R.id.text_nomSDino);
        text_nomSDino.setText(nom[1]);

        text_TailleDino = findViewById(R.id.text_tailleDino);
        text_PoidsDino = findViewById(R.id.text_poidsDino);
        text_EpoqueDino = findViewById(R.id.text_epoqueDino);
        text_localisationDino = findViewById(R.id.text_localisationDino);
        text_RegimeDino = findViewById(R.id.text_regimeDino);

        InputStream inputStream = getResources().openRawResource(R.raw.dino);
        try {
            database = new DinoDatabaseParser(inputStream);
            dino = database.getDino(nom);
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }

        text_TailleDino.setText(text_TailleDino.getText() + " " + dino.getTaille()[0] + " de long, " + " " + dino.getTaille()[1] + "m de haut");
        text_PoidsDino.setText(text_PoidsDino.getText() + " " + dino.getPoids());
        text_EpoqueDino.setText(text_EpoqueDino.getText() + " " + dino.getEpoque());
        //text_localisationDino.setText((CharSequence) dino.getLieuDeDecouverte());
        text_RegimeDino.setText(text_RegimeDino.getText() + " " + dino.getRegimeAlimentaire());

        ImageButton returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
