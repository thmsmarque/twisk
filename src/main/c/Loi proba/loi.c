
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <math.h>

void delaiUniforme(int temps, int delta)
{
    int bi, bs;
    int n, nbSec;
    bi = temps - delta;
    if (bi < 0)
        bi = 0;
    bs = temps + delta;
    n = bs - bi + 1;
    nbSec = (rand() / (float)RAND_MAX) * n;
    nbSec += bi;
    printf("%d\n", nbSec);
}

void delaiGauss(double moyenne, double ecartype)
{
    double U1 = rand() / (double)RAND_MAX;
    double U2 = rand() / (double)RAND_MAX;
    double Z = sqrt(-2.0 * log(U1)) * cos(2.0 * M_PI * U2);
    double X = moyenne + Z * ecartype;
    printf("%f\n", X);
}

void delaiExponentiel(double lambda)
{
    double U = rand() / (double)RAND_MAX;
    double X = -log(U) / lambda;
    printf("%f\n", X);
}

int main(int argc, char const *argv[])
{
    srand(time(NULL));
    double moyenne = 10.0;
    double lambda = 1.0 / moyenne;
    for (int i = 0; i < 5000; i++)
    {
        delaiExponentiel(lambda);
    }

    // GAUSS :
    /*
     double moyenne = 10.0;
    double ecartype = 4.0;
    for (int i = 0; i < 5000; i++) {
        delaiGauss(moyenne, ecartype);
    }
    */
    // UNIFORME :
    /*
    int temps = 10;
     int delta = 4;
     for (int i = 0; i < 5000; i++) {
         delaiUniforme(temps, delta);
     }
    */

    return 0;
}
