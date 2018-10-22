/*Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].*/


package LeetCode;

public class LeetCode01 {

   int[] numResult = new int[2];
   public int[] twoSum(int[] nums, int target) {
      int i = 0;
      int j = 0;

      for (i = 0; i < nums.length - 1; i++)
         for (j = i + 1; j < nums.length; j++)
            if (nums[i] + nums[j] == target) {
               numResult[0] = i;
               numResult[1] = j;
               return numResult;
            }
      return numResult;
   }

   public static void main(String[] args) {
      int[] nums = {2,7,11,13};
      LeetCode01 s2 = new LeetCode01();
      s2.twoSum(nums, 9);
      for(int i=0; i<s2.numResult.length; i++)
         System.out.println(s2.numResult[i]);
   }
}