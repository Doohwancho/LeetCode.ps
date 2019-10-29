/*
	Balanced strings are those who have equal quantity of 'L' and 'R' characters.
	
	Given a balanced string s split it in the maximum amount of balanced strings.
	
	Return the maximum amount of splitted balanced strings.
	
	 
	
	Example 1:
	
	Input: s = "RLRRLLRLRL"
	Output: 4
	Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
	Example 2:
	
	Input: s = "RLLLLRRRLR"
	Output: 3
	Explanation: s can be split into "RL", "LLLRRR", "LR", each substring contains same number of 'L' and 'R'.
	Example 3:
	
	Input: s = "LLLLRRRR"
	Output: 1
	Explanation: s can be split into "LLLLRRRR".
	
	
	
	<문제>
	
	L이 연속적으로 나온 숫자와 R이 연속적으로 나온 숫자가 같다면, 밸런스가 맞았다 라고 가정한다.
	
	문자열이 다음과 같이 주어졌다고 하자.
	
	"RLLLLRRRLR"
	
	문제에서 주어지는 문자열은, 밸런스가 맞은 문자열이 그 안에 최소 한개 이상 있다고 가정한다면, 위 문자열안의 밸런스가 맞은 문자열의 갯수를 반환하라.
	
	예를들어, "RLLLLRRRLR"의 경우,
	
	"RL" + "LLLRRR" + "LR" 총 3번 밸런스가 맞기 때문에 3을 반환해 주면 된다.
	
 */

package Greedy;

public class SplitaStringInBalancedStrings1221 {
	 
	//<문제풀이1>
	
	//R이 나오면 +1, L이 나오면 -1해서, +-값이 0이되면 balance가 되었기 때문에, balance==0이된 갯수를 반환.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Split a String in Balanced Strings.
	//Memory Usage: 34 MB, less than 100.00% of Java online submissions for Split a String in Balanced Strings.
	
	public static int balancedStringSplit(String s) {
		int balance = 0, rst = 0;
		
		for(char c : s.toCharArray()) {
			if(c == 'R') balance++;
			else balance--;
			if(balance == 0) rst++;
		}
		
		return rst;
	}
	
	
	public static void main(String[] args) {
		String s = "RLLLLRRRLR";
		System.out.println(balancedStringSplit(s));
	}
}






