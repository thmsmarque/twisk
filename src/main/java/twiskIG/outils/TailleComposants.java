package twiskIG.outils;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * Singleton pour définir la taille des différents composants graphiques
 */
public class TailleComposants {

    private static TailleComposants instance = null;



    private final int tailleVBOXActivite = 80;
    private final int tailleHBOXActivite = 50;

    private final int tailleVBOXGuichet = 50;
    private final int tailleCoteGuichetPlace = 20;

    private final int tailleFenetreX = (int) Screen.getPrimary().getVisualBounds().getWidth();
    private final int tailleFenetreY = (int) Screen.getPrimary().getVisualBounds().getHeight();


    private final int marge = 200;

    private TailleComposants(){

    }

    public static TailleComposants getInstance() {
        if(instance==null){
            instance = new TailleComposants();
        }
        return instance;
    }

    public int gettailleHBOXActivite() {
        return tailleHBOXActivite;
    }

    public int gettailleVBOXActivite() {
        return tailleVBOXActivite;
    }

    public int getTailleVBOXGuichet()
    {
        return tailleVBOXGuichet;
    }

    public int getTailleCoteGuichetPlace() {
        return tailleCoteGuichetPlace;
    }

    public int getTailleFenetreX() {
        return tailleFenetreX;
    }

    public int getTailleFenetreY() {
        return tailleFenetreY;
    }

    public int getMarge() {
        return marge;
    }
}
