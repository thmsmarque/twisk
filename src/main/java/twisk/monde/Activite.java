package main.java.twisk.monde;

public class Activite extends Etape {

    int temps;
    int ecartTemps;

    /**
     * Constructeur de la classe Activite
     * @param nom
     */
    public Activite(String nom) {
        super(nom);
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

    @Override
    public boolean estUneActivite() {
        return true;
    }

    @Override
    public String toString() {
        return "Activite{" +
                "temps=" + temps +
                ", ecartTemps=" + ecartTemps +
                ", gestionnaire=" + gestionnaire +
                '}';
    }
}
