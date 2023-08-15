#include <stdio.h>

int main()
{
    int x,y,input;
    scanf("%d %d", &x, &y);
    int arr1[x][y];
    int arr2[x][y];

    for(int p = 0; p < x; p++)
    {
        for(int q = 0; q < y; q++)
        {
            scanf("%d", &input);
            arr1[p][q] = input;
        }
    }

    for(int p = 0; p < x; p++)
    {
        for(int q = 0; q < y; q++)
        {
            scanf("%d", &input);
            arr2[p][q] = input;
        }
    }

    for(int p = 0; p < x; p++)
    {
        for(int q = 0; q < y; q++)
        {
            arr1[p][q] += arr2[p][q];
            printf("%d ", arr1[p][q]);
        }
        printf("\n");
    }
    return 0;
}
