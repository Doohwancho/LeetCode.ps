package array;

import java.util.Arrays;

public class MovingStonesUntilConsecutiveII1040 {
	
	//<문제풀이1 by lee215>
	
	//step1) moving rightmost or leftmost stone.
	//input : [6,5,4,3,10] --Arrays.sort()--> [3,4,5,6,10]
	//A[n - 1] - A[1] - n + 2 : 1+ [(A[n-1]-n+1)-(A[1]+1)+1] : 1+[(10-5+1)-(4+1)+1] : 3(= moving leftmost stone)
	//A[n - 2] - A[0] - n + 2 : 1+ [(A[n-2]-1)-(A[0]+n-1)+1] : 1+[(6-1)-(3+5-1)+1] : 0(= moving rightmost stone)
	
	//input : [1,100,102,200]
	//98 : 1(처음 1을 101로 옮긴 것) + [197(마지막엔 [197,198,199,200]으로 끝나는데, 그 중 가장 왼쪽 수)-101(1을 A[1]+1로 옮긴 수)+1]
	//107 : 1(처음 200을 101로 옮긴 것) + [101-(1+5-1)+1]
	
	//let's say you move A[0], then to calculate the available slots: (A[-1]-A[1]+1; 
	//total slots between A[-1], A[1]) -(n-3; in between you have n-3 occupied already) -2 (A[-1],A[1], also occupied); 
	//so you get ---> A[n - 1] - n + 2 - A[1]

	//모르게따ㅏㅏㅏㅏㅏㅏㅏ 패쓰!

	//The first step(as in each step) has to be moving rightmost or leftmost stone.
	//Starting the second step, the optimal way is to increment leftmost or decrement rightmost by ONE for each move.
	//To do so, we need two OR MORE adjacent stones that contain either the leftmost or the rightmost stone. e.g. [1,2,3,4,10] ->[2,3,4,5,10]->[3,4,5,6,10] ...
	//In the first step, we need to move leftmost or rightmost stone to somewhere that could form this adjacent structure.
	//To move A[0], it hasn't to be A[1]+1 since that may have already been occupied. So move A[0] to the first unoccupied slot to the right of A[1]. e.g[1,4,5,6,10]->[4,5,6,7,10]. It's certainly possible that no such slot exists when A[1]~A[n-1] are adjacent. That means we cannot move A[0]. If neither A[0] or A[n-1] is movable, the answer is 0.
	//Therefore, the unoccupied slots between A[1] and A[n-1] OR A[0] and A[n-2] are the maximum steps to move.
	
	//Runtime: 5 ms, faster than 96.64% of Java online submissions for Moving Stones Until Consecutive II.
	//Memory Usage: 41.4 MB, less than 100.00% of Java online submissions for Moving Stones Until Consecutive II.
	
    public int[] numMovesStonesII(int[] A) {
        Arrays.sort(A);
        int i = 0, n = A.length, low = n;
        int high = Math.max(A[n - 1] - A[1] - n + 2, A[n - 2] - A[0] - n + 2); //fill in all empty slots between A[1] ~ A[n-1] or A[0] ~ A[n - 2]
        for (int j = 0; j < n; ++j) {
            while (A[j] - A[i] >= n) ++i;	//The maximum number of elements that can be between A[j] and A[i] is 'n', therefore we reduce the gap between A[j] and A[i] till n-1
            if (j - i + 1 == n - 1 && A[j] - A[i] == n - 2)
                low = Math.min(low, 2);
            else
                low = Math.min(low, n - (j - i + 1));
        }
        return new int[] {low, high};
    }
}
