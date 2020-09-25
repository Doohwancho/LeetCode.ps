package september;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LargestNumber {
	
	//<Trial01>
	
	//끈기 부족으로 하다가 맘.
	
	//https://leetcode.com/problems/largest-number/discuss/267353/Tree-Solution.
	
	//이거랑 같은 논리
	
    class Trie{
        int val = -1;
        int cnt = 0;
        List<Trie> children;
        Map<Integer, Integer> map;
        
        Trie(){
            children = new ArrayList<>();
            map = new HashMap<>();
        }
        
        Trie(int i){
            val = i;
            cnt = 1;
            children = new ArrayList<>();
            map = new HashMap<>();
        }
        
        public void add(int n){
            //extract highest number
            int highestNum = highest(n);
            
            //n에서 highestNum 띄어내고 나머지 갖기.
            int zeros = zeros(n);
            
            //new Trie(highest number)
            Trie child = new Trie(highestNum);
            
            //
            if(n < 10 && zeros == 0){
                
            } else {
                
            }
            child.add(zeros);
            
            //
            children.add(child);
            
            
        }
        
        public int highest(int n) {
            int a = 0;
            while(n % 10 > 0) {
                a = n % 10;
                n /= 10;
            }
            return a;
        }
        
        public int zeros(int n) {
            int a = 1;
            while(n / 10 > 0) {
                a *= 10;
                n /= 10;
            }
            return a;
        }
        
        public StringBuilder recursive(StringBuilder sb){
            
            for(int i = children.size()-1; i >= 0; i--){
                Trie child = children.get(i);
                while(map.get(child) > 0){     //
                    sb.append(child.recursive(sb));
                }
            }
            return sb.append(val);
        }
    }
    
    
    
    public String largestNumber(int[] nums) {
        StringBuilder sb = new StringBuilder();
        Trie root = new Trie();
        
        for(int n : nums){
            root.add(n);
        }
        root.recursive(sb);
        
        return sb.toString();
    }

    
    
    //<문제풀이01>
    
    //3 30 34 5 9
    
    //얘를 
    
    //9 5 34 3 30
    
    //가장 첫수가 처음오도록 재정렬해서 그래도 붙여줌
    
    //Runtime: 10 ms
    //Memory Usage: 38.8 MB

    public String largestNumber(int[] num) {
        String[] array = Arrays.stream(num).mapToObj(String::valueOf).toArray(String[]::new);
        Arrays.sort(array, (String s1, String s2) -> (s2 + s1).compareTo(s1 + s2));
        return Arrays.stream(array).reduce((x, y) -> x.equals("0") ? y : x + y).get();
    }

}
