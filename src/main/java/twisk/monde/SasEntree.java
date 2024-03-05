package twisk.monde;

import twisk.outils.FabriqueNumero;

public class SasEntree extends Activite {

    Monde m;

    public String toC()
    {
        return null;
    }
    public SasEntree(){
        super("Entree");
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
