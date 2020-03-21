/*
 
	<문제>
	
	배열이 [2,0,2,1,1,0] 이렇게 주어지면
	
	오름차순 정렬하라.
 
 */

package array;

public class SortColors75 {
	
	/*
	//<Trial01>
	
	//우리 쉽게쉽게 좀 가자 친구야 ㅋㅋ
	
	//Note: You are not suppose to use the library's sort function for this problem.
	
    public void sortColors(int[] nums) {
        Arrays.sort(nums);
    }
    */
	
	/*
	//<문제풀이1>
	
	//단어의 빈도수를 파악하고, two pointer이용하여 빈도수 하나씩 까면서 숫자를 순서대로 업데이트 하는 방법.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Sort Colors.
	//Memory Usage: 38.2 MB, less than 5.51% of Java online submissions for Sort Colors.
	
	public void sortColors(int[] nums) {
        int maxNum = 0;
        for(int element : nums){
            maxNum = Math.max(maxNum, element);
        }
        
        int[] container = new int[maxNum+1];
        for(int element : nums){
            container[element]++;
        }

        for(int i = 0, j = 0; i < nums.length; ){
            if(container[j] > 0){
                nums[i] = j;
                container[j]--;
                i++;
            }   
            else {
                j++;
            }
        }
    }
	*/
	
	//<문제풀이2 by BichengWang>
	
	//재미난 로직이구만 기래
	
	//2 1 0 0 1 2 
	//2 1 0 0 1 2 
	//1 1 0 0 2 2 
	//1 1 0 0 2 2 
	//1 1 0 0 2 2 
	//0 1 1 0 2 2 
	//0 0 1 1 2 2
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Sort Colors.
	//Memory Usage: 38.2 MB, less than 5.51% of Java online submissions for Sort Colors.
    public void sortColors(int[] nums) {
        int begin = 0;
        int end = nums.length - 1;
        int mid = 0;

        while(mid <= end){
            if(nums[mid] == 0) swap(nums, begin++, mid++);
            else if(nums[mid] == 2) swap(nums, mid, end--);
            else mid++;
        }
    }
    
    private void swap(int[] nums, int l, int r){
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
    
    public static void main(String[] args) {
    	int[] test = {2,1,0,0,1,2};
    	SortColors75 obj = new SortColors75();
    	obj.sortColors(test);
	}
}
