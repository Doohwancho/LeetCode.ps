/*
	Let's call an array A a mountain if the following properties hold:
	
	A.length >= 3
	There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
	Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].
	
	Example 1:
	
	Input: [0,1,0]
	Output: 1
	Example 2:
	
	Input: [0,2,1,0]
	Output: 1
	Note:
	
	3 <= A.length <= 10000
	0 <= A[i] <= 10^6
	A is a mountain, as defined above.
 */

package BinarySearch;

public class PeakIndexInAMountainArr852 {
	
	/*
	//<문제풀이1>

	//순서대로 돌면서 상승하지 않는 지점의 -1이 최정상이므로, i-1을 반환하는 방법.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Peak Index in a Mountain Array.
	//Memory Usage: 38.8 MB, less than 90.00% of Java online submissions for Peak Index in a Mountain Array.
	
	public static int peakIndexInMountainArray(int[] A) {
        for(int i = 1; i < A.length; i++){
            if(A[i] <= A[i-1]) return i-1;
        }
        return -1;
    }
    */
	
	//<문제풀이2 by lee215>
	
	//Binary search, O(logN)
	
	//매 loop마다 반틈씩 똑 때서 비교하고 오른쪽이면 오른쪽, 왼족이면 왼쪽 살리는 방법.
	
	//하지만 {0,0,0,0,0,1,0}같이 A[i] == A[i-1]인 곳엔 쓰일 수 없음.
	
	//leetcode의 test input이 운좋게 맞아 떨어져서 된듯..
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Peak Index in a Mountain Array.
	//Memory Usage: 38.9 MB, less than 86.00% of Java online submissions for Peak Index in a Mountain Array.
	
    public static int peakIndexInMountainArray(int[] A) {
        int l = 0, r = A.length-1, m = 0;
        while(l < r) {
        	m = (l+r)/2;
        	if(A[m] < A[m+1]) {
        		l = m+1;
        	}
        	else {
        		r = m;
        	}
        }
        return l;
    }
	
	public static void main(String[] args) {
		int[] A = {0,1,2,3,4,3,2,1};
		System.out.println(peakIndexInMountainArray(A));
	}
}













