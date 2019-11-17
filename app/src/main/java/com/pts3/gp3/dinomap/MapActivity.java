package com.pts3.gp3.dinomap;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    GoogleMap googleMap;
    SeekBar seekBar;
    int state;
    Toast toast;
    TextView epoqueView;

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

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                state = progress;
                switch (state) {
                    case 0:
                        epoqueView.setText(R.string.cambrien);
                        Toast.makeText(getApplicationContext(), R.string.cambrien, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        epoqueView.setText(R.string.ordovicien);
                        Toast.makeText(getApplicationContext(), R.string.ordovicien, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        epoqueView.setText(R.string.silurien);
                        Toast.makeText(getApplicationContext(), R.string.silurien, Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        epoqueView.setText(R.string.devonien);
                        Toast.makeText(getApplicationContext(), R.string.devonien, Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        epoqueView.setText(R.string.carbonifere);
                        Toast.makeText(getApplicationContext(), R.string.carbonifere, Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        epoqueView.setText(R.string.permien);
                        Toast.makeText(getApplicationContext(), R.string.permien, Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        epoqueView.setText(R.string.trias);
                        Toast.makeText(getApplicationContext(), R.string.trias, Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        epoqueView.setText(R.string.jurassique);
                        Toast.makeText(getApplicationContext(), R.string.jurassique, Toast.LENGTH_SHORT).show();
                        break;
                    case 8:
                        epoqueView.setText(R.string.cretace);
                        Toast.makeText(getApplicationContext(), R.string.cretace, Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng center = new LatLng(46.603354, 1.8883335);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(center));
    }
}
