/*
 * 
	Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
	
	Example 1:
	Input: "aba"
	Output: True
	Example 2:
	Input: "abca"
	Output: True
	Explanation: You could delete the character 'c'.


	<문제>
	주어진 문자열 s가 palindrome인지 확인한다.
	
	palindrome이란, 문자열이 서로 좌우대칭되는 것을 의미한다. ex) "aba", "abccba"
	
	단, 문자열의 알파벳 중, 하나를 제외하여 palindrome을 만들 수 있다면, 그 경우 역시 true를 반환한다. ex) "abxccba" -> "x"제외하면 -> "abccba" (palindrome)
	

	
	<문제풀이 by tankztc>
	
	1. two pointer의 개념으로, 인덱스의 첫번째(left)와 마지막(right)부터 비교해 나간다.
	
	2. left와 right가 다른 경우, left와 right중, 해당 인덱스를 건너 뛰고, 나머지가 palindrome인지 비교하면 된다.
	
	예를들어, axbba인 경우, a=a다음, x=b인데, 서로 다르므로, left인 x를 건너뛰고 b와 b를 비교하거나, right인 b를 건너뛰고 x와 b를 비교하면 된다.
	
	3. 따라서, 해당 인덱스를 건너뛰고 palindrome인지 비교하게 해주는 함수를 만들어서, (left+1,right)와 (left,right-1)를 비교해준다.
	
	4. 둘 중, 하나라도 palindrome이면(true면), true를 반환해준다.(or 연산자 ||사용)
		
	
 */

package String;

public class ValidPalindrome680 {
	public static boolean isvalidPalindrome(String s, int left, int right)
	{
		if(left >= right)
		{
			return true;
		}
		for( ; left < right ; left++, right--)
		{
			if(s.charAt(left) != s.charAt(right))
			{
				return false;
			}
		}
		return true;
	}
	
	public static boolean validPalindrome(String s)
	{
		for(int left = 0, right = s.length()-1 ; left < right; left++, right--)
		{
			if(s.charAt(left) != s.charAt(right))
			{
				return isvalidPalindrome(s, left+1, right) || isvalidPalindrome(s, left, right-1);
			}
		}		
		return true;
	}
	
	public static void main(String[] args) {
		String s = "abbca";
		
		System.out.println(validPalindrome(s));
	}

}
