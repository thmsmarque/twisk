package twiskIG;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import twiskIG.mondeIG.MondeIG;
import twiskIG.vues.VueActiviteIG;
import twiskIG.vues.VueMenu;
import twiskIG.vues.VueMondeIG;
import twiskIG.vues.VueOutils;

public class MainTwisk extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Twist");
        BorderPane root = new BorderPane();
        MondeIG monde = new MondeIG();

        root.setCenter(new VueMondeIG(monde));
        root.setBottom(new VueOutils(monde));
        root.setTop(new VueMenu(monde));

        //pour récupérer la taille de l'écran :
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        stage.setScene(new Scene(root));
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());

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