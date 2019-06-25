/*
	Implement strStr().
	
	Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
	
	Example 1:
	
	Input: haystack = "hello", needle = "ll"
	Output: 2
	Example 2:
	
	Input: haystack = "aaaaa", needle = "bba"
	Output: -1
	
	
	
	<문제>
	
	문자열 타입의 haystack과 needle이 주어진다. needle이 haystack에 substring이라면 해당 인덱스의 첫번째를 반환하고, substring이 아니면 -1를 반환하라.
	
	
	<문제 풀이>
	
	.indexOf()를 사용하면 간단하게 해결가능하다. .indexOf()는 char type과 String type 모두를 받을 수 있으며, 받은것의 인덱스를 반환하고, 만약 없다면 -1를 반환하는 내장함수이다.
	
 */

package String;

public class ImplementstrStr28 {
	public static int strStr(String haystack, String needle)
	{
		return haystack.indexOf(needle);
	}
	
	public static void main(String[] args) 
	{
		String haystack = "hello";
		String needle = "ll";
		System.out.println(strStr(haystack, needle));
	}
}
