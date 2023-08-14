#include <stdio.h>
#include <string.h>

int main() {
    char grade[3];
    double gpa;

    scanf("%s", grade);

    if (strcmp(grade, "A+") == 0) {
        gpa = 4.3;
    } else if (strcmp(grade, "A0") == 0) {
        gpa = 4.0;
    } else if (strcmp(grade, "A-") == 0) {
        gpa = 3.7;
    } else if (strcmp(grade, "B+") == 0) {
        gpa = 3.3;
    } else if (strcmp(grade, "B0") == 0) {
        gpa = 3.0;
    } else if (strcmp(grade, "B-") == 0) {
        gpa = 2.7;
    } else if (strcmp(grade, "C+") == 0) {
        gpa = 2.3;
    } else if (strcmp(grade, "C0") == 0) {
        gpa = 2.0;
    } else if (strcmp(grade, "C-") == 0) {
        gpa = 1.7;
    } else if (strcmp(grade, "D+") == 0) {
        gpa = 1.3;
    } else if (strcmp(grade, "D0") == 0) {
        gpa = 1.0;
    } else if (strcmp(grade, "D-") == 0) {
        gpa = 0.7;
    } else {
        gpa = 0.0;
    }

    printf("%.1lf\n", gpa);

    return 0;
}

