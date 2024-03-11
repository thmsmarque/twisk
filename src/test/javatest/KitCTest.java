package javatest;

import org.junit.jupiter.api.Test;
import twisk.outils.KitC;

import static org.junit.jupiter.api.Assertions.*;

class KitCTest {

    @Test
    void creerEnvironnementtest() {
        KitC kit = new KitC();
        kit.creerEnvironnement(); //il se passe rien
    }

    @Test
    void creerFichiertest(){
        KitC kit = new KitC();
        kit.creerFichier("test\n test2 \n test3");
    }
}