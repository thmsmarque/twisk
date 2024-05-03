package twiskIG.mondeIG;

import twiskIG.exceptions.MondeException;

public class SimulationIG {
    private MondeIG monde;
    public SimulationIG(MondeIG monde) {
        this.monde = monde;
    }
    private void verifierMondeIG() throws MondeException {
        /*
RAPPEL :
Une entree et une sortie reliés.
PAs d'activite restreinte entree
Toues les éléments sont accessibles :
- activite accessible a des successeurs qui mènent à une sortie
- activite accessible par un predecesseur entree

- PAs deux guichets qui se suivent
- Pas deux activités après un guichet

 */
        for(EtapeIG etape : this.monde){
            //Pas de Guichet sortie
            if(etape.estGuichet() && etape.getEstUneSortie()){
                throw new MondeException("Un guichet est déclaré comme sortie : " + etape);
            }
            //pas d'éléments isolés
            if(etape.nbPredecesseurs()==0 && etape.nbSuccesseurs()==0 && //pas de successeurs ni de prédecesseurs
                    (
                            (!etape.getEstUneSortie() && !etape.getEstUneSortie()) || //ni une entrée ni une sortie
                            (!etape.getEstUneSortie() && etape.getEstUneSortie()) || //pas une entrée mais une sortie
                            (etape.getEstUneSortie() && !etape.getEstUneSortie())  // une entrée mais pas une sortie
                    )
            ){
                throw new MondeException("Un élément n'est relié à rien dans le monde : " + etape);
            }
            // activité sans predecesseurs qui n'est pas une entrée
            if(etape.estActivite() && etape.nbPredecesseurs()==0){
                throw new MondeException("L'activité n'est pas relié à une entrée : " + etape);
            }

        }
    }

    private void simuler(){

    }
}
