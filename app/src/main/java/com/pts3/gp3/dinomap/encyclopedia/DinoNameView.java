package com.pts3.gp3.dinomap.encyclopedia;

import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pts3.gp3.dinomap.R;
import com.pts3.gp3.dinomap.data.DinoDatabaseParser;

public class DinoNameView extends LinearLayout implements View.OnClickListener {
    private TextView nomCommunView;
    private TextView nomScientifiqueView;

    public DinoNameView(Context context, int background, String nomScientifique, String nomCommun) {
        this(context, nomScientifique, nomCommun);
        this.setBackgroundColor(background);
    }

    public DinoNameView(Context context, String nomScientifique, String nomCommun) {
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

        this.addView(nomScientifiqueView);
        this.addView(nomCommunView);

        this.setOnClickListener(this);

    }

    public DinoNameView(Context context, String[] nom) {
        this(context, nom[DinoDatabaseParser.NOM_SCIENTIFIQUE], nom[DinoDatabaseParser.NOM_COMMUN]);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), EncyclopediaActivity.class);
        String[] nom = new String[2];
        nom[DinoDatabaseParser.NOM_COMMUN] = nomCommunView.getText().toString();
        nom[DinoDatabaseParser.NOM_SCIENTIFIQUE] = nomScientifiqueView.getText().toString();
        intent.putExtra("nomDino", nom);

        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        getContext().startActivity(intent);
    }
}
