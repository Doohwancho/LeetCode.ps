/*
	Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
	
	If the last word does not exist, return 0.
	
	Note: A word is defined as a character sequence consists of non-space characters only.
	
	Example:
	
	Input: "Hello World"
	Output: 5
	
	
	
	<문제>
	
	String타입의 문장 s가 주어지면, 문장의 가장 마지막 단어의 길이를 구하라.
	
	
	
	<문제풀이>
	
	String 타입의 가장 마지막 단어를 구하려면, 먼저 두개의 단어가 존재해야 한다. 두개의 단어가 존재하려면 적어도 하나의 스페이스가 필요하다. 그것을 표현해 주는 부분이 s.trim().lastIndexOf(" ") > 0 이 부분이다.
	만약 스페이스가 존재하지 않는다면 -1를 반환하기 때문에, false이므로, : 이하 코드를 실행한다.
	만약 스페이스가 존재 한다면, 문장에 가장 뒤에서 부터 스페이스의 인덱스를 찾는다. 그 후, .substring()을 이용하여, 스페이스 이후부터의 문자열들(마지막 단어)를 도출한다.
	이것을 나타내는 것이 s.substring(s.lastIndexOf(" ")+1)이다. 다만, 여기서 .trim()이 중간중간에 추가된 이유는 이러하다.
	.trim()은 문자열 맨 처음과 끝부분에 스페이스가 있으면 모두 지워주는 역할을 한다. 위의 로직에서 스페이스는 단어와 단어 사이에만 존재해야 하기 때문에, 문장 맨 첫번째나 끝에 존재하는 스페이스는 지워준다.
	마지막으로 연산식은 "(조건) ? 참일때 실행되는 부분 : 거짓일때 실행되는 부분" 을 사용하였다.
	
 */


package String;

public class LengthOfLastWord58 {
	public static int lengthOfLastWord(String s)
	{
		return (s.trim().lastIndexOf(" ") > 0) ? s.trim().substring(s.trim().lastIndexOf(" ")+1).trim().length() : s.trim().length();
	}
	
	
	
	public static void main(String[] args) 
	{
		//String s = "Hello World";
		//String s = "a ";
		//String s = "b a ";
		String s = "  Today is a nice day";
		
		System.out.println(lengthOfLastWord(s));
	}
}
