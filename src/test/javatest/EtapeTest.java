package javatest;

import twisk.outils.FabriqueNumero;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class EtapeTest {

    @Test
    void testConstructeur(){

        Etape e = new Activite("Activite");
        Etape e3 = new Activite("Activite",5,5);
        Etape e1 = new Guichet("Guichet");
        Etape e2 = new Guichet("Guichet",5);


        //ACTIVITE :

        assertEquals(((Activite) e3).getTemps(),5);
        assertEquals(((Activite) e3).getEcartTemps(),5);

        //Valeur 0 par défaut dans le constructeur :
        assertEquals(((Activite) e).getTemps(),0);
        assertEquals(((Activite) e).getEcartTemps(),0);

        //GUICHET :
        assertEquals( ((Guichet) e2).getNbJetons(),5);

        // VAleur à 0 par défaut
        assertEquals( ((Guichet) e1).getNbJetons(),0);

    }

    @Test
    void nbSuccesseur() {

        Etape e = new Activite("Activite");
        Etape e3 = new Activite("Activite",5,5);
        Etape e1 = new Guichet("Guichet");
        Etape e2 = new Guichet("Guichet",5);
        Etape e4 = new Activite("Activite");
        Etape e5 = new Activite("Activite");

        e.ajouterSuccesseur(e3,e1,e2);
        e4.ajouterSuccesseur(e5);
        Iterator<Etape> ite = e.iterator();
        Iterator<Etape> ite4 = e4.iterator();
        Iterator<Etape> ite5 = e5.iterator();

        //Les iterateur qui doivent avoir un next :
        assertEquals(ite.next(),e3);
        assertEquals(ite.next(),e1);
        assertEquals(ite.next(),e2);
        assertEquals(ite4.next(),e5);

        // L'iterateur qui n'a pas de next :
        assertFalse(ite4.hasNext());
        assertFalse(ite5.hasNext());

        //Nombre de successeur :
        assertEquals(e.getGestionnaire().nbEtapes(),3);
        assertEquals(e4.getGestionnaire().nbEtapes(),1);
        assertEquals(e5.getGestionnaire().nbEtapes(),0);



    }

    @Test
    void estUneActivite() {

        Etape e = new Activite("Activite");
        Etape e1 = new Guichet("Guichet");

        //Activité :
        assertTrue(e.estUneActivite());

        //Guichet :
        assertFalse(e1.estUneActivite());

    }

    @Test
    void estUnGuichet() {

        Etape e = new Activite("Activite");
        Etape e1 = new Guichet("Guichet");

        //Activité :
        assertFalse(e.estUnGuichet());

        //Guichet :
        assertTrue(e1.estUnGuichet());

    }

    @Test
    void indicesTest()
    {
        FabriqueNumero.getInstance().reset();

        Etape e = new Activite("Activite");
        Etape e1 = new Guichet("Guichet");
        Etape e2 = new Activite("Activite");
        Etape e3 = new Guichet("Guichet");
        Etape e4 = new Activite("Activite");
        Etape e5 = new Guichet("Guichet");

        assertEquals(0,e.getIndiceEtape());
        assertEquals(1,e1.getIndiceEtape());
        assertEquals(2,e2.getIndiceEtape());
        assertEquals(3,e3.getIndiceEtape());
        assertEquals(4,e4.getIndiceEtape());
        assertEquals(5,e5.getIndiceEtape());

        assertEquals(1,((Guichet)e1).getSemaphore());
        assertEquals(2,((Guichet)e3).getSemaphore());
        assertEquals(3,((Guichet)e5).getSemaphore());
    }

    public void testSuccesseurs(){

    }
    public void testPredecesseurs(){

    }
}