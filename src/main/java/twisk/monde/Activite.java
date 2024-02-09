package main.java.twisk.monde;

import main.java.twisk.outils.FabriqueNumero;

public class Activite extends Etape {

    int temps;
    int ecartTemps;

    /**
     * Constructeur de la classe Activite
     * @param nom
     */
    public Activite(String nom,FabriqueNumero fn) {
        super(nom,fn);

        //Initialisation à 0 par défaut
        this.temps=0;
        this.ecartTemps=0;
    }

    /**
     * Constructeur de la classe Activite
     * @param nom
     * @param temps
     * @param ecartTemps
     */
    public Activite(String nom, int temps, int ecartTemps, FabriqueNumero fn){
        super(nom,fn);

        assert(temps>=0);
        assert(ecartTemps>=0);

        this.temps = temps;
        this.ecartTemps = ecartTemps;
    }

    /**
     * @return l'écart temp de l'activité
     */
    public int getEcartTemps() {
        return ecartTemps;
    }

    /**
     * @return le temps de l'activité
     */
    public int getTemps() {
        return temps;
    }

    /**
     * Vérifie si l'objet est une activité
     * @return true si l'objet est une activite
     */
    @Override
    public boolean estUneActivite() {
        return true;
    }

    @Override
    public String toString() {
        return "Activite{" +
                " nom= " + getNom() +
                " temps=" + temps +
                ", ecartTemps=" + ecartTemps +
                ", gestionnaire=" + gestionnaire +
                '}';
    }
}
