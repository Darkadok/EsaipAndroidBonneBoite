package org.esaip.cp12017.mloison.bonneboite.metier;

/**
 * Created by Darkadok on 25/01/2018.
 */

public class SingletonToken {

        private static final SingletonToken INSTANCE = new SingletonToken();
        private String value;
        // Private constructor prevents instantiation from other classes
        private SingletonToken() {}

        public static SingletonToken getInstance() {
            return INSTANCE;
        }

    public String getValue() {
        return INSTANCE.value;
    }

    public void setValue(String value) {
        INSTANCE.value = value;
    }
}
