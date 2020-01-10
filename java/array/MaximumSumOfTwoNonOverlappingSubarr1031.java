package Array;

public class MaximumSumOfTwoNonOverlappingSubarr1031 {
	/*
	//<trial01>
	
	//M,L둘 중 더 긴 것의 최대 subarray를 먼저 구하고 짧은 subarray를 나중에 구하는 방식
	
	//그런데 어떨땐 짧은 subarray가 더 효과적일 때가 있음.
	
	//그걸 자동화시켜 코드에 녹여야하는데...
	 
	//아직 초보티가 많이 나네.
	
    private void findMaxSum(int[] A, int[] valueAndCoord, int cap, boolean avoid){
        for(int i = 0; i < A.length-cap+1; i++){
            if(avoid && i >= valueAndCoord[2] && i <= valueAndCoord[3]){
                continue;
            }
            int l = i;
            int r = i + cap;
            int total = 0;

            while(l < r){
                total += A[l++];
            }

            if(avoid){
                if(total > valueAndCoord[1]){
                    valueAndCoord[1] = total;
                }   
            } else {
                if(total > valueAndCoord[0]){
                    valueAndCoord[0] = total;
                    valueAndCoord[2] = i;
                    valueAndCoord[3] = r-1;
                }    
            }
        }
    }
    
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
    	int[] valueAndCoord = new int[4];
    	int larger;
        int smaller;
        if(L > M){
            larger = L;
            smaller = M;
        } else {
            larger = M;
            smaller = L;
        }
        findMaxSum(A, valueAndCoord, larger, false);
        findMaxSum(A, valueAndCoord, smaller, true);
        return valueAndCoord[0]+valueAndCoord[1];
    }
    */
	
	//<문제풀이1 by lee215>
	
	//step1. find the global maximum of the window on the left
	//step2. find the maximum of the second window in the region to the right of the first window
	
	//case 1: L-window comes before M-windows
	//Once L-window reaches it's global maximum, it will stop sliding but M window can keep going on
	
	//case 2: M-window comes before L-windows
	//Once M-window reaches it's global maximum, it will stop sliding but L window can keep going on


	
	//----------------- len == N
	//---------------------------- len == M
	//|_______________|________________________|
	//0---------------lmax-------------------- i(N+M)
    
    //ex1)
    
    //A : [0,6,5,2,2,5,1,9,4], L : 1, M : 2
    
	//i: 3 (Lmax : 6 L_rest: 7)  (Mmax: 11 R_rest: 2) res: 13
	//i: 4 (Lmax : 6 L_rest: 4)  (Mmax: 11 R_rest: 2) res: 13
	//i: 5 (Lmax : 6 L_rest: 7)  (Mmax: 11 R_rest: 5) res: 16
	//i: 6 (Lmax : 6 L_rest: 6)  (Mmax: 11 R_rest: 1) res: 16
	//i: 7 (Lmax : 6 L_rest: 10) (Mmax: 11 R_rest: 9) res: 20
	//i: 8 (Lmax : 6 L_rest: 13) (Mmax: 11 R_rest: 4) res: 20

    
    //ex2)
    
    //A: [3,8,1,3,2,1,8,9,0], L : 3, M : 2
    
	//i: 5 (Lmax : 12 L_rest: 3)  (Mmax: 11 R_rest: 6)  res: 17
	//i: 6 (Lmax : 12 L_rest: 9)  (Mmax: 11 R_rest: 11) res: 22
	//i: 7 (Lmax : 12 L_rest: 17) (Mmax: 11 R_rest: 18) res: 29
	//i: 8 (Lmax : 12 L_rest: 9)  (Mmax: 11 R_rest: 17) res: 29
	
	//머리 꺠지겠다. 존나 똑똑허이...
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Sum of Two Non-Overlapping Subarrays.
	//Memory Usage: 38.6 MB, less than 8.70% of Java online submissions for Maximum Sum of Two Non-Overlapping Subarrays.
	
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        for (int i = 1; i < A.length; ++i)
            A[i] += A[i - 1];
        int res = A[L + M - 1], Lmax = A[L - 1], Mmax = A[M - 1];
        for (int i = L + M; i < A.length; ++i) {
            Lmax = Math.max(Lmax, A[i - M] - A[i - L - M]); 
            Mmax = Math.max(Mmax, A[i - L] - A[i - L - M]);
            res = Math.max(res, Math.max(Lmax + A[i] - A[i - M] /* take rest into account to avoid overlapping */, Mmax + A[i] - A[i - L]));
        }
        return res;
    }
    public static void main(String[] args) {
		
	}
	
}
