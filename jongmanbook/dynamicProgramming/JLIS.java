package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JLIS {

	static int[] A = {10,20,30,1,2};
	static int[] B = {10,20,30};
	
	static int[][] mem = new int[6][6];
	
	
	//1. brute-force
	
	private static int[] intuition() {
		Map<Integer, Integer> map = new HashMap<>();
		for(int a : A) {
			map.put(a, 1);
		}
		for(int b : B) {
			map.put(b, 1);
		}
		
		int[] rst = new int[map.size()];
		int i = 0;
		
		for(Integer key : map.keySet() ){
            rst[i++] = key;
        }
		Arrays.sort(rst);
		return rst;
	}
	
	
	//2. dp
	
	//ans:5
	
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	
	//0 0 0 0 0 0 
	//2 0 0 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	
	//0 0 0 0 0 0 
	//2 0 0 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	
	//0 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	
	//0 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	
	//0 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	
	//0 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	
	//3 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	
	//3 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	
	//3 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 1 0 0 0 
	
	//3 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 2 1 0 0 0 
	
	//3 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 2 1 0 0 0 
	
	//3 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//3 2 1 0 0 0 
	
	//3 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//0 0 0 0 0 0 
	//3 2 1 0 0 0 
	
	//3 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//4 0 0 0 0 0 
	//3 2 1 0 0 0 
	
	//3 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//4 0 1 0 0 0 
	//3 2 1 0 0 0 
	
	//3 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//4 2 1 0 0 0 
	//3 2 1 0 0 0 
	
	//3 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//4 2 1 0 0 0 
	//3 2 1 0 0 0 
	
	//3 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//4 2 1 0 0 0 
	//3 2 1 0 0 0 
	
	//3 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//4 2 1 0 0 0 
	//3 2 1 0 0 0 
	
	//5 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 0 0 0 0 0 
	//0 0 0 0 0 0 
	//4 2 1 0 0 0 
	//3 2 1 0 0 0 
	
	//5 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 1 0 0 0 0 
	//0 0 0 0 0 0 
	//4 2 1 0 0 0 
	//3 2 1 0 0 0 
	
	//5 0 0 0 0 0 
	//2 0 1 0 0 0 
	//1 1 0 0 0 0 
	//0 0 0 0 0 0 
	//4 2 1 0 0 0 
	//3 2 1 0 0 0 
	
	//5 2 0 0 0 0 
	//2 0 1 0 0 0 
	//1 1 0 0 0 0 
	//0 0 0 0 0 0 
	//4 2 1 0 0 0 
	//3 2 1 0 0 0 
	
	//5 2 0 0 0 0 
	//2 0 1 0 0 0 
	//1 1 0 0 0 0 
	//0 0 0 0 0 0 
	//4 2 1 0 0 0 
	//3 2 1 0 0 0 
	
	//5 2 1 0 0 0 
	//2 0 1 0 0 0 
	//1 1 0 0 0 0 
	//0 0 0 0 0 0 
	//4 2 1 0 0 0 
	//3 2 1 0 0 0 
	
	//5 2 1 0 0 0 
	//2 0 1 0 0 0 
	//1 1 0 0 0 0 
	//0 0 0 0 0 0 
	//4 2 1 0 0 0 
	//3 2 1 0 0 0 
	
	//5 2 1 0 0 0 
	//2 0 1 0 0 0 
	//1 1 0 0 0 0 
	//0 0 0 0 0 0 
	//4 2 1 0 0 0 
	//3 2 1 0 0 0 
	
	//5 2 1 0 0 0 
	//2 0 1 0 0 0 
	//1 1 0 0 0 0 
	//0 0 0 0 0 0 
	//4 2 1 0 0 0 
	//3 2 1 0 0 0 
	
	//5 2 1 0 0 0 
	//2 0 1 0 0 0 
	//1 1 0 0 0 0 
	//0 0 0 0 0 0 
	//4 2 1 0 0 0 
	//3 2 1 0 0 0 
	
	//5 2 1 0 0 0 
	//2 0 1 0 0 0 
	//1 1 0 0 0 0 
	//0 0 0 0 0 0 
	//4 2 1 0 0 0 
	//3 2 1 0 0 0 
	
	
	private static int dp(int ia, int ib) {
		if(mem[ia+1][ib+1] != 0) return mem[ia+1][ib+1];
		int a = ia == -1 ? Integer.MIN_VALUE : A[ia];
		int b = ib == -1 ? Integer.MIN_VALUE : A[ib];
		int maxElement = Math.max(a, b);
		
		for(int nextA = ia+1; nextA < A.length; nextA++) {
			if(maxElement < A[nextA]) {
				mem[ia+1][ib+1] = Math.max(mem[ia+1][ib+1], dp(nextA, ib)+1);
			}
		}
		for(int nextB = ib+1; nextB < B.length; nextB++) {
			if(maxElement < B[nextB]) {
				mem[ia+1][ib+1] = Math.max(mem[ia+1][ib+1], dp(ia, nextB)+1);
			}
		}
		return mem[ia+1][ib+1];
	}
	
	/*
	//얘는 또 안됨.
	
	//첫줄은 무조건 다 돌아야 하는구나
	
	private static int dp(int ia, int ib) {
		if(mem[ia][ib] != 0) return mem[ia][ib];
		int a = A[ia];
		int b = A[ib];
		int maxElement = Math.max(a, b);
		
		for(int nextA = ia; nextA < A.length; nextA++) {
			if(maxElement < A[nextA]) {
				mem[ia][ib] = Math.max(mem[ia][ib], dp(nextA, ib)+1);
			}
		}
		for(int nextB = ib; nextB < B.length; nextB++) {
			if(maxElement < B[nextB]) {
				mem[ia][ib] = Math.max(mem[ia][ib], dp(ia, nextB)+1);
			}
		}
		return mem[ia][ib];
	}
	*/
	
	
	
	public static void main(String[] args) {
		//1
//		int[] ret = intuition();
//		for(int r : ret) {
//			System.out.print(r+" ");
//		}
		
		System.out.println(dp(-1,-1));
		
	}
}
