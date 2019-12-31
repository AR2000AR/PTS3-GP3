package com.pts3.gp3.dinomap.encyclopedia;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pts3.gp3.dinomap.R;
import com.pts3.gp3.dinomap.data.Dino;
import com.pts3.gp3.dinomap.data.DinoDatabaseParser;
import com.pts3.gp3.dinomap.data.Epoque;

import org.jdom.JDOMException;

import java.io.IOException;
import java.io.InputStream;

public class EncyclopedieActivity extends AppCompatActivity {

    private DinoDatabaseParser database;

    private Dino dino;

    private TextView text_nomSDino;
    private TextView text_nomCDino;
    private TextView text_TailleDino;
    private TextView text_PoidsDino;
    private TextView text_EpoqueDino;
    private TextView text_RegimeDino;

    private ImageView imageDino;
    private LinearLayout descriptionDino;
    private ImageButton boutonUnlock;

    private AssetManager assetManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedie);

        Bundle extras = getIntent().getExtras();
        String nom[] = null;

        if(extras.getStringArray("nomDino") != null){
            nom = extras.getStringArray("nomDino");
        }

        text_nomCDino = findViewById(R.id.text_nomCDino);
        text_nomCDino.setText(nom[0]);

        text_nomSDino = findViewById(R.id.text_nomSDino);
        text_nomSDino.setText(nom[1]);


        text_TailleDino = findViewById(R.id.text_tailleDino);
        text_PoidsDino = findViewById(R.id.text_poidsDino);
        text_EpoqueDino = findViewById(R.id.text_epoqueDino);
        text_RegimeDino = findViewById(R.id.text_regimeDino);
        imageDino = findViewById(R.id.img_dino);
        descriptionDino = findViewById(R.id.descriptionDino);
        boutonUnlock = findViewById(R.id.bouton_lock);

        InputStream inputStream = getResources().openRawResource(R.raw.dino);

        assetManager = getAssets();

        try {
            database = new DinoDatabaseParser(inputStream);
            dino = database.getDino(nom);
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }

    if(!trouverImage(nom[1])){
        try {
            InputStream is = assetManager.open("images/icodinomap.png");
            Bitmap bm = BitmapFactory.decodeStream(is);
            imageDino.setImageBitmap(bm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        /*
        int[] imagesDino =  {R.drawable.alexeyisaurus, R.drawable.arcovenator, R.drawable.cetiosaurus, R.drawable.giganotosaurus, R.drawable.herrerasaurus, R.drawable.isanosaurus, R.drawable.liliensternus, R.drawable.loricatosaurus, R.drawable.mosasaurus, R.drawable.sarahsaurus};

        if(nom[1].equals("Alexeyisaurus")){
            imageDino.setBackgroundResource(imagesDino[0]);
        }

        if(nom[1].equals("Arcovenator")){
            imageDino.setBackgroundResource(imagesDino[1]);
        }
        if(nom[1].equals("Cetiosaurus")){
            imageDino.setBackgroundResource(imagesDino[2]);
        }
        if(nom[1].equals("Giganotosaurus")){
            imageDino.setBackgroundResource(imagesDino[3]);
        }
        if(nom[1].equals("Herrerasaurus")){
            imageDino.setBackgroundResource(imagesDino[4]);
        }
        if(nom[1].equals("Isanosaurus")){
            imageDino.setBackgroundResource(imagesDino[5]);
        }
        if(nom[1].equals("Liliensternus")){
            imageDino.setBackgroundResource(imagesDino[6]);
        }
        if(nom[1].equals("Loricatosaurus")){
            imageDino.setBackgroundResource(imagesDino[7]);
        }
        if(nom[1].equals("Mosasaurus")){
            imageDino.setBackgroundResource(imagesDino[8]);
        }
        if(nom[1].equals("Sarahsaurus")){
            imageDino.setBackgroundResource(imagesDino[9]);
        }
        */

        if(dino.getTaille()[0] == -1 && dino.getTaille()[1] == -1){
            text_TailleDino.setText("Aucune donnée");
        }else if(dino.getTaille()[0] == -1){
            text_TailleDino.setText(dino.getTaille()[1] + "m de haut");
        }else if(dino.getTaille()[1] == -1){
            text_TailleDino.setText(dino.getTaille()[0] + "m de long");
        }else{
            text_TailleDino.setText(dino.getTaille()[0] + "m de long,\n" + " " + dino.getTaille()[1] + "m de haut");
        }
        if(dino.getPoids() == -1){
            text_PoidsDino.setText("Aucune donnée");
        }else{
            text_PoidsDino.setText(dino.getPoids() + " tonne(s)");
        }
        for(Epoque epoque : dino.getEpoques()){
            if(text_EpoqueDino.getText() == ""){
                text_EpoqueDino.setText(epoque.name());
            }else {
                text_EpoqueDino.setText(text_EpoqueDino.getText() + ",\n" + epoque.name());
            }
        }

        text_RegimeDino.setText(dino.getRegimeAlimentaire());

        ImageView returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        boutonUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView modeDeVie = new TextView(v.getContext());
                TextView modeAlimentaire = new TextView(v.getContext());
                TextView commentaire = new TextView(v.getContext());
                if(!dino.getModeDeVie().equals("")){
                    modeDeVie.setText(dino.getModeDeVie());
                    descriptionDino.addView(nouveauTitre("Mode de vie :"));
                    descriptionDino.addView(textStyle(modeDeVie));
                }
                if(!dino.getModeAlimentaire().equals("")){
                    modeAlimentaire.setText(dino.getModeAlimentaire());
                    descriptionDino.addView(nouveauTitre("Mode d'alimentation :"));
                    descriptionDino.addView(textStyle(modeAlimentaire));
                }
                if(!dino.getCommentaire().equals("")){
                    commentaire.setText(dino.getCommentaire());
                    descriptionDino.addView(nouveauTitre("Commentaire :"));
                    descriptionDino.addView(textStyle(commentaire));
                }
                boutonUnlock.setVisibility(View.GONE);
            }
        });

    }

    public boolean trouverImage(String nomDino){
        try {
            String[] imgPath = assetManager.list("images");

            for(String img : imgPath){
                Log.e("path img", img);

                if(img.equals(nomDino.toLowerCase() + ".jpg")){
                    InputStream is = assetManager.open("images/" + img);
                    Log.e("path img", img);
                    Bitmap bm = BitmapFactory.decodeStream(is);
                    imageDino.setImageBitmap(bm);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public TextView nouveauTitre(String text){
        TextView titre = new TextView(this);
        titre.setText(text);
        titre.setTextColor(Color.BLACK);
        titre.setTextSize(22);
        titre.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/trebucbd.ttf"));
        return titre;
    }

    public TextView textStyle(TextView view){
        //view.setTextColor(Color.BLACK);
        view.setTextSize(20);
        view.setGravity(Gravity.FILL);
        view.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/trebuc.ttf"));
        return view;
    }
}
