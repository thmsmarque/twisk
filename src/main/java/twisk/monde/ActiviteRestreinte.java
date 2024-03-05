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
        String res = "delai("+this.temps+","+this.ecartTemps+");\n" +
                "transfert("+this.getIndiceEtape()+","
                + this.getGestionnaire().getListeetapes().get(this.getIndiceEtape()-1).toC() + ");\n" ;
        return res;

    }
}
