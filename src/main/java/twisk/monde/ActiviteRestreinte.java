package twisk.monde;

import twisk.outils.FabriqueNumero;

public class ActiviteRestreinte extends Activite{
    public ActiviteRestreinte(String nom, FabriqueNumero fn) {
        super(nom,fn);
    }

    public ActiviteRestreinte(String nom, int temps, int ecartTemps){
        super(nom,temps,ecartTemps);
    }

    public ActiviteRestreinte(String nom, int temps, int ecartTemps,FabriqueNumero fn){
        super(nom,temps,ecartTemps,fn);
    }
}
