package twisk.simulation;

import twisk.monde.Monde;
import twisk.monde.Etape;
import twisk.outils.KitC;

import java.util.Iterator;

public class Simulation {

    KitC kit;

    public Simulation()
    {
        this.kit = new KitC();
        this.kit.creerEnvironnement();
    }

    public void simuler(Monde monde)
    {
        /*System.out.println("Notre monde possède :\n" +
                "\t\tNombre étapes   : " + monde.nbEtapes()+"\n" +
                "\t\tNombre guichets : "+monde.nbGuichets()+"\n");
        Iterator<Etape> it = monde.iterator();
        while(it.hasNext())
        {
            Etape etape = it.next();
            System.out.println(etape.toString());
        }*/

        String fichierC = monde.toC();
        System.out.println(fichierC); // a supprimer quand ça sera bon
        this.kit.creerFichier(fichierC);
        // A faire quand on aura bien /tmp/...
        this.kit.compiler();
        this.kit.construireLaBibliothese();
    }

    public native int[] start_simulation (int nbEtapes, int nbGuichets, int nbClients, int[] tabJetonsGuichet);
    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);
    public native void nettoyage();

}
