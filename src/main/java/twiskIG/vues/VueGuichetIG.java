package twiskIG.vues;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.GuichetIG;
import twiskIG.mondeIG.MondeIG;
import twiskIG.outils.TailleComposants;
import twiskIG.vues.ecouteur.EcouteurSelection;
import twiskIG.vues.ecouteur.EcouteurSource;

import java.util.ArrayList;

public class VueGuichetIG extends VueEtapeIG  {

    private ArrayList<Pane> boxList; //Correspond aux emplacements pour accueillir les clients
    private HBox box;
    private boolean simEnCours = false;


    /**
     * Constructeur de la classe VueEtapeIG
     * @param monde
     * @param etape
     */
    public VueGuichetIG(MondeIG monde, GuichetIG etape){
        super(monde,etape);

        this.boxList = new ArrayList<>();

        for(int i = 0; i<etape.getNbJeton();i++)
        {
            boxList.add(new Pane());
        }

        this.box = new HBox();
        TailleComposants taille = TailleComposants.getInstance();

        //Style et taille :

        //etape.setLargeur(taille.getTailleCoteGuichetPlace()* etape.getNbJeton());
        this.setPrefSize(etape.getLargeur(), etape.getHauteur());



        //Style et taille :
        for(Pane box : this.boxList){
            box.setPrefSize((double) taille.getTailleCoteGuichetPlace(), (double) taille.getTailleCoteGuichetPlace());
            box.setStyle("-fx-border-color: #e81bd7; -fx-background-color: white ; -fx-border-width: 1px; -fx-border-radius: 4px; -fx-background-radius: 4px;");
            this.box.getChildren().add(box);
        }
        this.setOnMouseClicked(new EcouteurSelection(monde, etape));
        this.getChildren().add(box);


    }

    public void setSimEnCours(boolean estSelectionne) {
        this.simEnCours = estSelectionne;
    }

    @Override
    public void reagir() {

    }
}
