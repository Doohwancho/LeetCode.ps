#include <stdio.h>

#ifndef max
#define max(a,b) (((a) > (b)) ? (a) : (b))
#endif

#ifndef min
#define min(a,b) (((a) < (b)) ? (a) : (b))
#endif

int main(void){
    int a,b,c;

    scanf("%d %d %d", &a, &b, &c);

    if(a >= c && b >= c)
    {
        printf("%d", min(a,b));
    }
    else
    {
        printf("%d", min(max(a,b),c));
    }

    return 0;
}

