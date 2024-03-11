package javatest;

import org.junit.jupiter.api.Test;
import twisk.outils.KitC;

import static org.junit.jupiter.api.Assertions.*;

class KitCTest {

    @Test
    void creerEnvironnement() {
        KitC kit = new KitC();
        kit.creerEnvironnement();
    }
}