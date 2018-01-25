package org.esaip.cp12017.mloison.bonneboite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

/**
 * Created by Rolexel on 24/01/2018.
 */

public class AndroidAutoCompleteTextView extends AppCompatActivity implements TextWatcher {

    AutoCompleteTextView myAutoComplete;
    String item[]={
            "Liste des villes récupérée depuis le CSV"
    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAutoComplete = (AutoCompleteTextView)findViewById(R.id.rechercheVille);

        myAutoComplete.addTextChangedListener(this);
        myAutoComplete.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, item));

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