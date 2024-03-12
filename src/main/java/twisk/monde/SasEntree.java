package twisk.monde;

import twisk.outils.FabriqueNumero;

public class SasEntree extends Activite {

    Monde m;

    public String toC()
    {
        return null;
    }
    public String getDefineName()
    {
        return "ENTREE";
    }
    public String defineName()
    {
        Etape et = this.getGestionnaire().getListeetapes().iterator().next();
        return "\n#define ENTREE "+ this.getIndiceEtape() + et.defineName();
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
