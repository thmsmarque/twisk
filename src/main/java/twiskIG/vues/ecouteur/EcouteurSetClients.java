package twiskIG.vues.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import twiskIG.exceptions.TwiskException;
import twiskIG.mondeIG.GuichetIG;
import twiskIG.mondeIG.MondeIG;
import twiskIG.mondeIG.SimulationIG;

public class EcouteurSetClients implements EventHandler<ActionEvent>{
    private MondeIG monde;
    public EcouteurSetClients(MondeIG monde){

        this.monde = monde;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        int nbClient=0;

        Label label = new Label("Veuillez choisir le nombre de clients :");


        VBox root = new VBox();
        Stage stage = new Stage();
        //Group root = new Group();
        Scene scene = new Scene(root, 400, 130);
        stage.setScene(scene);
        stage.setTitle("Paramètres de clients");
        TextField input1 = new TextField();

        // bouton pour quitter --------------------------
        Button b = new Button("Ok");
        b.setOnAction(e -> {
            // Action à effectuer lors du clic sur le bouton "OK"
            int value1 = Integer.parseInt(input1.getText());
            if(value1>=50){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Nombre de clients invalide.");
                alert.setContentText("Les clients doivent être inférieur à 50.");
                alert.showAndWait();
            } else if(value1<=0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Nombre de clients invalide.");
                alert.setContentText("Les clients doivent être supérieur à 0");
                alert.showAndWait();
            } else monde.setNbCLient(value1);
            stage.close();
            monde.notifierObservateurs();

        });
        // mise en place du fond ------------------------
        root.setPadding(new Insets(20));
        root.setSpacing(10);
        root.getChildren().addAll(label, input1, b);
        stage.setScene(scene);

        stage.show();
        monde.notifierObservateurs();


    }
}
