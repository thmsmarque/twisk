package twisk.monde;

import twisk.outils.FabriqueNumero;

public class SasSortie extends Activite{
    Monde m;
    public SasSortie(){
        super("Sortie");

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
