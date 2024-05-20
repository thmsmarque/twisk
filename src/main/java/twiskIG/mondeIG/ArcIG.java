package twiskIG.mondeIG;

import com.google.gson.annotations.Expose;

/**
 * Classe ArcIG
 */
public class ArcIG {

    @Expose
    private PointDeControleIG p1;
    @Expose
    private PointDeControleIG p2;
    private boolean selection;


    /**
     * Constructeur de la classe ARcIG
     *
     * @param p1
     * @param p2
     */
    public ArcIG(PointDeControleIG p1, PointDeControleIG p2){
        this.p1=p1;
        this.p2=p2;
        this.selection=false;

        // ajout des etapes des points en successeurs / predecesseurs :

        EtapeIG e1 = p1.getEtape();
        EtapeIG e2 = p2.getEtape();

        e1.ajouterSuccesseur(e2); //etape 1 = extrémité de la flèche, etape 2 =  pointe de la flèche
        e2.ajouterPredeceseseur(e1);
    }

    /**
     * Indique si l'arc est selectionné
     * @param selection
     */
    public void setSelection(boolean selection) {
        this.selection = selection;
    }

    public boolean getSelection(){ return this.selection; }



    public ArcIG(PointDeControleIG p1){
        this.p1=p1;
        this.p2=null;
    }

    public ArcIG(){
        this.p1=null;
        this.p2=null;
    }

    public void ajouterPDC(PointDeControleIG p2){
        if(p1!=null) {
            this.p2 = p2;
        }else this.p1=p2;
    }

    public PointDeControleIG getP1() {
        return p1;
    }

    public PointDeControleIG getP2() {
        return p2;
    }

    @Override
    public String toString() {
        return "(" + p1 + " ; " + p2+ ")";
    }
}
