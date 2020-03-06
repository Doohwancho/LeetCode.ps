package array;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumDivisibleByK974 {
	
	/*
	//<Trial01>
	
	//모든 combination을 구하고, 각각의 합을 K로 나눈 나머지가 0인것의 갯수만 구하려고했는데,
	
	//문제를 다시보니 subarray만 ㅇㅈ이구만. 붙어있어야 됨
	
	static int rst = 0;
	// 백트래킹 사용
    // 사용 예시 : combination(arr, visited, 0, n, r)
    private static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
        if(r == 0) {
            print(arr, visited, n);
        } else {
            for(int i=start; i<n; i++) {
                visited[i] = true;
                combination(arr, visited, i + 1, n, r - 1);
                visited[i] = false;
            }
        }
    }
	
	private static void print(int[] arr, boolean[] visited, int n) {
		int total = 0;
        for(int i=0; i<n; i++) {
            if(visited[i] == true) {
                System.out.print(arr[i] + " ");
                total+=arr[i];
            }
        }
        System.out.println("total: "+total);
        if(total % 5 == 0) {
        	System.out.println("rst++! "+total);
        	rst++;
        }
        System.out.println();
    }
	
	public static void main(String[] args) {
        int[] arr = {4,5,0,-2,-3,1};
        int n = arr.length;
        boolean[] visited = new boolean[n];
 
        for(int i=1; i<=n; i++) {
            combination(arr, visited, 0, n, i);
        }
        System.out.println(rst);
    }
    */
	
	/*
	//<Trial02 - time limit exceeded>
	
	//brute-force
	
	//너무 날로 먹을라그랬나
	
	public int subarraysDivByK(int[] A, int K) {
        int rst = 0;
        for(int i = 0; i < A.length; i++){
            int total = A[i];
            if(total % K == 0){
                rst++;
            }
            for(int j = i+1; j < A.length; j++){
                total += A[j];
                if(total % K == 0){
                    rst++;
                }
            }
        }
        return rst;
    }
    */
	/*
	
	//<문제풀이1 by lee215>
	
	//clear explanation link : https://leetcode.com/problems/subarray-sums-divisible-by-k/discuss/310767/(Python)-Concise-Explanation-and-Proof
	
	//닝겐노 로직와 잉크레따따블 데스네
	
	//a: 4 prefix: 4 res: 0 count: {0=1, 4=1}
	//a: 5 prefix: 4 res: 1 count: {0=1, 4=2}
	//a: 0 prefix: 4 res: 3 count: {0=1, 4=3}
	//a: -2 prefix: 2 res: 3 count: {0=1, 2=1, 4=3}
	//a: -3 prefix: 4 res: 6 count: {0=1, 2=1, 4=4}
	//a: 1 prefix: 0 res: 7 count: {0=2, 2=1, 4=4}
	
	//Runtime: 20 ms, faster than 38.44% of Java online submissions for Subarray Sums Divisible by K.
	//Memory Usage: 44.9 MB, less than 5.26% of Java online submissions for Subarray Sums Divisible by K.
	
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1);
        int prefix = 0, res = 0;
        for (int a : A) {
            prefix = (prefix + a % K + K) % K; //a % K + K takes care of the cases where a < 0. abs(a % K) < K, a % K + K > 0. prefix = Math.floorMod(prefix + a, K); also works
            res += count.getOrDefault(prefix, 0); //First we count all the frequencies using count[p]; Then we do res += count[p]*(count[p]-1)/2; also the consecutive sum of x numbers is x*(x+1)/2; so, to shorted the code we can also use res += count[p]++; that is we add the frequency and then increment.
            //If you are still not convinced, observe what nC2(no of ways to choose 2 items from n) and sum of n-1 consecutive no is, you will find that both are the same
            count.put(prefix, count.getOrDefault(prefix, 0) + 1);
        }
        return res;
    }
    */
	
    //<문제풀이2 by lee215>
    
    //문제풀이1과 같은 원리, 좋은 성능
    
    //Runtime: 5 ms, faster than 86.08% of Java online submissions for Subarray Sums Divisible by K.
    //Memory Usage: 44.6 MB, less than 5.26% of Java online submissions for Subarray Sums Divisible by K.
    public int subarraysDivByK(int[] A, int K) {
        int[] count = new int[K];
        count[0] = 1;
        int prefix = 0, res = 0;
        for (int a : A) {
            prefix = (prefix + a % K + K) % K;
            res += count[prefix]++;
        }
        return res;
    }
    
    public static void main(String[] args) {
    	System.out.println(-4%5);
    	System.out.println((-4%5+5));
		System.out.println((-4%5+5)%5);
		System.out.println((4%5+5)%5);
	}
}
