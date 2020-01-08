package Array;

import java.util.ArrayList;
import java.util.List;

public class Subsets78 {
    private static void helper(List<List<Integer>> rst,List<Integer> tmp, int[] nums, int size) {
//    	List<Integer> temp = new ArrayList<>();
    	System.out.println("size: "+ size);
        for(int i = nums.length-size; i < nums.length; i++){
        	if(size > 0) {
            	helper(rst, tmp, nums, size-1);
            }
        	tmp.add(nums[i]);
        	System.out.println(tmp);
        }   
        rst.add(tmp);
    }
    
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        int size = nums.length;
        List<Integer> tmp = new ArrayList<>();
        helper(rst, tmp, nums, size);
        return rst;
    }
    public static void main(String[] args) {
		int[] test = new int[3];
		test[0] = 1;
		test[1] = 2;
		test[2] = 3;
		System.out.println(subsets(test));
	}
}
