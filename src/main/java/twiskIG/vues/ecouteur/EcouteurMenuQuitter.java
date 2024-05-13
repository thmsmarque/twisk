package twiskIG.vues.ecouteur;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.outils.ThreadsManager;

public class EcouteurMenuQuitter implements EventHandler<ActionEvent> {
    public EcouteurMenuQuitter() {
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        ThreadsManager.getInstance().detruireTout();
        Platform.exit();
    }
}