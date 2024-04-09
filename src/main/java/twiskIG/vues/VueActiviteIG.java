package twiskIG.vues;

import javafx.scene.layout.HBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;
import twisk.vues.ecouteur.EcouteurSelection;

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
       this.box.setPrefSize(taille.getTailleHBOX() , taille.getTailleHBOX());
        this.setOnMouseClicked(new EcouteurSelection(monde, etape));
        this.getChildren().add(box);


    }

    @Override
    public void reagir() {

    }
}
