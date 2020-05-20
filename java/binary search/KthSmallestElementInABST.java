package mayChallenge;

public class KthSmallestElementInABST {

	//<문제풀이1>
	
	//머리 싸매고 고민중이었는데 그냥 간단한거였음.
	
	//binary search tree라고 했으니까 inorder로 돌면 가장 작은 숫자대로 순서대로 나올거아녀
	
	//cnt로 몇번째 작은 숫자인지 파악한 다음에, k번째 작은 숫자가 등장하면 rst에 넣어서 계산하면 되지
	
	//근데 여기서 한발짝 더 나아가서
	
	//bst(root.right, k)할 때, if(cnt < k)를 추가시켜서 optimize시킴. (mach7의 아이디어)
	
	//이미 cnt==k이 되었다면, 더이상 right node볼 필요없이 바로 root로 올라가면 되자너
	
    int cnt = 0;
    int rst = Integer.MAX_VALUE;
    
    private void bst(TreeNode root, int k){
        if(root == null) return;
        bst(root.left, k);
        cnt++;
        if(cnt == k){
            rst = root.val;
        }
        //bst(root.right, k); //will also do.
        if(cnt < k) bst(root.right, k);
    }
    
    public int kthSmallest(TreeNode root, int k) {
        bst(root, k);
        return rst;
    }
    
    
    //<문제풀이2 by miaow>
    
    //global variable 사용하는게 꺼려진다면, cnt, rst변수 대신에 int[]타입인 counter, result를 이용하는 방법이 있다.
    
    public int kthSmallest(TreeNode root, int k) {
        int [] counter = new int[]{0};
        int [] result = new int[]{0};
        inOrder(root, k, counter, result);
        return result[0];
    }

    private void inOrder (TreeNode root, int k, int[] counter, int[] result) {
        if (root != null) {
            inOrder(root.left, k, counter, result);
            counter[0]++;
            if (counter[0] == k) {
                result[0] = root.val;
                return;
            }
            inOrder(root.right, k, counter, result);
        }
    }
    
    //<문제풀이3 by ... 이름이 없는 고인물 친구>
    
    //stack을 이용한 방법
    
    //in-order recursive와 같은 방법
    
    //이긴 한데, cnt를 찾자마자 return해준다는 점에서 
    
    //in-order보다 쪼~금 더 빠름
    
    //root까지 다시 안올라가도 되자너
    
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> st = new Stack<>();
        TreeNode p = root;
        int cnt = 0;
        
        while(!st.isEmpty() || p != null){
            if(p != null){
                st.push(p);
                p = p.left;
            } else {
                TreeNode node = st.pop();
                if(++cnt == k) return node.val;
                p = node.right;
            }
        }
        return 0;
    }
}
