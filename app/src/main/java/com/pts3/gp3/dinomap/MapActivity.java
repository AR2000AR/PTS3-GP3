package com.pts3.gp3.dinomap;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    private String epoque;

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
            DinoDatabaseParser database = new DinoDatabaseParser(getResources().openRawResource(R.raw.dino));
            for (String[] curentDino : database.getDinoNameListe()) {
                dino.add(database.getDino(curentDino));

            }
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }

        state=seekBar.getProgress();
        epoque=epoqueChoice();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                state = progress;
                epoque=epoqueChoice();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                afficherMarqueur(getDino(),epoque);
                t.setText("");
            }
        });

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                t.setX((float) (motionEvent.getX() - 0.5*(t.getWidth())));
                return false;
            }
        });
    }

    private void mettreAJourTexteIndicateur(int progress) {



    }

    private String epoqueChoice() {
        switch (state) {
            case 0:
                epoqueView.setText(R.string.cambrien);
                t.setText("-540M à \n -500M");
                return getString(R.string.cambrien);
            case 1:
                epoqueView.setText(R.string.ordovicien);
                t.setText("-500M à \n -440M");
                return getString(R.string.ordovicien);
            case 2:
                epoqueView.setText(R.string.silurien);
                t.setText("-440M à \n -410M");
                return getString(R.string.silurien);
            case 3:
                epoqueView.setText(R.string.devonien);
                t.setText("-410M à \n -355M");
                return getString(R.string.devonien);
            case 4:
                epoqueView.setText(R.string.carbonifere);
                t.setText("-355M à \n -295M");
                return getString(R.string.carbonifere);
            case 5:
                epoqueView.setText(R.string.permien);
                t.setText("-295M à \n -250M");
                return getString(R.string.permien);
            case 6:
                epoqueView.setText(R.string.trias);
                t.setText("-250M à \n -200M");
                return getString(R.string.trias);
            case 7:
                epoqueView.setText(R.string.jurassique);
                t.setText("-200M à \n -135M");
                return getString(R.string.jurassique);
            case 8:
                epoqueView.setText(R.string.cretace);
                t.setText("-135M à \n -65M");
                return getString(R.string.cretace);
        }
        return null;
    }

    private void afficherMarqueur(List<Dino> list, String epoque){
        googleMap.clear();
        for (Dino d: list) {
            if (epoque.equals(d.getEpoque())) {
                for(LatLng l : d.getLieuDeDecouverte()) {
                    googleMap.addMarker(new MarkerOptions().position(l).title(""));

                }
            }
        }



    }


    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng center = new LatLng(46.603354, 1.8883335);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(center));

        afficherMarqueur(dino, epoque);
    }

    public List<Dino> getDino() {
        return dino;
    }
}
