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

    private boolean simEnCours;

    /**
     * Constructeur de la classe VuePointDeControl
     * @param monde
     */
    public VuePointDeControle(MondeIG monde, PointDeControleIG point, boolean simEnCours) {
        this.monde=monde;
        this.point =point;
        this.simEnCours=simEnCours;

        //TEMP :
        setCenterX(point.getPosX());
        setCenterY(point.getPosY());

        //Visuel :
        setRadius(5);
        setFill(Color.BLUEVIOLET);
        if(!this.simEnCours) setOnMouseClicked(new EcouteurVuePointDeControleIG(monde,point));
    }

    public void setX(int x){
        setCenterX(x);
    }

    public void setY(int y){
        setCenterY(y);
    }

    public void setSimEnCours(boolean simEnCours) {
        this.simEnCours = simEnCours;
    }

    @Override
    public void reagir() {

    }
}


