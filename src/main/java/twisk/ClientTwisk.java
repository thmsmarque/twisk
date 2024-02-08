package main.java.twisk;

import main.java.twisk.monde.*;
import main.java.twisk.simulation.Simulation;
public class ClientTwisk {

    public static void main(String[] args) {
        Monde monde = new Monde();
        Simulation sim = new Simulation();
        sim.simuler(monde);
    }

    Monde zoo()
    {
        Monde m = new Monde();
        Etape zoo = new Activite("Zoo");
        Etape parking = new ActiviteRestreinte("Parking");
        Etape guichet1 = new Guichet("guichet",2500);
        Etape guichet2 = new Guichet("guichet",2);
        Etape sortie = new ActiviteRestreinte("Sortie");
        guichet1.AjouterSuccesseur(zoo);
        parking.AjouterSuccesseur(zoo);
        m.ajouter(zoo,parking,guichet1,guichet2,sortie);
        m.aCommeEntree(guichet1,zoo);
        m.aCommeSortie(sortie);
        return m;
    }
}
