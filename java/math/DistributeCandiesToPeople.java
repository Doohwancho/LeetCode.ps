package augustChallenge;

public class DistributeCandiesToPeople {
	
	//<Trial01>
	
	//제대로 뇌절했네
	
	class Solution {
	    
	    private int findLevel(int candies, int wholeCycle, int num_people){
	        int l = 1, r = (int)Math.sqrt(candies);
	        while(l <= r){
	            int m = (l+r)/2;
	            int t = wholeCycle*m+(num_people*num_people)*cumulatedSum(m-1);
	            if(t == candies){
	                return m;
	            } else if(t < candies){
	                l = m+1;
	            } else {
	                r = m-1;
	            }
	        }
	        return l;
	    }
	    
	    private int cumulatedSum(int i){
	        return i%2 == 1 ? i*(i/2)+i:(i+1)*i/2;
	    }
	    
	    public int[] distributeCandies(int candies, int num_people) {
	        int[] rst = new int[num_people];
	        
	        int wholeCycle = cumulatedSum(num_people);
	        int level = findLevel(candies, wholeCycle, num_people);
	        int dummies = candies - (wholeCycle*level+(num_people*num_people)*cumulatedSum(level-1));
	        
	        for(int i = 0, adder = num_people * cumulatedSum(level-1) + (i+1); i < num_people && candies > 0; i++){
	            rst[i] = (i+1 <= candies ? i+1 : candies) + (num_people * cumulatedSum(level-1)) + (dummies-adder > 0 ? adder : dummies > 0 ? dummies : 0);
	            dummies -= adder;
	            adder++;
	            candies -= rst[i];
	        }
	        
	        return rst;
	    }
	}
	
	
	//<문제풀이1 by lee215>
	
	//Runtime: 2 ms
	//Memory Usage: 36.9 MB
	
    public int[] distributeCandies(int candies, int n) {
        int[] rst = new int[n];
        for(int i = 0; candies > 0; i++){
            rst[i%n] += Math.min(candies, i+1);
            candies -= (i+1);
        }
        return rst;
    }
    
}
