package com.pts3.gp3.dinomap.encyclopedia;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.pts3.gp3.dinomap.R;
import com.pts3.gp3.dinomap.data.DatabaseParser;

import org.jdom.JDOMException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        /*BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
        String eachline = null;
        try {
            eachline = bufferedReader.readLine();
            bufferedReader.readLine();
            while (eachline != null) {
                Log.d("TEST",eachline);
                eachline = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
