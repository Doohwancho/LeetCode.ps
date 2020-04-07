package array;

public class FindPeakElement162 {
	
	/*
	//<문제풀이1>
	
	//이게 왜 medium이지
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Find Peak Element.
	//Memory Usage: 40.9 MB, less than 5.97% of Java online submissions for Find Peak Element.
    public int findPeakElement(int[] nums) {
        int idx = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i-1] < nums[i]){
                idx = i;
                if(i < nums.length-1 && nums[i] > nums[i+1]) return i;
            }
        }
        return idx;
    }
    */
	
    //<문제풀이2>
    
    //걍 i-1이 한번이라도 더 크다는건, 그게 peak라는 말이니까 바로 i-1반환해주면 되지
    
    //idx는 else문 통해 최대값의 인덱스로 계속 업데이트 시켜주고
    
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Peak Element.
    //Memory Usage: 40.2 MB, less than 5.97% of Java online submissions for Find Peak Element.
    public int findPeakElement(int[] nums) {
        int idx = 0;
        for(int i = 1; i < nums.length; i++)
            if(nums[i-1] > nums[i]) return i-1;
            else idx = i;        
        return idx;
    }
}
