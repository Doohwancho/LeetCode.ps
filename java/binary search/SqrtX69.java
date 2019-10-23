/*
	Implement int sqrt(int x).
	
	Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
	
	Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
	
	Example 1:
	
	Input: 4
	Output: 2
	Example 2:
	
	Input: 8
	Output: 2
	Explanation: The square root of 8 is 2.82842..., and since 
	             the decimal part is truncated, 2 is returned.

 
 
 	
 	
 	<문제>
 	
 	숫자 x가 주어지면, 소숫점 자리를 버린 루트 x를 반환하라.

 */

package BinarySearch;

public class SqrtX69 {
	
	//<문제풀이1>
	
	//큰 틀은 binary-search 이고, 값이 답과 맞는지 확인해주는 m(middle)을 제곱하여 x와 같은지 확인.
	
	//x가 1이나 0이면 binary-search가 안먹히므로, 유효성 검사.
	
	//INT_MAX가 2147483647인데 leetcode에서 비열하게 이것보다 더 큰 숫자인 2147395599를 넣어서
	//int로 처리하지않고 그것보다 더 큰 숫자범위를 커버하는 long으로 담음.
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Sqrt(x).
	//Memory Usage: 33.5 MB, less than 5.00% of Java online submissions for Sqrt(x).

	public static int mySqrt(int x) {
        if(x <= 1) return x;
		long l = 0, r = x, m = 0;
        while(l < r) {
        	m = l + (r-l)/2;
        	long sqrt = m * m;
        	if(sqrt == x) return (int)m;
        	else if(sqrt > x) r = m;
        	else l = m + 1;
        }
		return (int)l-1;
    }
	
	public static void main(String[] args) {
		int x = 2;
		System.out.println(mySqrt(x));
	}
}
