#include <stdio.h>
 #ifndef max
 #define max(a,b)  (((a) > (b)) ? (a) : (b))
 #endif

 #ifndef min
 #define min(a,b)  (((a) < (b)) ? (a) : (b))
 #endif

int main(void)
{
    int a,b,c;
    int d,e;
    int ret;
    scanf("%d %d %d %d %d",&a,&b,&c,&d,&e);
    ret = min(a,(min(b,c))) + min(d,e) - 50;
    if(ret < 0)
    {
        printf("0\n");
    }
    printf("%d", ret);
    return 0;
}
