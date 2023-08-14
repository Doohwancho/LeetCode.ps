#include <stdio.h>

int main()
{
    char str[1001];
    int a;
    scanf("%s %d", str, &a);
    printf("%c", str[a-1]);
    return 0;
}
