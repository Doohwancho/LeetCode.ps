#include <stdio.h>
#include <string.h>

int main()
{
    char str[101];
    scanf("%s", str);

    for(int i = 0; i < 100; i++)
    {
        if(islower(str[i]))
        {
            str[i] = toupper(str[i]);
        } else
        {
            str[i] = tolower(str[i]);
        }
    }
    printf("%s", str);

    return 0;
}
