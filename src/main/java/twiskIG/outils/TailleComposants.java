package twiskIG.outils;

/**
 * Singleton pour définir la taille des différents composants graphiques
 */
public class TailleComposants {

    private static TailleComposants instance = null;

    private final int tailleVBOX = 80;
    private final int tailleHBOX = 50;

    private final int tailleFenetre = 700;


    private TailleComposants(){

    }

    public static TailleComposants getInstance() {
        if(instance==null){
            instance = new TailleComposants();
        }
        return instance;
    }

    public int getTailleHBOX() {
        return tailleHBOX;
    }

    public int getTailleVBOX() {
        return tailleVBOX;
    }

    public int getTailleFenetre() {
        return tailleFenetre;
    }
}
