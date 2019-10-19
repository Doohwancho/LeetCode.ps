/*
	We are playing the Guess Game. The game is as follows:
	
	I pick a number from 1 to n. You have to guess which number I picked.
	
	Every time you guess wrong, I'll tell you whether the number is higher or lower.
	
	You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
	
	-1 : My number is lower
	 1 : My number is higher
	 0 : Congrats! You got it!
	Example :
	
	Input: n = 10, pick = 6
	Output: 6
	
	
	
	
	<문제>
	
	1부터 n까지 수 중에, 내가 마음속으로 임의의 숫자를 정했으면,
	
	상대방이 숫자를 하나 던져서, 내가 생각한 숫자가 상대방이 생각한 숫자보다 위면 1, 아래면 -1, 같으면 0이라고 말한다.
	
	이렇게 몇차례에 걸쳐서 상대방이 내가 생각한 숫자를 맞추는 게임(함수)를 만들어라.
	
	단, guess()라는 함수는 이미 만들어져 있는데,
	
	guess라는 함수가 위에서 말한, 숫자를 비교하여 1,-1,0을 반환하는 함수이다.
 */


package BinarySearch;

public class GuessNumberHigherOrLower374 {
	
	//<문제풀이 by 	yfcheng>
	
	//일반적인 binary-search를 사용한다.
	
	//1부터 n까지 수라고 했으니까, i부터 j를 1부터 n으로 정해놓고,
	
	//일반적인 binary search처럼 middle을 (start+end)/2
	
	//로 하는데, leetcode에서 악질적이게 input을 미친듯이 큰 숫자를 넣으면 
	
	//21~~~ + 21~~~~ 하면 int의 범위에 벗어나 overflow가 일어나기 때문에,
	
	//(start+end)/2와 값을 똑같지만 공식만 조금 변형한 start+(end-start)/2를 사용한다.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Guess Number Higher or Lower.
	//Memory Usage: 32.9 MB, less than 100.00% of Java online submissions for Guess Number Higher or Lower.
	
	static int pick = 6;
	
	private static int guess(int mid) {
		/*fill in the blanks*/
		return 0;
	}
	
	public static int guessNumber(int n) {
        int i = 1, j = n;
        while(i < j) {
            int mid = i + (j - i) / 2;
            if(guess(mid) == 0) {
                return mid;
            } else if(guess(mid) == 1) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return i;
	}
     
	
	public static void main(String[] args) {
	    int n = 1028;
		System.out.println(guessNumber(n));
	}
}
