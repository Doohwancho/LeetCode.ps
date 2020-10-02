package october;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
	
	
	//<문제풀이1>
	
	//backtracking
	
	//Runtime: 5 ms
	//Memory Usage: 41.1 MB
    private void dfs(List<List<Integer>> rst, List<Integer> tmp, int[] candidates, int rest, int idx){
        if(rest < 0){ //모든 경우 iterate중, list의 총 합이 target을 넘어버리면, 빠꾸하고 dfs(i+1)
            return;
        }
        if(rest == 0){
            rst.add(new ArrayList<>(tmp)); //rid rst.add(tmp);하면 주솟값을 넘겨주기 때문에, 다음 iterate때 tmp.add()때 값이 또 추가되거나, tmp.remove()때 값이 빠짐.
            return;
        }
        for(int i = idx; i < candidates.length; i++){ //모든 경우 다 iterate
            tmp.add(candidates[i]);
            dfs(rst, tmp, candidates, rest-candidates[i], i);
            tmp.remove(tmp.size()-1);    
        }
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> rst = new ArrayList<>();
        dfs(rst, new ArrayList<>(), candidates, target, 0);
        return rst;
    }
    
    
    //<문제풀이2 by shpolsky>
    
    //dp
	
	//int[] cand= {2,3,5};
	//int t = 8;

	//[[]]
	//[[], [[2]]]
	//[[], [[2]], [[3]]]
	//[[], [[2]], [[3]], [[2, 2]]]
	//[[], [[2]], [[3]], [[2, 2]], [[2, 3], [5]]]
	//[[], [[2]], [[3]], [[2, 2]], [[2, 3], [5]], [[2, 2, 2], [3, 3]]]
	//[[], [[2]], [[3]], [[2, 2]], [[2, 3], [5]], [[2, 2, 2], [3, 3]], [[2, 2, 3], [2, 5]]]
	//[[], [[2]], [[3]], [[2, 2]], [[2, 3], [5]], [[2, 2, 2], [3, 3]], [[2, 2, 3], [2, 5]], [[2, 2, 2, 2], [2, 3, 3], [3, 5]]]
	//[[2, 2, 2, 2], [2, 3, 3], [3, 5]]
	
	//순서대로 1,2,3,4,5,6,7,8
	
	//순서대로 쌓이니까 dp.get(i-cands[j]-1)가 가능한 것
	
	//2,3,5들은 if (i == cands[j]) 에 걸림
	
	//4같은애가 걸렸으면, 4-i(2) 해서 2 채워넣음. if (cands[j] <= l.get(0))에 걸리자너
	
	//if (cands[j] <= l.get(0)) 이게 없으면, target이 5일때, 2->3한번, 3->2한번해서 [2,3],[3,2] 중복으로 올라감.
	
	//그래서 Arrays.sort()하고 쓰는 것.
    
    //Runtime: 13 ms
    //Memory Usage: 45.9 MB
    
    public static List<List<Integer>> combinationSum(int[] cands, int t) {
        Arrays.sort(cands); 
        List<List<List<Integer>>> dp = new ArrayList<>();
        for (int i = 1; i <= t; i++) { 
            List<List<Integer>> newList = new ArrayList(); 
            for (int j = 0; j < cands.length && cands[j] <= i; j++) {
                if (i == cands[j]) {
                	newList.add(Arrays.asList(cands[j]));
                }
                else {
                	for (List<Integer> l : dp.get(i-cands[j]-1)) {
                		if (cands[j] <= l.get(0)) { 
                            List cl = new ArrayList<>();
                            cl.add(cands[j]); 
                            cl.addAll(l);
                            newList.add(cl);
                        }
                    }
                }
            }
            dp.add(newList);
        }
        return dp.get(t-1);
    }
}
