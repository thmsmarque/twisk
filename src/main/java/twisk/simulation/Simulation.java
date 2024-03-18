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

    public void simuler(Monde monde) {
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
        System.out.println(fichierC);

        this.kit.creerFichier(fichierC);
        // A faire quand on aura bien /tmp/...
        this.kit.compiler();
        this.kit.construireLaBibliothese();

        //Chargement de la bibliothèque où sont définies les fonctions
        System.load("/tmp/twisk/libTwisk.so") ;


        // Ecriture du main.c ---------------------------------
        System.out.println(monde.nbGuichets());
        int nbEtapes = monde.nbEtapes();
        int nbGuichets = monde.nbGuichets();
        int nbClients = 10; // TEMPORAIRE
        int[] tabJetonsGuichet = new int[nbGuichets];
        tabJetonsGuichet[0] = 2;

        //lancement simulation :
        int[] resSim;
        resSim = start_simulation(nbEtapes,nbGuichets,nbClients,tabJetonsGuichet);

        //affichage des numéros de processus :
        System.out.println("Les clients :");
        for(int i=0; i<nbClients;i++){
            System.out.print(resSim[i]+ " ");
        }
        System.out.println("\n ");

        int[] posClients = ou_sont_les_clients(nbEtapes,nbClients);

        //On affiche où sont les clients :
        do {
            posClients = ou_sont_les_clients(nbEtapes,nbClients);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int etapeActuel = 0;
            for(int i =0;i<nbEtapes;i++) {
                if (etapeActuel != 1) {
                    System.out.println("Etape " + i + "Clients" + posClients[i + nbClients * i]);
                    int nbClientDansAct = posClients[i + nbClients * i];
                    for (int j = i + nbClients * i + 1; j < i + nbClients * i + 1 + nbClientDansAct; j++) {
                        //j est initié à la position dans le tableau posClients où se trouve le premier client de l'étape
                        //S'il n'y a aucun client dans l'étape alors j >= à la condition qui sort de la boucle
                        System.out.print(posClients[j]+ " ");
                    }
                    System.out.println(" ");
                }
                etapeActuel++;
            }

            System.out.println("Etape 1 client(s) :" + posClients[nbClients+1]);
            int nbClientDansAct = posClients[nbClients+1];
            for(int j = nbClients+2; j<nbClients+2+nbClientDansAct;j++){

                //j est initié à la position dans le tableau posClients où se trouve le premier client de l'étape
                //S'il n'y a aucun client dans l'étape alors j >= à la condition qui sort de la boucle
                System.out.print(posClients[j] + " ");
            }
            System.out.println(" ");
            etapeActuel++;
            System.out.println("\n \n \n");
        }while(posClients[nbClients+1] != nbClients); //while tous les clients ne sont pas dans le sasSortie donc posClient[nbAct-1 + nbClient * nbAct-1] == nbClient

        nettoyage();
    }

    public native int[] start_simulation (int nbEtapes, int nbGuichets, int nbClients, int[] tabJetonsGuichet);
    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);
    public native void nettoyage();

}
