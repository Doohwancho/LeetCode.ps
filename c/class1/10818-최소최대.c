#include <stdio.h>

#ifndef max
#define max(a,b) (((a) > (b)) ? (a) : (b))
#endif

#ifndef min
#define min(a,b) (((a) < (b)) ? (a) : (b))
#endif

int main()
{
    int a,b;
    int c = 1000001;
    int d = -1000001;

    scanf("%d", &a);
    for(int i = 0; i < a; i++)
    {
        scanf("%d", &b);
        c = min(c, b);
        d = max(d, b);
    }
    printf("%d %d\n", c,d);

    return 0;
}
