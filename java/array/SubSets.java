package julyChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSets {
	
	
	//<문제풀이1>
	
	//나. 천재. 강림.
	
	
	//넣는순서
	
	//1. 중복되지 않는 개별 원소를 넣어줌. if(i > 0 && nums[i] == nums[i-1]) continue; 이걸로 중복방지
	
	//그럼 [[1],[2],[3],[4]] 이렇게 들어감
	
	//2. 이제 2부터 (1st : 마지막 애가 2보다 작으면서) (2nd: 2가 안들어간 애들)을 복사한 후, 2를 넣고 rst에 넣어줌
	
	//[1][2][3][4]
	//[1,2]
	//[1,3][2,3][1,2,3]
	//[1,4][2,4][3,4][1,2,4][1,3,4][2,3,4][1,2,3,4]
	//[]
	
	//요딴식으로 나옴
	
	//마지막에 빈 array[]를 넣어줌. 끝!
	
	//Runtime: 2 ms
	//Memory Usage: 39.8 MB
	
    public List<List<Integer>> subsets(int[] nums) {
        
        Arrays.sort(nums);
        
        List<List<Integer>> rst = new ArrayList<>();
        int len = 0;
        
        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i] == nums[i-1]) continue;
            List<Integer> tmp = new ArrayList<>();
            tmp.add(nums[i]);
            rst.add(tmp);
            len++;
        }
        
        for(int j = 1; j < len; j++){
            if(rst.get(j) == rst.get(j-1)) continue;
            int t = rst.get(j).get(0);
            int idx = 0;
            while(idx < rst.size()){
                List<Integer> prev = rst.get(idx);
                
                if(prev.get(prev.size()-1) < t & !prev.contains(t)){
                    List<Integer> tmp = new ArrayList<>(rst.get(idx));
                    tmp.add(t);
                    rst.add(tmp);
                }
                idx++;
            }
        }
        
        rst.add(new ArrayList<>());
        
        return rst;
    }
    
    
    //<문제풀이1 by caikehe?
    
    //dfs
    
    //첨 생각했지만 구현은 못한 것
    
    //인데 얜 만들었네
    
	//[[], [1], [1, 2], [1, 2, 3]]
	//[[], [1], [1, 2], [1, 2, 3]]
	//[[], [1], [1, 2], [1, 2, 3], [1, 3]]
	//[[], [1], [1, 2], [1, 2, 3], [1, 3]]
	//[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3]]
	//[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3]]
	//[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
	//[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
    
    //Runtime: 2 ms
    //Memory Usage: 40.2 MB
    
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), ret);
        return ret;
    }

    private void dfs(int[] nums, int idx, List<Integer> path, List<List<Integer>> ret) {
        ret.add(path);
        for (int i = idx; i < nums.length; i++) {
            List<Integer> p = new ArrayList<>(path);
            p.add(nums[i]);
            dfs(nums, i+1, p, ret);
        }
    }
}
