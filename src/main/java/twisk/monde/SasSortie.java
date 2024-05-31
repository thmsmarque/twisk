package twisk.monde;

import twisk.outils.FabriqueNumero;

public class SasSortie extends Activite{
    Monde m;
    public SasSortie(){
        super("Sortie");

    }

    public String defineName()
    {
        return "\n#define SORTIE "+ this.getIndiceEtape();
    }

    public String getDefineName()
    {
        return "SORTIE";
    }

    public String toC()
    {
        return "\n";
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
