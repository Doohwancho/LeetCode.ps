package october;

public class SerializeAndDeserializeBST {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	//<Trial01>
	
	//[41,37,44,24,39,42,48,1,35,38,40,null,43,46,49,0,2,30,36,null,null,null,null,null,null,45,47,null,null,null,null,null,4,29,32,null,null,null,null,null,null,3,9,26,null,31,34,null,null,7,11,25,27,null,null,33,null,6,8,10,16,null,null,null,28,null,null,5,null,null,null,null,null,15,19,null,null,null,null,12,null,18,20,null,13,17,null,null,22,null,14,null,null,21,23]
    
	//왜지?
	
	//bst를 inorder로 돌으면 젤 작은애들 순서대로 튀어나오잖어 그걸로 string만들고
	
	//그 string을 다시 bst만들때 순서대로 돌면서 bst규칙에 맞게 넣었는디 흠;
	
	//젤 작은애부터 순서대로 넣는게 핵심이 아니었나부네
	
	private StringBuilder inOrder(TreeNode root){
		if (root == null) return null;
		
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        
		StringBuilder l = inOrder(root.left);
		if (l != null) sb.append(l);
        
		StringBuilder r = inOrder(root.right);
		if (r != null) sb.append(r);
		return sb;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return new String();
		return inOrder(root).toString();
    }

    private void insertBST(TreeNode parent, TreeNode root, int n, boolean left){
        if(root == null){
            TreeNode tmp = new TreeNode(n);
            if(left){
                parent.left = tmp;    
            } else {
                parent.right = tmp;
            }
            return;
        }
        if(n < root.val){
            insertBST(root, root.left, n, true);
        } else {
            insertBST(root, root.right, n, false);
        }
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0) return null; 
        
        char[] dataCharset = data.toCharArray();
        TreeNode rst = new TreeNode(Integer.parseInt(String.valueOf(dataCharset[0])));
        
        for(int i = 1; i < data.length(); i++){
            insertBST(rst, rst, Integer.parseInt(String.valueOf(dataCharset[i])), true);
        }
        return rst;
    }
    
    
    
    
    //<문제풀이 1 by naveen_kothamasu>
    
    //이친구는 preOrder로 돈 다음, bst방식에 맞게 집어넣었네?
    
    //왜지?
    
    //첫빠따가 serialize(root)에서 root의 첫빠따랑 같아야 해서 그런가?
    
    //inorder는 젤 작은애가 첫빠따가 되도록 설정되있단말야 trial01에서
    
    //근데 preorder쓰면 첫빠따가 멘 처음에 들어가니까 그런가?
    
    
    
    //PS: I have i=0 as class variable but it is not used for storing any state. I think the "state" in the question is talking about variables shared across serialize and deserialize calls.
    
    //이친구를 문제를 이렇게 해석했구만

    //Runtime: 11 ms
    //Memory Usage: 39.8 MB
    
    
    public String serialize(TreeNode root) {
        return preOrder(root);
    }
    private String preOrder(TreeNode root){
        Stack<TreeNode> s = new Stack<>();
        StringBuilder sb = new StringBuilder();
        TreeNode curr = root;
        while(curr != null || !s.isEmpty()){
            while(curr != null){
                sb.append(curr.val+" ");
                s.push(curr);
                curr = curr.left;
            }
            curr = s.pop();
            curr = curr.right;
        }
        return sb.toString();
    }
    public TreeNode deserialize(String data) {
        if(data.length() == 0) return null;
        String[] s = data.split(" ");
        return decode(s, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    int i = 0;
    private TreeNode decode(String[] s, Integer min, Integer max){
        if(i == s.length) return null;
        int val = Integer.parseInt(s[i]);
        if(val < min || val > max) {
            return null;
        }
        TreeNode curr = new TreeNode(val);
        ++i;
        curr.left = decode(s, min, curr.val);
        curr.right = decode(s, curr.val, max);
        return curr;            
    }
}
