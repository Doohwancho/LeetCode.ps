/*
	Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
	
	Note: For the purpose of this problem, we define empty string as valid palindrome.
	
	Example 1:
	
	Input: "A man, a plan, a canal: Panama"
	Output: true
	Example 2:
	
	Input: "race a car"
	Output: false
	
	
	<문제>
	
	Palindrome이란, 문장의 앞,뒤를 뒤집었을때 원래 문장과 같은 것을 뜻한다.
	예를들어, "다 같은 금은 같다"를 뒤집어도 원래 문장을 만들 수 있다.(띄어쓰기 무시)
	다른 예시로, "A man, a plan, a canal: Panama"도 palindrome이다.(특수문자도 무시한다)
	
	문자열이 주어지면, palindrome인지 확인하고, 맞다면 true, 아니면 false를 반환하라.
	
	
	<문제풀이>
	
	step01 : trim(), replace(특수문자들), .toLowerCase(), replace(space)
	step02 : 역순 for문으로 StringBuilder에 저장(.reverse())
	뒤집기 전의 s와 StringBuilder.toString()과 .equal()로 비교(==로 비교하면 주소값을 비교하기 때문에 안됨)	
 */


package String;

public class ValidParlindrome125ver1 {
	public static boolean isPalindrome(String s)
	{
		//step01 : trim(), replace(특수문자들), .toLowerCase(), replace(space)
		String  newS = s.trim().toLowerCase().replace(",", "").replace(".", "").replace("\\","").replace("-", "").replace("=", "").replace("+", "").replace("!", "").replace("@", "").replace("#", "").replace("$", "").replace("%", "").replace("^", "").replace("&", "").replace("*", "").replace("(", "").replace(")","").replace(".", "").replace("?", "").replace(":", "").replace(";","").replace("|","").replace("`","").replace(" ", "").replace("\"","").replace("'","");
		
		//step02 : 역순 for문으로 StringBuilder에 저장(.reverse())
		StringBuilder sb = new StringBuilder();
		for(int i = newS.length()-1; i >= 0 ; i--)
		{
			sb.append(newS.charAt(i));
		}
		
		//step03 : 뒤집기 전의 s와 StringBuilder.toString()과 .equal()로 비교(==로 비교하면 주소값을 비교하기 때문에 안됨)	
		return newS.contentEquals(sb.toString());
	}
	
	
	public static void main(String[] args) 
	{
		String s = "A man, a plan, a canal: Panama";
		System.out.println(isPalindrome(s));
	}
}
