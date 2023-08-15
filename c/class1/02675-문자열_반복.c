#include <stdio.h>
#include <string.h>

int main()
{
    int a;
    scanf("%d", &a);
    for(int i = 0; i < a; i++)
    {
        int b;
        char str[21];
        scanf("%d %s", &b, str);

        for(int j = 0; j < strlen(str); j++)
        {
            for(int p = 0; p < b; p++)
            {
                printf("%c", str[j]);
            }
        }
        printf("\n");
    }
    return 0;
}
