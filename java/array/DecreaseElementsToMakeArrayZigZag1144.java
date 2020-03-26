package array;

public class DecreaseElementsToMakeArrayZigZag1144 {

	/*
	//<Trial01>
	
	//모르것따ㅏㅏㅏㅏ
	
	public int movesToMakeZigzag(int[] nums) {
        
        int rst = 0;
        for(int i = 1; i < nums.length-1; i++){
            if((nums[i-1] < nums[i] && nums[i] < nums[i+1]) || (nums[i-1] > nums[i] && nums[i] > nums[i+1])){
                rst = nums[i]-Math.min(Math.abs(nums[i]-nums[i+1]),Math.abs(nums[i]-nums[i-1]))+1;
            }
        }
        return rst;
    }
	*/
	
	
	//<문제풀이1 by lee215>
	
	//밑에 식을 압축한 것. 천잰가
	
	/*
	int movesToMakeZigzag(vector<int>& nums) {
	    if(nums.size()<3) return 0;
	    int res1=0;
	    for(int i=0;i<nums.size();i+=2){
	        int left=INT_MAX;
	        int right=INT_MAX;
	        if(i-1>=0) left=nums[i-1];
	        if(i+1<nums.size()) right=nums[i+1];
	        int target=min(left,right)-1;
	        res1+=max(0,nums[i]-target);
	    }
	    int res2=0;
	    for(int i=1;i<nums.size();i+=2){
	        int left=INT_MAX;
	        int right=INT_MAX;
	        if(i-1>=0) left=nums[i-1];
	        if(i+1<nums.size()) right=nums[i+1];
	        int target=min(left,right)-1;
	        res2+=max(0,nums[i]-target);
	    }
	    return min(res1,res2);
	}
	*/
	
	//case1)
	
	//int[] A : [9,6,1,6,2]
	
	//stdout
	//i: 0 res[0]: 4 res[1]: 0
	//i: 1 res[0]: 4 res[1]: 6
	//i: 2 res[0]: 4 res[1]: 6
	//i: 3 res[0]: 4 res[1]: 12
	//i: 4 res[0]: 4 res[1]: 12
	
	//Expected : 4
	
	
	//case2)
	
	//Input : [2,7,10,9,8,9]
	
	//stdout
	//i: 0 res[0]: 0 res[1]: 0
	//i: 1 res[0]: 0 res[1]: 6
	//i: 2 res[0]: 4 res[1]: 6
	//i: 3 res[0]: 4 res[1]: 8
	//i: 4 res[0]: 4 res[1]: 8
	//i: 5 res[0]: 4 res[1]: 10
	
	//Expected : 4
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Decrease Elements To Make Array Zigzag.
	//Memory Usage: 37.1 MB, less than 100.00% of Java online submissions for Decrease Elements To Make Array Zigzag.
	public int movesToMakeZigzag(int[] A) {
        int res[] = new int[2],  n = A.length, left, right;
        for (int i = 0; i < n; ++i) {
            left = i > 0 ? A[i - 1] : 1001;
            right = i + 1 < n ? A[i + 1] : 1001;
            res[i % 2] += Math.max(0, A[i] - Math.min(left, right) + 1); //Math.min(left, right)은 [1,2,4]나 [4,2,1]처럼 어느방향을 뺄지 모를때 사용. A[i] - Math.min(left, right) + 1은 방향결정 후, 얼마만큼 A[i]에서 빼야하는지에 관한 것. 구한 값이 음수면 더하지 않음. 계속 += 해주는 이유는 한쪽만 뽈록하고 나머진 다 오목하면 오목할때 더하질 못해서 어짜피 한번밖에 못더함
        }
        return Math.min(res[0], res[1]);
    }
}
