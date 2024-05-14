package twisk.simulation;

import javafx.concurrent.Task;
import twisk.monde.Monde;
import twisk.monde.Etape;
import twisk.monde.Guichet;
import twisk.outils.FabriqueNumero;
import twisk.outils.FabriqueNumeroLibTwisk;
import twisk.outils.KitC;
import twisk.outils.ThreadsManager;
import twiskIG.mondeIG.MondeIG;
import twiskIG.mondeIG.SujetObserve;

import java.util.Iterator;

public class Simulation extends SujetObserve {

    KitC kit;
    private int nbClients;
    private GestionnaireClients g;
    public Simulation()
    {
        this.kit = new KitC();
        this.kit.creerEnvironnement();
        this.nbClients=5; //de base
    }

    public void simuler(Monde monde, MondeIG mondeIG) {

        mondeIG.switchEtatSim();
        System.out.println("Lancement de la simulation logique");
                GestionnaireClients g = monde.getG();
                KitC kit = new KitC();
                String fichierC = monde.toC();
                System.out.println(fichierC);
                kit.creerFichier(fichierC);
                kit.compiler();
                kit.construireLaBibliothese();

                //Chargement de la bibliothèque où sont définies les fonctions
                System.load("/tmp/twisk/libTwisk"+ FabriqueNumeroLibTwisk.getInstance().getNumero()+".so") ;
                FabriqueNumeroLibTwisk.getInstance().incrementer();


                // Ecriture du main.c ---------------------------------
                int nbEtapes = monde.nbEtapes();
                int nbGuichets = monde.nbGuichets();
                int nbClients = 10;
                int[] tabJetonsGuichet = new int[nbGuichets];
                for(Etape et : monde.getLesEtapes())
                {
                    if(et.estUnGuichet())
                    {
                        Guichet guich = (Guichet)et;
                        tabJetonsGuichet[guich.getSemaphore()-1] = guich.getNbJetons();
                    }
                }

                for(int i=0; i<tabJetonsGuichet.length;i++)
                {
                    System.out.print((i+1) + " : "+tabJetonsGuichet[i]+ "\t");
                }
                //lancement simulation : -----------------------------
                int[] resSim;
                resSim = start_simulation(nbEtapes,nbGuichets,nbClients,tabJetonsGuichet);
                //Gestionnaire de clients :
                g.setClients(resSim);


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
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    int etapeActuel = 0;
                    for(int i =0;i<nbEtapes;i++) {
                        if (etapeActuel != monde.getSortie().getIndiceEtape()) {
                            System.out.print("Etape " + monde.getLesEtapes().getListeetapes().get(i).getNom() + " | Nombre de personnes : " + posClients[i + nbClients * i] + "  => ");



                            int nbClientDansAct = posClients[i + nbClients * i];
                            int rang = 0;
                            for (int j = i + nbClients * i + 1; j < i + nbClients * i + 1 + nbClientDansAct; j++) {
                                //j est initié à la position dans le tableau posClients où se trouve le premier client de l'étape
                                //S'il n'y a aucun client dans l'étape alors j >= à la condition qui sort de la boucle
                                System.out.print(posClients[j]+ " ");


                                //On met à jour le gestionnaire de clients :
                                g.allerA(posClients[j],monde.getLesEtapes().getListeetapes().get(i),rang);
                                rang++;
                            }
                            System.out.println(" ");
                        }
                        etapeActuel++;
                    }

                    notifierObservateurs();
                    System.out.print("Etape Sortie client(s) : " + " | Nombre de personnes : " + posClients[monde.getSortie().getIndiceEtape()*nbClients+1] + " => ");
                    int nbClientDansAct = posClients[monde.getSortie().getIndiceEtape()*nbClients+1];



                    //System.out.println("Nb clients act" + nbClientDansAct);
                    //System.out.println("Boucle : " + nbClients+2+nbClientDansAct);
                    for(int j = nbClients+2; j<nbClients+2+nbClientDansAct;j++){

                        //j est initié à la position dans le tableau posClients où se trouve le premier client de l'étape
                        //S'il n'y a aucun client dans l'étape alors j >= à la condition qui sort de la boucle
                        System.out.print(posClients[j] + " ");
                        g.allerA(posClients[j],monde.getSortie(),monde.getSortie().getIndiceEtape());

                    }
                    System.out.println(" ");
                    etapeActuel++;
                    System.out.println("\n");
                }while(posClients[monde.getSortie().getIndiceEtape()*nbClients+1] != nbClients && mondeIG.getEnCoursDeSim()); //while tous les clients ne sont pas dans le sasSortie donc posClient[nbAct-1 + nbClient * nbAct-1] == nbClient
                System.out.println("La simulation est terminée");
                if(mondeIG.getEnCoursDeSim()) {
                    //La simulation n'a pas été interrompu en cours de sim
                    mondeIG.switchEtatSim();
                }else
                {
                    //La simulation a été interrompu avant la fin
                        kit.detruireLesProcessus(resSim);
                }
                mondeIG.notifierObservateurs();
                nettoyage();
                FabriqueNumero.getInstance().reset();

        ThreadsManager.getInstance().detruireTout();


    }

    public void setNbClients(int nbClients){
        System.out.println("Le nombre de client est à : "+nbClients);
        this.nbClients=nbClients; //de base
    }

    public GestionnaireClients getG() {
        return g;
    }

    public native int[] start_simulation (int nbEtapes, int nbGuichets, int nbClients, int[] tabJetonsGuichet);
    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);
    public native void nettoyage();

}
