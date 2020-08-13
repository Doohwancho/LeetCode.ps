package augustChallenge;

import java.util.PriorityQueue;

public class IteratorForCombination {

	//<문제풀이1>
	
	//n-pointer
	
	//.next()할때마다 포인터를 한칸씩 밀어주는 방법.
	
	//Runtime: 10 ms
	//Memory Usage: 41.2 MB
	
	class CombinationIterator {

	    String s;
	    int len = 0;
	    boolean flag;
	    int cl = 0;
	    int[] index;
	    
	    
	    public CombinationIterator(String characters, int combinationLength) {
	        s = characters;
	        len = characters.length();
	        flag = true;
	        cl = combinationLength;
	        index = new int[len];
	        for(int i = 0; i < combinationLength; i++){
	            index[i] = 1;
	        }
	    }
	    
	    public String next() {
	        StringBuilder sb = new StringBuilder();
	        for(int i = 0; i < index.length; i++){
	            if(index[i] == 1){
	                sb.append(s.charAt(i));    
	            }
	        }
	        moveDigit(1);
	        return sb.toString();
	    }
	    
	    public boolean hasNext() {
	        return flag;
	    }
	    
	    private void moveDigit(int last){
	        if(last == cl+1 || last == len){
	            flag = false;
	            return;
	        }
	        if(index[len-last] == 1){
	            moveDigit(last+1);
	        } else{
	            for(int i = len-last; i > 0; i--){
	                if(index[i-1] == 1){
	                    index[i-1] = 0;
	                    if(last >= 1){ 
	                        for(int j = i; j < len; j++){
	                            index[j] = last-- > 0 ? 1 : 0;
	                        }
	                    }
	                    break;
	                }
	            }
	        }
	    }
	}
	
	//<문제풀이2 by Poorvank>
	
	//brute-force
	
	//첨에만 쥬낸 시간걸리지만 string이 길지 않은데 한번만 넣고 .next()랑 .hasNext()만 쥰내쓰는 경우면 이방법을 쓰는게 좋을듯.
	
	//Runtime: 14 ms
	//Memory Usage: 41.5 MB
	
	PriorityQueue<String> pq = new PriorityQueue<>();
    public CombinationIterator(String s, int k) {
        generateSub(s,k,0,new StringBuilder());
    }
    private void generateSub(String s ,int len,int start,StringBuilder result) {
        if (len == 0){
            pq.add(result.toString());
            return;
        }
        for (int i = start; i <= s.length()-len; i++){
            result.append(s.charAt(i));
            generateSub(s, len-1, i+1, result);    //재귀 안으로 들어가서 지랄난것을
            result.deleteCharAt(result.length()-1); //맨 마지막거 한개 빼고 다음걸로 넘어감
        }
    }
    public String next() {
        return pq.poll();
    }
    public boolean hasNext() {
        return !pq.isEmpty();
    }
	
}
