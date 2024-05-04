package twiskIG.mondeIG;

import org.junit.Test;
import twisk.monde.Etape;
import twiskIG.exceptions.MondeException;
import twiskIG.exceptions.TwiskException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationIGTest {

    //TEST 1 ======================================
    @Test
    public void testGuichetSortie() throws MondeException, TwiskException {

       MondeIG mondeIG = new MondeIG();
       mondeIG.ajouter("Activite",0);
       mondeIG.ajouter("Activite",1);
       mondeIG.ajouter("Guichet",2);
       Iterator<EtapeIG> iterator = mondeIG.iterator();

       // Accès au guichet (deuxième dans la liste) -------------------
        List<EtapeIG> etapes = new ArrayList<>();
        mondeIG.iterator().forEachRemaining(etapes::add);
        EtapeIG guichet = etapes.get(2);
        guichet.setEstUneSortie(true);
        // ------------------------------------------------------------

       PointDeControleIG p0 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p1 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p2 = new PointDeControleIG(0,0,iterator.next());
        mondeIG.ajouter(p0,p1);
        mondeIG.ajouter(p1,p2);

        SimulationIG simulationIG = new SimulationIG(mondeIG,"test");

        assertThrows(MondeException.class, simulationIG::test1);
    }

    @Test
    public void testPasGuichetSortie() throws TwiskException, MondeException {
        MondeIG mondeIG = new MondeIG();
        mondeIG.ajouter("Activite",0);
        mondeIG.ajouter("Guichet",1);
        mondeIG.ajouter("Activite",2);
        Iterator<EtapeIG> iterator = mondeIG.iterator();

        //  activite 1 = entree et activite 2 = sortie -------------------
        List<EtapeIG> etapes = new ArrayList<>();
        mondeIG.iterator().forEachRemaining(etapes::add);
        EtapeIG activite1 = etapes.get(0);
        activite1.setEstUneEntree(true);
        EtapeIG activite2 = etapes.get(2);
        activite2.setEstUneSortie(true);
        // ------------------------------------------------------------

        PointDeControleIG p0 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p1 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p2 = new PointDeControleIG(0,0,iterator.next());
        mondeIG.ajouter(p0,p1);
        mondeIG.ajouter(p1,p2);

        SimulationIG simulationIG = new SimulationIG(mondeIG,"test");
        simulationIG.test1();
    }

    //TEST 2 ======================================
    @Test
    public void testElementIsole() throws TwiskException, MondeException {
        MondeIG mondeIG = new MondeIG();
        mondeIG.ajouter("Activite",0);
        mondeIG.ajouter("Guichet",1);
        mondeIG.ajouter("Activite",2);
        Iterator<EtapeIG> iterator = mondeIG.iterator();

        //  activite 1 = entree et activite 2 = sortie -------------------
        List<EtapeIG> etapes = new ArrayList<>();
        mondeIG.iterator().forEachRemaining(etapes::add);
        EtapeIG activite1 = etapes.get(0);
        activite1.setEstUneEntree(true);
        EtapeIG activite2 = etapes.get(2);
        activite2.setEstUneSortie(true);
        // ------------------------------------------------------------

        PointDeControleIG p0 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p1 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p2 = new PointDeControleIG(0,0,iterator.next());
        mondeIG.ajouter(p0,p1);

        SimulationIG simulationIG = new SimulationIG(mondeIG,"test");
        assertThrows(MondeException.class,simulationIG::test2);
    }

    @Test
    public void testPasElementIsole() throws TwiskException, MondeException {
        MondeIG mondeIG = new MondeIG();
        mondeIG.ajouter("Activite",0);
        mondeIG.ajouter("Guichet",1);
        mondeIG.ajouter("Activite",2);
        Iterator<EtapeIG> iterator = mondeIG.iterator();

        //  activite 1 = entree et activite 2 = sortie -------------------
        List<EtapeIG> etapes = new ArrayList<>();
        mondeIG.iterator().forEachRemaining(etapes::add);
        EtapeIG activite1 = etapes.get(0);
        activite1.setEstUneEntree(true);
        EtapeIG activite2 = etapes.get(2);
        activite2.setEstUneSortie(true);
        // ------------------------------------------------------------

        PointDeControleIG p0 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p1 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p2 = new PointDeControleIG(0,0,iterator.next());
        mondeIG.ajouter(p0,p1);
        mondeIG.ajouter(p1,p2);

        SimulationIG simulationIG = new SimulationIG(mondeIG,"test");
        simulationIG.test2();
    }

    //TEST 3 ======================================

    @Test
    public void testPasDentree() throws TwiskException, MondeException {
        MondeIG mondeIG = new MondeIG();
        mondeIG.ajouter("Activite",0);
        mondeIG.ajouter("Guichet",1);
        mondeIG.ajouter("Activite",2);
        Iterator<EtapeIG> iterator = mondeIG.iterator();

        //  activite 1 = entree et activite 2 = sortie -------------------
        List<EtapeIG> etapes = new ArrayList<>();
        mondeIG.iterator().forEachRemaining(etapes::add);
        EtapeIG activite2 = etapes.get(2);
        activite2.setEstUneSortie(true);
        // ------------------------------------------------------------

        PointDeControleIG p0 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p1 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p2 = new PointDeControleIG(0,0,iterator.next());
        mondeIG.ajouter(p0,p1);
        mondeIG.ajouter(p1,p2);

        SimulationIG simulationIG = new SimulationIG(mondeIG,"test");
        assertThrows(MondeException.class,simulationIG::test3);
    }

    @Test
    public void testEntree() throws TwiskException, MondeException {
        MondeIG mondeIG = new MondeIG();
        mondeIG.ajouter("Activite",0);
        mondeIG.ajouter("Guichet",1);
        mondeIG.ajouter("Activite",2);
        Iterator<EtapeIG> iterator = mondeIG.iterator();

        //  activite 1 = entree et activite 2 = sortie -------------------
        List<EtapeIG> etapes = new ArrayList<>();
        mondeIG.iterator().forEachRemaining(etapes::add);
        EtapeIG activite1 = etapes.get(0);
        activite1.setEstUneEntree(true);
        EtapeIG activite2 = etapes.get(2);
        activite2.setEstUneSortie(true);
        // ------------------------------------------------------------

        PointDeControleIG p0 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p1 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p2 = new PointDeControleIG(0,0,iterator.next());
        mondeIG.ajouter(p0,p1);
        mondeIG.ajouter(p1,p2);

        SimulationIG simulationIG = new SimulationIG(mondeIG,"test");
        simulationIG.test3();
    }

    // TEST 4 ====================================================
    @Test
    public void testDeuxGuichetsSuivent() throws TwiskException, MondeException {
        MondeIG mondeIG = new MondeIG();
        mondeIG.ajouter("Activite",0);
        mondeIG.ajouter("Guichet",1);
        mondeIG.ajouter("Guichet",2);

        mondeIG.ajouter("Activite",3);
        Iterator<EtapeIG> iterator = mondeIG.iterator();

        //  activite 1 = entree et activite 2 = sortie -------------------
        List<EtapeIG> etapes = new ArrayList<>();
        mondeIG.iterator().forEachRemaining(etapes::add);
        EtapeIG activite1 = etapes.get(0);
        activite1.setEstUneEntree(true);
        EtapeIG activite2 = etapes.get(3);
        activite2.setEstUneSortie(true);
        // ------------------------------------------------------------

        PointDeControleIG p0 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p1 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p2 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p3 = new PointDeControleIG(0,0,iterator.next());

        mondeIG.ajouter(p0,p1);
        mondeIG.ajouter(p1,p2);
        mondeIG.ajouter(p2,p3);

        SimulationIG simulationIG = new SimulationIG(mondeIG,"test");
        assertThrows(MondeException.class,simulationIG::test4);
    }

    // TEST 5 ==========================================================
    @Test
    public void testDeuxActivitesApresGuichet() throws TwiskException, MondeException {
        MondeIG mondeIG = new MondeIG();
        mondeIG.ajouter("Activite",0);
        mondeIG.ajouter("Guichet",1);
        mondeIG.ajouter("Activite",2);

        mondeIG.ajouter("Activite",3);
        Iterator<EtapeIG> iterator = mondeIG.iterator();

        //  activite 1 = entree et activite 2 = sortie -------------------
        List<EtapeIG> etapes = new ArrayList<>();
        mondeIG.iterator().forEachRemaining(etapes::add);
        EtapeIG activite1 = etapes.get(0);
        activite1.setEstUneEntree(true);
        EtapeIG activite2 = etapes.get(2);
        activite2.setEstUneSortie(true);
        EtapeIG activite3 = etapes.get(3);
        activite3.setEstUneSortie(true);
        // ------------------------------------------------------------

        PointDeControleIG p0 = new PointDeControleIG(0,0,iterator.next());
        // Deux pdc pour le guichet :
        PointDeControleIG p1 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p11 = new PointDeControleIG(0,0,etapes.get(1));

        PointDeControleIG p2 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p3 = new PointDeControleIG(0,0,iterator.next());

        mondeIG.ajouter(p0,p1);
        mondeIG.ajouter(p1,p2);
        mondeIG.ajouter(p11,p3);

        SimulationIG simulationIG = new SimulationIG(mondeIG,"test");
        assertThrows(MondeException.class,simulationIG::test5);
    }

    // TEST 6 ===========================================================
    @Test
    public void testEntreeDFS() throws TwiskException, MondeException {
        MondeIG mondeIG = new MondeIG();
        mondeIG.ajouter("Activite",0);
        mondeIG.ajouter("Guichet",1);
        mondeIG.ajouter("Activite",2);
        Iterator<EtapeIG> iterator = mondeIG.iterator();

        //  pas d'entrée activite 2 = sortie -------------------
        List<EtapeIG> etapes = new ArrayList<>();
        mondeIG.iterator().forEachRemaining(etapes::add);
        EtapeIG activite2 = etapes.get(2);
        activite2.setEstUneSortie(true);
        // ------------------------------------------------------------

        PointDeControleIG p0 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p1 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p2 = new PointDeControleIG(0,0,iterator.next());
        mondeIG.ajouter(p0,p1);
        mondeIG.ajouter(p1,p2);

        SimulationIG simulationIG = new SimulationIG(mondeIG,"test");
        assertThrows(MondeException.class,simulationIG::test6);
    }

    // TEST 7 =============================================================
    @Test
    public void testSortieDFS() throws TwiskException, MondeException {
        MondeIG mondeIG = new MondeIG();
        mondeIG.ajouter("Activite",0);
        mondeIG.ajouter("Guichet",1);
        mondeIG.ajouter("Activite",2);

        Iterator<EtapeIG> iterator = mondeIG.iterator();

        //  activite 1 = entree et pas de sortie  -------------------
        List<EtapeIG> etapes = new ArrayList<>();
        mondeIG.iterator().forEachRemaining(etapes::add);
        EtapeIG activite1 = etapes.get(0);
        activite1.setEstUneEntree(true);

        // ------------------------------------------------------------

        PointDeControleIG p0 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p1 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p2 = new PointDeControleIG(0,0,iterator.next());

        mondeIG.ajouter(p0,p1);
        mondeIG.ajouter(p1,p2);

        SimulationIG simulationIG = new SimulationIG(mondeIG,"test");
        assertThrows(MondeException.class,simulationIG::test7);
    }
}

/* MONDE SIMPLE FONCTIONNEL: act - guichet - act
 MondeIG mondeIG = new MondeIG();
       mondeIG.ajouter("Activite",0);
       mondeIG.ajouter("Guichet",1);
       mondeIG.ajouter("Activite",2);
       Iterator<EtapeIG> iterator = mondeIG.iterator();

       //  activite 1 = entree et activite 2 = sortie -------------------
        List<EtapeIG> etapes = new ArrayList<>();
        mondeIG.iterator().forEachRemaining(etapes::add);
        EtapeIG activite1 = etapes.get(0);
        guichet.setEstUneEntree(true);
        EtapeIG activite2 = etapes.get(2);
        guichet.setEstUneSortie(true);
        // ------------------------------------------------------------

       PointDeControleIG p0 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p1 = new PointDeControleIG(0,0,iterator.next());
        PointDeControleIG p2 = new PointDeControleIG(0,0,iterator.next());
        mondeIG.ajouter(p0,p1);
        mondeIG.ajouter(p1,p2);

        SimulationIG simulationIG = new SimulationIG(mondeIG,"test");
 */