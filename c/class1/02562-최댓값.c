#include <stdio.h>

int main()
{
    int a = -1;
    int b;
    int index = 0;
    for(int i = 0; i < 9; i++)
    {
        scanf("%d", &b);
        if(b > a)
        {
            a = b;
            index = i+1;
        }
    }
    printf("%d\n", a);
    printf("%d\n", index);
    return 0;
}
