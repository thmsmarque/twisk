package twisk.monde;

import twisk.outils.FabriqueNumero;

public class ActiviteRestreinte extends Activite{
    public ActiviteRestreinte(String nom) {
        super(nom);
    }

    public ActiviteRestreinte(String nom, int temps, int ecartTemps){
        super(nom,temps,ecartTemps);
    }

    public String toC()
    {
        Etape et = this.getGestionnaire().getListeetapes().iterator().next();
        //System.out.println("Est ici (activite restreinte) "+ et);
        String res = "delai("+this.temps+","+this.ecartTemps+");\n"
                /*La suite est écrite via le guichet*/;
        return res;

    }
}
