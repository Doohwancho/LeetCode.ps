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
	        
	        map.put(val, list.size());
	        list.add(val);
	        return true;
	    }
	    
	    public boolean remove(int val) {
	        if(!map.containsKey(val)) {
	            return false;
	        }
	        
	        int idx = map.remove(val); //map.remove(key) returns the value previously associated with the key
	        int last = list.remove(list.size() - 1);
	        if(val != last) { //가장 마지막에 들어온게 val이면, if문 건너뛰고 return true, 그게 아니면, 
	            list.set(idx, last); //리스트에 뺀거 다시 넣고
	            map.put(last, idx); //val이 있던 자리에 list에 맨 마지막에 있던(list.remove()로 뺐던) last를 박아넣음
	        }
	        return true;
	    }
	    
	    public int getRandom() {
	        return list.get(rnd.nextInt(list.size()));
	    }
	}
}
