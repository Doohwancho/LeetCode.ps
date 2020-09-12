package september;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
	
	//<문제풀이1>
	
	//Tree
	
	//Runtime: 1 ms
	//Memory Usage: 37.8 MB
	List<List<Integer>> rst;
    
	class Trie{
        int k = -1;
        int n = -1;
        int sum = -1;
        Trie[] children;
        List<Integer> acc;
        
        Trie(int k, int n){
            this.k = k;
            this.n = n;
            sum = 0;
            children = new Trie[9];
            acc = new ArrayList<>();
        }
        
        Trie(int k, int n, List<Integer> arr, int sum){
            this.k = k;
            this.n = n;
            this.sum = sum;
            children = new Trie[9];
            this.acc = new ArrayList<>(arr);
        }
        
        private void addChild(int i){
        	sum += i;
            acc.add(i);
            
            if(acc.size() == k){
                if(sum == n){
                    rst.add(new ArrayList<>(acc));
                } 
                return;
            }
            for(int j = i+1; j < 10; j++){
            	if(j+sum > n) continue;
                children[j-1] = new Trie(k, n, new ArrayList<>(acc), sum);
                children[j-1].addChild(j);
            }
        }
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        if(n > 45) return new ArrayList<>();
        rst = new ArrayList<>();
        
        for(int i = 1; i < Math.min(n, 9)+1; i++){
            Trie child = new Trie(k, n);
            child.addChild(i);
        }
        return rst;
    }
    
    
    
    //<문제풀이2 by jinwu>
    
    //backtracking
    
    //이게 가장 최적화 된듯. 성능으로나 코드 길이로나.
    
    //Runtime: 0 ms
    //Memory Usage: 36.6 MB
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> rst = new ArrayList<>();
        combination(rst, new ArrayList<Integer>(), k, n, 1);
        return rst;
    }
    
    private void combination(List<List<Integer>> rst, List<Integer> comb, int k, int n, int idx){
        if(comb.size() == k){
            if(n == 0) rst.add(new ArrayList<>(comb));
            return;
        }
        
        for(int i = idx; i < 10; i++){
            comb.add(i);
            combination(rst, comb, k, n-i, i+1);
            comb.remove(comb.size()-1);
        }
    }
}
