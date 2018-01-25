package org.esaip.cp12017.mloison.bonneboite;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.esaip.cp12017.mloison.bonneboite.activity.MainActivity;
import org.esaip.cp12017.mloison.bonneboite.metier.companie;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void Ville_IsCorrect() throws Exception {
        JSONObject jo = new JSONObject("{\n" +
                "      \"address\": \"CAISSE D'EPARGNE ET DE PREVOYANCE DE L, CAISSE D'EPARGNE, Service des ressources humaines, 5 PARVIS DES DROITS DE L HOMME, 57000 METZ\",\n" +
                "      \"alternance\" : false,\n" +
                "      \"city\": \"METZ\",\n" +
                "      \"contact_mode\": \"Envoyer un CV et une lettre de motivation\",\n" +
                "      \"distance\": 0,\n" +
                "      \"headcount_text\": \"250 à 499 salariés\",\n" +
                "      \"lat\": 49.11613,\n" +
                "      \"lon\": 6.1727,\n" +
                "      \"naf\": \"7010Z\",\n" +
                "      \"naf_text\": \"Activités des sièges sociaux\",\n" +
                "      \"name\": \"CAISSE D'EPARGNE\",\n" +
                "      \"siret\": \"77561862203725\",\n" +
                "      \"stars\": 4,\n" +
                "      \"url\": \"http://labonneboite.pole-emploi.fr/77561862203725/details\"\n" +
                "    }");
        companie c = new companie(jo);

        assertEquals("CAISSE D'EPARGNE", c.getName());
        assertEquals("77561862203725", c.getSiret());
    }
}
