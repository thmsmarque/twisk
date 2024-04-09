package twiskIG.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twiskIG.mondeIG.MondeIG;
import twiskIG.mondeIG.PointDeControleIG;
import twiskIG.vues.ecouteur.EcouteurVuePointDeControleIG;

/**
 * Classe VuePointDeControle
 */
public class VuePointDeControle extends Circle implements Observateur{
    private MondeIG monde;

    private PointDeControleIG point;

    /**
     * Constructeur de la classe VuePointDeControle
     * @param monde
     */
    public VuePointDeControle(MondeIG monde, PointDeControleIG point) {
        this.monde=monde;
        this.point =point;

        //TEMP :
        setCenterX(point.getPosX());
        setCenterY(point.getPosY());

        //Visuel :
        setRadius(5);
        setFill(Color.BLUEVIOLET);
        setOnMouseClicked(new EcouteurVuePointDeControleIG(monde,point));


        //ajouter observateur et notifier
    }

    public void setX(int x){
        setCenterX(x);
    }

    public void setY(int y){
        setCenterY(y);
    }

    @Override
    public void reagir() {

    }
}


