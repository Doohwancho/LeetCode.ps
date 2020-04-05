package array;

public class RemoveDuplicatesFromSortedArrayII80 {
	
	/*
	//<문제풀이1>
	
	//개 똥꼬쑈 레알로다가
	
	//먼저 2개 이상 중복된 숫자는 artificial_number로 다 채운 다음, 그다움 loop에서 artificial_number가 나오면 그 다음것들과 자리 바꾸는 방법
	
	//인데 너무 개 어거지네
	
	//Runtime: 1 ms, faster than 13.63% of Java online submissions for Remove Duplicates from Sorted Array II.
	//Memory Usage: 39.8 MB, less than 5.26% of Java online submissions for Remove Duplicates from Sorted Array II.
	public int removeDuplicates(int[] nums) {
        int len = nums.length;
        int duplicates = 0;
        int artificialNum = -100;
        
        for(int i = 0, j = 0, dup = 0; i < nums.length; i++){
            if(nums[i] == nums[j]){
                dup++;
            } else {
                if(dup <= 2){
                    j = i;
                    dup = 1;
                } else {
                    int i_ = i-1;
                    while(dup > 2){
                        nums[i_] = artificialNum;
                        i_--;
                        dup--;
                        duplicates++;
                    }
                    j = i;
                    dup = 1;
                }
            }
            if(i == len-1 && dup > 2){
                int i_ = i-1;
                while(dup > 2){
                    nums[i_] = artificialNum;
                    i_--;
                    dup--;
                    duplicates++;
                }
            }
        }
        
        for(int i = 0; i < len-1; i++){
            if(nums[i] == artificialNum){
                int minuses = 0;
                int i_ = i;
                int j = i;
                while(j < len && nums[j] == artificialNum){
                    j++;
                    minuses++;
                }
                while(j < len && minuses > 0){
                    nums[i_] = nums[j];
                    nums[j] = artificialNum;
                    i_++;
                    j++;
                    minuses--;
                }
            }
        }
        
        return len-duplicates;
    }
    */
	
	//<문제풀이2 by StefanPochmann>

	//ㅏ...
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted Array II.
	//Memory Usage: 39.8 MB, less than 5.26% of Java online submissions for Remove Duplicates from Sorted Array II
	public static int removeDuplicates(int[] nums) {
	    int i = 0;
	    for (int n : nums) {
	        if (i < 2 || n > nums[i-2]) {
	        	nums[i++] = n;
	        }
	    }
	            
	    return i;
	}
	
	public static void main(String[] args) {
		int[] test = {1,1,1,2,2,3,3,3};
		removeDuplicates(test);
	}
}
