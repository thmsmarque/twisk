package twiskIG.vues.ecouteur;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import twisk.outils.ThreadsManager;
import twiskIG.exceptions.MondeException;
import twiskIG.mondeIG.MondeIG;
import twiskIG.mondeIG.SimulationIG;

public class EcouteurLancementSimulation implements EventHandler<ActionEvent> {

    MondeIG m;
    public EcouteurLancementSimulation(MondeIG m)
    {
        this.m = m;
    }
    @Override
    public void handle(ActionEvent actionEvent) {

        if(!m.getEnCoursDeSim()) {
            //Si aucune simulation n'est en cours
            System.out.println("Lancement de la simulation...");
            m.notifierObservateurs();
            try {
                SimulationIG sim = new SimulationIG(m);
            } catch (MondeException e) {
                System.out.println("oohhhh");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de l'instanciation du monde");
                alert.setContentText(e.getMessage());
                alert.show();
                PauseTransition p = new PauseTransition(Duration.seconds(5));
                p.setOnFinished(event-> alert.close());
                p.play();
            }
        }else
        {
            //Si une simulation est en cours
            m.switchEtatSim();
            m.notifierObservateurs();
        }
    }
}
