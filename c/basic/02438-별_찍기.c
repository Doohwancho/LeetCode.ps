#include <stdio.h>

int main() {
    int a;
    scanf("%d", &a);

    char star[100];
    star[0] = '\0';

    for (int i = 0; i < a; i++) {
        strcat(star, "*");
        printf("%s\n", star);
    }

    return 0;
}

