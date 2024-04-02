package twisk.simulation;

import twisk.monde.Etape;

public class Client {
    private int numeroClient;
    private int rang;

    Etape etape;

    public Client(int numero){
        this.numeroClient=numero;
        rang = 0;
        etape= null;
    }

    public void allerA(Etape etape, int rang){
        this.etape=etape;
        this.rang=rang;
    }

    @Override
    public String toString() {
        return " Client : " + numeroClient + " Rang : " + rang + " Etape : \n" + etape.getIndiceEtape();
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
