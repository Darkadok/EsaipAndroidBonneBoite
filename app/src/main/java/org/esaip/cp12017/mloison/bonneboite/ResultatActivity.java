package org.esaip.cp12017.mloison.bonneboite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by Darkadok on 23/01/2018.
 */

public class ResultatActivity extends AppCompatActivity {

    companieAdapter _adapter;
    RecyclerView _recyclerView;
    List<companie> _companies;

    public ResultatActivity(List<companie>companies) {
        _companies = companies;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        //creation du RecyclerView
        _recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        _recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //creation du companieAdapter
        _adapter = new companieAdapter(_companies);
        _recyclerView.setAdapter(_adapter);

    }
}
