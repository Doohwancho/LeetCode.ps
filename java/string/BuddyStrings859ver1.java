/*
 * 
	Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.
	
	 
	
	Example 1:
	
	Input: A = "ab", B = "ba"
	Output: true
	Example 2:
	
	Input: A = "ab", B = "ab"
	Output: false
	Example 3:
	
	Input: A = "aa", B = "aa"
	Output: true
	Example 4:
	
	Input: A = "aaaaaaabc", B = "aaaaaaacb"
	Output: true
	Example 5:
	
	Input: A = "", B = "aa"
	Output: false
	
	
	
	<문제>
	
	문자열 A와 B가 주어지면, A중에 2개의 문자의 위치를 서로 바꿔서 B를 만들 수 있다면 true, 아니면 false를 반환하라.
	
	예를들어, A = "ab", B = "ba" 인 경우, "ab"의 "a"와 "b"를 서로 바꾸면 B인 "ba"되므로, true를 반환한다.
	
	
	
	<문제풀이 - trial01>
	String A = "abab", B = "abab"; 에서 막힘
	
	step01. A와 B의 문자열의 길이가 서로 다르다면, 두개 문자의 위치를 바꿔도 어짜피 다르게 나오기 때문에 false 반환
	
	step02. A와 B의 길이가 같다는것을 확인했으면, for문으로 돌면서, 같은 인덱스의 서로 다른 문자열을 StringBuilder에 담음(A는 Aswap에, B는 Bswap에)
	
	step03. 만약 A = "aa" B = "aa"처럼, 같은 인덱스의 값이 같고 서로 같은 문자열이여서 StringBuilder에 안담기는 경우를 고려하여, duplicate이라는 변수를 만듬. "aaaa"처럼 모든 값이 같다면 나중에 반환시 true 조건식으로 처리 
	
	step04. Aswap을 뒤집어서 Bswap과 일치하는지 확인
	
 */


package String;

public class BuddyStrings859ver1 {
	public static boolean buddyStrings(String A, String B)
	{
		
		if(A.length()!= B.length() || A.length() == 0 || B.length() == 0) { return false; }
		
		StringBuilder Aswap = new StringBuilder();
		StringBuilder Bswap = new StringBuilder();
		boolean duplicate = true;                  //"aa" "aa"인 경우 예외처리 ("ab" "ab"일 때와 "aa" "aa"일 때의 차이를 구분)
		
		for(int i = 0; i < A.length(); i++)
		{
			if(duplicate && i > 0 && !(A.charAt(i) == A.charAt(i-1)))
			{
				duplicate = false;
			}
			if(A.charAt(i) == B.charAt(i))
			{
				continue;
			}
			else 
			{
				Aswap.append(A.charAt(i));
				Bswap.append(B.charAt(i));
			}	
		}
		String compare = Aswap.reverse().toString();
		
		return (Aswap.length()>0 || duplicate) ? compare.equals(new String(Bswap.toString())) : false;

	}
	
	public static void main(String[] args) 
	{
		//String A = "ab", B = "ba";
		//String A = "aaaaaaabc", B = "aaaaaaacb";
		//String A = "ab", B = "ab";
		//String A = "aa", B = "aa";
		String A = "abab", B = "abab";
		
		
		System.out.println(buddyStrings(A,B));
	}
}
