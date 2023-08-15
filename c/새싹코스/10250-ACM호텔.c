#include <stdio.h>

int main()
{
    int T;
    int h,w,n;
    scanf("%d", &T);

    for(int i = 0; i < T; i++)
    {

        scanf("%d %d %d", &h, &w, &n);

        if(n % h == 0)
        {
            printf("%d%02d\n", h, n / h);
        }
        else
        {
            printf("%d%02d\n", n % h, n / h + 1);
        }
    }
    return 0;
}
