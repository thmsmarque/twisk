package twiskIG.mondeIG;

import twisk.ClientTwisk;
import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twisk.simulation.Simulation;
import twiskIG.exceptions.MondeException;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

public class SimulationIG {
    private MondeIG mondeIG;

    public SimulationIG(MondeIG mondeIG) {
        this.mondeIG = mondeIG;
        this.simuler();
    }

    /**
     * Cette méthode vérifie que le monde est possible à simuler
     * @throws MondeException
     */
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
        for(EtapeIG etape : this.mondeIG){
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

    /**
     * Cette méthode simule le monde, elle fait appel à simuler de Simulation
     */
    private void simuler(){
        Simulation sim = new Simulation();
        sim.simuler(creerMonde());
    }

    /**
     * Créer le monde
     */
    private Monde creerMonde()
    {
         Monde monde = new Monde();
         CorrespondancesEtapes correspondancesEtapes = new CorrespondancesEtapes();
         Iterator<EtapeIG> it = mondeIG.iterator();

         //On parcourt toutes les étapes de l'interface graphiques et on les ajoute dans le monde logique
        while(it.hasNext())
        {
            EtapeIG etIG = it.next();

            if(etIG.estActivite())//Si l'étape est une activité
            {
                //On vérifie d'abord si elle n'est pas précédé d'un guichet
                boolean succedeUnGuichet = false;
                for(EtapeIG a : etIG.predecesseurs.values())
                {
                    if(a.estGuichet()) //c'est une activité restreinte
                    {
                        succedeUnGuichet = true;
                    }
                }

                //Si elle l'est alors c'est une activité restreinte
                if(succedeUnGuichet)
                {
                    System.out.println(etIG.getNom() + " est une activité restreinte");

                    ActiviteRestreinte activiteRestreinte = new ActiviteRestreinte(etIG.getNom(),etIG.getDelai(),etIG.getEcart());
                    correspondancesEtapes.ajouter(etIG,activiteRestreinte);
                    monde.ajouter(activiteRestreinte);

                }else //Sinon quoi c'est une activité normale
                {
                    System.out.println(etIG.getNom() + " est une activité");

                    Activite etape = new Activite(etIG.getNom(),etIG.getDelai(),etIG.getEcart());
                    correspondancesEtapes.ajouter(etIG,etape);
                    monde.ajouter(etape);

                }

            }//Si ce n'est pas une activité mais un guichet
            else if(etIG.estGuichet())
            {
                System.out.println(etIG.getNom() + " est un guichet");

                GuichetIG guichetIG = (GuichetIG) etIG;
                Guichet guichet = new Guichet(etIG.getNom(), guichetIG.getNbJeton());
                correspondancesEtapes.ajouter(etIG,guichet);
                monde.ajouter(guichet);

            }
        }
        //Une fois cela fait, on rajoute leurs successeurs et leurs prédécesseurs

        it = mondeIG.iterator();

        while(it.hasNext())
        {
            EtapeIG etIG = it.next();
            Etape et = correspondancesEtapes.get(etIG);

            //Si cette étape est définie comme entrée ou sortie :
            if(etIG.getEstUneEntree())
            {
                monde.aCommeEntree(et);
            }
            if(etIG.getEstUneSortie())
            {
                monde.aCommeSortie(et);
            }

            //AJout de ses successeurs
            for(EtapeIG a : etIG.successeurs.values())
            {
                et.ajouterSuccesseur(correspondancesEtapes.get(a));
            }

        }


        return monde;
    }
}
