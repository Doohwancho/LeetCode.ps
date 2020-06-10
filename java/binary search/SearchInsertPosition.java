package juneChallenge;

public class SearchInsertPosition {
	
	//<문제풀이1>
	
	//binary search
	
	//EEEEEEEEEEEEZZZZZZZZZZZZZZZZZZZZZZZZZZ
	
	//target이 nums에 있으면 인덱스 반환하고, 없으면 -1을 반환하라고 켔으면
	
	//if(nums[m] == target)을 처음에 따로 빼놔서 return시키고
	
	//while이 다 돌면 -1으로 리턴하는 방식을 했을텐데,
	
	//만약에 target이 nums에 없다면, 있었어야 하는 자리를 반환하라고 해서 저리짬.
	
	//nums[m]이 target보다 같거나 크면, 일단 r을 target의 인덱스로 한다음,
	
	//l을 target의 인덱스까지 올려서 결국 l을 반환하는 것.
	
	//
	
	//Runtime: 0 ms
	//Memory Usage: 38.8 MB
	
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length;
        while(l < r){
            int m = (l+r)/2;
            if(nums[m] >= target){
                r = m;
            } else {
                l = m+1;
            }
        }
        return l;
    }
}
