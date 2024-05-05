package depot;

import twisk.monde.*;
import twisk.simulation.Simulation;

public class ClientMonde {

    public static void main(String[] args) {
        Monde monde = new Monde();

        Activite zoo = new Activite("balade au zoo", 3, 1);
        Activite zoo2 = new Activite("balade", 3, 1);

        Guichet guichet = new Guichet("accès au toboggan", 2);
        Activite tob = new ActiviteRestreinte("toboggan", 2, 1);

       //zoo.ajouterSuccesseur(guichet);
       zoo.ajouterSuccesseur(guichet,zoo2);
       zoo2.ajouterSuccesseur(tob);

        guichet.ajouterSuccesseur(tob);

        //monde.ajouter(zoo, tob, guichet);
        monde.ajouter(zoo,zoo2, tob, guichet);

        monde.aCommeEntree(zoo);
        monde.aCommeSortie(tob);

        Simulation s = new Simulation();
        s.setNbClients(5);
        s.simuler(monde);
    }

}