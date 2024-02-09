package main.java.twisk.monde;

import main.java.twisk.outils.FabriqueNumero;

public class SasEntree extends Activite {

    Monde m;
    public SasEntree(){
        super("Entree", FabriqueNumero.getInstance());
    }

    @Override
    public String toString() {
        return "SasEntree{" +
                "m=" + m +
                ", temps=" + temps +
                ", ecartTemps=" + ecartTemps +
                ", gestionnaire=" + gestionnaire +
                '}';
    }
}
