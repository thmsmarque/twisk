package twiskIG.vues;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.TilePane;
import twiskIG.mondeIG.MondeIG;
import twiskIG.vues.ecouteur.EcouteurAjoutGuichet;
import twiskIG.vues.ecouteur.EcouteurLancementSimulation;
import twiskIG.vues.ecouteur.EcouteurOutils;

/**
 * Classe VueOutils
 */
public class VueOutils extends TilePane implements Observateur {
    private Button boutonAct,boutonGuichet, boutonLancement;
    private MondeIG monde;
    private int num;

    /**
     * Constructeur de la classe VueOutils
     * @param monde
     */
    public VueOutils(MondeIG monde){
        super();
        this.monde=monde;
        monde.ajouterObservateur(this);

        this.boutonAct=new Button("+");
        this.boutonAct.setPrefSize(40,40);
        this.boutonAct.setStyle("-fx-border-radius: 20px; " +
                "-fx-background-radius: 20px; " +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 15px;" +
                "-fx-background-color: lightblue");
        boutonAct.setTooltip(new Tooltip("Nouvelle activité"));

        this.monde=monde;
        this.boutonGuichet=new Button("+");
        this.boutonGuichet.setPrefSize(40,40);
        this.boutonGuichet.setStyle("-fx-border-radius: 20px; " +
                "-fx-background-radius: 20px; " +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 15px;" +
                "-fx-background-color: lightgreen");
        boutonGuichet.setTooltip(new Tooltip("Nouveau guichet"));

        if(!monde.getEnCoursDeSim())
        {
            this.monde=monde;
            this.boutonLancement=new Button("|>");
            this.boutonLancement.setPrefSize(40,40);
            this.boutonLancement.setStyle("-fx-border-radius: 20px; " +
                    "-fx-background-radius: 20px; " +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 15px;" +
                    "-fx-background-color: darkgreen");
            boutonLancement.setTooltip(new Tooltip("Lancement Simulation"));
        }else
        {
            this.monde=monde;
            this.boutonLancement=new Button("X");
            this.boutonLancement.setPrefSize(40,40);
            this.boutonLancement.setStyle("-fx-border-radius: 20px; " +
                    "-fx-background-radius: 20px; " +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 15px;" +
                    "-fx-background-color: darkred");
            boutonLancement.setTooltip(new Tooltip("Arrêt Simulation"));
        }

        setMargin(boutonGuichet, new Insets(5,5,5,5));
        setMargin(boutonAct, new Insets(5,5,5,5));
        setMargin(boutonLancement, new Insets(5,5,5,5));


        this.reagir();


        this.getChildren().clear();
        this.getChildren().addAll(this.boutonAct,this.boutonGuichet,this.boutonLancement);

        //PErmet de numéroter les activités "graphiquement"
        this.num =1;


    }

    /**
     * Methodes reagir de la classe OutilsVue
     */
    public void reagir(){
        num++;
        this.boutonAct.setOnAction(new EcouteurOutils(this.monde,num));
        this.boutonGuichet.setOnAction(new EcouteurAjoutGuichet(this.monde,num));
        this.boutonLancement.setOnAction(new EcouteurLancementSimulation(monde));


        if(!monde.getEnCoursDeSim())
        {
            this.boutonLancement.setText("|>");
            this.boutonLancement.setStyle("-fx-border-radius: 20px; " +
                    "-fx-background-radius: 20px; " +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 15px;" +
                    "-fx-background-color: darkgreen");
            boutonLancement.setTooltip(new Tooltip("Lancement Simulation"));
        }else
        {
            this.boutonLancement.setText("X");
            this.boutonLancement.setStyle("-fx-border-radius: 20px; " +
                    "-fx-background-radius: 20px; " +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 15px;" +
                    "-fx-background-color: darkred");
            boutonLancement.setTooltip(new Tooltip("Arrêt Simulation"));
        }
    }

}
