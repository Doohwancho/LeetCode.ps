package HashTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SingleNumber136 {
	
	/*
	//<문제풀이1>
	
	//딕셔너리에 key를 원소, value를 해당 원소의 빈도수를 넣고, 빈도수가 1이면 해당 원소를 반환하는 방식.
	
	//느리고 메모리 100%아님
	
	//Runtime: 10 ms, faster than 10.41% of Java online submissions for Single Number.
	//Memory Usage: 39.9 MB, less than 96.30% of Java online submissions for Single Number.
	
	public static int singleNumber(int[] nums) 
	{
	    Map<Integer, Integer> container = new HashMap<>();
	    
	    for(int i = 0; i < nums.length; i++)
	    {
	    	container.put(nums[i], container.getOrDefault(nums[i], 0)+1);
	    }
	    
	    Iterator<Integer> keys = container.keySet().iterator();
	    
	    while(keys.hasNext())
	    {
	    	int key = keys.next();
	    	if(container.get(key)==1)
	    	{
	    		return (int)key;
	    	}
	    }
		return 0;
	}
	*/
	
	/*
	
	//<문제풀이2>
	
	//먼저 오름차순 정렬을 해주고, 1st,2nd 페어끼리 비교해가며, 다르면 첫번째꺼를 반환하는 방법.
	
	//페어비교하다가 {2,2,3,3,4,4,5}처럼 마지막에 하나 남은것을 가려내기 위해 if(i == len-2) return nums[len-1]; 사용
	
	//7ms단축했지만 100%아님
	
	//Runtime: 3 ms, faster than 44.35% of Java online submissions for Single Number.
	//Memory Usage: 38.8 MB, less than 98.52% of Java online submissions for Single Number.
	
    public static int singleNumber(int[] nums) {
    	//유효성검사
    	if(nums.length == 1) return nums[0];
    	
        int len = nums.length;
        Arrays.sort(nums);

		for(int i = 1; i < len; i+=2)
        {
			if(nums[i]==nums[i-1])
			{
				if(i == len-2) return nums[len-1];
				continue;
			}
			else return nums[i-1];
		}
		return 0;
    }
    */
	
	//<문제풀이3 by jeantimex>
	
	//XOR연산자 활용
	
	//XOR연산자인 ^=은 이렇게 동작함.
	
	//A^A = 0, A^B^A = B.
	
	//왜냐면 ^는 같으면 false,다르면 true이기 때문에, A^A는 false. 따라서 0. 
	
	//따라서, A^A^B == 0^B == B
	
	//신기허네

	//nums[]의 값들 : 4 - 1 - 2 - 1 - 2
	//ans의 변화 :    4 - 5 - 7 - 6 - 4
	
	
    public static int singleNumber(int[] nums) {
        int ans = 0;

        for (int i = 0; i < nums.length; i++)
        {
        	ans ^= nums[i];
        }

        return ans;
    }
    
	
	
	public static void main(String[] args) 
	{
		int[] nums = {4,1,2,1,2};
		//int[] nums = {2,2,1};
		System.out.println(singleNumber(nums));
	}
}
