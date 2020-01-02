package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PancakeSorting969 {
	
	/*
	//<Trial01>
	
	//index0부터 뒤집어야 한다. start index가 고정되어 있는데
	
	//내 임의대로 조정할 수 있다고 생각하고 짰다가 망함
	
	private static void flip(int[] A, int start, int end) {
		int mid = (start + end) / 2;
		while(start <= mid) {
			int tmp = A[start];
			A[start] = A[end];
			A[end] = tmp;
			start++;
			end--;
		}
	}
	
    public static List<Integer> pancakeSort(int[] A) {
    	List<Integer> rst = new ArrayList<>();
    	
        int[] compare = new int[A.length];
        for(int i = 0; i < A.length; i++) {
        	compare[i] = A[i];
        }
    	Arrays.sort(compare);
    	
    	for(int i = 0; i < compare.length; i++) {
    		for(int j = 0; j < A.length; j++) {
    			if(i != j && compare[i] == A[j]) {
    				flip(A, i, j);
    				rst.add(j+1);
    			}
    		}
    	}
    	return rst;
    }
    */
	
    //<문제풀이1 by lee215>
	
	//for문을 역순으로 돌면서, 가장 긴게 index0에 가도록 한 후, 그대로 뒤집으면, 가장 긴게 마지막으로 감.
	
	//한칸 땡겨서 위에거 반복.
    
	//이걸 쪼개면, 1. 가장 큰 숫자를 구하고, 2. index0으로 옮기고  3. 통쨰로 뒤집으면, 1번에서 구한 가장 큰 숫자가 가장 마지막을 감. 
	
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Pancake Sorting.
    //Memory Usage: 36.2 MB, less than 94.74% of Java online submissions for Pancake Sorting.
    
    public static List<Integer> pancakeSort(int[] A) {
        List<Integer> res = new ArrayList<>();
        for (int x = A.length, i; x > 0; --x) { //역순 돌면서(가장 큰 숫자대로 하나하나 쌓아가야 하니까)
            for (i = 0; A[i] != x; ++i); //현재 가장 큰 숫자 x의 인덱스 i를 구했으면,
            reverse(A, i + 1); //그 숫자를 뒤집어서 index0으로 만들고,
            res.add(i + 1); //한번 뒤집었으니까 res에 인덱스 더해주고
            reverse(A, x); //index0에 있는 x를 마지막으로 옮긴 후
            res.add(x); //뒤집었으니까 옮긴 인덱스를 res에 더해줌
        }
        return res;
    }

    public static void reverse(int[] A, int k) {
        for (int i = 0, j = k - 1; i < j; i++, j--) {
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }
    }
    
    public static void main(String[] args) {
		int[] test = new int[4];
		test[0] = 3;
		test[1] = 2;
		test[2] = 4;
		test[3] = 1;
		System.out.println(pancakeSort(test));
	}
}
