package main.java.twisk.monde;

import main.java.twisk.outils.FabriqueNumero;

public class ActiviteRestreinte extends Activite{
    public ActiviteRestreinte(String nom, FabriqueNumero fn) {
        super(nom,fn);
    }

    public ActiviteRestreinte(String nom, int temps, int ecartTemps,FabriqueNumero fn){
        super(nom,temps,ecartTemps,fn);
    }
}
