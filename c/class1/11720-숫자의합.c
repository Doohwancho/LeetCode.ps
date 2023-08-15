#include <stdio.h>

int main()
{
    int a, b;
    scanf("%d", &a);
    char str[a];
    int ret = 0;

    scanf("%s", str);

    for(int i = 0; i < a; i++)
    {
        ret += (int)(str[i] - '0');
    }
    printf("%d\n", ret);
    return 0;
}
