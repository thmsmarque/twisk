package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.lang.String;
import java.util.Iterator;

public abstract class Etape implements Iterable<Etape>{
    private String nom;
    GestionnaireEtapes gestionnaire;

    int indiceEtape;


    /**
     * Constructeur de la classe etape
     * @param nom
     */
    public Etape(String nom){
        this.nom = nom;
        gestionnaire = new GestionnaireEtapes();
        indiceEtape = FabriqueNumero.getInstance().getNumeroEtape();
    }

    /**
     *
     * @return nom de l'etape
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return gestionne d'étapes de l'etape
     */
    public GestionnaireEtapes getGestionnaire() {
        return gestionnaire;
    }

    /**
     *Ajoute un successeur au gestionnaire d'étape
     * @param successeur
     */
    public void ajouterSuccesseur(Etape...successeur){
        this.gestionnaire.ajouter(successeur);
    }

    /**
     * Vérifie si l'objet est une activité ou un guichet
     * @return boolean
     */
    public boolean estUneActivite(){
    return false;
    }

    /**
     * Vérifie si l'objet est une activité ou un guichet
     * @return boolean
     */
    public boolean estUnGuichet(){
    return false;
    }

    /**
     * Crée l'iterator de la classe Etape
     * @return l'iterator de la classe Etape
     */
    public Iterator<Etape> iterator(){
    return this.gestionnaire.iterator();
    }

    @Override
    public String toString() {
        return "Etape{" +
                "nom='" + nom + '\'' +
                ", gestionnaire=" + gestionnaire +
                '}';
    }

    /**
     * retourne l'entier represantant l'indice de l'étape
     * @return l'indice de l'étape
     */
    public int getIndiceEtape()
    {
        return indiceEtape;
    }

    public abstract String toC();

    public abstract String defineName();
    public abstract String getDefineName();



}
