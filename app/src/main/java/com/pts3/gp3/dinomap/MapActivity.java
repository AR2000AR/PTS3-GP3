package com.pts3.gp3.dinomap;

import android.os.Bundle;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        seekBar = findViewById(R.id.seekBarEpoque);
        epoqueView = findViewById(R.id.epoqueView);
        dino = new ArrayList<>();
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
            }
        });
    }

    private String epoqueChoice() {
        switch (state) {
            case 0:
                epoqueView.setText(R.string.cambrien);
                return getString(R.string.cambrien);
            case 1:
                epoqueView.setText(R.string.ordovicien);
                return getString(R.string.ordovicien);
            case 2:
                epoqueView.setText(R.string.silurien);
                return getString(R.string.silurien);
            case 3:
                epoqueView.setText(R.string.devonien);
                return getString(R.string.devonien);
            case 4:
                epoqueView.setText(R.string.carbonifere);
                return getString(R.string.carbonifere);
            case 5:
                epoqueView.setText(R.string.permien);
                return getString(R.string.permien);
            case 6:
                epoqueView.setText(R.string.trias);
                return getString(R.string.trias);
            case 7:
                epoqueView.setText(R.string.jurassique);
                return getString(R.string.jurassique);
            case 8:
                epoqueView.setText(R.string.cretace);
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
