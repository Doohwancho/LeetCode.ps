#include <stdio.h>

int main()
{
    int a;
    scanf("%d", &a);

    char str[a+1];
    str[a] = '\0';

    for(int i = 0; i < a; i++)
    {
        str[i] = ' ';
    }

    for(int i = a; i > 0; i--)
    {
        str[i-1] = '*';
        printf("%s\n", str);
    }

    return 0;
}
