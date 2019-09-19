package HashTable;


/*
	Given two strings s and t , write a function to determine if t is an anagram of s.
	
	Example 1:
	
	Input: s = "anagram", t = "nagaram"
	Output: true
	Example 2:
	
	Input: s = "rat", t = "car"
	Output: false



	<문제>
	
	스트링 타입의 문자열 s,t가 주어지는데, s와 t가 anagram인지 확인하여 anagram이면 true를, 아니면 false를 반환하라.
	
	anagram이란, 문자를 더하거나 빼지않고, 순서를 다르게 하여 만들 수 있는 단어이다.
	
	알파벳 대문자는 등장하지 않고, 소문자만 사용하기로 한다.
 */

public class ValidAnagram242 {

	/*
	//<문제풀이1>
	
    //new int[26]에 s의 알파벳의 빈도수를 측정하고, t를 loop돌면서 1씩 빼며 확인하는데,
	//만약 s에서 없는 단어가 t에서 한번 더 나왔으면, 빈도수가 마이너스이니, 그땐 false를 반환하고,
	//한번 덜 나왔다고 하더라도, 원래 없던 문자를 빼니 마이너스가 나오니, false를 반환하면 된다.
	//따라서 t에 새로운 문자가 나오던, s에 나왔던 문자가 중복해서 나오던, loop돌면서 빼다가 마이너스 뜨면 false를 반환하면 되겠다.
	//유효성 검사로 s의 길이와 t의 길이가 서로 다르면, 어짜피 문자의 순서를 바꿔서 볼 필요도 없이 false니, 이 부분도 넣는게 좋겠다.
	
	//Runtime: 3 ms, faster than 93.23% of Java online submissions for Valid Anagram.
	//Memory Usage: 36.1 MB, less than 98.06% of Java online submissions for Valid Anagram.
	
	public static boolean isAnagram(String s, String t) {
		if(s.length() != t.length()) return false;
		int[] container = new int[26];
		for(char c : s.toCharArray()) container[c-'a']++;
		for(char c : t.toCharArray()) if(--container[c-'a'] < 0) return false;
		return true;
    }
    */
	
	
	//<문제풀이2 by jinwu>
	
	//for문 한번에, s와 t의 문자를 동시에 체크하면서,
	
	//다시한면 int[26]를 loop돌면서, +- 제로인지 확인하는 방법.
	
	//문제풀이1과 거의 동일.
	
	//Runtime: 3 ms, faster than 93.23% of Java online submissions for Valid Anagram.
	//Memory Usage: 36.3 MB, less than 98.06% of Java online submissions for Valid Anagram.
	
	public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
	    
	    int[] count = new int[26];
	
	    for(int i = 0; i < s.length(); i++) {
	        count[s.charAt(i) - 'a']++;
	        count[t.charAt(i) - 'a']--;
	    }
	    
	    for(int x : count) if(x != 0) return false;
	    
	    return true;
    }
	
	
	public static void main(String[] args) {
		String s = "anagram";
		String t = "nagaram";
		
		System.out.println(isAnagram(s, t));
	}
}
