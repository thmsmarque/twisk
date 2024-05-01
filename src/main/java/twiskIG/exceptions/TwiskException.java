package twiskIG.exceptions;

import javafx.scene.control.Alert;

public class TwiskException extends Exception {

    public TwiskException(String exception) {
        super(exception);
        // Créer une boîte de dialogue d'information
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur!");
        alert.setHeaderText(null); // Pas de texte d'en-tête
        alert.setContentText(exception);
        // Afficher la boîte de dialogue
        alert.showAndWait();
    }

}
