#include <stdio.h>
#include <string.h>

int main()
{
    int alphabet[26];
    char str[101];
    scanf("%s", str);

    int size = strlen(str);

    for (int i = 0; i < 26; i++) {
        alphabet[i] = -1;
    }

    for(int i = 0; i < size; i++)
    {
        if(alphabet[str[i]-'a'] != -1) continue;
        alphabet[str[i]-'a'] = i;
    }

    for(int i = 0; i < 26; i++)
    {
        printf("%d ", alphabet[i]);
    }
    printf("\n");
    return 0;
}
