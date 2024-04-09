package twiskIG.mondeIG;

import twisk.exceptions.TwiskException;
import twisk.outils.FabriqueIdentifiant;
import twisk.outils.TailleComposants;

import java.util.ArrayList;
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

    /**Constructeur de la classe abstraite EtapeIG
     * @param nom
     * @param larg
     * @param haut
     */
    public EtapeIG(String nom, int larg, int haut) {
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

    }

    /**
     * Indique si l'activité est selectionné
     * @param selection
     */
    public void setSelection(boolean selection) {
        this.selection = selection;
    }

    public void setPointsdeC(){
        this.pointsdeC.get(0).setPosX(this.posX+this.largeur/2);
        this.pointsdeC.get(0).setPosY(this.posY);

        this.pointsdeC.get(1).setPosX(this.posX+this.largeur);
        this.pointsdeC.get(1).setPosY(this.posY+this.hauteur/2);

        this.pointsdeC.get(2).setPosX(this.posX);
        this.pointsdeC.get(2).setPosY(this.posY+this.hauteur/2);

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
                ", n°'" + identifiant + '\'' + "\n";
    }

    @Override
    public Iterator<PointDeControleIG> iterator() {
        return pointsdeC.iterator();
    }
}
