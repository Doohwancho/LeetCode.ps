package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class InsertDeleteGetRandomO1380 {
	
	/*
	//<Trial01>
	  
	//hashtable
	
	//random에서 막힘
	
	//.iterator()설명이 
	
	//Returns an iterator over the elements in this set. The elements are returned in no particular order (unless this set is an instance of someclass that provides a guarantee).
	
	//이라서 random하게 반환하는줄 알았는데 아니네? 같은거만 주구장창 반환하네?
	
	//(unless this set is an instance of someclass that provides a guarantee) 이거 때문인듯
	
	class RandomizedSet {

	    Set<Integer> container;
	    
	    public RandomizedSet() {
	        container = new HashSet<>();
	        
	    }
	    
	    public boolean insert(int val) {
	        return container.add(val);
	    }
	    
	    public boolean remove(int val) {
	        return container.remove(val);
	    }
	    
	    public int getRandom() {
	        return container.iterator().next();
	    }
	}
	*/
	//<문제풀이1 by balint>
	
	//hashtable+arraylist
	
	//Runtime: 8 ms, faster than 80.49% of Java online submissions for Insert Delete GetRandom O(1).
	//Memory Usage: 45.2 MB, less than 84.00% of Java online submissions for Insert Delete GetRandom O(1).
    Map<Integer, Integer> map = new HashMap<>();
    List<Integer> list = new ArrayList<>();
    Random rnd = new Random();
    
    InsertDeleteGetRandomO1380(){
    	System.out.println(123);
    }

    public boolean insert(int val) { //self-explanatory
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
        //바로 아래코드를 쉽게 풀어쓴 것
        
		//int indexToRemove = map.get(val);
		//int valueLast = list.get(list.size() - 1);
		
		//// update list -> shuffle
		//list.set(indexToRemove, valueLast);
		//list.remove(list.size() - 1);
		
		//// update map
		//map.put(valueLast, indexToRemove);
		//map.remove(val);
        
        
        int idx = map.remove(val);//.remove() returns either 1. the previous value associated with key, 2. or null if there was no mapping for key.
        int last = list.remove(list.size() - 1); //.remove(index) .remove returns the element previously at the specified position(last prev_element)
        if(val != last) { 
            list.set(idx, last); 
            map.put(last, idx); 
        }
        return true;
    }
    
    public int getRandom() {
        return list.get(rnd.nextInt(list.size())); //random.nextInt(10) 하면 0부터 9(10-1)까지의 숫자를 랜덤하게 반환
    }
    
    public static void main(String[] args) {
		
	}
}

