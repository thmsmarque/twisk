package twiskIG.vues.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import twiskIG.mondeIG.MondeIG;

import java.io.File;

public class EcouteurChargerMonde implements EventHandler<ActionEvent> {

    MondeIG monde;
    Stage stage;
    public EcouteurChargerMonde(MondeIG monde, Stage stage)
    {
        this.monde = monde;
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        File file;

        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            monde.chargerMonde(file);
        }
    }
}
