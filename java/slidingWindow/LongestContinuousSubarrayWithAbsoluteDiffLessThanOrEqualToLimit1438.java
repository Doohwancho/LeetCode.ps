package array;

import java.util.TreeMap;

public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit1438 {

	//<Trial01>
	
	//sliding window
	
	//기초 뼈대 인데 최댓값 최솟값 개념만 이식하면 될거같은데
	
    public int longestSubarray(int[] nums, int limit) {
        int rst = 0;
        
        for(int i = 0, j = 0, diff = 0; j < nums.length; j++){
            if(Math.abs(nums[j]-nums[i]) > limit){
                while(Math.abs(nums[j]-nums[i]) > limit){
                    i++;
                }
            } else {
                rst = Math.max(rst, j-i+1);
            }
        }
        
        return rst;
    }
    
    
    //<문제풀이1>
    
    //나, 천재, 강림 
    
    //time: O(n^2)
    //space: O(n)
    
    //Runtime: 36 ms, faster than 59.64% of Java online submissions for Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit.
    //Memory Usage: 65.1 MB, less than 12.82% of Java online submissions for Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit.
    
    public int longestSubarray(int[] nums, int limit) {
        int rst = 0;
        
        for(int i = 0, j = 0, maxi = nums[0], mini = nums[0]; j < nums.length; j++){ //j는 맨 오른쪽애, i는 맨 왼쪽애
            maxi = Math.max(maxi, nums[j]); //j가 어디까지 갔냐가 중요한게 아니라, i~j중에 젤 큰애랑 젤 작은애가 누구냐가 중요. 그걸 maxi,mini에 계속 업데이트
            mini = Math.min(mini, nums[j]);
            
            if(maxi-mini > limit){ //만약 limit을 벗어났으면
                maxi = Integer.MIN_VALUE; //maxi,mini를 초기화 해주고
                mini = Integer.MAX_VALUE;
                
                while(++i < j && Math.abs(nums[j]-nums[i]) > limit){} //i를 문제가 안터지는 지점까지 오른쪽으로 땡김
                
                int i_ = i;
                while(i_ <= j){ //새로운 i가 정해졌으니, i~j까지의 최대수, 최소수를 다시 업데이트 시켜줌.
                    maxi = Math.max(maxi, nums[i_]);
                    mini = Math.min(mini, nums[i_]);
                    i_++;
                }
            } else { //i~j범위안 최대값-최솟값차가 limit범위 안에있으면 rst에 업데이트
                rst = Math.max(rst, j-i+1);
            }
        }
        
        return rst; 
    }
    
    
    
    //<문제풀이2 by prdp89>
    
    //treemap을 이용해서 최댓값,최솟값 구하는법
    
    //time: O(NlogN)
    //space: O(N)
    
    //Runtime: 190 ms, faster than 7.77% of Java online submissions for Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit.
    //Memory Usage: 108.5 MB, less than 5.02% of Java online submissions for Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit.
    
    public int longestSubarray(int[] A, int limit) {
        int i = 0, j;
        TreeMap<Integer, Integer> m = new TreeMap<>();
        for (j = 0; j < A.length; j++) {
            m.put(A[j], 1 + m.getOrDefault(A[j], 0));
            if (m.lastEntry().getKey() - m.firstEntry().getKey() > limit) {
                m.put(A[i], m.get(A[i]) - 1);
                if (m.get(A[i]) == 0)
                    m.remove(A[i]);
                i++;
            }
        }
        return j - i;
    }
}
