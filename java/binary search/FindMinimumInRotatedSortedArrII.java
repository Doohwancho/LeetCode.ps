package julyChallenge;

public class FindMinimumInRotatedSortedArrII {
	
	//<문제풀이1>
	
	//binary search
	
	//를 조금 바꾼 것.
	
	//X rotated : [1,2,3,4,5]
	//O rotated : [3,4,5,1,2] 
	
	//근데 포인터 m이 가운데인 5를 가르킬 때, 이게 rotated됬으면 l을 오른쪽으로 땡겨야 되고, rotated가 안된 그냥 [1,2,3,4,5]라면 r을 왼쪽으로 땡겨야 될거 아냐?
	
	//그걸 구분하는 로직을 만드는게 핵심이지?
	
	
	
	//if(nums[r] <= nums[m] && nums[l] >= nums[nums.length-1]) l++;
    
	//1) nums[r] <= nums[m] 
	
	//[3,3,1,3]에서, m이 두번째 3을 가르키고있으면, 오른쪽에 넘길라고 넣은 것.
	
	//2) nums[l] >= nums[nums.length-1]
	
	//[1,3,3]같은 경우에 왼쪽으로 땡겨야 되는데, nums[r] <= nums[m]만있으면 오른쪽으로 가겠지?
	
	//그래서 맨 왼쪽에 애 l(1)이 맨 오른쪽에 애보다 크거나 작은지 보는 조건을 넣은거여.
	
	//rotated되었을 경우에만 오른쪽으로 땡기라고. 아니면 왼쪽으로 땡기고.
	
	//Runtime: 0 ms
	//Memory Usage: 39.6 MB
	
    public int findMin(int[] nums) {
        int l = 0, r = nums.length-1;
        while(l < r){
            int m = (l+r)/2;
            if(nums[r] <= nums[m] && nums[l] >= nums[nums.length-1]){
                l++;
            } else {
                r = m;
            }
        }
        return nums[l];
    }
}
