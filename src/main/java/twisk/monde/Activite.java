package main.java.twisk.monde;

public class Activite extends Etape {

    int temps;
    int ecartTemps;
    public Activite() {
        super("Act");
    }

    public Activite(String nom, int temps, int ecartTemps){
        super(nom);
        this.temps = temps;
        this.ecartTemps = ecartTemps;
    }

    @Override
    boolean estUneActivite() {
        return true;
    }
}
