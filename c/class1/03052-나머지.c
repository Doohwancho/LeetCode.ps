#include <stdio.h>

int main()
{
    int a, sum = 0;
    int arr[42] = { 0, };

    for(int i = 0; i < 10; i++)
    {
        scanf("%d", &a);
        arr[a % 42]++;
    }

    for(int i = 0; i < 42; i++)
    {
        if(arr[i] > 0) sum++;
    }

    printf("%d\n", sum);

    return 0;
}
