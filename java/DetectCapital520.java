/*
 * 
Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.
 

Example 1:

Input: "USA"
Output: True
 

Example 2:

Input: "FlaG"
Output: False
 */

/*
 * 문제
 * 
 * 문자열이 주여지면 다음의 조건들을 만족시키면 True를 반환하고, 아니면 False를 반환하라.
 * 조건1. 모든 알파벳이 대문자일경우 
 * 조건2. 모든 알파벳이 소문자일경우
 * 조건3. 가장 첫번째 알파벳만 대문자이고, 나머지가 소문자일 경우
 * 
 * 
 * 
 * 문제 풀이
 * 
 * 첫번째 알파벳이 대문자인 경우 a와, 소문자인 경우 b로 나뉜다.
 * a의 경우, a-1.나머지 문자열이 모두 대문자일때(ex.USA) a-2.나머지 문자열이 모두 소문자일때(ex.Leetcode) true를 반환한다.
 * b의 경우, b-1.나머지 문자열이 모두 소문자일때(ex.america) true를 반환한다.
 * 
 */

package String;

public class DetectCapital520 {

	    private static boolean restNoCap(String word)
	    {
	        for(int i = 1; i < word.length(); i++)
	        {
	            if(Character.isUpperCase(word.charAt(i))) return false;
	        }
	        return true;
	    }
	    private static boolean restAllCap(String word)
	    {
	        for(int i = 1; i < word.length(); i++)
	        {
	            if(Character.isLowerCase(word.charAt(i))) return false;
	        }
	        return true;
	    }
	    public static boolean detectCapitalUse(String word) {
	        if(word.length()==1)
	        {
	            return true;
	        }
	        if(Character.isUpperCase(word.charAt(0)))
	        {
	            return restNoCap(word) | restAllCap(word);
	        }
	        else
	        {
	            return restNoCap(word);
	        }   
	    }
	
	public static void main(String[] args) {
		String word = "LeEtcode";
		System.out.println(detectCapitalUse(word));
	}
}



