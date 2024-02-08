package main.java.twisk.monde;

import java.lang.String;
import java.util.Iterator;

public abstract class Etape {
    private String nom;

    public Etape(String nom){
        this.nom = nom;
    }

    void AjouterSuccesseur(Etape...successeur){

    }

    boolean estUneActivite(){
    return false;
    }

    boolean estUnGuichet(){
    return false;
    }

    Iterator<Etape> iterator(){
    return this.iterator();
    }
}
