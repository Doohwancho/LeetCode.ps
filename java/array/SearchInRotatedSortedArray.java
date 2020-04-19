package thirtyDayChallenge;

public class SearchInRotatedSortedArray {

	/*
	//<문제풀이1>
	
	//쉽게쉽게 갑시다 거

	//196 / 196 test cases passed.
	//Status: Accepted
	//Runtime: 1 ms
	//Memory Usage: 39.7 MB
	
    public int search(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target) return i;
        } 
        return -1;
    }
    */
	
	//<문제풀이2>
	
	//이거거등
	
	//{3,4,5,1,2}같이 1부터 쪼로록 시작 안해도, 어쨌든 오름차순 정렬되어있기 때문에 binary search 쓰면 되지롱
	
	//문제에서도 Your algorithm's runtime complexity must be in the order of O(log n)
	
	//이랬는데 O(log n)의 대표적인 예가 binary search 이지롱
	
	//일반적인 binary search는
	
	//m = (l+r)/2;
    //if(nums[m] == target) return m;
	//else if(nums[m] < target) l = m+1;
    //else r = m-1;
	
	//이거잖어?
	
	//근데 이 array는 앞에 반이 뚝 짤려서 뒤에 붙었잖어?
	
	//그래서 먼저 앞에 반에서 놀껀지 뒤에 반에서 놀껀지 부터 정해야 돼.
	
	//nums[m]이 앞에서 놀구있는데 target은 뒤에 있거나, 그 반대 상황이면 답이 이상하게 나와여
	
	//else if(nums[m] >= nums[0] && target < nums[0]) l = m+1;
    //else if(target >= nums[0] && nums[m] < nums[0]) r = m-1;
	
	//그래서 이 부분을 추가해줬징
	
	//196 / 196 test cases passed.
	//Status: Accepted
	//Runtime: 0 ms
	//Memory Usage: 38.7 MB
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        int m;
        while(l<r+1){
            m = (l+r)/2;
            if(nums[m] == target) return m;
            else if(nums[m] >= nums[0] && target < nums[0]) l = m+1;
            else if(target >= nums[0] && nums[m] < nums[0]) r = m-1;
            else if(nums[m] < target) l = m+1;
            else r = m-1;
        }
        return -1;
    }
}
