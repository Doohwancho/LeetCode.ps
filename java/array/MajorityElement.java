package mayChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

	
	// <문제풀이1>

	// 빈도수 체크하는 지루한 방식. 성능구림.

	// 46 / 46 test cases passed.
	// Status: Accepted
	// Runtime: 10 ms
	// Memory Usage: 45.4 MB

	public int majorityElement(int[] nums) {
		Map<Integer, Integer> m = new HashMap<>();
		for (int n : nums) {
			m.put(n, m.getOrDefault(n, 0) + 1);
			if (m.get(n) > nums.length / 2) {
				return n;
			}
		}
		return 0;
	}
	

	
	// <문제풀이2 by anpthai>

	// 힙한 풀이. 역시 성능은 개나줌.

	// 46 / 46 test cases passed.
	// Status: Accepted
	// Runtime: 10 ms
	// Memory Usage: 44.9 MB
	public int majorityElement(int[] nums) {
		Arrays.sort(nums);
		return nums[nums.length / 2];
	}
	

	
	//<문제풀이3 by mo10>
	
	//greedy
	
	//성능이 젤 좋다. Moore voting algorithm이라네.
	
	//http://www.cs.utexas.edu/~moore/best-ideas/mjrty/example.html
	
	//For the Moore algorithm, there should be a second loop after finding the candidate and the count,
	//to again iterate over the array and confirm if candidate's count > n/2 :
	//this will ensure that there is no false candidate produced.
	
	//그치. 가장 많이 카운트 되는건 반환하지만, nums.length/2이상 나오는지는 모르자너
	
	//46 / 46 test cases passed.
	//Status: Accepted
	//Runtime: 1 ms
	//Memory Usage: 42.3 MB
	
	public int majorityElement(int[] nums) {
		int cnt = 0, val = 0;
		for(int n : nums) {
			if(cnt == 0) {
				val = n;
			}
			if(val != n) {
				cnt--;
			} else {
				cnt++;
			}
		}
		return val;
	}
	
	
	
	//<문제풀이4 by kotomi_null>
	
	//한 숫자만 과반수 넘으면,
	
	//bitmask로 32개(맨 앞에 부등호 빼면 31개)의 자릿수 돌면서
	
	//과반수 넘는 bit만 더해주는 방법.
	
	//이것도 힙하네

	//46 / 46 test cases passed.
	//Status: Accepted
	//Runtime: 4 ms
	//Memory Usage: 42.9 MB
	
	public static int majorityElement(int[] nums) {
        int half = nums.length / 2;
        int major = 0;
        for(int i = 0; i < 32; i++) {
            int ones = 0;
            for(int num : nums) {
                ones += (num >> i) & 1;
            }
            if(ones > half) {
                major += 1 << i;
            }
            System.out.println("i: "+i+" ones: "+ones+" major: "+major);
        }
        return major;
    }
	
	public static void main(String[] args) {
		int[] nums = {3,2,3};
		System.out.println(majorityElement(nums));
	}
}
