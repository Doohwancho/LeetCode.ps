package array;

import java.util.HashSet;
import java.util.Set;

public class CheckIfNandItsDoubleExist1346 {
	
	/*
	//<문제풀이1>
	
	//별로
	
	//Runtime: 2 ms, faster than 46.41% of Java online submissions for Check If N and Its Double Exist.
	//Memory Usage: 40.9 MB, less than 100.00% of Java online submissions for Check If N and Its Double Exist.
	
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int zeros = 0;
        
        for(int i : arr){
            set.add(i);
            if(i == 0){
                zeros++;
            }
        }
        
        if(zeros > 1){
            return true;
        }
        
        for(int j : arr){
            if(j != 0 && set.contains(j*2)){
                return true;
            }
        }
        return false;
    }
    */
	
	/*
	//<문제풀이2>
	
	//약간 나아졌지만 여전히 별로
    
    //Runtime: 2 ms, faster than 46.41% of Java online submissions for Check If N and Its Double Exist.
    //Memory Usage: 38.6 MB, less than 100.00% of Java online submissions for Check If N and Its Double Exist.
    public boolean checkIfExist(int[] arr) {
        for(int i = 0; i < arr.length; i++){
            for(int j = i+1; j < arr.length; j++){
                if(arr[j] == arr[i]*2 || arr[i] == arr[j]*2){
                    return true;
                }
            }
        }
        return false;
    }
    */
    
	//<문제풀이3>
	
	//드디어 뇌란걸 쓰기 시작하는구나 나란녀석..크...크윽...
	
	//When we store a number, we go number * 2. that way, we don't have to deal with float when we divide the number half.

	//Think of two cases.
	
	//case1) [6,x,3,x] -> we store 12(6*2). so when we see 3, we simply go 3 * 4.
	
	//case2) [3,x,6,x] -> we store 6(3*2). so when we see 6, we simply find 6 in the Set.
	
	//Runtime: 1 ms, faster than 99.41% of Java online submissions for Check If N and Its Double Exist.
	//Memory Usage: 39.3 MB, less than 100.00% of Java online submissions for Check If N and Its Double Exist.
    
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        
        for(int i : arr){
            if(set.contains(i) || set.contains(i*4)){
                return true;
            } else {
                set.add(i*2);
            }
        }
        return false;
    }
}
