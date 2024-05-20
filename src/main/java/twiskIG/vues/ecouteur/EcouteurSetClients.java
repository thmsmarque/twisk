package twiskIG.vues.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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

        Label label = new Label("Veuillez choisir le nombre de vlients");
        Label labeld = new Label("jetons :");


        VBox root = new VBox();
        Stage stage = new Stage();
        //Group root = new Group();
        Scene scene = new Scene(root, 600, 230);

        // set Scene to the stage
        stage.setScene(scene);

        // set title for the frame
            stage.setTitle("Nombre de clients dans la simulation : ");

        // input field
        TextField input1 = new TextField();

        // bouton pour quitter --------------------------
        Button b = new Button("Ok");
        b.setOnAction(e -> {
            // Action à effectuer lors du clic sur le bouton "OK"
            int value1 = Integer.parseInt(input1.getText());
            monde.setNbCLient(value1);
            stage.close();
            monde.notifierObservateurs();

        });
        // mise en place du fond ------------------------
        root.setPadding(new Insets(20));
        root.setSpacing(10);
        root.getChildren().addAll(label, labeld, input1, b);
        stage.setScene(scene);

        stage.show();
        monde.notifierObservateurs();


    }
}
