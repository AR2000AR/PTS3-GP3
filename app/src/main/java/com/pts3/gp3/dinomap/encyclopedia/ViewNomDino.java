package com.pts3.gp3.dinomap.encyclopedia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.pts3.gp3.dinomap.R;
import com.pts3.gp3.dinomap.data.DinoDatabaseParser;

/*public class ViewNomDino extends LinearLayout implements View.OnClickListener {
    private TextView nomCommunView;
    private TextView nomScientifiqueView;

    public ViewNomDino(Context context, int background, String nomScientifique, String nomCommun) {
        this(context, nomScientifique, nomCommun);

        this.setBackground(getResources().getDrawable(R.drawable.contourbord));
        this.setBackgroundColor(background);
    }

    public ViewNomDino(Context context, String nomScientifique, String nomCommun) {
        super(context);
        this.setOrientation(LinearLayout.VERTICAL);
        nomCommunView = new TextView(this.getContext());
        nomCommunView.setText(nomCommun);
        nomScientifiqueView = new TextView(this.getContext());
        nomScientifiqueView.setText(nomScientifique);

        nomScientifiqueView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        nomCommunView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        nomScientifiqueView.setTextColor(context.getColor(R.color.colorBlack));
        nomCommunView.setTextColor(context.getColor(R.color.colorBlack));

        nomCommunView.setTypeface(Typeface.createFromAsset(this.getContext().getAssets(), "fonts/trebuc.ttf"));
        nomScientifiqueView.setTypeface(Typeface.createFromAsset(this.getContext().getAssets(), "fonts/trebuc.ttf"));

        this.addView(nomScientifiqueView);
        this.addView(nomCommunView);

        this.setOnClickListener(this);

    }

    public ViewNomDino(Context context, String[] nom) {
        this(context, nom[DinoDatabaseParser.NOM_SCIENTIFIQUE], nom[DinoDatabaseParser.NOM_COMMUN]);
    }
*/
public class ViewNomDino extends ConstraintLayout implements View.OnClickListener {

    private ConstraintLayout innerLayout;
    private String nomCommun;
    private String nomScientifique;

    public ViewNomDino(Context context, int background, String nomScientifique, String nomCommun) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        innerLayout = (ConstraintLayout) inflater.inflate(R.layout.view_nom_dino, this);
        this.nomCommun = nomCommun;
        this.nomScientifique = nomScientifique;
        TextView nomCommunView = innerLayout.findViewById(R.id.nomCom);
        nomCommunView.setText(nomCommun);
        TextView nomScientifiqueView = innerLayout.findViewById(R.id.nomSc);
        nomScientifiqueView.setText(nomScientifique);

        innerLayout.setBackgroundColor(background);
        this.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), EncyclopedieActivity.class);
        String[] nom = new String[2];
        nom[DinoDatabaseParser.NOM_COMMUN] = nomCommun;
        nom[DinoDatabaseParser.NOM_SCIENTIFIQUE] = nomScientifique;
        intent.putExtra("nomDino", nom);

        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        getContext().startActivity(intent);
    }

    public String[] getDinoId() {
        String[] nom = new String[2];
        nom[DinoDatabaseParser.NOM_COMMUN] = nomCommun;
        nom[DinoDatabaseParser.NOM_SCIENTIFIQUE] = nomScientifique;
        return nom;
    }

    public void setUnlocked(boolean unlocked) {
        ImageView img = innerLayout.findViewById(R.id.coin);
        if (unlocked) {
            img.setImageResource(R.drawable.cadenaouvert);
        } else {
            img.setImageResource(R.drawable.cadenafermee);
        }
    }
}
