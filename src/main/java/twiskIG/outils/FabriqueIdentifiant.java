package twiskIG.outils;

/**
 * Classe FabriqueIdenifiant
 */
public class FabriqueIdentifiant {

    private static FabriqueIdentifiant instance = null;

    private int noEtape=0;

    private char noPC1 ='a';

    private int noPC2=0;

    /**
     * Constructeur vide de la classe FAbriqueIdentifiant
     */
    private FabriqueIdentifiant(){

    }

    /** Instance de la class FabriqueIdentifiant
     * @return Singleton de la classe FabriqueIdentifiant
     */
    public static FabriqueIdentifiant getInstance(){
        if(instance==null){
            instance = new FabriqueIdentifiant();
        }
        return instance;
    }

    /** Définit un identifiant Etape pour le singleton
     * @return Le string correspondant à l'identifiant de l'étape
     */
    public String getIdentifiantEtape(){
        String identifiant = String.valueOf(noEtape);
        noEtape++;
        return identifiant;
    }

    /** Définit un identifiant pour l'instance de la classe PointDe Controle
     * @return Une chaine de caractère identifiant de type "a0,a1,a2,a3," puis "b0,b2,..."
     */
    public String getIdentifiantPointControle() {
        StringBuilder s = new StringBuilder();
        if (noPC2 == 4) {
            assert(noPC1!='z') : "Impossible de créer une activité supplémentaire";
            this.noPC1++;
            this.noPC2 = 0;
        }
        s.append(noPC1);
        s.append(noPC2);
        noPC2++;

        return s.toString();
    }

    public void reset()
    {
        noEtape=0;
        noPC1='a';
        noPC2=0;
    }

}
