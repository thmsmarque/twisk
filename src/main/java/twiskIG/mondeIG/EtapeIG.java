package twiskIG.mondeIG;

import twiskIG.exceptions.TwiskException;
import twiskIG.outils.FabriqueIdentifiant;
import twiskIG.outils.TailleComposants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * Classe abstraite ActiviteIG
 */
public abstract class EtapeIG implements Iterable<PointDeControleIG> {
    private String nom;
    private String identifiant;
    private int posX;
    private int posY;
    private int largeur;
    private int hauteur;
    private int delai;
    private int ecart;
    private boolean selection;
    private ArrayList<PointDeControleIG> pointsdeC;
    private boolean estUneEntree;
    private boolean estUneSortie;
    HashMap<String,EtapeIG> predecesseurs;
    HashMap<String,EtapeIG> successeurs;


    /**Constructeur de la classe abstraite EtapeIG
     * @param nom
     * @param larg
     * @param haut
     */
    public EtapeIG(String nom, int larg, int haut)
    {
        this.nom = nom;
        this.largeur = larg;
        this.hauteur = haut;

        //valeur par défaut
        this.delai = 5;
        this.ecart=5;

        FabriqueIdentifiant instanceIdentifiant = FabriqueIdentifiant.getInstance();
        this.identifiant = instanceIdentifiant.getIdentifiantEtape();

        //POSITION INITIALE :

        TailleComposants taille = TailleComposants.getInstance();

        Random random = new Random();
        this.posX = random.nextInt(taille.getTailleFenetre());
        this.posY = random.nextInt(taille.getTailleFenetre());
        this.pointsdeC = new ArrayList<>();

        for(int i =0;i<4;i++){
            this.pointsdeC.add(new PointDeControleIG(0,0,this));
        }

        setPointsdeC();

        this.selection = false;
        this.estUneEntree = false;
        this.estUneSortie = false;

        // Initialisation des successeur / predecesseur
        this.successeurs = new HashMap<>();
        this.predecesseurs = new HashMap<>();
    }

    /**
     * Ajoute des successeurs à la hashmap des successeurs de l'étape
     * @param e
     */
    public void ajouterSuccesseur(EtapeIG e){
    this.successeurs.put(e.getIdentifiant(),e);
    }

    /**
     * Ajoute des predecesseurs à la hashmap des predecesseur de l'étape
     * @param e
     */
    public void ajouterPredeceseseur(EtapeIG e){
    this.predecesseurs.put(e.getIdentifiant(),e);
    }

    public void supprimerSuccesseurs(EtapeIG e){
        this.successeurs.remove(e.getIdentifiant());
    }

    public void supprimerPredesseurs(EtapeIG e){
        this.predecesseurs.remove(e.getIdentifiant());
    }

    public void clearAllSuccPrec(){
        this.successeurs.clear();
        this.predecesseurs.clear();
    }

    /**
     * Indique si l'activité est selectionné
     * @param selection
     */
    public void setSelection(boolean selection) {
        this.selection = selection;
    }

    public void setPointsdeC(){

            //Point du haut
            this.pointsdeC.get(0).setPosX(this.posX+this.largeur/2);
            this.pointsdeC.get(0).setPosY(this.posY);

            //Point de droite
            this.pointsdeC.get(1).setPosX(this.posX+this.largeur);
            this.pointsdeC.get(1).setPosY(this.posY+this.hauteur/2);

            //Point de gauche
            this.pointsdeC.get(2).setPosX(this.posX);
            this.pointsdeC.get(2).setPosY(this.posY+this.hauteur/2);

            //Point du bas
            this.pointsdeC.get(3).setPosX(this.posX+this.largeur/2);
            this.pointsdeC.get(3).setPosY(this.posY+this.hauteur);



    }

    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * Renvoie le nombre de PointDeControleIG dans la classe
     * @return le nombre de Point De Controle (4)
     * Pour test
     */
    public int nbPointDeControle(){
        return pointsdeC.size();
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getNom() {
        return nom;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean getSelection(){ return this.selection; }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean getEstUneEntree(){
        return this.estUneEntree;
    }

    public boolean getEstUneSortie(){
        return this.estUneSortie;
    }

    public void setEstUneEntree(boolean estUneEntree) {
        this.estUneEntree = estUneEntree;
    }

    public void setEstUneSortie(boolean estUneSortie) {
        this.estUneSortie = estUneSortie;
    }

    public void setDelai(int delai) throws TwiskException {
        if(delai<=0){
            throw new TwiskException("La valeur du délai est invalide");

        } else this.delai = delai;
    }

    public void setLargeur(int larg)
    {
        this.largeur = larg;
    }
    public void setHauteur(int haut)
    {
        this.hauteur= haut;
    }

    /**
     * Méthode abstraite qui renvoi un booléen selon si l'étape est une sous-classe ActiviteIG ou non
     * @return est une activite ou non
     */
    public abstract Boolean estActivite();
    public abstract Boolean estGuichet();

    public int getDelai() {
        return delai;
    }

    public void setEcart(int ecart) throws TwiskException {
        if(ecart>this.delai) {
            throw new TwiskException("L'ecart entre est invalide");
        } else this.ecart = ecart;
    }

    @Override
    public String toString() {
        return "Etape : "+
                nom + '\'' +
                ", n°'" + identifiant + '\'' + "\n Successeurs :" + successeurs.size() + " \n Predecesseurs : " + predecesseurs.size() + "\n =================" ;
    }

    @Override
    public Iterator<PointDeControleIG> iterator() {
        return pointsdeC.iterator();
    }
}
