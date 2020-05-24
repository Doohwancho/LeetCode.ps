package mayChallenge;

public class ConstructBinarySearchTreeFromPreorderTraversal {

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

	// <Trial01>
	
	//이론상 완벽한데? 하고 돌렸는데,
	
	//결과값이 []로 나옴. 암것도 안들어감.
	
	//메서드가 끝나면, 메서드 안에서 선언된 변수, 객체가 날아감. 
	
	//void로 선언했기 때문에, 변수든 객체든 유효범위가 메서드의 {}가 끝나는 순간, 없어지는듯.
	
	//System.identityHashCode(root)로 객체주소를 확인해 보면,
	
	//root = null일땐, root의 주소가 0이었다가, bst()메서드로 들어가서 
	
	//root = new TreeNode(val)을 해서 이어진 뒤, root의 주솟값이 생기지만, 
	
	//bst()메서드가 끝나는 순간, void때문에 아무것도 리턴하지 않았으므로, new TreeNode(val)의 객체주소가 날아가버려서 
	
	//다시 root의 주소가 0으로 리셋되는걸 확인 가능.
	
	TreeNode root = null;

	public void bst(TreeNode root, int val) {
		if (root == null) {
			root = new TreeNode(val);
		} else if (val < root.val) {
			bst(root.left, val);
		} else {
			bst(root.right, val);
		}
	}

	public TreeNode bstFromPreorder(int[] preorder) {
		for (int p : preorder) {
			bst(root, p);
		}
		return root;
	}
	
	//<문제풀이1>
	
	//Trial01에서 약간 변형
	
	//return 값을 줘서 root = bst(root, p)로 붙여버리면, 메서드{}안에서 선언된게 안날아가고 붙음.
	
	//=가 메서드 바깥에 있어서 메서드가 다 끝나도, 변수를 안죽이고 붙이는 역할을 하나봄.
	
	//trial01에서 root = new TreeNode(val);할때, =로 붙이긴 했는데,
	
	//메서드가 끝나면서, =연산자도 변수랑 똑같이 {}안에서만 유효하기 때문에, {}가 사라지면, 같이 사라지나봄.
	
	//근데 문제풀이1에선 root = bst(root, p);로 메서드가 끝났을때 return root한걸 메서드 바깥에서 붙여버렸으니까, 살아있는듯.
	
	//Runtime: 0 ms
	//Memory Usage: 37.6 MB
	
	public TreeNode bst(TreeNode root, int val) {
		if (root == null) {
			return new TreeNode(val);
		} else if (val < root.val) {
			root.left = bst(root.left, val);
		} else {
			root.right = bst(root.right, val);
		}
        return root;
	}

	public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = null;
        
		for (int p : preorder) {
			root = bst(root, p);
		}
		return root;
	}
	
	
	//<문제풀이2>
	
	//윾시 구글신은 위대해
	
	//얘는 전역변수 root에 붙여주는건데
	
	//addNode()메서드가 끝나도 붙어있네?
	
	//Runtime: 0 ms
	//Memory Usage: 37.6 MB
	
	TreeNode root = null;
	
	public void addNode(int key) {
        TreeNode newNode = new TreeNode(key);

        if (root == null) {
            root = newNode; // 트리가 비어있으면 root 에 삽입
        } else {
            TreeNode focusNode = root;  //  탐색용 노드
            TreeNode parent;            //  탐색용 노드의 부모 노드

            while(true) {
                parent = focusNode; //  이동

                if (key < parent.val) {             //  삽입하려는 키가 현재 노드보다 작으면
                    focusNode = parent.left;   //  왼쪽으로 이동

                    if (focusNode == null) {        //  왼쪽 노드가 비어있으면
                        parent.left = newNode; //  왼쪽 노드에 삽입
                        return;
                    }
                } else {                            //  삽입하려는 키가 현재 노드와 같거나 크다면
                    focusNode = parent.right;  //  오른쪽으로 이동

                    if (focusNode == null) {        //  오른쪽 노드가 비어있으면
                        parent.right = newNode;//  오른쪽 노드에 삽입
                        return;
                    }
                }
            }
        }
    }
	
    public TreeNode bstFromPreorder(int[] preorder) {
        
        for(int p : preorder){
            addNode(p);
        }
        return root;
    }
    
    
    
    //<문제풀이3 by lee215>
    
    //bound를 이용하는 방법
    
    //left child will take preorder[i] smaller than bound(parent.val)
    
    //right child will take preorder[i] smaller than bound(Integer.MAX_VALUE)
    
    //Runtime: 0 ms
    //Memory Usage: 37.2 MB
    
    int i = 0;
    
    private TreeNode bst(int[] preorder, int bound){
        if(i >= preorder.length || preorder[i] > bound) return null;
        TreeNode root = new TreeNode(preorder[i++]);
        root.left = bst(preorder, root.val);
        root.right = bst(preorder, bound);
        return root;
    }
    
    public TreeNode bstFromPreorder(int[] preorder) {
		return bst(preorder, Integer.MAX_VALUE);
	}
}
