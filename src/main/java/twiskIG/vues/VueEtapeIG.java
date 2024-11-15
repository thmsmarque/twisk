package twiskIG.vues;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.MondeIG;
import twiskIG.outils.TailleComposants;
import twiskIG.vues.ecouteur.EcouteurSource;

/**
 * Classe abstraite Vue etape IG
 */
public abstract class VueEtapeIG extends VBox implements Observateur  {

    private EtapeIG etape;

    private Label label;

    private MondeIG monde;

    private boolean simEnCours = false;




    /**
     * Constructeur de la classe VueEtapeIG
     * @param monde
     * @param etape
     */
    public VueEtapeIG(MondeIG monde, EtapeIG etape,boolean simEnCours){
        this.etape = etape;
        this.monde = monde;
        if(etape.estGuichet()) {this.label = new Label(etape.getNom() + " : " + etape.getNbJeton() + " jetons");}
        else {this.label = new Label(etape.getNom());}
        this.simEnCours = simEnCours;
        label.setStyle("-fx-text-fill: black; -fx-font-weight: bold");
        setPadding(new Insets(5));
        HBox h = new HBox();
        h.getChildren().add(label);
       if(etape.getEstUneSortie()) {
           Image imagesortie = new Image(getClass().getResourceAsStream("/images/sortie.jpg"));
           ImageView iconsortie = new ImageView(imagesortie);
           iconsortie.setFitHeight(10);
           iconsortie.setFitWidth(10);
           h.getChildren().addAll(iconsortie);
       }

        if(etape.getEstUneEntree()) {
            Image imagesortie = new Image(getClass().getResourceAsStream("/images/entree.jpg"));
            ImageView iconsortie = new ImageView(imagesortie);
            iconsortie.setFitHeight(10);
            iconsortie.setFitWidth(10);
            h.getChildren().addAll(iconsortie);
        }


        //REpositionner + style :
        relocate(etape.getPosX(), etape.getPosY());
        if(etape.estActivite()) {
            if (etape.getSelection()) {
                setStyle("-fx-border-color: #1baae8;  -fx-border-radius: 8px; -fx-border-width: 2px; -fx-background-color: rgb(108,108,108); -fx-background-radius: 8px;");
            } else {
                setStyle("-fx-border-color: #1baae8; -fx-background-color: #ffffff ; -fx-border-width: 2px; -fx-border-radius: 8px; -fx-background-radius: 8px;");
            }
        }
        if(etape.estGuichet()) {
            if (etape.getSelection()) {
                setStyle("-fx-border-color: #31bf0c;  -fx-border-radius: 8px; -fx-border-width: 2px; -fx-background-color: rgb(108,108,108); -fx-background-radius: 8px;");
            } else {
                setStyle("-fx-border-color: #31bf0c; -fx-background-color: #ffffff ; -fx-border-width: 2px; -fx-border-radius: 8px; -fx-background-radius: 8px;");
            }
        }
        TailleComposants taille = TailleComposants.getInstance();
        setPrefSize(taille.gettailleVBOXActivite()*2, taille.gettailleVBOXActivite());

        h.setStyle("-fx-alignment: CENTER");
        getChildren().add(h);

        //Drag n drop :
        if(!this.simEnCours) this.setId(etape.getIdentifiant());
        if(!this.simEnCours) setOnDragDetected(new EcouteurSource(this));

    }

    public void setSimEnCours(boolean estSelectionne) {
        this.simEnCours = estSelectionne;
    }

    public EtapeIG getEtape() {
        return etape;
    }

    @Override
    public void reagir() {

    }
}
