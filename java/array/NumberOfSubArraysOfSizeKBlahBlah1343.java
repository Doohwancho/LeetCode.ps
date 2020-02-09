package array;

import java.util.Arrays;

public class NumberOfSubArraysOfSizeKBlahBlah1343 {
	
	/*
	//<문제풀이1>
	
	//brute-force
	
	//Runtime: 1990 ms, faster than 50.00% of Java online submissions for Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold.
	//Memory Usage: 56.5 MB, less than 100.00% of Java online submissions for Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold.
	
	public static int numOfSubarrays(int[] arr, int k, int threshold) {
        int rst = 0;
        
        for(int i = 0; i < arr.length-k+1; i++){
            int cnt = k-1;
            int total = 0;
            
            while(cnt >= 0){
                total += arr[i+cnt];
                cnt--;
            }
            
            if(total >= threshold * k){
                rst++;
            }
        }
        return rst;
    }
    */
	
	//<문제풀이2>
	
	//sliding
	
	//Runtime: 2 ms, faster than 100.00% of Java online submissions for Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold.
	//Memory Usage: 57.1 MB, less than 100.00% of Java online submissions for Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold.
	
    public static int numOfSubarrays(int[] arr, int k, int threshold) {
        int rst = 0;
        
        for(int i = 0, total = 0, cap = k, firstIn = 0; i < arr.length; i++){
            if(cap > 0){
                total += arr[i];
                cap--;
            }
            else{
                total += arr[i];
                total -= arr[firstIn++];
                
            }
            if(cap == 0 && total >= threshold * k){
                rst++;
            }
        }
        return rst;
    }
	
	public static void main(String[] args) {
		int[] arr = {11,13,17,23,29,31,7,5,2,3};
		int k = 3;
		int threshold = 5;
		System.out.println(numOfSubarrays(arr,k,threshold));	
	}
}
