package DepthFirstSearch;

import java.util.Stack;

public class ConvertSortedArraytoBinarySearchTree108 {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	//<문제풀이1 by caikehe>
	
	//binary search방법을 고대로 쓴 것. 왜 이 쉬운 생각을 못했을까.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Sorted Array to Binary Search Tree.
	//Memory Usage: 36.9 MB, less than 100.00% of Java online submissions for Convert Sorted Array to Binary Search Tree.
	
	public static TreeNode sortedArrayToBST(int[] nums) {
	    return helper(nums, 0, nums.length-1);
	}

	private static TreeNode helper(int[] nums, int l, int r) {
	    if (l > r) {
	        return null;
	    }
	    if (l == r) { //주어진 노드의 원소가 한개밖에 없으면, 해당 원소 반환
	        return new TreeNode(nums[l]);
	    }
	    TreeNode node = new TreeNode(nums[(l+r)/2]); //주어진 nums의 가운데것을 root삼음
	    node.left = helper(nums, l, (l+r)/2-1);  //binary search방식으로 left child, right child 채움
	    node.right = helper(nums, (l+r)/2+1, r);
	    return node;
	}

	public static void main(String[] args) {
		int[] nums = {-10,-3,0,5,9};
		System.out.println(sortedArrayToBST(nums));
	}
}
