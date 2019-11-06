/*
	Given an array A of integers, we must modify the array in the following way: we choose an i and replace A[i] with -A[i], and we repeat this process K times in total.  (We may choose the same index i multiple times.)
	
	Return the largest possible sum of the array after modifying it in this way.
	
	 
	
	Example 1:
	
	Input: A = [4,2,3], K = 1
	Output: 5
	Explanation: Choose indices (1,) and A becomes [4,-2,3].
	Example 2:
	
	Input: A = [3,-1,0,2], K = 3
	Output: 6
	Explanation: Choose indices (1, 2, 2) and A becomes [3,1,0,2].
	Example 3:
	
	Input: A = [2,-3,-1,5,-4], K = 2
	Output: 13
	Explanation: Choose indices (1, 4) and A becomes [2,3,-1,5,4].
	 
	
	Note:
	
	1 <= A.length <= 10000
	1 <= K <= 10000
	-100 <= A[i] <= 100
	
	
	
	
	
	
	<문제>
	
	어레이 A와 숫자 K가 다음과 같이 주어진다.
	
	A = [2,-3,-1,5,-4], K = 2
	
	어레이의 숫자의 부호를 반대로 K번 바꿀수 있다고 했을 때, 얻을 수 있는 어레이 숫자들의 최대합은?
	
	위를 예로들면, 2번 부호를 바꿀 수 있는데, 가장 숫자가 작은 -4와 -3을 +4와 +3로 바꾸면
	 
	[2,3,-1,5,4]가 되어, 어레이의 합이 13이 된다.
	 
	A = [3,-1,0,2], K = 3
	
	의 경우, -1을 한번 뒤집고 0을 두번 뒤집으면,
	
	[3,1,0,2]가 되어, 총합인 6이 된다.
	
 */

package Greedy;

import java.util.Arrays;

public class MaximizeSumOfArrayAfterKNegations1005 {
	
	/*
	//<문제풀이1>
	
	//step1 - 마이너스값부터 플러스 값으로 바꾼다.
	
	//step2 - 마이너스 값을 모두 없앴다면, 몇번 더 부호를 바꿔야 하는지 봐서, 
	//		     짝수번 바꿔야 되면 어짜피 2번 뒤집을때마다 제자리니까 어레이의 합을 반환하고,
	//		     홀수번 뒤집어야하면, 가장 작은 숫자를 한번 뒤집어 준 어레이의 합을 반환한다.
	
	//Runtime: 5 ms, faster than 18.35% of Java online submissions for Maximize Sum Of Array After K Negations.
	//Memory Usage: 37.3 MB, less than 94.12% of Java online submissions for Maximize Sum Of Array After K Negations.
	
	public static int largestSumAfterKNegations(int[] A, int K) {
        Arrays.sort(A);
		
        int k = K;
		int smallest = Integer.MAX_VALUE;
		int idx = -1;
		
		for(int j = 0; j < A.length; j++) {
			if(smallest > Math.abs(A[j])) {
				smallest = Math.abs(A[j]);
				idx = j;
			}
		}
		
		for(int i = 0; i < K; i++) {
			if(A[i] < 0 && A[i+1] < 0) {
				A[i] *= -1;
				k--;
			}
			else if(A[i] < 0) { 
				A[i] *= -1;
				k--;
				break;
			}
			else {
				break;
			}
		}
		
		return k % 2 == 0 ? Arrays.stream(A).sum():Arrays.stream(A).sum() - (2 * A[idx]);
		
    }
	*/
	
	//<문제풀이 by votrubac>
	
	//A[i]의 범위가 -100에서 100까지라는 것을 이용한 풀이.
	
	//O(n)답게 int[]을 이용했고, 큰 틀은 위 풀이와 같음. 단지 Arrays.sort()로 정렬하는거나 가장 작은 숫자를 별도로 찾는 부분이 빠져서 4ms더 빠름.
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Maximize Sum Of Array After K Negations.
	//Memory Usage: 37 MB, less than 94.12% of Java online submissions for Maximize Sum Of Array After K Negations.
	
    public static int largestSumAfterKNegations(int[] A, int K) {
        int[] cnt = new int[201];
        int res = 0;
        for (int i : A) ++cnt[i + 100];
        for (int i = -100; i <= 100 && K > 0; ++i) {
            if (cnt[i + 100] > 0) {
               int k = i < 0 ? Math.min(K, cnt[i + 100]) : K % 2; //k = K%2부분이 마이너스 다 떨고 남은 K값 처리할때 쓰임. 
               cnt[-i + 100] += k;
               cnt[i + 100] -= k;
               K = i < 0 ? K - k : 0;
            }
         }
         for (int i = -100; i <= 100; ++i) res += i * cnt[i + 100];
         return res;
     }
	
	public static void main(String[] args) {
		int[] A = {2,-3,-1,5,-4};
		int K = 2;
		System.out.println(largestSumAfterKNegations(A, K));
	}
}

