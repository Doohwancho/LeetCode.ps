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
	
	소괄호, 중괄호, 대괄호로 구성된 문자열 s가 주어진다. 각각의 쌍에 맞게끔 괄호가 닫혀야 한다. 예를들면 "()"는 소괄호 쌍에 맞게끔 닫혀서 가능하지만, "(}"는 쌍에 안맞게 닫혔으므로 불가능하다.
	또한 "{[]}"처럼, 다른종류의 괄호가 2번 이상 열리는 경우, 열린 괄호의 가장 마지막 괄호부터 쌍에 맞춰서 괄호가 닫혀야 한다.
	
	
	
	<문제풀이>
	HashMap의 key:value를 String형태로 선언한다. 여기에 key값을 여는괄호 "(","{","["를 넣고, value값을 닫힌괄호 ")","}","]"로 넣는데, 쌍에 맞게끔 넣는다.
	주어진 문자열 s를 알파벳 하나하나 for문으로 돌아야 하기 때문에, char[]로 바꿔서 돌면서, 여는괄호로 시작한다면 선언한 리스트에 여는괄호의 파트너괄호(ex. "{"라면, "}")를넣어주고, 
	닫힌괄호가 나타난다면, 마지막에 넣어진 닫힌괄호와 비교하여, 다르면 false를 반환하고, 같다면 리스트에 마지막 닫힌괄호를 제거해준다.
	맨 마지막에 반환시, .isEmpty()를 해주는 이유는, s가 "(){}["처럼 나타난 경우, for문을 모두 통과해도 parenthesis 리스트 안에 "["가 남기 때문이다.
 * 
 * 
 */


package String;


import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class ValidParenthesis20 {
	public static boolean isValid(String s)
	{
		if(s.length() < 1) {
			return true;
		}
		Map<String, String> ht = new HashMap<String, String>();
		List<String> parenthesis = new ArrayList<String>();
			
		ht.put("(",")");
		ht.put("{","}");
		ht.put("[","]");
		
		char[] chr = s.toCharArray();

		for(char ch : chr)
		{
			if(ht.containsKey(ch+""))
			{
				parenthesis.add(ht.get(ch+""));
			}
			else
			{
				if(parenthesis.size() > 0 && parenthesis.get(parenthesis.size()-1).equals(ch+"")) //==와 .equals()의 차이 -> https://ojava.tistory.com/15
				{
					parenthesis.remove(parenthesis.size()-1);
				}
				else
				{
					return false;
				}
			}
		}
		return parenthesis.isEmpty();
	}
	
	public static void main(String[] args) {
		//String s = "()[]{}";
		//String s = "([)]";
		String s = "]";
		System.out.println(isValid(s));
	}
}
