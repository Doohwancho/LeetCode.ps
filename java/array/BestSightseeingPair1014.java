package array;

public class BestSightseeingPair1014 {
	
	/*
	//<Trial01 - time limit exceeded>
	
	//brute-force
	
	public int maxScoreSightseeingPair(int[] A) {
        int rst = 0;
        
        for(int i = 0 ; i < A.length-1; i++){
            int adder = Integer.MIN_VALUE;
            int j = i+1;
            while(j < A.length){
                adder = Math.max(adder, A[j]-j++);
            }
            
            rst = Math.max(rst, A[i]+i+adder);
        }
        
        return rst;
    }
    */
	
	//<문제풀이1 by lee215>
	
	//미쳤다
	
	//Runtime: 2 ms, faster than 99.68% of Java online submissions for Best Sightseeing Pair.
	//Memory Usage: 54.3 MB, less than 7.69% of Java online submissions for Best Sightseeing Pair.
    public int maxScoreSightseeingPair(int[] A) {
        int res = 0, cur = 0;
        for (int a: A) {
            res = Math.max(res, cur + a);
            cur = Math.max(cur, a) - 1; //cur will record the best score that we have met. + when we move forward, sightsee-ing spot will be 1-distance further, therefore decrease by 1. -1 per each loop represents i-j
        }
        return res;
    }
}
