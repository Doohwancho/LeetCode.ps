/*
	Given a set of distinct integers, nums, return all possible subsets (the power set).
	
	Note: The solution set must not contain duplicate subsets.
	
	Example:
	
	Input: nums = [1,2,3]
	Output:
	[
	  [3],
	  [1],
	  [2],
	  [1,2,3],
	  [1,3],
	  [2,3],
	  [1,2],
	  []
	]
	
	
	<문제>
	
	
	Input: nums = [1,2,3]
	
	인풋 리스트의 값들의 콤비네이션을 가진 어레이 리스트를 반환하라.
 */

package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets78 {
	/*
	//<Trial01>

	//큰그림은 맞았는데 방법에서 막힘 제길
	
	//List<Integer> temp = new ArrayList<>();    
    //[[1,2,3],[1,2],[1,3],[1],[2,3],[3],[]]
    
	private static void helper(List<Integer> tmp, int[] nums, int i, int size) {
    	if(size > 0) {
    		List<Integer> temp = new ArrayList<>();
        	helper(temp,nums,i+1,size-1);
        	for(int j = i+1; j < nums.length; j++) {
        		tmp.add(nums[j]);
        	}
        }
    }
    
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        int size = nums.length;
        for(int i = 0; i < size; i++) {
        	List<Integer> tmp = new ArrayList<>();
        	tmp.add(nums[i]);
        	helper(tmp, nums, i, size);
        	rst.add(tmp);
        }
        return rst;
    }
    */
	
	//<문제풀이1 by syftalent>
	
	//recursive
	
	//Runtime: 1 ms, faster than 27.76% of Java online submissions for Subsets.
	//Memory Usage: 37.2 MB, less than 99.18% of Java online submissions for Subsets.
	
//	idx: 0  path: []        ret: []
//	idx: 1  path: [1]       ret: [[]]
//	idx: 2  path: [1, 2]    ret: [[], [1]]
//	idx: 3  path: [1, 2, 3] ret: [[], [1], [1, 2]]
//	idx: 3  path: [1, 3]    ret: [[], [1], [1, 2], [1, 2, 3]]
//	idx: 2  path: [2]       ret: [[], [1], [1, 2], [1, 2, 3], [1, 3]]
//	idx: 3  path: [2, 3]    ret: [[], [1], [1, 2], [1, 2, 3], [1, 3], [2]]
//	idx: 3  path: [3]       ret: [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3]]
	
    public static List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), ret);
        return ret;
    }

    private static void dfs(int[] nums, int idx, List<Integer> path, List<List<Integer>> ret) {
    	System.out.println("idx: "+idx+"  path: "+path+"      ret: "+ret);
        ret.add(path);
        for (int i = idx; i < nums.length; i++) {
            List<Integer> p = new ArrayList<>(path); //path와 p를 구분지어 줌으로서 i번째에서 res.add(path)하고 i+1번에서 res.add(path)했을 때, path가 동일 주소값을 가지고 있기 때문에 i+1번째 값이 i번째 path에 overwrite되는 것을 방지
            p.add(nums[i]);
        	dfs(nums, i+1, p, ret);
        }
    }
    public static void main(String[] args) {
		int[] test = new int[3];
		test[0] = 1;
		test[1] = 2;
		test[2] = 3;
		System.out.println(subsets(test));
	}
}
