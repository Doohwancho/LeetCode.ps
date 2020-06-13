package juneChallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class InsertDeleteGetRandomO1 {
	
	
	//<문제풀이1>
	
	//음..존나 오래걸리네?
	
	//getRandom()함수에서 set->list로 바꾸고 Collection.shuffle()라는 내장함수를 씀
	
	//Runtime: 698 ms
	//Memory Usage: 46.1 MB
	class RandomizedSet {

	    Set<Integer> set;
	    
	    public RandomizedSet() {
	        set = new HashSet<>();
	    }
	    
	    public boolean insert(int val) {
	        return set.add(val);
	    }
	    
	    public boolean remove(int val) {
	        return set.remove(val);
	    }
	    
	    public int getRandom() {
	        List<Integer> asList = new ArrayList(set);
	        Collections.shuffle(asList);
	        return asList.get(0);
	    }
	}
	
	
	//<문제풀이2>
	
	//약 650ms 더 빨라짐
	
	//개꿀
	
	//이것보다 더 잘 할 수 있나?
	
	//Runtime: 41 ms
	//Memory Usage: 44.6 MB
	
	class RandomizedSet {

	    Set<Integer> set;
	    List<Integer> list;
        Random rand;
    
	    public RandomizedSet() {
	        set = new HashSet<>();
	        list = new ArrayList<>();
            rand = new Random();
	    }
	    
	    public boolean insert(int val) {
	        if(set.add(val)){
	            return list.add(val);   
	        } else {
	            return false;
	        }
	    }
	    
	    public boolean remove(int val) {
	        if(set.remove(val)){
	            return list.remove((Integer)val);
	        } else {
	            return false;
	        }
	    }
	    
	    public int getRandom() {
	        return list.get(rand.nextInt(list.size()));
	    }
	}
	
	
	//<문제풀이3 by balint>
	
	//짜잔! 잘하는 방법이 있었습니다!
	
	//Runtime: 8 ms
	//Memory Usage: 44.4 MB
	
	
	public class RandomizedSet {

	    Map<Integer, Integer> map = new HashMap<>();
	    List<Integer> list = new ArrayList<>();
	    Random rnd = new Random();

	    public boolean insert(int val) {
	        if(map.containsKey(val)) {
	            return false;
	        }
	        
	        map.put(val, list.size()); //list.size()를 벨류로 넣는 이유는, list의 인덱스를 나타내주기 위함.
	        list.add(val);
	        return true;
	    }
	    
	    public boolean remove(int val) {
	        if(!map.containsKey(val)) {
	            return false;
	        }
            //map = {{A:0},{B:1},{C:2}}
            //list = {A,B,C}
            //val = B
            //라고 했을때, remove(B)를 했다고 치자.
	        
	        int idx = map.remove(val); //map.remove(key) returns the value previously associated with the key. 그니까 B는 val의 리스트의 인덱스인 1이 되겠지?
	        int last = list.remove(list.size() - 1); //일단 list에 마지막애인 C를 빼봐.
            
            //중간점검
            //map = {{A:0},{C:2}}
            //list = {A,B}
            //val = B
            //idx = 1
            //last = C
            
	        if(val != last) { //val이 last랑 같다면,(== val이 list에 가장 마지막에 있는 애랑 같다면), C이미 뺐잖아? 그럼 그냥 return true하고 끝내면 되지?
	            list.set(idx, last); //근데 val(B)이 리스트에서 맨 마지막 애인 last(C)가 아니면, 리스트에 뺀 last(C)를 B의 자리에 엎어쓰기(list = {A,C,C})
	            map.put(last, idx); //map에 last를 박는데, value를 idx로 넣어. map = {{A:0},{C:1}}
                
                //결과물을 보자.
                //map = {{A:0},{C:1}}
                //list = {A,C,C}
                
                //리스트에 C가 중복되긴 하지만 다른 함수가 동작할때 영향을 주지 않아서 상관없어.
	        }
	        return true;
	    }
	    
	    public int getRandom() {
	        return list.get(rnd.nextInt(list.size()));
	    }
	}
}
