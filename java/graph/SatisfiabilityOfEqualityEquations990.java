package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SatisfiabilityOfEqualityEquations990 {

	//<문제풀이1>
	
	//union-circle
	
	//undirect-graph로 ==인 애들 이어준 후,
	
	//!=인 애들 돌면서 둘의 endpoint가 다른지 비교.
	
	//Runtime: 3 ms, faster than 27.99% of Java online submissions for Satisfiability of Equality Equations.
	//Memory Usage: 38.7 MB, less than 5.06% of Java online submissions for Satisfiability of Equality Equations.
	
    public class UnionCircle{
        Map<Character, Character> map;
        
        public UnionCircle(String[] equations){
            map = new HashMap<>();
            
            for(String equation : equations){
                map.put(equation.charAt(0), equation.charAt(0));
                map.put(equation.charAt(3), equation.charAt(3));
            }
        }
                       
        private Character find(Character x){
            while(map.get(x) != x) x = map.get(x);
            return x;
        }
        
        public void union(Character i, Character j){
            Character i_ = find(i);
            Character j_ = find(j);
            if(i_ == j_) return;
            map.put(i_, j_);
        }
        
        public boolean verify(List<String> list){
            for(String unequal : list){
                if(find(unequal.charAt(0)) == find(unequal.charAt(3))) return false;
            }
            return true;
        }
    }          
                       
    
    
    public boolean equationsPossible(String[] equations) {
        UnionCircle uc = new UnionCircle(equations);
        List<String> list = new ArrayList<>();
        
        for(String equation : equations){
            if(equation.charAt(1) == '='){
                uc.union(equation.charAt(0), equation.charAt(3));    
            } else {
                list.add(equation);
            }
        }
        return uc.verify(list);
    }
}
