package com.pts3.gp3.dinomap.encyclopedia;

import android.content.Context;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pts3.gp3.dinomap.R;

public class DinoNameView extends LinearLayout {
    TextView nomCommunView;
    TextView nomScientifiqueView;

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
    }
}
