/*
	Given a string s and a string t, check if s is subsequence of t.
	
	You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
	
	A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
	
	Example 1:
	s = "abc", t = "ahbgdc"
	
	Return true.
	
	Example 2:
	s = "axc", t = "ahbgdc"
	
	Return false.
	
	Follow up:
	If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?

	
	
	
	<문제>
	
	문자열 s,t가 다음과 같이 주어졌다고 해보자. 
	
	s = "abc", t = "ahbgdc"
	
	이 때, s는 t의 substring이다.
	
	비록 a,b,c사이에 다른 알파벳들이 있다고 해도, t에 a,b,c가 순서있게 나타났기 때문이다.
	
	 고로, true를 반환한다.
	 
	 마찬가지로, s = "hbc"나, s = bdc"나, s = "agd"여도 true를 반환한다.
	 
	 하지만, s = "hcg"라면 false를 반환한다.
	 
	 s의 모든 알파벳이 t에 있지만, 원래 c가 g보다 늦게 위치해있기 때문이다.
	
 */

package BinarySearch;

public class IsSubsequence392 {
	
	/*
	//<문제풀이1>
	
	//2중 for문으로 돌면서, s에 알파벳을 찾았으면, 해당 인덱스+1부터 다시 loop하는 방식.
	
	//Runtime: 6 ms, faster than 87.40% of Java online submissions for Is Subsequence.
	//Memory Usage: 48.9 MB, less than 100.00% of Java online submissions for Is Subsequence.
	
	public static boolean isSubsequence(String s, String t) {
		if(t.length()<s.length()) return false;
		
		char[] t_charset = t.toCharArray();
		int t_len = t.length()-1;
		int idx = 0;
		
		for(char s_char : s.toCharArray()) {
			if(idx > t_len) return false;
			for(int i = idx; i < t.length(); i++) {
				if(s_char == t_charset[i]) {
					idx = i+1;
					break;
				}
				if(i == t_len) return false;
			}
		}
		return true;
    }
    */
	
	
	//<문제풀이2 by fabrizio3>
	
	//일단 길이가 긴 t대신 s를 .toCharArray()하기 때문에 시간단축.
	
	//큰 틀은 문제풀이1과 같음. 대신,
	
	//t.indexOf(ch, fromIndex)를 이용한 방식. 
	
	//만약 못찾았으면 -1을 반환함.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Is Subsequence.
	//Memory Usage: 49.3 MB, less than 100.00% of Java online submissions for Is Subsequence.
	
	public static boolean isSubsequence(String s, String t) {
		if(t.length()<s.length()) return false;
        int lastFoundIndex = 0;
        char[] charsS = s.toCharArray();
        for(int i=0; i<charsS.length; i++) {
            int currentCharIndex = t.indexOf(charsS[i],lastFoundIndex);
            if(currentCharIndex==-1 || t.length()-currentCharIndex<charsS.length-i)  {
                return false;
            } else {
                lastFoundIndex = currentCharIndex + 1;
            }
        }
        return true;    
    }
	
	public static void main(String[] args) {
		String s = "acb";
		String t = "ahbgdc";
		System.out.println(isSubsequence(s,t));
	}
}













