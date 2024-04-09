package twiskIG.vues.ecouteur;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import twisk.exceptions.TwiskException;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

public class EcouteurVuePointDeControleIG implements EventHandler<MouseEvent> {

    private MondeIG monde;

    private  PointDeControleIG pdc;
    public EcouteurVuePointDeControleIG(MondeIG monde, PointDeControleIG point){
        this.monde = monde;
        this.pdc = point;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        if(this.monde.getPointSauv()==null){
            this.monde.setPointSauv(pdc);
        } else {
            try {
                this.monde.ajouter(this.monde.getPointSauv(),pdc);
            } catch (TwiskException e) {
                this.monde.setPointSauv(null);

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la création de l'arc");
                alert.setContentText(e.getMessage());
                alert.show();
                PauseTransition p = new PauseTransition(Duration.seconds(5));
                p.setOnFinished(event-> alert.close());
                p.play();
            }
        }
        monde.notifierObservateurs();
        //DANS MONDE : garder en mémoire le dernier point sélectionné
        // si aucun point selectionné : on l'enregistre
        // sinon : on crée un nouvel arc

    }
}
