package org.esaip.cp12017.mloison.bonneboite.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import org.esaip.cp12017.mloison.bonneboite.R;
import org.esaip.cp12017.mloison.bonneboite.metier.inseeVille;
import java.util.List;

/**
 * Created by Rolexel on 24/01/2018.
 */

public class AndroidAutoCompleteTextView extends AppCompatActivity implements TextWatcher {

    AutoCompleteTextView myAutoComplete;
    private static String item[]={
            "Liste des villes récupérée depuis le CSV"
    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        myAutoComplete = (AutoCompleteTextView)findViewById(R.id.autoCompleteCP);

        myAutoComplete.addTextChangedListener(this);
        myAutoComplete.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, item));

    }

    public static void loadCP(List<inseeVille> villes){
        for(int i = 0; i<villes.size();i++){
            item[i] = villes.get(i).getCodePostal();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}