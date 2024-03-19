package twisk;

import twisk.monde.*;
import twisk.outils.FabriqueNumero;
import twisk.simulation.Simulation;

import java.util.Scanner;

public class ClientTwisk {

    public static void main(String[] args) {
        System.out.println("Bienvenu dans cette première ébauche du simmulateur\nQue voulez-vous simuler?\n" +
                "\t0 : Quitter l'application\n" +
                "\t1 : Zoo\n" +
                "\t2 : Gare\n" +
                "\t3 : Fac\n" +
                "\t4 : Route des enfers" +
                "Ton choix :\t");
        Scanner scan = new Scanner(System.in);
        int choix;
        do {
            choix = scan.nextInt();
        }while(choix < 0 || choix > 4);
        Simulation sim = new Simulation();
        switch(choix)
        {
            case 0 : System.out.println("Au revoir!"); break;
            case 1 : System.out.println("Simulation du zoo :\n"); sim.simuler(zoo());break;
            case 2 : System.out.println("Simulation de la gare :\n"); sim.simuler(gare());break;
            case 3 : System.out.println("Simulation de la fac :\n"); sim.simuler(fac());break;
            case 4 : System.out.println("Simulation de la route des enfers :\n"); sim.simuler(enfer());break;
            default: System.out.println("Au revoir!"); break;
        }


    }

    static Monde zoo()
    {

        Monde m = new Monde();
        Etape zoo = new Activite("Zoo");
        Etape parking = new ActiviteRestreinte("Parking");
        Etape guichet1 = new Guichet("guichet",2500);
        Etape guichet2 = new Guichet("guichet",2);
        Etape sortie = new ActiviteRestreinte("Sortie");
        guichet1.ajouterSuccesseur(zoo);
        parking.ajouterSuccesseur(zoo);
        m.ajouter(zoo,parking,guichet1,guichet2,sortie);
        m.aCommeEntree(guichet1,zoo);
        m.aCommeSortie(sortie);
        return m;
    }

    static Monde gare()
    {

        Monde m = new Monde();
        Etape hallGare = new Activite("Gare",10,20);
        Etape parking = new ActiviteRestreinte("Parking",20,30);
        Etape quai1 = new Activite("Quai 1");
        Etape quai2 = new Activite("Quai 2");
        Etape guichet1 = new Guichet("guichet",30);
        Etape guichet2 = new Guichet("guichet",2);
        Etape sortie = new ActiviteRestreinte("Sortie");
        m.getEntree().ajouterSuccesseur(guichet1);
        guichet1.ajouterSuccesseur(quai1);
        guichet2.ajouterSuccesseur(quai2);
        parking.ajouterSuccesseur(hallGare);
        hallGare.ajouterSuccesseur(/*quai1*/quai2);
        //quai1.ajouterSuccesseur(sortie);
        quai2.ajouterSuccesseur(m.getSortie());
        m.ajouter(hallGare,parking,guichet1,guichet2,sortie,quai1,quai2);

        //m.aCommeEntree(hallGare,parking);
        //m.aCommeSortie(sortie);

        return m;
    }

    static Monde fac()
    {
        Monde m = new Monde();
        Etape salle1 = new Activite("Salle_de_classe_1",10,5);
        Etape fileRU = new Guichet("File_attente_du_RU", 3);
        Etape RU = new ActiviteRestreinte("RU",10,4);
        m.getEntree().ajouterSuccesseur(salle1);
        salle1.ajouterSuccesseur(fileRU);
        fileRU.ajouterSuccesseur(RU);
        RU.ajouterSuccesseur(m.getSortie());
        m.ajouter(salle1,fileRU,RU);

        return m;
    }

    static Monde enfer()
    {
        Monde m = new Monde();
        Etape entree = new Activite("Parking");
        Etape fileH = new Guichet("Bureau_Regulateur", 2);
        Etape salle1 = new Activite("Salle_de_classe_1",10,5);
        Etape salle2 = new Activite("Bureau_de_Martine",8,3);
        Etape angoisse = new ActiviteRestreinte("TD_outils_Systeme",10,4);

        entree.ajouterSuccesseur(fileH);

        fileH.ajouterSuccesseur(salle1,salle2);
        salle2.ajouterSuccesseur(angoisse);
        salle1.ajouterSuccesseur(m.getSortie());
        angoisse.ajouterSuccesseur(m.getSortie());

        m.aCommeEntree(entree);
        m.aCommeSortie(angoisse);

        m.ajouter(entree,salle1,fileH,angoisse,salle2);

        return m;

    }
}
