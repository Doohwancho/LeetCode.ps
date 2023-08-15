#include <stdio.h>
#include <string.h>

int main()
{
    int T;

    scanf("%d", &T);

    for(int i = 0; i < T; i++)
    {
        char str[81];
        scanf("%s", str);

        int last = 0, sum = 0;
        for(int j = 0; j < strlen(str); j++)
        {
            if(str[j] == 'O')
            {
                last++;
                sum += last;
            }
            else if(str[j] == 'X')
            {
                last = 0;
            }
        }
        printf("%d\n", sum);
    }
    return 0;
}
