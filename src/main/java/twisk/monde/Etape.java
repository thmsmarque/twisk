package main.java.twisk.monde;

import java.lang.String;
import java.util.Iterator;

public abstract class Etape implements Iterable<Etape>{
    private String nom;
    GestionnaireEtapes gestionnaire;

    /**
     * Constructeur d'etape
     * @param nom
     */
    public Etape(String nom){
        this.nom = nom;
    }

    /**
     *Ajoute un successeur au gestionnaire d'étape
     * @param successeur
     */
    void AjouterSuccesseur(Etape...successeur){
        this.gestionnaire.ajouter(successeur);
    }

    /**
     * Vérifie si l'objet est une activité ou un guichet
     * @return boolean
     */
    boolean estUneActivite(){
    return false;
    }

    /**
     * Vérifie si l'objet est une activité ou un guichet
     * @return boolean
     */
    boolean estUnGuichet(){
    return false;
    }

    /**
     * Crée l'iterator de la classe Etape
     * @return l'iterator de la classe Etape
     */
    public Iterator<Etape> iterator(){
    return this.gestionnaire.iterator();
    }
}
