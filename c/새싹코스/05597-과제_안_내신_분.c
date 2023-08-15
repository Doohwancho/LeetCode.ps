#include <stdio.h>

#ifndef max
#define max(a,b) (((a) > (b)) ? (a) : (b))
#endif

#ifndef min
#define min(a,b) (((a) < (b)) ? (a) : (b))
#endif

int main()
{
    int a, ret1 = 0, ret2 = 0;
    int arr[30] = { 0, };

    for(int i = 0; i < 28; i++)
    {
        scanf("%d", &a);
        arr[a-1] = 1;
    }

    for(int i = 0; i < 30; i++)
    {
        if(arr[i] == 0)
        {
            if(ret1 == 0)
            {
                ret1 = i+1;
            } else
            {
                ret2 = i+1;
            }
        }
    }
    printf("%d\n", min(ret1, ret2));
    printf("%d\n", max(ret1, ret2));
    return 0;
}
