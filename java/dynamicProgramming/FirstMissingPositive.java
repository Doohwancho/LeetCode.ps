package september;

import java.util.PriorityQueue;

public class FirstMissingPositive {
	
	//<문제풀이1 by coderoath>
	
	//0보다 작거나 너무 큰 숫자는 맨 뒤로 보내버림. 크게 쓸모없는 숫자라.
	
	//else if는 nums[i]가 제자리에 있는 숫자인지 보는 것. 제자리에 있으면 오케이하고 다음으로 넘어감.
	
	//만약 nums[i]가 제자리에 있는애가 아니면, else에서 제자리로 보내버림. 그리고 다음으로 넘어가지 않고, 다시 새로 들어온 애 i번째애 체크.
	
	//else if구문이랑 else구문은 sort 알고리즘 중 하나인 듯. ASC로 정렬함.
	
	//[4,4,4,4,4,2,4] 이런 애도, else if에 4랑 같은애들이 걸려서 쭉 i++되다가
	
	//2에 왔을때 2가 else if에 걸려서  i번째로 가고,
	
	//그 다음 iterate땐 else문에 걸려서 2가 제자리로 돌아가게 되는 방법.
	
	//Runtime: 0 ms
	//Memory Usage: 36.6 MB
	
    public int firstMissingPositive(int[] nums){
        int rst = 1;
        int len = nums.length;
        int i = 0;
        int j = nums.length-1;
        while(i <= j){
            if(nums[i] < 1 || nums[i] >= len){ //nums[i] >= len인 이유는, 모든 애들이 다 len이상이면 어짜피 return 1할거아녀.
                swap(nums, i, j);
                j--;
            } else if(nums[i]==nums[nums[i]-1]){ 
                i++;
            } else{
                swap(nums, i, nums[i]-1);
            }
        }
        
        for(int n : nums){
            System.out.println(n);
            if(n != rst){
                return rst;
            }
            rst++;
        }
        return rst;
    }
    void swap(int[] nums,int i,int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
}
