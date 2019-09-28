package HashTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
	We define a harmounious array as an array where the difference between its maximum value and its minimum value is exactly 1.
	
	Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.
	
	Example 1:
	
	Input: [1,3,2,2,5,2,3,7]
	Output: 5
	Explanation: The longest harmonious subsequence is [3,2,2,2,3].
	 
 */

public class LongestHarmoniousSubseq594 {
	/*
	 * //<Trial01>
	 * 
	 * //harmonious arr이, 숫자의 차이가 정확히 1이여야 한다는 가장 기초적인 점을 간과함.
	 * 
	 * //정신 차리자.
	 * 
	 * public static int findLHS(int[] nums) { int largest = 0; int secondLargest =
	 * 0;
	 * 
	 * Map<Integer, Integer> map = new HashMap<>(); for(int num : nums) map.put(num,
	 * map.getOrDefault(num, 0)+1);
	 * 
	 * for(int value : map.values()) { if(value > largest) largest = value; else
	 * if(value > secondLargest) secondLargest = value; }
	 * 
	 * return largest + secondLargest; }
	 */

	/*
	 * //<문제풀이1>
	 * 
	 * //nums를 loop돌면서 map에 key:value를 숫자:빈도수로 집어넣고,
	 * 
	 * //keyset만 loop돌면서 1차이나는 숫자의 빈도수들 중, 가장 큰것을 반환하는 방법.
	 * 
	 * //좀 빠를줄알았는데, 생각보다 속도가 안나옴.
	 * 
	 * //물론 더 빠르게 할려면 int[]를 사용해야 하는데, 문제에서 주어진 input 조건이 Note: The length of the
	 * input array will not exceed 20,000.임.
	 * 
	 * //여기서 숫자가 몇개나오는진 알려줬는데, 숫자의 범위를 안알랴줌이기 때문에 숫자범위가 Integer.MIN_VALUE 부터
	 * Integer.MAX_VALUE 까지면 int[]선언시 답도없게 되서
	 * 
	 * //안했는데.. 흠
	 * 
	 * //Runtime: 29 ms, faster than 67.30% of Java online submissions for Longest
	 * Harmonious Subsequence. //Memory Usage: 39.9 MB, less than 94.74% of Java
	 * online submissions for Longest Harmonious Subsequence.
	 */
	/*
	public static int findLHS(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : nums)
			map.put(num, map.getOrDefault(num, 0) + 1);

		int LHS = 0;

		for (int num : map.keySet()) {
			if (map.containsKey(num - 1)) {
				int addedNum = map.get(num) + map.get(num - 1);
				if (addedNum > LHS)
					LHS = addedNum;
			}
		}
		return LHS;
	}
	*/
	// <문제풀이2 by andyreadsall>

	// 문제풀이1과 방법이 같은데, 특이점은 int대신 long 사용함. 이유는, int범위가 //2147483647 부터 -2147483648
	// 인데,

	// Integer.MAX_VALUE에서 +1하면 int의 최솟값이 나와버리기 때문에, 값의 범위가 더 큰 long사용.

	// 1ms정도 빠른 이유는 math.max()사용해서 불필요한 >연산과 addNum에 값 할당하는걸 안해서 그런듯.

	// Runtime: 27 ms, faster than 88.00% of Java online submissions for Longest
	// Harmonious Subsequence.
	// Memory Usage: 41.3 MB, less than 52.63% of Java online submissions for
	// Longest Harmonious Subsequence.

	/*
	public static int findLHS(int[] nums) {
		Map<Long, Integer> map = new HashMap<>();
		for (long num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		int result = 0;
		for (long key : map.keySet()) {
			if (map.containsKey(key + 1)) {
				result = Math.max(result, map.get(key + 1) + map.get(key));
			}
		}
		return result;
	}

	 */
	
	//<문제풀이 by Aayush_93>
	
	//오.. 빠름. 근데 메모리 많이먹음. Arrays.sort()시 int[]의 사이즈가 7이상이면 merge sort할텐데, 반으로 쪼개기를 겁나 많이할 때 메모리 겁나먹어서 그런듯.
	
	//역시 int[]가 가장 간단하면서 빠르네.
	
	//Runtime: 16 ms, faster than 96.54% of Java online submissions for Longest Harmonious Subsequence.
	//Memory Usage: 48.1 MB, less than 5.26% of Java online submissions for Longest Harmonious Subsequence.
	
	public static int findLHS(int[] nums) {
		Arrays.sort(nums);
		int min = 0;
		int count = 0;
		for (int i = 1; i < nums.length;) {
			if (nums[i] - nums[min] == 0)
				i++;
			else if (nums[i] - nums[min] == 1) {
				count = Math.max(count, i - min + 1);
				i++;
			} else
				min++;
		}
		return count;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 3, 2, 2, 5, 2, 3, 7 };
		System.out.println(findLHS(nums));
	}
}
