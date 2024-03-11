package twisk.monde;

import twisk.outils.FabriqueNumero;

public class Activite extends Etape {

    int temps;
    int ecartTemps;

    public Activite(String nom){
        super(nom);
        this.temps=0;
        this.ecartTemps=0;
    }

    /**
     * Constructeur de la classe Activite
     * @param nom
     * @param temps
     * @param ecartTemps
     */

    public Activite(String nom, int temps, int ecartTemps){
        super(nom);

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

    @Override
    public String toC() {
        Etape et = this.getGestionnaire().getListeetapes().iterator().next().iterator().next();
        String res = "délai("+this.temps+","+this.ecartTemps+");\n" +
                "transfert("+this.getNom()+","+et.getIndiceEtape()+");\n";
        StringBuilder build = new StringBuilder();
        build.append(res).append(et.toC());
        return res;
    }
}
