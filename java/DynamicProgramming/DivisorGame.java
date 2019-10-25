/*
	Alice and Bob take turns playing a game, with Alice starting first.
	
	Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:
	
	Choosing any x with 0 < x < N and N % x == 0.
	Replacing the number N on the chalkboard with N - x.
	Also, if a player cannot make a move, they lose the game.
	
	Return True if and only if Alice wins the game, assuming both players play optimally.
	
	 
	
	Example 1:
	
	Input: 2
	Output: true
	Explanation: Alice chooses 1, and Bob has no more moves.
	Example 2:
	
	Input: 3
	Output: false
	Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
	 
	 
	 
	 
	<문제>
	
	alice와 bob이 게임을 한다
	
	게임의 규칙은 다음과 같다.
	
	처음에 숫자 N이 주어진다.
	
	각 턴마다, 1부터 N-1까지의 숫자 중, N에서 나눴을 때 나머지 없이 딱 나눠지는 숫자를 N에서부터 빼준다.
	
	예를들어 N이 8이면, 8 나누기 4의 나머지가 0 이므로, 8에서 4를 뺀 4를 상대방에게 넘겨준다.
	
	물론 2나 1도 나눴을 때 나머지가 0이므로, 6이나 7을 상대방에게 넘겨줄 수 있다.
	
	만약 넘겨받은 숫자가 1이면 그 사람은 게임에서 진다.
	
	alice와 bob이 최적의 방식으로 플레이 한다고 하고 alice가 먼저 선턴을 잡는다고 했을 때,
	
	divisorGame(N)에서 alice가 이기면 true, 아니면 false를 반환하라.
 */

package DynamicProgramming;

public class DivisorGame {
	
	
	/*
	//<Trial01>
	
	//상대턴의 prime number면 상대가 무조건 짐(2빼고). 1이 되기 때문.
	
	//어떻게 상대한테 prime number가 되게 할 것인가?
	
	//prime number = n - x(n % x == 0)
	
	//1부터 n까지 숫자중 isDivisor() + isPrime(n-x) == true면 끝
	
	//막힌점 -> if both player played optimally라고 문제에 나와있는데,
	
	//N이 4의 경우 두가지로 플레이 가능
	
	//경우의 수1: alice:4 -> bob:2 -> alice:1 ===================== bob win
	
	//경우의 수2: alice:4 -> bob:3 -> alice:2 -> bob:1  =========== alice win
	
	//1을뺄지 2를뺄지 어떻게 optimal choice하는지 모르겠음.
	
	//1부터 N-1까지 숫자들 중에서 N에서 나눴을때 나머지없이 딱 맞아 떨어지는 숫자중 가장 큰숫자를 나눈다라고 했긴 했는데 틀림.
	
	private static boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) if(n%i==0) return false;
	    return true;
	}
	
	private static boolean isDivisor(int a, int b) {
		return (a % b) == 0;
	}
	
	public static boolean divisorGame(int N) {
		
		int n = N;
		
		while(n > 1) {
			int lastDivisor = 0;
			
			for(int i = 2; i < n; i++) {
				if(isDivisor(N, i) && (N - i) != 2 && isPrime(N - i)) {
					return true;
				}
				if(n % i == 0) lastDivisor = i;
			}
			if(lastDivisor > 0) n %= lastDivisor;
			//else return n == 2;
		}
		return false;
    }
	*/
	
	
	//<문제풀이1 by lee215>
	
	//N이 짝수면 무조건 이기고 홀수면 무조건 짐.
	
	//step01) N이 짝수일 때, 1을빼면 무조건 홀수가 됨.
	
	//step02) 홀수가 1부터 N-1까지 숫자중 나누어 떨어지는 숫자를 N에서 빼면 무조건 짝수가 됨. 그럼 상대편은 step1반복. 1이 될때까지.
	
	//씨발
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Divisor Game.
	//Memory Usage: 33 MB, less than 13.33% of Java online submissions for Divisor Game.
	
	public static boolean divisorGame(int N) {
		return N % 2 == 0;
	}
	
	public static void main(String[] args) {
		int N = 4;
		System.out.println(divisorGame(N));
	}
}
