package array;

public class FindMinimumInRotatedSortedArr153 {
	
	/*
	//<문제풀이1>
	
	//이게 왜 medium문제지? 
	
	//Runtime: 1 ms, faster than 5.26% of Java online submissions for Find Minimum in Rotated Sorted Array.
	//Memory Usage: 39.9 MB, less than 6.82% of Java online submissions for Find Minimum in Rotated Sorted Array.
    public int findMin(int[] nums) {
        for(int i = 1; i < nums.length; i++){
            if(nums[i] < nums[i-1]){
                return nums[i];
            }
        }
        return nums[0];
    }
    */
	
	/*
	//<문제풀이2>
	
	//1ms아낄려고 을마나 많은 시간을 쏟는건진 모르겠지만
	
	//일단 [1,2,3]처럼 축회전된 arr을 묻는 문제에서 축회전이 안된상태로 나오거나 [2] 이따구로 나오면 예외처리해주는 걸 if문에 해줌
	
	//else문은 binary search를 이용한 것. [3,4,5,1,2]도 오름차순-ish하게 정렬되있자너
	
	//우리가 찾는건 1인데, 3,4,5에 nums[m]이 걸려있으면, nums[0]인 3보다 nums[m]이 더 크니까, 오른쪽으로 땡기고
	
	//만약 nums[m]이 1이나 2, 2라고 하자 2에 걸쳐있으면, nums[0]인 3보다 작으니까 왼쪽으로 땡기는겨
	
	//그러다가 nums[m]이 nums[m-1]보다 작을 경우(전체 arr에서 딱 한번뿐이여. 5,1 이부분)
	
	//1을 뺀다
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array.
	//Memory Usage: 39.6 MB, less than 35.23% of Java online submissions for Find Minimum in Rotated Sorted Array.
    public static int findMin(int[] nums) {
        if(nums.length == 1 || nums[nums.length-1] > nums[0]){
            return nums[0];
        } else {
            int l = 1, r = nums.length-1;
            while(l <= r){
                int m = (l+r)/2;
                if(nums[m] < nums[m-1]){
                    return nums[m];
                }
                else{
                    if(nums[m] > nums[0]){
                        l = m+1;
                    } 
                    else {
                        r = m-1;
                    }
                }
            }
            return nums[l];
        }
    }
    */
    
    //<문제풀이3 by diaa>
    
    //if(nums[m] > nums[r]) l = m+1; 일단 이부분을 써서 [3,4,5,1,2]를 예로들면 [3,4,5]에서 빼내고
    
    //else r = m; 을 써서 1,2, ... 에서 가장 작은수인 1로 땡긴 후
    
    //nums[l]을 반환하는 방법.
	
	//이 방법을 쓰면 문제풀이2에서 쓰인 번거로운 예외처리를 안해줘도 된다.
	
	//[1]같은 경우 while문이 안돌아서 바로 반환할거고
	
	//[2,1]의 경우도 2는 if문에서 걸러지고 1은 else문에서 확정됨.
	
	//[1,2,3]의 경우도 if문이 한번도 안돌고 else문만 돌아서 결국 가장 작은수인 1로 확정될 것이기 때문.
    
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array.
    //Memory Usage: 39.2 MB, less than 59.09% of Java online submissions for Find Minimum in Rotated Sorted Array.
    public static int findMin(int[] nums) {
        int l = 0, r = nums.length-1, m;
        while(l < r){
            m = (l+r)/2;
            if(nums[m] > nums[r]){
                l = m+1;
            } else {
                r = m;
            }
        }
        return nums[l];
    }
	
	public static void main(String[] args) {
		int[] test = {4,5,6,7,0,1,2};
		System.out.println(findMin(test));
	}
    
}
