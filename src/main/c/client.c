#include <stdio.h>

#include "../ressource/codeC/def.h"

void simulation(int ids)
{
    int etapeActuelle = 0, destination = 2;
    entrer(etapeActuelle);
    delai(6,3);
    transfert(etapeActuelle,destination);
    etapeActuelle = destination;
    destination = 1;
    delai(3,1);
    transfert(etapeActuelle,destination);
    etapeActuelle = destination;

}

/*void delai(int temps, int delta) // 0 < delta < temps < 100
void entrer(int etape)
void transfert(int source, int destination)*/