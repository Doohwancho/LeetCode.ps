package Array;

import java.util.Map;
import java.util.HashMap;

//hashmap method by caikehe
//for문을 돌면서 hashmap안에 target-해당 index의 값이 있는지 확인하는 방법

//Runtime: 2 ms, faster than 29.15% of Java online submissions for Two Sum II - Input array is sorted.
//Memory Usage: 38 MB, less than 97.19% of Java online submissions for Two Sum II - Input array is sorted.

public class TwoSumIISortedInputArray167ver3 
{
	public static int[] twoSum(int[] numbers, int target)
	{
		Map<Integer, Integer> map = new HashMap();
		int[] rst = new int[2];
		
		for(int i = 0; i < numbers.length; i++)
		{
			if(map.containsKey(target-numbers[i]))
			{
				rst[0] = map.get(target-numbers[i])+1;
				rst[1] = i+1;                           //i+1이 뒤에 있는 이유는 값 반환시, 리스트 안의 값들이 오름차순 정렬되있어야 하는데, map.get(target-numbers[i])+1이 먼저 들어간 값이라 i+1이 더 클수밖에 없기 때문. 
				break;
			}
			map.put(numbers[i], i+1);
		}
		return rst;
	}
	
	public static void main(String[] args) 
	{
		int[] numbers = {2,7,11,15};
		int target = 9;
		System.out.println(twoSum(numbers, target));
	}
}
