package com.pts3.gp3.dinomap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pts3.gp3.dinomap.data.Dino;
import com.pts3.gp3.dinomap.data.DinoDatabaseParser;
import com.pts3.gp3.dinomap.data.Epoque;
import com.pts3.gp3.dinomap.encyclopedia.EncyclopedieActivity;

import org.jdom.JDOMException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap googleMap;
    private SeekBar seekBar;
    private int state;
    private TextView epoqueView;
    private List<Dino> dino;
    private Epoque epoque;
    private DinoDatabaseParser database;

    private TextView t;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //Texte qui va apparaitre par dessus la seek bar quand on va deplacer le curseur


        seekBar = findViewById(R.id.seekBarEpoque);
        epoqueView = findViewById(R.id.epoqueView);
        dino = new ArrayList<>();


        t = findViewById(R.id.t);
        t.setTextSize(15);
        t.setX(0);


        //layoutSeekBar.addView(t);

        /**
         * rempli la liste avec des dino
         */
        try {
          database = new DinoDatabaseParser(getResources().openRawResource(R.raw.dino));
            for (String[] curentDino : database.getDinoNameListe()) {
                dino.add(database.getDino(curentDino));

            }
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }

        state = seekBar.getProgress();
        epoque = epoqueChoice();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                state = progress;
                epoque = epoqueChoice();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                afficherMarqueur(getDino());
            }
        });

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getX() - t.getWidth() * 0.5 > 0) {
                    t.setX((float) (motionEvent.getX() - 0.5 * (t.getWidth())));
                }
                return false;
            }
        });
    }

    private void mettreAJourTexteIndicateur(int progress) {


    }

    private Epoque epoqueChoice() {
        switch (state) {
            case 0:
                epoqueView.setText(R.string.cambrien);
                t.setText("-540M à \n -500M");
                return Epoque.CAMBRIEN;
            case 1:
                epoqueView.setText(R.string.ordovicien);
                t.setText("-500M à \n -440M");
                return Epoque.ORDOVICIEN;
            case 2:
                epoqueView.setText(R.string.silurien);
                t.setText("-440M à \n -410M");
                return Epoque.SILURIEN;
            case 3:
                epoqueView.setText(R.string.devonien);
                t.setText("-410M à \n -355M");
                return Epoque.DEVONIEN;
            case 4:
                epoqueView.setText(R.string.carbonifere);
                t.setText("-355M à \n -295M");
                return Epoque.CARBONIFERE;
            case 5:
                epoqueView.setText(R.string.permien);
                t.setText("-295M à \n -250M");
                return Epoque.PERMIEN;
            case 6:
                epoqueView.setText(R.string.trias);
                t.setText("-250M à \n -200M");
                return Epoque.TRIAS;
            case 7:
                epoqueView.setText(R.string.jurassique);
                t.setText("-200M à \n -135M");
                return Epoque.JURASSIQUE;
            case 8:
                epoqueView.setText(R.string.cretace);
                t.setText("-135M à \n -65M");
                return Epoque.CRETACE;
        }
        return null;
    }

    private void afficherMarqueur(List<Dino> list) {
        googleMap.clear();
        for (Dino d : list) {
            if (d.getEpoques().contains(this.epoque)) {
                for (LatLng l : d.getLieuDeDecouverte()) {
                    //googleMap.addMarker(new MarkerOptions().position(l).title(""));

                    //MarkerOptions m = new MarkerOptions().position(l).title(d.getNomCommun()).snippet(d.getNomScientifique());

                    Marker m = googleMap.addMarker(new MarkerOptions().position(l).title(d.getNomScientifique()).snippet(d.getNomCommun()));

                    /*MarkerOptions m = new MarkerOptions();
                    m.position(l);
                    m.title("")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icodinomapbitmap));*/

                }
            }
        }
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        Dino d = database.getDino(marker.getTitle());
        Intent intent = new Intent(this, EncyclopedieActivity.class);
        String[] nom = new String[2];
        nom[DinoDatabaseParser.NOM_COMMUN] = d.getNomCommun();
        nom[DinoDatabaseParser.NOM_SCIENTIFIQUE] = d.getNomScientifique();
        intent.putExtra("nomDino", nom);

        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng center = new LatLng(46.603354, 1.8883335);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(center));

        googleMap.setOnInfoWindowClickListener(this);

        afficherMarqueur(getDino());
    }

    public List<Dino> getDino() {
        return dino;
    }
}
