/*
	The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
	
	F(0) = 0,   F(1) = 1
	F(N) = F(N - 1) + F(N - 2), for N > 1.
	Given N, calculate F(N).
	
	 
	
	Example 1:
	
	Input: 2
	Output: 1
	Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
	Example 2:
	
	Input: 3
	Output: 2
	Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
	Example 3:
	
	Input: 4
	Output: 3
	Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
	
	
	
	<문제>
	
	피보나치 수열이란, N이 주어지면 (N-1)+(N-2)를 한 숫자이다.
	
	숫자 N이 주어지면, N의 F(N) = F(N - 1) + F(N - 2), for N > 1 을 구하라. 
	
	단, F(0) = 0, F(1) = 1 이다.
	
	
	
	<문제풀이>
	
	문제에서 친절하게 알려준 F(N) = F(N - 1) + F(N - 2), for N > 1  을 그대로 식에 적용한다
	
	comprehension으로 적용하면, (조건문) ? true일때 적용되는 구간 : false일때 적용되는 구간 이므로,
	
	(N > 1) ? F(N - 1) + F(N - 2) : false일때 적용되는 구간 까지 만들 수 있다.
	
	':'이후에 나오는 구간은 해당 조건문이 거짓일 때, 즉, N이 1을 초과하지 않을 때(N이 1이거나 0일 때) 발동이 된다.
	
	이 경우는 다시 두가지로 나뉜다. N이 1일 때와 N이 0일 때. 
	
	이를 다시 comprehension으로 표현하면, (N == 1) ? 1 : 0으로 표현 할 수 있다.
	
	방금 만든 부분을, 맨 처음 만들었었던 (N > 1) ? F(N - 1) + F(N - 2) : false일때 적용되는 구간
	
	에서, false일때 적용되는 구간, ':'이후에 넣어주기만 하면 된다.
	
	
 * 
 */


package Array;

public class FibonacciNumber509 {
	public static int fib(int N)
	{
		//F(0) = 0
		//F(1) = 1
		//F(N) = F(N - 1) + F(N - 2), for N > 1.
		
		return (N > 1) ? fib(N - 1) + fib(N - 2) : (N == 1) ? 1 : 0;
	}
	
	public static void main(String[] args) {
		int N = 3;
		System.out.println(fib(N));
	}
}
