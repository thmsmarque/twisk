package twiskIG.vues.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import twiskIG.mondeIG.MondeIG;

import java.io.File;

public class EcouteurSauvegarder implements EventHandler<ActionEvent> {

    MondeIG monde;
    Stage stage;
    public EcouteurSauvegarder(MondeIG monde, Stage stage)
    {
        this.stage = stage;
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        File file;

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
         file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            monde.sauvegarderMonde(file);
        }


    }
}
