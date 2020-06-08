package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum15 {

	
	//<Trial01>
	
	//binary search로 어떻게 지지고 볶았는데 잘 안되네
	
	//중복처리 어케하는지 모르게따ㅏㅏ
	
	//-4,-1,-1,0,1,2
	
	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> rst = new ArrayList<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			for (int j = i + 1; j < nums.length - 1; j++) {
				int l = j + 1, r = nums.length;
				while (l < r) {
					int m = (l + r) / 2;
					if(nums[m] + nums[i] + nums[j] == 0) {
						l = m;
						break;
					}
					if ((nums[m] + nums[i] + nums[j]) > 0) {
						r = m;
					} else {
						l = m+1;
					}
				}
				if(l < nums.length && nums[l] + nums[i] + nums[j] == 0) {
					List<Integer> tmp = new ArrayList<>();
					tmp.add(nums[i]);
					tmp.add(nums[j]);
					tmp.add(nums[l]);
					rst.add(tmp);
					System.out.println(rst);
				}
				if (nums[i] == nums[j]) { //중복처리 어케하까ㅏㅏㅏㅏㅏㅏ
                    int comp = nums[j];
                    while(j < nums.length && nums[j] == comp){
                        j++;
                    }
                    i = j-1;
					break;
				} 
				
			}
		}
		return rst;
	}
	
	//<문제풀이1 by shpolsky>
	
	//binary search
	
	//난 3번째 원소만 binary search썼는데 얜 두번째 세번쨰 둘다 binary search로 찾네
	
	//Runtime: 18 ms, faster than 89.77% of Java online submissions for 3Sum.
	//Memory Usage: 43.3 MB, less than 73.68% of Java online submissions for 3Sum.
	public List<List<Integer>> threeSum(int[] num) {
	    Arrays.sort(num);
	    List<List<Integer>> res = new LinkedList<>(); 
	    for (int i = 0; i < num.length-2; i++) {
	    	if(num[i] > 0) break;   //nums[i]가 양수라는건, num이 오름차순 정렬이 됬기 때문에, 두번째, 세번째 원소가 둘다 양수란 얘기니까 볼 필요 없음. break. by zizizi
	        if (i == 0 || num[i] != num[i-1]) { //첫번째 원소의 중복처리. 
	            int lo = i+1, hi = num.length-1, sum = 0 - num[i];
	            while (lo < hi) {
	                if (num[lo] + num[hi] == sum) {
	                    res.add(Arrays.asList(num[i], num[lo], num[hi]));
	                    while (lo < hi && num[lo] == num[lo+1]) lo++; //두번째 원소의 중복처리
	                    while (lo < hi && num[hi] == num[hi-1]) hi--; //세번째 원소의 중복처리
	                    lo++; hi--;
	                } else if (num[lo] + num[hi] < sum) lo++;
	                else hi--;
	           }
	        }
	    }
	    return res;
	}

	public static void main(String[] args) {
		int[] nums = { -1, 0, 1, 2, -1, -4 };
		System.out.println(threeSum(nums));
	}
}
