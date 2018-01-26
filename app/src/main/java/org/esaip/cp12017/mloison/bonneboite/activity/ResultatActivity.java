package org.esaip.cp12017.mloison.bonneboite.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.esaip.cp12017.mloison.bonneboite.R;
import org.esaip.cp12017.mloison.bonneboite.metier.companie;
import org.esaip.cp12017.mloison.bonneboite.metier.companieAdapter;

import java.util.List;

/**
 * Created by Darkadok on 23/01/2018.
 */

public class ResultatActivity extends AppCompatActivity {

    companieAdapter _adapter;
    RecyclerView _recyclerView;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        TextView t = (TextView) findViewById(R.id.textView1);
        t.setText(RechercheActivity.companies.size()+" entreprises trouvées :");

        //creation du RecyclerView
        _recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        _recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //creation du companieAdapter
        _adapter = new companieAdapter(RechercheActivity.companies);
        _recyclerView.setAdapter(_adapter);

    }

    /* Cliquer sur un bouton pour voir la fiche de l'entreprise (par Intent)
    Button b_browser = (Button)findViewById(R.id.button_browser);
b_browser.setOnClickListener( new OnClickListener() {
        @Override
        public void onClick(View v) {
            // 1. Appeler une URL web
            String url = "http://www.ltm.fr";
            Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
            startActivity(intent);
        }
    });*/
}
