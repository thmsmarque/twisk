package twiskIG.vues;

import javafx.scene.layout.HBox;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.MondeIG;
import twiskIG.outils.TailleComposants;
import twiskIG.vues.ecouteur.EcouteurSelection;

public class VueActiviteIG extends VueEtapeIG {

    HBox box;
    /**
     * Constructeur de la classe VueEtapeIG
     *
     * @param monde
     * @param etape
     */
    public VueActiviteIG(MondeIG monde, EtapeIG etape) {
        super(monde, etape);
        this.box = new HBox();

        //Style et taille :

        this.box.setStyle("-fx-border-color: #e81bd7; -fx-background-color: white ; -fx-border-width: 1px; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        TailleComposants taille = TailleComposants.getInstance();
       this.box.setPrefSize(taille.gettailleHBOXActivite() , taille.gettailleHBOXActivite());
        this.setOnMouseClicked(new EcouteurSelection(monde, etape));
        this.getChildren().add(box);


    }

    @Override
    public void reagir() {

    }
}
