package javatest;

import main.java.twisk.monde.Activite;
import main.java.twisk.monde.Etape;
import main.java.twisk.monde.Guichet;
import org.junit.jupiter.api.Test;

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

        assert(e.iterator().next()==e3) : "Le successeur n'est pas défini";



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