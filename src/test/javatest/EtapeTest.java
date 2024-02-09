package javatest;

import org.junit.jupiter.api.Test;
import main.java.twisk.monde.Activite;
import main.java.twisk.monde.Etape;
import main.java.twisk.monde.Guichet;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class EtapeTest {

    @Test
    void testConstructeur(){

    }

    @Test
    void ajouterSuccesseur() {
        Etape e = new Activite("Activite");
        Etape e3 = new Activite("Activite",5,5);
        Etape e1 = new Guichet("Guichet");
        Etape e2 = new Guichet("Guichet",5);
        Etape e4 = new Activite("Activite");
        Etape e5 = new Activite("Activite");

        e.AjouterSuccesseur(e3,e1,e2);
        e4.AjouterSuccesseur(e5);
        Iterator<Etape> ite = e.iterator();
        Iterator<Etape> ite4 = e4.iterator();

        assertEquals(ite.next(),e3);
        assertEquals(ite.next(),e1);
        assertEquals(ite.next(),e2);
        assertEquals(ite4.next(),e5);
        //A revoir, ne fonctionne pas :
        assertFalse(ite4.hasNext());


    }

    @Test
    void estUneActivite() {
        Etape e = new Activite("Activite");
        Etape e3 = new Activite("Activite",5,5);
        Etape e1 = new Guichet("Guichet");
        Etape e2 = new Guichet("Guichet",5);

    }

    @Test
    void estUnGuichet() {
        Etape e = new Activite("Activite");
        Etape e3 = new Activite("Activite",5,5);
        Etape e1 = new Guichet("Guichet");
        Etape e2 = new Guichet("Guichet",5);

    }
}