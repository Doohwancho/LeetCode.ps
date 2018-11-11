
public class smallestRange {

	class Solution {
	    public int smallestRangeI(int[] A, int K) {
	    Arrays.sort(A);
		return Math.max(0, A[A.length-1] - A[0] - 2*K);
	    }
	}
}
