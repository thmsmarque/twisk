package twiskIG.vues;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;
import twisk.vues.ecouteur.EcouteurSelection;

public class VueArcIG extends Pane implements Observateur {
    private MondeIG monde;

    private ArcIG arc;

    public VueArcIG(MondeIG monde , ArcIG arc){
        this.monde = monde;

        this.arc=arc;
        // ligne :
        int p1x = arc.getP1().getPosX();
        int p1y = arc.getP1().getPosY();

        int p2x = arc.getP2().getPosX();
        int p2y = arc.getP2().getPosY();
        Line line = new Line(p1x,p1y,p2x,p2y);

// triangle :
        double angle = Math.atan2(p2y - p1y, p2x - p1x);
        double longueur = 10;

        double x1 = p2x - longueur * Math.cos(angle);
        double y1 = p2y - longueur * Math.sin(angle);
        double x2 = x1 + longueur * Math.cos(angle + Math.PI / 2);
        double y2 = y1 + longueur * Math.sin(angle + Math.PI / 2);
        double x3 = x1 + longueur * Math.cos(angle - Math.PI / 2);
        double y3 = y1 + longueur * Math.sin(angle - Math.PI / 2);

        Polyline poly = new Polyline();
        poly.getPoints().addAll(new Double[]{
                (double)p2x, (double)p2y,
                x2, y2,
                x3, y3,
                (double)p2x, (double)p2y
        });

        if(!arc.getSelection()) {
            line.setStrokeWidth(3);
            line.setStroke(Color.BLACK);

            poly.setStyle("-fx-background-color: #000000; -fx-fill: #000000");
        } else {
            line.setStrokeWidth(3);
            line.setStroke(Color.MAGENTA);
            poly.setStroke(Color.MAGENTA);
            poly.setStyle("-fx-background-color: magenta; -fx-fill: magenta");
        }

        this.setOnMouseClicked(new EcouteurSelection(monde, arc));

        getChildren().add(poly);
        getChildren().add(line);

    }



    @Override
    public void reagir() {

    }
}
