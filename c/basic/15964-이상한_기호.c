#include <stdio.h>

long long annotation_calculate(long long a, long long b);

int main()
{
    long long a,b; //Q. (1 ≤ A, B ≤ 100,000) 라는데, 왜 int로 선언하면 30%만 패스하는걸까?
    scanf("%lld %lld", &a,&b);
    printf("%lld\n", annotation_calculate(a,b));
    return 0;
}

long long annotation_calculate(long long a, long long b)
{
    long long ret;
    ret = (a + b) * (a - b);
    return ret;
}
