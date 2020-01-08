package com.pts3.gp3.dinomap.encyclopedia;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.pts3.gp3.dinomap.R;
import com.pts3.gp3.dinomap.data.DinoDatabaseParser;
import com.pts3.gp3.dinomap.data.GestionaireAchat;

import org.jdom.JDOMException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EncyclopedieMenuActivity extends AppCompatActivity {

    private DinoDatabaseParser database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedie_menu);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayout listLayout = findViewById(R.id.listeLayout);

        InputStream inputStream = getResources().openRawResource(R.raw.dino);
        try {
            database = new DinoDatabaseParser(inputStream);
            List<String[]> dinos = database.getDinoNameListe();
            for (String[] names: dinos) {
                Log.d("DINO",String.format("Com : %s | Sc : %s",names[0],names[1]));
            }
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }

        int c = 0;
        int[] background = {getColor(R.color.test3), getColor(R.color.test2)};

        GestionaireAchat gestionaireAchat = new GestionaireAchat(this);

        for (String[] nom : database.getDinoNameListe()) {
            ViewNomDino dinoView = new ViewNomDino(this, background[c++ % 2], nom[DinoDatabaseParser.NOM_SCIENTIFIQUE], nom[DinoDatabaseParser.NOM_COMMUN]);
            dinoView.setUnlocked(gestionaireAchat.isUnlocked(database.getDino(nom)));
            listLayout.addView(dinoView);
        }
    }

}
