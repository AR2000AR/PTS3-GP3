package com.pts3.gp3.dinomap.encyclopedia;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.pts3.gp3.dinomap.R;
import com.pts3.gp3.dinomap.data.DatabaseParser;
import com.pts3.gp3.dinomap.data.Dino;

import org.jdom.JDOMException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EncyclopediaMenu extends AppCompatActivity {

    private LinearLayout listLayout;
    private DatabaseParser database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia_menu);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listLayout = (LinearLayout) findViewById(R.id.listeLayout);

        InputStream inputStream = getResources().openRawResource(R.raw.dino);
        try {
            database = new DatabaseParser(inputStream);
            List<String[]> dinos = database.getDinoNameListe();
            for (String[] names: dinos) {
                Log.d("DINO",String.format("Com : %s | Sc : %s",names[0],names[1]));
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Dino dino = database.getDino(database.getDinoNameListe().get(0));
        Log.d("PAUSE","PAUSE");

        for (int i = 0; i < 20; i++) {
            listLayout.addView(new DinoNameView(this, getString(R.string.placeholder_name_sc), getString(R.string.placeholder_name_com)));
        }
    }

    private DinoNameView makeViewFromDino(Dino dino) {
        return new DinoNameView(this, dino.getNomScientifique(), dino.getNomCommun());
    }
}
