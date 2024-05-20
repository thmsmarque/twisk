package twiskIG.vues;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.GuichetIG;
import twiskIG.mondeIG.MondeIG;
import twiskIG.outils.TailleComposants;
import twiskIG.vues.ecouteur.EcouteurSelection;
import twiskIG.vues.ecouteur.EcouteurSource;

import java.util.ArrayList;

public class VueGuichetIG extends VueEtapeIG  {

    private HBox box;
    private boolean simEnCours = false;
    private ArrayList<HBox> boxList; //Correspond aux emplacements pour accueillir les clients
    private int nombreDePlacesDansLafile = 15;

    /**
     * Constructeur de la classe VueEtapeIG
     * @param monde
     * @param etape
     */
    public VueGuichetIG(MondeIG monde, GuichetIG etape,boolean simEnCours){
        super(monde,etape,simEnCours);

        this.boxList = new ArrayList<>();


        for(int i = 0; i<nombreDePlacesDansLafile;i++)
        {
            HBox pane = new HBox();
            //pane.getChildren().add(new VueClientIG());
            boxList.add(pane);
        }

        this.box = new HBox();
        TailleComposants taille = TailleComposants.getInstance();

        //Style et taille :

        //etape.setLargeur(taille.getTailleCoteGuichetPlace()* nombreDePlacesDansLaFile);
        this.setPrefSize(etape.getLargeur(), etape.getHauteur());



        //Style et taille :
        for(HBox box : this.boxList){
            box.setPrefSize((double) taille.getTailleCoteGuichetPlace(), (double) taille.getTailleCoteGuichetPlace());
            box.setStyle("-fx-border-color: #e81bd7; -fx-background-color: white ; -fx-border-width: 1px; -fx-border-radius: 4px; -fx-background-radius: 4px;");
            this.box.getChildren().add(box);
        }
        if(!simEnCours) this.setOnMouseClicked(new EcouteurSelection(monde, etape));
        this.getChildren().add(box);
    }

    public ArrayList<HBox> getBoxList()
    {
        return boxList;
    }

    public void setSimEnCours(boolean estSelectionne) {
        this.simEnCours = estSelectionne;
    }

    public int getNombreDePlacesDansLafile()
    {
        return nombreDePlacesDansLafile;
    }

    /**
     * Methode qui définit dans quel sens va la file
     * @return -1 si erreur, 0 si gauche->droite, 1 si droite->gauche
     */
    public int sensFile()
    {
        return 0;
    }

    @Override
    public void reagir() {

    }
}
