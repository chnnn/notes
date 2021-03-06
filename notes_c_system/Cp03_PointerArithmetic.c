#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <math.h>
#include <string.h>
int main(int argc, char const *argv[])
{
    int x = 5;
    int* p = &x;
    *p = 6;
    int **q = &p;
    int ***r = &q;
    printf("%d\n", *p);
    printf("%d\n", *q);
    printf("%d\n", **q);
    printf("%d\n", **r);
    printf("%d\n", ***r);
    ***r = 10;
    printf("x = %d\n", x);
    **q = *p + 2;
    printf("x = %d\n", x);

    printf("*p = %d\n", *p);
    printf("p = %d\n", p);
    *p++; // careful
    printf("*p = %d\n", *p);
    printf("p = %d\n", p);
    printf("-----------------------\n");
    p = 1; // compiler warning: assignment to ‘int *’ from ‘int’ makes pointer from integer without a cast
    printf("p = %d\n", p);
    //! printf("*p = %d\n", *p); // runtime segmentation fault
    return 0;
}
