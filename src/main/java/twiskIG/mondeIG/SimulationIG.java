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
        test1();
        test2();
        test3();
        test4();
        test5();
        /*
RAPPEL :
Une entree et une sortie reliés.
PAs d'activite restreinte entree
Toues les éléments sont accessibles :
- activite accessible a des successeurs qui mènent à une sortie
- activite accessible par un predecesseur entree

- Pas deux activités après un guichet

 */
    }

    /**
     * Vérifier qu'il n'y a pas de guichet assigné comme sortie
     * @throws MondeException
     */
    private void test1() throws MondeException {
        for (EtapeIG etape : this.mondeIG) {
            if (etape.estGuichet() && etape.getEstUneSortie()) {
                throw new MondeException("Erreur dans le test 1. Un guichet est déclaré comme sortie : " + etape);
            }
        }
    }

    /**
     * Vérifier qu'il n'y a pas d'éléments isolés dans le monde
     * @throws MondeException
     */
    private void test2() throws MondeException {
        for (EtapeIG etape : this.mondeIG) {
            if (etape.nbPredecesseurs() == 0 && etape.nbSuccesseurs() == 0 && //pas de successeurs ni de prédecesseurs
                    (
                            (!etape.getEstUneEntree() && !etape.getEstUneSortie()) || //ni une entrée ni une sortie
                                    (!etape.getEstUneEntree() && etape.getEstUneSortie()) || //pas une entrée mais une sortie
                                    (etape.getEstUneEntree() && !etape.getEstUneSortie())  // une entrée mais pas une sortie
                    )
            ) {
                throw new MondeException("Erreur dans le test 2. Un élément n'est relié à rien dans le monde : " + etape);
            }
        }
    }

    /**
     * Vérifier qu'il n'y a pas d'activité sans predecesseurs qui n'est pas une entrée
     * @throws MondeException
     */
    private void test3() throws MondeException {
        for (EtapeIG etape : this.mondeIG) {
            if (etape.estActivite() && etape.nbPredecesseurs() == 0) {
                throw new MondeException("Erreur dans le test 3. L'activité n'est pas reliée à une entrée : " + etape);
            }
        }
    }

    /**
     * Vérifier qu'il n'y a pas deux guichets qui se suivent
     * @throws MondeException
     */
    private void test4() throws MondeException {
        for(EtapeIG etape : this.mondeIG) {
            if (etape.estGuichet() && etape.nbSuccesseurs() > 0) { // etape est un guichet et possède des successeurs
                for (EtapeIG etapesuccesseur : etape.getSuccesseurs().values()) { // on itère sur les successeurs de l'étape à analyser
                    if (etapesuccesseur.estGuichet()) { //si l'un des successeurs est un guichet (=2 guichets se suivent)
                        throw new MondeException("Erreur dans le test 4. Deux guichets se suivent :" + etape + " et" + etapesuccesseur);
                    }
                }
            }
        }
    }

    /**
     * Vérifier qu'il n'y a pas deux activités après un guichet
     * @throws MondeException
     */
    private void test5() throws MondeException{
        int nbActivite;
        for(EtapeIG etape : this.mondeIG) {

            if (etape.estGuichet() && etape.nbSuccesseurs() > 0) { // etape est un guichet et possède des successeurs
                nbActivite=0;
                for (EtapeIG etapesuccesseur : etape.getSuccesseurs().values()) { // on itère sur les successeurs de l'étape à analyser
                    if (etapesuccesseur.estActivite()) { //l'un des successeurs est une activité
                        nbActivite=+1;
                        if(nbActivite>=2) throw new MondeException("Erreur dans le test 5. Deux activités suivent le guichet :" + etape);
                    }
                }
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
