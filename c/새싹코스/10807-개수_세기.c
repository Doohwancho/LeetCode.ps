#include <stdio.h>

int main()
{
    int a,b,c,ret;
    ret = 0;

    scanf("%d", &a);
    int arr[a];
    arr[0] = 0;

    for(int i = 0; i < a; i++)
    {
        scanf("%d", &b);
        arr[i] = b;
    }
    scanf("%d", &c);

    for(int i = 0; i < a; i++)
    {
        if(arr[i] == c) ret++;
    }
    printf("%d", ret);

    return 0;
}
