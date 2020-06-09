package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TheKStrongestValuesInAnArray1471 {
	
	//<문제풀이1>
	
	//brute-force
	
	//two-pointer
	
	//Runtime: 29 ms, faster than 86.48% of Java online submissions for The k Strongest Values in an Array.
	//Memory Usage: 53 MB, less than 100.00% of Java online submissions for The k Strongest Values in an Array.
	
	public static int[] getStrongest(int[] arr, int k) {
		Arrays.sort(arr);
		int m = (arr.length-1)/2;
		int[] rst = new int[k];
        
        for(int i = 0, j = arr.length-1, idx = 0; idx < k; ){
            int j_ = Math.abs(arr[j]-arr[m]);
            int i_ = Math.abs(arr[i]-arr[m]);
            if(j_ >= i_){
            	rst[idx++] = arr[j--];
            } else {
            	rst[idx++] = arr[i++];
            }
        }
        
        return rst;
    }
}
