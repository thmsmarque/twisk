package twisk.monde;

import twisk.outils.FabriqueNumero;

public class SasSortie extends Activite{
    Monde m;
    public SasSortie(){
        super("Sortie");

    }

    public String toC()
    {

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
