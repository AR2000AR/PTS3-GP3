package com.pts3.gp3.dinomap.encyclopedia;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.pts3.gp3.dinomap.R;
import com.pts3.gp3.dinomap.data.Dino;
import com.pts3.gp3.dinomap.data.DinoDatabaseParser;

import org.jdom.JDOMException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EncyclopedieMenu extends AppCompatActivity {

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
        Dino dino = database.getDino(database.getDinoNameListe().get(0));
        Log.d("PAUSE","PAUSE");

        int c = 0;
        int[] background = {getColor(R.color.dinoNameView1), getColor(R.color.dinoNameView2)};
        /*for (int i = 0; i < 20; i++) {
            listLayout.addView(new DinoNameView(this, background[c++ % 2], getString(R.string.placeholder_name_sc), getString(R.string.placeholder_name_com)));
        }*/

        for (String[] nom : database.getDinoNameListe()) {
            listLayout.addView(new ViewNomDino(this, background[c++ % 2], nom[DinoDatabaseParser.NOM_SCIENTIFIQUE], nom[DinoDatabaseParser.NOM_COMMUN]));
        }
    }

    private ViewNomDino makeViewFromDino(Dino dino) {
        return new ViewNomDino(this, dino.getNomScientifique(), dino.getNomCommun());
    }
}
