/*

	Given a pattern and a string str, find if str follows the same pattern.
	
	Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
	
	Example 1:
	
	Input: pattern = "abba", str = "dog cat cat dog"
	Output: true
	Example 2:
	
	Input:pattern = "abba", str = "dog cat cat fish"
	Output: false
	Example 3:
	
	Input: pattern = "aaaa", str = "dog cat cat dog"
	Output: false
	Example 4:
	
	Input: pattern = "abba", str = "dog dog dog dog"
	Output: false
	
	
	
	
	
	<문제>
	
	pattern과 str 문자열이 다음과 같이 주어진다.
	
	pattern = "abba", str = "dog cat cat dog"
	
	여기서 패턴의 a는 str에 dog를 나타내고, b는 cat을 나타내는 것을 유추할 수 있다.
	
	만약 기존 패턴과 다르게 나온다면 false를 반환한다.
	
	예를들어, pattern = "abba", str = "dog cat cat cow"
	
	라면, 마지막 인덱스에서 dog가 나와야 하는데 cow가 나왔으므로, false를 반환한다.
	
 */

package HashTable;

import java.util.HashMap;
import java.util.Map;

public class WordPattern290 {

	/*
	// <문제풀이1>

	// pattern = "ab", str = "dog dog" 이나

	// pattern = "aa", str = "dog cat"

	// 의 경우, pattern에 대응하는 str만 체크해 주어야 할 뿐만 아니라,

	// str에 대응하는 pattern도 체크해 주어야 하기 때문에,

	// map에서 key:value 저장시, pattern:str도 저장하고, str:pattern도 저장해서,

	// 매 loop마다 그 두 부분이 맞는지 체크한다.

	// S에서 넘겨주는 값이 char이고, T에서 넘겨주는 값이 String인데, 같은 로직의 match함수를 사용해야 했으므로,

	// 오버라이딩을 사용함.

	// 완벽한 답인줄 알았는데 의외로 이것보다 좋은 답이 있었음.

	// 3ms에 메모리 97%정도면 양호하다고 생각.

	// Runtime: 3 ms, faster than 15.27% of Java online submissions for Word
	// Pattern.
	// Memory Usage: 34.4 MB, less than 97.30% of Java online submissions for Word
	// Pattern.

	public static boolean wordPattern(String pattern, String str) {
		Map map = new HashMap<>();
		char[] S = pattern.toCharArray();
		String[] T = str.split("\\s");

		if (S.length != T.length)
			return false;

		for (int i = 0; i < pattern.length(); i++) {
			if (match(S[i], T[i], map) || match(T[i], S[i], map))
				return false;
		}
		return true;
	}

	// 기존에 있는지 체크, 없으면 넣어주기 + key:value값 맞는지 확인
	private static boolean match(char s, String t, Map map) {
		if (map.get(s) == null)
			map.put(s, t);
		return !map.get(s).equals(t);
	}

	private static boolean match(String s, char t, Map map) {
		if (map.get(s) == null)
			map.put(s, t);
		return !map.get(s).equals(t);
	}
	*/
	
	//<문제풀이2 by StefanPochmann>
	
	//문제풀이 1과 큰 로직은 같은데, 다른점은
	
	//문제풀이1은 map에 값 저장시, value값을 char이나 string으로 해서
	
	//매 loop마다 .equals()로 string을 비교해서 2ms느렸는데,
	
	//이 사람은 value값에 인덱스값을 저장하고 integer을 ==으로 비교해서 훨씬 빨리 나옴.

	//또한, map.put()의 리턴값이, 예전에 넣어놨던 키값으로 하면 null이 뜨는게 아니라
	
	//기존에 넣어놨던 key값의 value가 뜨기 때문에, 이 것으로 비교한 점이 인상적.
	
	//for문에서 int로 선언하면, !=로 비교가 안되고, !Objects.equals(a, b);로 비교해줘야하는데,
	
	//Integer로 선언했기때문에 !=로 간단히 비교 가능.
	
	//Runtime: 1 ms, faster than 99.56% of Java online submissions for Word Pattern.
	//Memory Usage: 34.2 MB, less than 97.30% of Java online submissions for Word Pattern.

	public static boolean wordPattern(String pattern, String str) {
		String[] words = str.split(" ");
		if (words.length != pattern.length())
			return false;
		Map index = new HashMap();
		for (Integer i = 0; i < words.length; ++i)
			if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
				return false;
		return true;
	}

	public static void main(String[] args) {
		String pattern = "abba";
		String str = "dog cat cat cow";

		System.out.println(wordPattern(pattern, str));
	}
}
