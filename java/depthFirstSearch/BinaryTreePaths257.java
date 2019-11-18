package DepthFirstSearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BinaryTreePaths257 {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	
	//<문제풀이1>
	
	//pre/in/post order traversal중 진행순서가 pre와 맞아서 pre를 씀.
	
	//https://doohwancho.tistory.com/category/Problem%20Solving/Data%20Structure%20%26%20Algorithm
	
	//매 노드마다 "-> node.val"을 String에 붙여주고,
	
	//자식이 없을 때(else문) 여태껏 붙여온 문자열을 더해줌.
	
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Binary Tree Paths.
    //Memory Usage: 36.6 MB, less than 100.00% of Java online submissions for Binary Tree Paths.
    
	
    List<String> list = new ArrayList<String>();
    
    private String dfs(TreeNode root, String str){
        if(root == null){
            return "";
        }
        if(!str.equals(root.val+"")){
            str += "->"+root.val+""; 
        }
        if(root.left != null || root.right != null){
            dfs(root.left, str);
            dfs(root.right, str);
        } 
        else{
            list.add(str);
        }
        return str;
    }
    

    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null) return list;
        dfs(root, root.val+"");
        return list;
    }
}
