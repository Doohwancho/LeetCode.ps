#include <stdio.h>

int main()
{
    int a,b;
    scanf("%d %d", &a, &b);

    if(b >= 45)
    {
        printf("%d %d\n", a, (b-45));
    }
    else
    {
        if(a == 0)
        {
            printf("%d %d\n", 23, (b+15));
        }
        else
        {
            printf("%d %d\n", a-1, (b+15));
        }
    }
    return 0;
}
