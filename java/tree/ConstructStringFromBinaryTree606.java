package Tree;

public class ConstructStringFromBinaryTree606 {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	/*
	//<Trial01>
	
	//Input: [1,2,3,null,4]
	//Output : "1(2(4))(3)"
	//Expected : "1(2()(4))(3)"

	//null이라고 명시되서 나올때 어떻게 따로 처리를 할까.
	
	String rst = "";

	private void preOrder(TreeNode t, String open) {
		if (t == null) return;
		rst += open + (t.val + "");
		preOrder(t.left, "(");
		preOrder(t.right, "(");
		rst += ")";
	}

	public String tree2str(TreeNode t) {
		if (t == null) return "";			
		preOrder(t, "");
		return rst.substring(0, rst.length() - 1);
	}
	*/
	
	
	
	//<문제풀이 by StefanPochmann>
	
	//정규표현식 고인물이 푸는 방법.

	//+는 적어도 +이전에 나온게 한개 이상 있어야 매칭됨. $은 마지막을 나타냄.  
	
	//Input : [1,2,3,null,4]
	//Expected : "1(2()(4))(3)"
	
	//.replaceAll()을 빼고 돌리면 다음과 같은 결과가 나옴. 
	
	//"1(2(4()())())(3()())"
	
	//leaf면(노드의 가장 아랫쪽 끝단이라 left child, right child모두 없으면), ()()이 딸려나옴.
	
	//그걸 제거하는게 .replaceAll("(\\(\\))+$", "")임.
	
	//백슬래쉬는 다른 문자와 결합하면, 그 문자가 정규표현식에서 나타내는 의미가 아닌, 그 문자 고유의 의미로 바뀜.
	
	//에를들어, 마침표 .은 정규표현식에서는 모든 문자를 지칭하지만, 백슬래쉬가 붙은 마침표 \.은 그냥 마침표를 나타냄.
	
	//그래서 백슬래쉬 앞에 붙은 백슬래쉬 \\는 \을 타나내고, \뒤에 따라오는 (나 )와 결합해서
	
	//()가 되는것임.
	
	//\\(\\)을 감싸고있는 ()은, 문자 ()을 뜻하는게 아니라, 정규표현식에서 어떠한 문자를 감싸는 의미의 ()임.
	
	//그래서 껍데기 ()+$와 그 안의 내용물 \\(\\) == ()을 분리시켜서 보면 이해하기 편함. 
	
	//고인물같아 간지나긴 하지만, 성능은 쓰래기.
	
	//Runtime: 158 ms, faster than 5.00% of Java online submissions for Construct String from Binary Tree.
	//Memory Usage: 51 MB, less than 5.26% of Java online submissions for Construct String from Binary Tree.
	
	public String tree2str(TreeNode t) {
	    return t == null ? "" : (t.val + "(" + tree2str(t.left) + ")(" + tree2str(t.right) + ")").replaceAll("(\\(\\))+$", "");
	}
	
}
