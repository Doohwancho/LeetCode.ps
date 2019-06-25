/*
	<문제>


	Write a function that takes a string as input and reverse only the vowels of a string.
	
	Example 1:
	
	Input: "hello"
	Output: "holle"
	Example 2:
	
	Input: "leetcode"
	Output: "leotcede"
	Note:
	The vowels does not include the letter "y".
	
	
	문자열 s가 주어지면, 자음은 남겨두고 모음들의 위치만 반대로 뒤집어라.
	
	
	<문제풀이>
	
	step01)모음들을 hashset에 저장한다
	step02)문자열 s를 for문을 사용하여 역순으로 돌면서, stringbuilder에 모음만 입력한다.
	step03)이번엔 s를 for문을 사용하여 정방향으로 돌면서, 모음자리에 수집한 reverse된 모음들을 넣어준다.
	
	
 * 
 */


package String;

import java.util.HashSet;
import java.util.Set;

public class ReverseVowels345 {
	public static String reverseVowels(String s)
	{
		//step01
		Set<Character> vowels = new HashSet<Character>();
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
		
		vowels.add('A');
		vowels.add('E');
		vowels.add('I');
		vowels.add('O');
		vowels.add('U');
		
		char[] letter = s.toCharArray();
		int sbIndex = 0;
		StringBuilder reverseVowels = new StringBuilder();
		StringBuilder rst = new StringBuilder();
		
		//step02
		for(int i = s.length()-1; i >= 0 ; i--)
		{
			if(vowels.contains(letter[i]))
			{
				reverseVowels.append(letter[i]);
			}
		}
		
		//step03
		for(int j = 0 ; j < s.length(); j++)
		{
			if(vowels.contains(letter[j]))
			{
				rst.append(reverseVowels.charAt(sbIndex));
				sbIndex++;
			}
			else
			{
				rst.append(letter[j]);
			}
		}
		
		return rst.toString();
	}
	
	public static void main(String[] args) {
		String s = "hello";
		System.out.println(reverseVowels(s));
	}
}
