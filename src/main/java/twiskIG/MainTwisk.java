package twiskIG;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueActiviteIG;
import twisk.vues.VueMenu;
import twisk.vues.VueMondeIG;
import twisk.vues.VueOutils;

public class MainTwisk extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Twist");
        BorderPane root = new BorderPane();
        MondeIG monde = new MondeIG();

        root.setCenter(new VueMondeIG(monde));
        root.setBottom(new VueOutils(monde));
        root.setTop(new VueMenu(monde));



        stage.setScene(new Scene(root,900,900));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*
--module-path
/home/clara/Documents/S4/java/ressource/javafx-sdk-17.0.10/lib/
--add-modules
javafx.controls,javafx.fxml
 */