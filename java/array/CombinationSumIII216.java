package array;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII216 {
	
	/*
	//<Trial01>
	
	//combination구하는건, k가 3이면 3중 for문, k가 4면 4중 for문,
	
	//이런식으로 나오니까 재귀로 k를 하나씩 까면서 for문을 돌면 k번나오지 않을까?
	 
	//k가 3이면, 첫번째 두번째 loop의 합을 container에 쑤셔넣고 막타칠때 n - total of container만 보면 되지 않을까?
	  
	//막타가 안먹혔으면 다시 이전 stack으로 돌아와서 loop돌면 되지않을까? 
	
	//라고 생각했는데 구현을 못함 제기랄
	
	//combination합이 n인데, 어쨌든 숫자의 갯수는 k이므로, k사이즈의 리스트를 만들어 돌려먹는건 괜찮은 생각이었던 것 같음
	
	//다만 위에 재귀용법을 쓰는건 stack overflow나는 바보같은 짓인것같긴함. 쓰바
	
	private void recursive(List<List<Integer>> rst, int[] container, int n, int idx, int k, int prev){
        if(k > 0){
            if(k == 1){
                int total = 0;
                for(int element : container){
                    total += element;
                }
                if(n-total < 10 && n-total > prev-1){
                    container[idx] = n-total;
                    List<Integer> list = new ArrayList<>();
                    for(int element : container){
                        list.add(element);
                    }
                    rst.add(list);
                }
            } else {
                container[idx] = prev;
                recursive(rst, container, n, idx++, k-1, prev+1);
            }
        }
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> rst = new ArrayList<>();
        int[] container = new int[k];
        recursive(rst, container, n, 0, k, 1);
        return rst;
    }
    */
	
	//<문제풀이 by caikehe>
	
	//이 똑똑이도 나랑 대략 같은 생각으로 접근한 것 같은데 성공시킴. 아 인생
	
	//int[k]안쓰고 ArrayList에 k하나씩 까면서 stack한칸씩 내려갈때마다 값 더해주고, 꼬일걸 방지해서 List<Integer> p = new ArrayList<>(path); 방식을 씀
	
	//nums는 굳이 왜 쓴지 모르겠음. 걍 p.add(i+1)하면 되는 것 같은데.
	
	//파라미터에 n-(i+1)이랑.
			
	//Runtime: 1 ms, faster than 55.66% of Java online submissions for Combination Sum III.
	//Memory Usage: 37.7 MB, less than 6.00% of Java online submissions for Combination Sum III.
	
	public List<List<Integer>> combinationSum3(int k, int n) {
	    List<Integer> nums = new ArrayList<>();
	    for (int i = 1; i <= 9; i++) {
	        nums.add(i);
	    }
	    List<List<Integer>> ret = new ArrayList<>();
	    dfs(nums, k, n, 0, new ArrayList<>(), ret);
	    return ret;
	}

	private void dfs(List<Integer> nums, int k, int n, int idx, List<Integer> path, List<List<Integer>> ret) {
	    if (k <= 0 && n <= 0) {
	        if (k == 0 && n == 0) {
	            ret.add(path);
	        }
	        return; // backtracking
	    }
	    for (int i = idx; i < nums.size(); i++) {
	        List<Integer> p = new ArrayList<>(path); //재귀때 path넘겼다가 다시 이전 stack으로 복귀하는 과정에서 path가 꼬일것을 염려해 새로운 arraylist에다 옮겨주는 센스
	        p.add(nums.get(i));
	        dfs(nums, k-1, n-nums.get(i), i+1, p, ret);
	    }
	}

}
