package twiskIG.vues;

import javafx.scene.layout.HBox;
import twiskIG.mondeIG.ClientIG;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.MondeIG;
import twiskIG.outils.TailleComposants;
import twiskIG.vues.ecouteur.EcouteurSelection;

public class VueActiviteIG extends VueEtapeIG {

    private HBox box;
    private boolean simEnCours = false;
    /**
     * Constructeur de la classe VueEtapeIG
     *
     * @param monde
     * @param etape
     */
    public VueActiviteIG(MondeIG monde, EtapeIG etape,boolean simEnCours) {
        super(monde, etape);
        this.box = new HBox();
        this.simEnCours=simEnCours;

        //Style et taille :

        this.box.setStyle("-fx-border-color: #e81bd7; -fx-background-color: white ; -fx-border-width: 1px; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        TailleComposants taille = TailleComposants.getInstance();

       this.box.setPrefSize(taille.gettailleHBOXActivite() , taille.gettailleHBOXActivite());

        if(!this.simEnCours) this.setOnMouseClicked(new EcouteurSelection(monde, etape));

        this.getChildren().add(box);

    }

    public HBox getBox()
    {
        return box;
    }

    public void setSimEnCours(boolean estSelectionne) {
        this.simEnCours = estSelectionne;
    }

    @Override
    public void reagir() {

    }
}
