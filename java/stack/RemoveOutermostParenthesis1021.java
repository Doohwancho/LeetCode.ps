/*
	A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.  For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
	
	A valid parentheses string S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B, with A and B nonempty valid parentheses strings.
	
	Given a valid parentheses string S, consider its primitive decomposition: S = P_1 + P_2 + ... + P_k, where P_i are primitive valid parentheses strings.
	
	Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.
	
	 
	
	Example 1:
	
	Input: "(()())(())"
	Output: "()()()"
	Explanation: 
	The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
	After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
	Example 2:
	
	Input: "(()())(())(()(()))"
	Output: "()()()()(())"
	Explanation: 
	The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
	After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
	Example 3:
	
	Input: "()()"
	Output: ""
	Explanation: 
	The input string is "()()", with primitive decomposition "()" + "()".
	After removing outer parentheses of each part, this is "" + "" = "".
	
	
	
	
	
	
	<문제>
	
	Input: "(()())(())(()(()))"
	Output: "()()()()(())"
	
	인풋을 자세히 보면, "(()())" + "(())" + "(()(()))" 으로 나눌 수 있다.
	
	여기서 가장 바깥쪽에 감싸는 괄호를 빼고 내부 알맹이 괄호만 반환하라.
 */


package Stack;

public class RemoveOutermostParenthesis1021 {
	
	/*
	//<문제풀이1>
	
	//input의 괄호 '('를 1로, ')'를 0으로 바꾸면 다음과 같이 숫자로 바꿀 수 있다.
	
	//String s = "(()())(())(()(()))";
    //            110100 1100 11011000
	
	//그러면 가장 바깥쪽 괄호가 닫힐때마다, +-0이 되는 패턴을 볼 수 있다.
	
	//또한, 가장 바깥쪽 괄호를 빼고 안에 괄호만 넣으려면, 
	
	//시작은 +2일때, 마지막은 0일때 그 조건을 충족시킨다.
	
	//이 점 + 괄호를 정답지로 뺄 땐 stringbuilder를 사용한 것을 이용하면 다음과 같은 코드가 나옴.
	
	//성능은 나쁘지 않은 편.
	
	//Runtime: 3 ms, faster than 68.95% of Java online submissions for Remove Outermost Parentheses.
	//Memory Usage: 35.8 MB, less than 100.00% of Java online submissions for Remove Outermost Parentheses.
	
	public static String removeOuterParentheses(String S) {
		
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		
		for(int i = 0, closeCheck = 0; i < S.length(); i++) {
			if(S.charAt(i) == '(') closeCheck++;
			else if(S.charAt(i) == ')') closeCheck--;
			if(closeCheck > 1) flag = true;
			else if(closeCheck == 0) flag = false;
			if(flag) sb.append(S.charAt(i));
		}
		return sb.toString();
    }
	*/
	
	//<문제풀이 by lee215>
	
	//문제풀이1을 flag없이 한단계 더 압축한 것. 1ms더 빠르다.
	
	//Runtime: 2 ms, faster than 97.93% of Java online submissions for Remove Outermost Parentheses.
	//Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Remove Outermost Parentheses.
	
	public static String removeOuterParentheses(String S) {
        StringBuilder s = new StringBuilder();
        int opened = 0;
        for (char c : S.toCharArray()) {
            if (c == '(' && opened++ > 0) s.append(c);
            if (c == ')' && opened-- > 1) s.append(c);
        }
        return s.toString();
    }
	
	public static void main(String[] args) {
		String s = "(()())(())(()(()))";
		System.out.println(removeOuterParentheses(s));
	}
}
