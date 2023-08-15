#include <stdio.h>
#include <string.h>

int main()
{
    char a[1000001];
    int alphabet[26];
    int max_cnt = 0;
    int size;
    char ans;

    scanf("%s", a);
    size = strlen(a);

    for(int i = 0; i < size; i++)
    {
        if(a[i] >= 'a')
        {
            alphabet[a[i]-'a']++;
        }
        else
        {
            alphabet[a[i]-'A']++;
        }
    }

    for(int i = 0; i < 26; i++)
    {
        if(alphabet[i] == max_cnt)
        {
            ans = '?';
        }
        else if(alphabet[i] > max_cnt)
        {
            max_cnt = alphabet[i];
            ans = i+'A';
        }
    }

    printf("%c", ans);

    return 0;
}
