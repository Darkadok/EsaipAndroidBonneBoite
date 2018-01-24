package org.esaip.cp12017.mloison.bonneboite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Darkadok on 23/01/2018.
 */

public class ResultatActivity extends AppCompatActivity {

    companieAdapter _adapter;
    RecyclerView _recyclerView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
    }
}
