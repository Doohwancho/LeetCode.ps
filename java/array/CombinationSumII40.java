package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CombinationSumII40 {
    
	/*
    //<Trial01>
    
	//Input : [2,5,2,1,2] , 5
	//Output : [[1,2,2],[1,2,2],[1,2,2]]
	//Expected : [[1,2,2],[5]]
	
	//문제점 : The solution set must not contain duplicate combinations.
    
	//쓰벌
    
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> rst = new ArrayList<>();
        List<List<Integer>> container;
        
        Arrays.sort(candidates);
        
        for(int i = 0; i < candidates.length; i++){
            if(i > 0 && candidates[i] == candidates[i-1]) continue;
            container = new ArrayList<>();
            List<Integer> tmp = new ArrayList<>();
            tmp.add(candidates[i]);
            container.add(tmp);
            for(int j = i+1; j < candidates.length; j++){
                int idx = container.size()-1; //for loop도중 container에 객체추가하면 concurrent modification exception떠서 이리 바꿈
                while(idx >= 0){
                    List<Integer> temp = container.get(idx--);
                    int total = 0;
                    for(int element : temp){
                        total += element;
                    }
                    if(total == target){
                    	rst.add(temp);
                    }
                    else if(candidates[j] + total < target){ 
                        List<Integer> copy = new ArrayList<>(temp);
                        temp.add(candidates[j]);
                        container.add(copy);
                    }
                    else if(candidates[j] + total == target){
                        List<Integer> copy = new ArrayList<>(temp);
                        temp.add(candidates[j]);
                        rst.add(temp);
                        container.add(copy);
                    }
                }
            }
        }
        return rst;
    }
    */
	
    //<문제풀이 by reeclapple>
	
	//위에거와 로직은 비슷한데, 재귀로 중복제거하는 로직이 다름
	
	//Runtime: 3 ms, faster than 79.78% of Java online submissions for Combination Sum II.
	//Memory Usage: 39.8 MB, less than 43.16% of Java online submissions for Combination Sum II.
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates==null||candidates.length==0) return Collections.emptyList();//Or throw exception();

        List<List<Integer>> results = new LinkedList<>();

        LinkedList<Integer> work = new LinkedList<>();

        Arrays.sort(candidates);

        for (int i=0,len=candidates.length;i<len;i++){

            if (i>0&&candidates[i]==candidates[i-1]) continue; //Avoid duplicates;
            combinationSumHelper(candidates,i,target,work,results);//DFS
        }
        return results;
    }
    //Use DFS
    private void combinationSumHelper(int[] candidates,int index, int target,LinkedList<Integer> work,List<List<Integer>> results){
        //Compare candidates[index] and target;
        //If equals, terminate the search,return result 
        //If candidates[index] > target, terminate the search, no result
        //Otherwise, study rest of elements.
        if (candidates[index]>target){
            return;
        }else if (candidates[index]==target){//Update the 
            work.addLast(candidates[index]);
            results.add(new ArrayList<Integer>(work));
            work.removeLast();
            return;
        }
        work.addLast(candidates[index]);
        for (int i=index+1,len=candidates.length;i<len;i++){
            if (i>index+1&&candidates[i]==candidates[i-1]) continue;//Avoid dulipcates
            if (candidates[i]<=target-candidates[index]){
                combinationSumHelper(candidates,i,target-candidates[index],work,results);
            }
        }
        work.removeLast();
    }
}
