
//solution1

//Runtime: 8 ms, faster than 77.94% of C++ online submissions for Prime Number of Set Bits in Binary Representation.
//Memory Usage : 5.9 MB, less than 80.66 % of C++ online submissions for Prime Number of Set Bits in Binary Representation.

//�̰ź��� �� ���� ���� �ֳ�?

//�����Լ�����ϳ�?

//isPrime() -> O(N)

//numberOfBinary() -> O(1)


class Solution {
public:
    bool isPrime(int n) {
        if (n == 0 || n == 1) return false;

        for (int i = 2; i <= n / 2; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    int numberOfBinary(int n) {
        int count = 0;
        while (n) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    int countPrimeSetBits(int left, int right) {
        int rst = 0;
        for (int i = left; i <= right; i++) {
            if (isPrime(numberOfBinary(i))) {
                rst++;
            }
        }
        return rst;
    }
};