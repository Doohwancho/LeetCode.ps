package september;

import java.util.TreeSet;

public class ContainsDuplicateIII {
	
	
	//<문제풀이1>
	
	//brute-force
	
	//long변환을 중간에 한건, Integer.MAX_VALUE을 초과하는 연산이 나와서 그럼.
	
	//다른방법은 뭐가있을까.
	
	//Runtime: 493 ms
	//Memory Usage: 40.1 MB
	
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        
        long[] lnums = new long[len];
        for(int i = 0; i < len; i++){
            lnums[i] = (long)nums[i];
        }
        
        k = k >= len ? len-1 : k;
        
        for(int i = 0, j = k; i < len; i++){
            
            for(int p = j; p > i; p--){
                if(Math.abs(lnums[p]-lnums[i]) <= t) return true;    
            }
            if(j+1 < len){ //j를 index out of bound안뜨게 len-1을 limit으로 잘 조절해준다.
                j++;
            }
        }
        return false;
    }
    
    
    //<Trial01>
    
    //nums의 누적합을 구해서 k번째 떨어져있는애랑 차이가 t이하면 return true
    
    //일반적인 input에서는 되는데, accum[i]+accum[p]가 Integer.MIN-VALUE인 경우,
    
    //-2147483648 인데, Math.abs(2147483648)을 했을때,
    
    //-2147483648 이 나와서 실패.
    
    //왜냐면 int의 최대범위는 -2147483648부터 +2147483647까지이기 때문.
    
    //그래서 int[] accum을 long[] accum으로 바꾸면, Time limit exceeded가 뜸 제기랄?
    
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        
        int[] accum = new int[len];
        
        for(int i = 1; i < len; i++){
            accum[i] = accum[i-1]+nums[i]-nums[i-1];
        }
        
        for(int i = len-1, j = i-k; i >= 0; i--, j--){
            for(int p = j >= 0 ? j : 0; p < i; p++){
                if(Math.abs(accum[i]-accum[p]) <= t) return true;
            }
        }
        
        return false;
    }
    
    
    
    //<문제풀이2 by jinwu>
    
    //Treeset, O(N lg K)
    
    //Runtime: 28 ms
    //Memory Usage: 43.6 MB
    
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null && nums.length == 0 || k == 0){
            return false;
        }
        
        TreeSet<Long> ts = new TreeSet<>();
        
        for(int i = 0; i < nums.length; i++){
            //find floor, ceiling
            Long floor = ts.floor((long)nums[i]);
            Long ceiling = ts.ceiling((long)nums[i]);
            if(floor != null && nums[i]-floor <= t ||
               ceiling != null && ceiling-nums[i] <= t){
                return true;
            }
            
            //add
            ts.add((long)nums[i]);
            
            //remove prev
            if(i >= k){
                ts.remove((long)nums[i-k]);    
            }
        }
        
        return false;
    }
}
