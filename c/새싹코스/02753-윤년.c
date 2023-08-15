#include <stdio.h>

int main(void){
    int year;
    scanf("%d", &year);
    if(year % 400 == 0)
    {
        printf("1\n");
    }
    else if(year % 100 == 0)
    {
        printf("0\n");
    }
    else if(year % 4 == 0)
    {
        printf("1\n");
    }
    else
    {
        printf("0\n");
    }
    return 0;
}
