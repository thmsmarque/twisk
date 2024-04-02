package twisk.simulation;

import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireClientsTest {

    @Test
    public void setClientsTest(){
        //ON vérifie que les numéros de processus sont bien instanciés
        GestionnaireClients g = new GestionnaireClients();
        int[] tab = new int[] {1,2,3,4,5};
        g.setClients(tab);
        assertSame(g.getClient(2).getNumeroClient(),2);
    }

    @Test
    public void allerA1Etape(){
        GestionnaireClients g = new GestionnaireClients();
        int[] tab = new int[] {1,2};
        g.setClients(tab);

        Etape e0 = new Activite("Activite",5,5);
        int rang = 0;

        g.allerA(1,e0,rang);
        rang++;
        g.allerA(2,e0,rang);

        assertSame(g.getClient(1).getEtape(),e0);

    }

    @Test
    public void allerA2Etapes(){
        GestionnaireClients g = new GestionnaireClients();
        int[] tab = new int[] {1,2};
        g.setClients(tab);

        Etape e0 = new Activite("Activite",5,5);
        Etape e1 = new Activite("Activite2",5,5);
        int rang = 0;

        g.allerA(1,e0,rang);

        g.allerA(2,e1,rang);

        assertSame(g.getClient(1).getEtape(),e0);
        assertSame(g.getClient(2).getEtape(),e1);


    }

}