package twisk.simulation;

import twisk.monde.Activite;
import twisk.monde.Etape;

public class Client {
    private int numeroClient;
    private int rang;

    Etape etape;

    public Client(int numero){
        this.numeroClient=numero;
        rang = 0;
        Etape fantome = new Activite("temporaire"); // A voir pour la suite
        etape= fantome;
    }

    public void allerA(Etape etape, int rang){
        this.etape=etape;
        this.rang=rang;
    }

    @Override
    public String toString() {
        return " Client : " + numeroClient + " Rang : " + rang + " Etape : "+ etape.getIndiceEtape() + " \n";
    }

    public Etape getEtape() {
        return etape;
    }

    public int getNumeroClient() {
        return numeroClient;
    }

    public int getRang() {
        return rang;
    }
}
