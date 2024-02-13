#include "../ressource/codeC/def.h"

int main(int argc, char** argv) {

    //Préparation du monde
    int nbEtapes = 3;
    int nbGuichets = 1;
    int nbClients = 20;
    int* tabJetonsGuichet = {10};

    //Lancement de la simulation
    int* resSim;
    resSim = start_simulation(nbEtapes,nbGuichets,nbClients,tabJetonsGuichet);

    //Affichage des numéros de processus
    printf("Les clients :");
    for(int i=0; i<resSim.length; i++)
    {
        printf("\t%d",resSim[i]);
    }

    int* posClients = ou_sont_les_clients(nbEtapes,nbClients);
    //On affiche où sont les clients
    do
    {
        for(int i = 0; i<nbEtapes;i++)
        {
            printf("étape %d %d client(s) : ",i,posClients[i+nbClients*i]);
            int nbClientDansAct = posClients[i+nbClients*i];
            for(int j = i+nbClients*i + 1;j<i+nbClients*i + 1 + posClients[i+nbClients*i];++)
            {
                //j est initié à la position dans le tableau posClients où se trouve le premier client de l'étape
                //S'il n'y a aucun client dans l'étape alors j >= à la condition qui sort de la boucle
                printf("\t%d",posClient[j]);
            }
            printf("\n");
        }
    }while(posClients[(nbEtapes-1) + nbClients * (nbEtapes-1)])//while tous les clients ne sont pas dans le sasSortie donc posClient[nbAct-1 + nbClient * nbAct-1] == nbClient


    //On fait le nettoyage
    nettoyage();

    return 0 ;
    }