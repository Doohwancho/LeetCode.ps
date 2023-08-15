#include <stdio.h>
#include <math.h>

int getVerifierNumber(int a, int b, int c, int d, int e);

int main()
{
    int a,b,c,d,e;
    scanf("%d %d %d %d %d", &a,&b,&c,&d,&e);
    printf("%d\n", getVerifierNumber(a,b,c,d,e));
}

int getVerifierNumber(int a, int b, int c, int d, int e)
{
    return (int)(pow(a,2) + pow(b,2) + pow(c,2) + pow(d,2) + pow(e,2)) % 10;
}

