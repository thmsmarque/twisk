package main.java.twisk.monde;

import main.java.twisk.outils.FabriqueNumero;

public class SasSortie extends Activite{
    Monde m;
    public SasSortie(){
        super("Sortie", FabriqueNumero.getInstance());

    }

    @Override
    public String toString() {
        return "SasSortie{" +
                "m=" + m +
                ", temps=" + temps +
                ", ecartTemps=" + ecartTemps +
                ", gestionnaire=" + gestionnaire +
                '}';
    }
}
