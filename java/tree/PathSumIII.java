package augustChallenge;

public class PathSumIII {

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
	
	//<문제풀이1>
	
	//preorder traversal
	
	//Runtime: 11 ms
	//Memory Usage: 40.9 MB

    private void preorder(TreeNode root, int sum,List<Integer> arr, int[] rst){
        if(root == null) return;
        
        arr.add(root.val);
        for(int i = arr.size()-1, j = 0; i >= 0; i--){
            j += arr.get(i);
            if(j == sum){
                rst[0]++;
            }
        }
        List<Integer> rightArr = new ArrayList<>(arr);
        preorder(root.left, sum, arr, rst);
        preorder(root.right, sum, rightArr, rst);
    }
    
    public int pathSum(TreeNode root, int sum) {
        int[] rst = new int[1];
        preorder(root, sum, new ArrayList<>(), rst);
        return rst[0];
    }
    
    
    //<문제풀이2 by jiangsichu>
    
    //brute-force + dfs
    
    //Runtime: 21 ms
    //Memory Usage: 39.6 MB
    
    //return sum of root.val's starting from the root
    private int dfs(TreeNode root, int sum){
        if(root == null) return 0;
        return (root.val == sum ? 1 : 0) + dfs(root.left, sum-root.val) + dfs(root.right, sum-root.val);
    }
    
    //visit every node and trigger dfs()
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum); 
    }
    
    
    
    //<문제풀이3 by xuyirui>
    
    //prefix sum hashmap
    
    //문제풀이1와 같은로직, 성능개선.
    
    //Runtime: 2 ms
    //Memory Usage: 39.4 MB
    
    private int count = 0;
    public int pathSum(TreeNode root, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); //root.val이 바로 sum일때, sum - root.val하면 0이자너~ 그럼 +1해줘야 되자너~. 그리고 sum을 누적으로 더해주다가 sum-target해서 0되도 +1해줘야 되자너~
        pathSumHelper(map, root, 0, target);
        return count;
    }
    
    private void pathSumHelper(Map<Integer, Integer> prefixSumMap, TreeNode root, int sum, int target) {
        if (root == null) {
            return;
        }
        sum += root.val;
        count += prefixSumMap.getOrDefault(sum - target, 0);
        prefixSumMap.put(sum, prefixSumMap.getOrDefault(sum, 0) + 1);
        pathSumHelper(prefixSumMap, root.left, sum, target);
        pathSumHelper(prefixSumMap, root.right, sum, target);
        prefixSumMap.put(sum, prefixSumMap.get(sum) - 1); //돌거 다 돌고 오른쪽으로 가기 전 왼쪽 누적합 삭제.
        //이게 왠지 pathSum(helper.left)와 pathSum(helper.right)사이에 있어야 할 것 같지만,
        //왼쪽 맨 끝까지 내려가면 left,right child모두 null이기 때문에, 누적된 sum지우고 오른쪽으로 넘어가기 때문에 괜찮다.
    }
}
