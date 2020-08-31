package augustChallenge;

public class DeleteNodeInABST {
	

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
	
	//<문제풀이01>
	
	//binary search tree
	
	//key = key.right로 덮어쓰고, key.left를 pre-order-traversal로 돌면서 key.right에 하나하나 insert하는 방식
	
	//약간 똥꼬쑈한거같긴한데 성능 좋으면 됬지 뭘.
	
	//Runtime: 0 ms
	//Memory Usage: 40 MB
	
	
	//pre-order-traversal로 key.left를 돌면서, 하나하나 key를 덮어쓴 key.right에 bst규칙에 맞게 넣어줌
    private void dfs(TreeNode tmpLeft, TreeNode root){ //dfs의 왼쪽 파라미터는 insert해줄 key.left이고, 오른쪽 파라미터는 root를 덮어쓴 key.right임
        if(tmpLeft == null) return;
        addLeft(root, tmpLeft.val);
        dfs(tmpLeft.left, root);
        dfs(tmpLeft.right, root);
    }
    
    private void addLeft(TreeNode root, int key){
        if(root == null) return;
        
        TreeNode newNode = new TreeNode(key); 
        TreeNode x = root; 
        TreeNode y = null; 

        while (x != null) { 
            y = x; 
            if (key < x.val) 
                x = x.left; 
            else
                x = x.right; 
        } 
        
        if(y == null){
            y = newNode;
        }
        else if(key < y.val){
            y.left = newNode;
        } else {
            y.right = newNode;
        }
        
        return;
    }
    
    private TreeNode findNode(TreeNode root, TreeNode prev, int key){
        if(root == null) return null;
        
        //if found, node = node.right, add previous root.left to updated root according to BST rules
        if(root.val == key){
            if(root.right != null){
                TreeNode tmp = root.left; //왼쪽애들 일단 빼놓고
                root = root.right; //root를 root.right에 덮어써서 key를 지워버림
                if(prev == null){  //첫빠따가 key일 경우, 따로처리
                    TreeNode tmpRoot = root;
                    dfs(tmp, root); //dfs의 왼쪽 파라미터는 insert해줄 key.left이고, 오른쪽 파라미터는 root를 덮어쓴 key.right임
                    return tmpRoot;
                }
                else if(prev.val > key){
                    prev.left = root;
                } else {
                    prev.right = root;
                }
                dfs(tmp, root);
                return null;
            } else {
                if(prev == null){
                    return root.left;
                }
                else if(prev.val < root.val){
                    prev.right = root.left;
                } else {
                    prev.left = root.left;
                }
                return null;
            }
        }
        
        prev = root;
        
        //find node on bst
        if(key < root.val){
            findNode(root.left, prev, key);
        } else {
            findNode(root.right, prev, key);
        }
        
        return root;
    }
    
    public TreeNode deleteNode(TreeNode root, int key) {
        return findNode(root, null, key);
    }
    
    
    
    
    
    //<문제풀이2 by booboohsu>
    
    //음 이분도 미친새끼구만.
    
    //key를 찾았는데, key.left == null이면, key = key.right아면 되고,
    
    //key.right == null이면, key = key.left하면 되는데,
    
    //key.left랑 key.right랑 둘 다 살아있으면 어떻게 할까가 문제인 거잖아?
    
    //난 key = key.right로 덮어쓰고, key.left를 pre-order-traversal로 돌면서 key.right에 하나하나 insert하는 방식을 썼는데,
    
    //얘는 key.left는 내비 두고, key.right에 젤 작은 수를 찾은 후, key에 박아버림.
    
    //(key.left에 있는 모든수들은 key.right에 젤 작은수보다 무조건 작을 수 밖에 없기 때문)
    
    //그리고 key.right에 가서 아까 박은 key.right에 젤 작은 수를 찾아 없애버림.
    
    //가장 기존 structure을 데미지 안주는 방법인덧.
    
    //80줄쓴거 30줄에 해결했네. 그리고 수많은 if-else문 안씀 + 이전 노드 참조하는 prev도 안씀. 깔끔 그 자체.
    
    //Runtime: 0 ms
    //Memory Usage: 40.2 MB
    
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null){
            return null;
        }
        if(key < root.val){
            root.left = deleteNode(root.left, key);
        }else if(key > root.val){
            root.right = deleteNode(root.right, key);
        }else{
            if(root.left == null){
                return root.right;
            }else if(root.right == null){
                return root.left;
            }
            
            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    private TreeNode findMin(TreeNode node){
        while(node.left != null){
            node = node.left;
        }
        return node;
    }
}
