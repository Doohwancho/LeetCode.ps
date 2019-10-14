/*
	Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
	
	An input string is valid if:
	
	Open brackets must be closed by the same type of brackets.
	Open brackets must be closed in the correct order.
	Note that an empty string is also considered valid.
	
	Example 1:
	
	Input: "()"
	Output: true
	Example 2:
	
	Input: "()[]{}"
	Output: true
	Example 3:
	
	Input: "(]"
	Output: false
	Example 4:
	
	Input: "([)]"
	Output: false
	Example 5:
	
	Input: "{[]}"
	Output: true
	
	
	
	
	
	<문제>
	
	(),{},[]은 각각 한 쌍이다.
	
	(},[),{]은 쌍이 아니다.
	
	[()], [{()}]도 쌍이다.
	
	쌍이면 true,아니면 false를 반환하라.
 */

package Stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParenthesis20 {
	
	
	/*
	//<문제풀이1>
	
	//(),{},[] 각각 dictionary(map)으로 페어지어준 후, stack으로 가장 마지막에 나온 괄호가 무엇인지 파악하면서,
	
	//최근 나온 괄호가 닫는 괄호일때, 마지막에 stack에 쌓인 괄호의 짝을 dictionary로 찾아 맞으면 pass, 다르면 false반환.
	
	//마지막엔 ()( 같은 케이스가 있을 수 있기 때문에, 열린괄호는 모두 닫혀야만 함으로,
	
	//stack.size() == 0을 넣었음
	
	//성능은 나쁘지 않은편
	
	//Runtime: 2 ms, faster than 61.20% of Java online submissions for Valid Parentheses.
	//Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Valid Parentheses.
	public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        
        for(char c : s.toCharArray()) {
        	if(c == ')' || c == '}' || c == ']') {
        		if(stack.size() > 0 && c == map.get(stack.pop())) continue;
        		else return false;
        	}
        	stack.push(c);
        }
		return stack.size() == 0;
    }
    */
	
	//<문제풀이2 by phoenix13steve>
	
	//dictionary 굳이 안쓰고 if-else로 처리.
	
	//열린괄호가 나왔을 때, 열린괄호와 대응되는 닫힌괄호를 stack에 쌓고, 
	
	//닫힌괄호가 나왔을 때, pop()으로 마지막에 넣어둔 열린관호와 대응되는 닫힌괄호가 같은지 확인하는 방법.
	
	//dictionary이용하는 방법보다 1ms 더 빠름.
	
	//Runtime: 1 ms, faster than 98.72% of Java online submissions for Valid Parentheses.
	//Memory Usage: 34.3 MB, less than 100.00% of Java online submissions for Valid Parentheses.
	
	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (char c : s.toCharArray()) {
			if (c == '(')
				stack.push(')');
			else if (c == '{')
				stack.push('}');
			else if (c == '[')
				stack.push(']');
			else if (stack.isEmpty() || stack.pop() != c)
				return false;
		}
		return stack.isEmpty();
	}
	
	public static void main(String[] args) {
		String s = "{[]}";
		System.out.println(isValid(s));
	}
}
