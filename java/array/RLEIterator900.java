package array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RLEIterator900 {
	
	/*
	//<Trial01 - Time limit exceeded>
	
	List<Integer> tmp = new ArrayList<>();
    int idx = -1;

    public RLEIterator(int[] A) {
    	
        for(int i = 1; i < A.length; i+=2){
            for(int j = 0; j < A[i-1]; j++){
                tmp.add(A[i]);
            }
        }
    }

    public int next(int n) {
        idx += n;
        return idx < tmp.size() ? tmp.get(idx) : -1;
    }
    */
	
	/*
	//<Trial02 - java.lang.NegativeArraySizeException>
	
	//array를 list로 바꿈.
	 
	//근데 이것도 인풋 미칠듯이 크면 안됨
	  
	//Your RLEIterator object will be instantiated and called as such:
	//RLEIterator obj = new RLEIterator(A);
	//int param_1 = obj.next(n);
	
	//이건 RLEIterator(A)가 끝나고 .next(n)한다는 말이니까, RLEIterator도중에 next를 할 수 없다는 말 아닌가?
	
    int[] container;
    int index = -1;

    public RLEIterator(int[] A) {

        int arrSize = 0;
        
        for(int i = 0; i < A.length; i+=2){
            if(A[i+1] != 0){
                arrSize += A[i];
            }
        }
        container = new int[arrSize];
        
        for(int j = 1, idx = 0; j < A.length; j+=2){
            while(A[j-1]-- > 0){
                container[idx++] = A[j];
            }
        }
    }

    public int next(int n) {
        index += n;
        return index < container.length ? container[index] : -1;
    }
	*/
	
	/*
	//<문제풀이1 by wangzi6147>
	
	//아 그냥 this.A = A하면 되지.. 머리란걸 좀 쓰자 친구야
	
	//Runtime: 5 ms, faster than 62.55% of Java online submissions for RLE Iterator.
	//Memory Usage: 39 MB, less than 100.00% of Java online submissions for RLE Iterator.
	
    private int[] A;
    private int i;

    public RLEIterator(int[] A) {
        this.A = A;
        i = 0;
    }
    
    public int next(int n) {
        while (i < A.length && A[i] < n) {
            n -= A[i];
            i += 2;
        }
        if (i == A.length) {
            return -1;
        }
        A[i] -= n;
        return A[i + 1];
    }
	*/
	
}
