package twiskIG.vues.ecouteur;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import twiskIG.exceptions.TwiskException;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.GuichetIG;
import twiskIG.mondeIG.MondeIG;
import twiskIG.outils.TailleComposants;

public class EcouteurMenuParametre implements EventHandler<ActionEvent> {

    MondeIG monde;
    EtapeIG etape;

    public EcouteurMenuParametre(MondeIG monde){
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        for (EtapeIG etape : monde) {
            if (etape.getSelection()) {
                this.etape = etape;
            }
        }


        if(etape.estActivite()) {
            Label label = new Label("Veuillez choisir le délai et l'écart de l'activité");
            Label labeld = new Label("Délai :");
            Label labele = new Label("Ecart : ");


            VBox root = new VBox();
            Stage stage = new Stage();
            //Group root = new Group();
            Scene scene = new Scene(root, 600, 230);

            // set Scene to the stage
            stage.setScene(scene);

            // set title for the frame
            stage.setTitle("Paramètres de l'activité");

            // slider délai ----------------------------------
            Slider slider = new Slider();
            slider.setMin(1);
            slider.setMax(15);
            slider.setValue(5);
            slider.setPrefWidth(300);


            //Autoriser les marques sous le slider :
            slider.setShowTickMarks(true);
            slider.setShowTickLabels(true);

            slider.setBlockIncrement(10);
            slider.setMajorTickUnit(1);
            slider.setMinorTickCount(0);


            // slider écart : -----------------------------------------
            Slider slidere = new Slider();
            slidere.setMin(1);
            slidere.setMax(slider.getValue());
            slidere.setValue(5);
            slidere.setPrefWidth(300);

            //Autoriser les marques sous le slider :
            slidere.setShowTickMarks(true);
            slidere.setShowTickLabels(true);

            slidere.setBlockIncrement(10);
            slidere.setMajorTickUnit(1);
            slidere.setMinorTickCount(0);


            slider.valueProperty().addListener( //récupère la valeur du délai
                    new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                            slider.setValue(t1.intValue());
                            slidere.setMax(slider.getValue());
                            try {
                                etape.setDelai(t1.intValue());
                            } catch (TwiskException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
            );


            slidere.valueProperty().addListener( //récupère la valeur de l'écart
                    new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                            slidere.setValue(t1.intValue());
                            try {
                                etape.setEcart(t1.intValue());
                            } catch (TwiskException e) {
                                throw new RuntimeException(e);
                            }
                            //trouver un moyen de le mettre à jour
                        }
                    }
            );

            // bouton pour quitter --------------------------
            Button b = new Button("Ok");
            b.setOnAction(e -> stage.close());
            // mise en place du fond ------------------------
            root.setPadding(new Insets(20));
            root.setSpacing(10);
            root.getChildren().addAll(label, labeld, slider, labele, slidere, b);
            stage.setScene(scene);

            stage.show();

            monde.notifierObservateurs();
        }

        if(etape.estGuichet())
        {
            Label label = new Label("Veuillez choisir le nombre de jetons");
            Label labeld = new Label("jetons :");


            VBox root = new VBox();
            Stage stage = new Stage();
            //Group root = new Group();
            Scene scene = new Scene(root, 600, 230);

            // set Scene to the stage
            stage.setScene(scene);

            // set title for the frame
            stage.setTitle("Paramètres du guichet");

            // input field
            TextField input1 = new TextField();

            // bouton pour quitter --------------------------
            Button b = new Button("Ok");
            b.setOnAction(e -> {
                        // Action à effectuer lors du clic sur le bouton "OK"
                        int value1 = Integer.parseInt(input1.getText());
                        GuichetIG guich = (GuichetIG) this.etape;
                        try {
                            guich.changerNbJeton(value1);
                            guich.setLargeur(TailleComposants.getInstance().getTailleCoteGuichetPlace()*value1);
                            guich.setPointsdeC();
                        } catch (TwiskException ex) {
                            throw new RuntimeException(ex);
                        }
                stage.close();
                System.out.println("Nombre de jetons : " + value1);

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
}
