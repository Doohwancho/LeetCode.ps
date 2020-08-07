package augustChallenge;

public class VerticalOrderTraversalOfBinaryTree {
	
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

	//<Trial01>
	
	//일단 vertical 넣는것 까진 하고,
	
	//한 곳에 여러개 넣는경우면, 오름차순 정렬하라는걸로 알아먹었는데, 그냥 순서대로 넣으라는거였나보네
	
	//홍대병 걸리지 말자 제발
	
	class Solution {
	    
	    List<List<Integer>> rst;
	    List<Integer> pos;
	    
	    private void dfs(TreeNode root, int x, int y){
	        if(root == null) return;
	        dfs(root.left, x-1, y+1);
	        
	        int idx = -1;
	        for(int i = 0; i < pos.size(); i++){
	            if(pos.get(i) == x){
	                idx = i;
	            }
	        }
	        
	        if(idx == -1){
	            pos.add(x);
	            List<Integer> tmp = new LinkedList<>();
	            tmp.add(root.val);
	            rst.add(tmp);
	        } else{
	            List<Integer> temp = rst.get(idx);
	            int l = 0, r = temp.size()-1;
	            while(l <= r){
	                int m = (l+r)/2;
	                if(temp.get(m) > root.val){
	                    r = m-1;
	                } else {
	                    l = m+1;
	                }
	            }
	            temp.add(l, root.val);
	        }
	        dfs(root.right, x+1, y+1);
	    }
	    
	    public List<List<Integer>> verticalTraversal(TreeNode root) {
	        rst = new ArrayList<>();
	        pos = new ArrayList<>();
	        dfs(root, 0, 0);
	        return rst;
	    }
	}
	
	
	//<Trial02>
	
	//걍 왼쪽부터 오른쪽 순서대로 넣으면 되나? 했는데
	
	//위 -> 아래, 왼쪽 -> 오른쪽 순이구나
	
	//y를 쓰란말인것 같은데
	
	
	class Solution {
	    
	    List<List<Integer>> rst;
	    List<Integer> pos;
	    
	    private void dfs(TreeNode root, int x, int y){
	        if(root == null) return;
	        dfs(root.left, x-1, y+1);
	        
	        int idx = -1;
	        for(int i = 0; i < pos.size(); i++){
	            if(pos.get(i) == x){
	                idx = i;
	            }
	        }
	        
	        if(idx == -1){
	            pos.add(x);
	            List<Integer> tmp = new LinkedList<>();
	            tmp.add(root.val);
	            rst.add(tmp);
	        } else{
	            if(x <= 0){
	                rst.get(idx).add(root.val);
	            } else {
	                rst.get(idx).add(0, root.val);
	            }
	        }
	        dfs(root.right, x+1, y+1);
	    }
	    
	    public List<List<Integer>> verticalTraversal(TreeNode root) {
	        rst = new ArrayList<>();
	        pos = new ArrayList<>();
	        dfs(root, 0, 0);
	        return rst;
	    }
	}
	
	
	//<Trial03>
	
	//어씨 거의 다 된거같은데
	
	//Input:
	//[0,10,1,null,null,2,4,3,5,null,null,6,null,7,9,8,null,null,null,null,11,null,null,12]
	//Output: [[10,3],[0,2,7],[8],[6],[1,5],[4,9,12],[11]]
	//Expected: [[8],[6],[10,3],[0,2,7],[1,5],[4,9,12],[11]]
	
	//x,y넣는거 다 스무스하게 되는데 이번엔 x순서가 문제네?
	
	//아 걍 inorder-traversal로 왼쪽부터 넣지 말고
	
	//젤 왼쪽에 있는애를 먼저넣으란 말이네?
	
	//posX를 posY로 만들라는 말이네?
	
	//어씨 이거 hard인가? 왜이리 복잡해?
	
	//medium이네? ㅋㅋ
	
	class Solution {
	    
	    List<List<Integer>> rst;
	    List<Integer> posX;
	    List<List<Integer>> posY;
	    
	    private void dfs(TreeNode root, int x, int y){
	        if(root == null) return;
	        dfs(root.left, x-1, y+1);
	        
	        //find previous x's idx
	        int idx = -1;
	        for(int i = 0; i < posX.size(); i++){
	            if(posX.get(i) == x){
	                idx = i;
	            }
	        }
	        
	        //if prev-x don't exists
	        if(idx == -1){
	            //add x coordinates
	            posX.add(x);
	            
	            //add y coordinates
	            List<Integer> pa = new LinkedList<>();
	            pa.add(y);
	            posY.add(pa);
	            
	            //add to rst
	            List<Integer> tmp = new LinkedList<>();
	            tmp.add(root.val);
	            rst.add(tmp);
	        } 
	        //if prev-x exists
	        else{
	            //find index y smaller than prev-y's index
	            List<Integer> temp = posY.get(idx);
	            int l = 0, r = temp.size()-1;
	            boolean flag = false;
	            while(l <= r){
	                int m = (l+r)/2;
	                //if (x,y) are the same, turn var 'flag' on, to compare numbers
	                if(temp.get(m) == y){
	                    l = rst.get(idx).get(m) > root.val ? m: m+1;
	                    break;
	                }
	                else if(temp.get(m) > y){
	                    r = m-1;
	                } else {
	                    l = m+1;
	                }
	            }
	            temp.add(l, y);
	            rst.get(idx).add(l, root.val);
	        }
	        dfs(root.right, x+1, y+1);
	    }
	    
	    public List<List<Integer>> verticalTraversal(TreeNode root) {
	        rst = new ArrayList<>();
	        posX = new ArrayList<>();
	        posY = new ArrayList<>();
	        dfs(root, 0, 0);
	        return rst;
	    }
	}
	
	
	//<문제풀이1 by zzhai>
	
	//treeset으로 x를 순서대로 잡아주고
	
	//treeset에 넣을때,
	
	//(a, b) -> a[1] != b[1] ? a[1] - b[1] 로 level더 작은거(더 위에있는거) 우선순위로 넣어주고,
	
	//x,y같으면 a[0] - b[0]로 더 작은숫자를 앞에 넣어줌.
	
	//이게 젤 깔끔하네
	
	//Runtime: 3 ms
	//Memory Usage: 39.5 MB
	
	
	class Solution {
	    
	    
	    Map<Integer, TreeSet<int[]>> map = new TreeMap<>(); 
	    public List<List<Integer>> verticalTraversal(TreeNode root) {
	        List<List<Integer>> res = new LinkedList<>(); 
	        populate(root, 0, 0);
	        for (int i : map.keySet()) { 
	            List<Integer> list = new LinkedList<>();
	            for (int[] j : map.get(i)) 
	                list.add(j[0]); 
	                
	            res.add(list); 
	        }
	        return res; 
	    }
	    
	    private void populate(TreeNode root, int order, int level) {
	        if (root == null) return; 
	        if (!map.containsKey(order)) 
	            map.put(order, new TreeSet<int[]>((a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]));
	        
	        map.get(order).add(new int[]{root.val, level}); 
	        populate(root.left, order - 1, level + 1); 
	        populate(root.right, order + 1, level + 1); 
	    }

	}
}
