
#include "../ressources/codeC/def.h"
#define sasEntree 0
#define guichet 2
#define activite 3
#define sasSortie 1

void simulation(int ids)
{

    //SasEntree
    entrer(sasEntree);
    delai(6,3);
    //Guichet
    transfert(sasEntree,guichet);
    P(ids,1);
    //Activite restreinte
    transfert(guichet,activite);
    delai(4,2);
    V(ids,1);
    //SasSortie
    transfert(activite,sasSortie);

}

/*void delai(int temps, int delta) // 0 < delta < temps < 100
void entrer(int etape)
void transfert(int source, int destination)*/