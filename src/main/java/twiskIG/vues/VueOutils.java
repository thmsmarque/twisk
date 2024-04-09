package twiskIG.vues;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.TilePane;
import twiskIG.mondeIG.MondeIG;
import twiskIG.vues.ecouteur.EcouteurOutils;

/**
 * Classe VueOutils
 */
public class VueOutils extends TilePane implements Observateur {
    private Button bouton;
    private MondeIG monde;
    private int num;

    /**
     * Constructeur de la classe VueOutils
     * @param monde
     */
    public VueOutils(MondeIG monde){
        super();
        this.monde=monde;
        this.bouton=new Button("+");
        this.bouton.setPrefSize(40,40);
        this.bouton.setStyle("-fx-border-radius: 20px; " +
                "-fx-background-radius: 20px; " +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 15px;" +
                "-fx-background-color: lightblue");
        bouton.setTooltip(new Tooltip("Nouvelle activité"));

        setMargin(bouton, new Insets(5,5,5,5));
        this.reagir();
        monde.ajouterObservateur(this);

        //PErmet de numéroter les activités "graphiquement"
        this.num =1;


    }

    /**
     * Methodes reagir de la classe OutilsVue
     */
    public void reagir(){

        num++;
        this.bouton.setOnAction(new EcouteurOutils(this.monde,num));
        this.getChildren().clear();
        this.getChildren().add(this.bouton);

    }

}
