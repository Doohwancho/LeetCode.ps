package HashTable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.tree.TreeNode;

/*
	Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.
	
	If there is no answer, return the empty string.
	Example 1:
	Input: 
	words = ["w","wo","wor","worl", "world"]
	Output: "world"
	Explanation: 
	The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
	Example 2:
	Input: 
	words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
	Output: "apple"
	Explanation: 
	Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".



	<문제>
	
	words에 다양한 String이 나오는데, 한글자씩 추가되는 형태로 나온다. "w", "wo","wor","worl","world 이런 식으로.
	
	중간에 엉뚱한 문자들이 섞여있는건 무시하고, 위에서 가장 긴 문자를 반환하면 된다.
	
	단, "world"랑 "worle"이 나왔으면, 맨 끝에 d가 e보다 사전적으로 앞에 나오니까, "world"를 반환한다.
 */

public class LongestWordInDictionary720 {

	/*
	 * //<Trial01>
	 * 
	 * //set에 모든 문자열을 담고, 다시 loop 돌면서, 마지막 알파벳을 제외한 나머지 문자열이 set안에 있는지 .contains로
	 * 확인.
	 * 
	 * //만약 있는데 rst의 길이가 그 단어의 길이보다 짧으면, 알파벳 한개가 추가되었다는 의미니까 그냥 rst에 그 단어를 넣고,
	 * 
	 * //만약 rst랑 그 단어의 길이가 같다면, 맨 마지막 알파벳이 사전적으로 어느게 먼저나오는지 판단하여 rst에 넣음.
	 * 
	 * //안돌아가는 이유 : 단어 한개에는 적용가능. 근데 두개 이상은 적용 불가.
	 */
	
//	public static String longestWord(String[] words) {
//		String rst = "";
//		Set<String> set = new HashSet<>();
//		for (String s : words)
//			set.add(s);
//		for (String s : words) {
//			if (set.contains(s.substring(0, s.length() - 1))) {
//				if (rst.length() < s.length())
//					rst = s;
//				else if (rst.charAt(rst.length() - 1) - s.charAt(s.length() - 1) > 0)
//					rst = s;
//			}
//		}
//		return rst;
//	}

	/*
	 * //<문제풀이 by alexander>
	 * 
	 * //먼저 words를 오름차순 정렬 한 후, loop돈다.
	 * 
	 * //loop 돌면서 각 단어를 set에 추가시킴 + 단어에 마지막 알파벳을 제외한것이 set에 있는지 확인을 동시에 함.
	 * 
	 * //사전적으로 앞에있냐 뒤에있냐 판가름은 Arrays.sort()에서 이미 해결.
	 * 
	 * //사전에서 앞에 나오는 단어가 먼저 들어가기 때문에, 그 단어보다 더 긴 단어가 나오지 않는이상, 같은 길이에 맨 뒤 알파벳만 다른
	 * 단어들은 무시됨.
	 * 
	 * //Runtime: 14 ms, faster than 53.29% of Java online submissions for Longest
	 * Word in Dictionary. //Memory Usage: 36.7 MB, less than 90.63% of Java online
	 * submissions for Longest Word in Dictionary.
	 */
//	public static String longestWord(String[] words) {
//		Arrays.sort(words);
//		Set<String> built = new HashSet<String>();
//		String res = "";
//		for (String w : words) {
//			if (w.length() == 1 || built.contains(w.substring(0, w.length() - 1))) {
//				res = w.length() > res.length() ? w : res;
//				built.add(w);
//			}
//		}
//		return res;
//	}

	
	
	
	/*
	 * <문제풀이 by tankztc>
	 * 
	 * 트리를 활용하니 약 3배 빠르다.
	 * 
	 * Runtime: 5 ms, faster than 97.12% of Java online submissions for Longest Word in Dictionary.
	 * Memory Usage: 37.8 MB, less than 81.25% of Java online submissions for Longest Word in Dictionary.
	 */
	


	static int maxLen = 0;
	static String longestWord;

	static void dfs(TrieNode root, int depth) {
		if (!root.isWord)
			return;

		if (depth > maxLen) { //depth가 크다는 말은, 단어의 길이가 더 길다는 것.
			maxLen = depth;
			longestWord = root.word;
		}

		for (int i = 0; i < 26; ++i) {
			if (root.children[i] != null) {
				dfs(root.children[i], depth + 1);
			}
		}
	}
	public static String longestWord(String[] words) {
		TrieNode root = buildTrie(words);
		for (int i = 0; i < 26; ++i) {        //알파벳 소문자가 26개고, buildTrie()함수에서 각 단어의 알파벳 소문자를 숫자화 한것을 노드의 인덱스를 하여 저장하였음. 
			if (root.children[i] != null) {   //애초에 저장하고 loop돌릴때 i가 0, 그러니까 'a'부터 시작하니, 자동적으로 lexicographical하게 됨
				dfs(root.children[i], 1);
			}
		}
		return longestWord;
	}

	static TrieNode buildTrie(String[] words) {
		TrieNode root = new TrieNode(); //static으로 선언된 함수라, class TrieNode를 이 클래스 안에 넣으면, TrieNode를 읽기 전에 이 함수가 읽히는데, 이 때, TrieNode가 없으므로 에러. 따라서 TrieNode 클래스는, 이 클래스 밖에 써주어야 함.
										//루트의 children들은 words에 나오는 각 단어들의 첫번째 알파벳이 됨. 그 첫번째 알파벳의 children은 그 단어의 두번째 알파벳 ... 꼬리에 꼬리를 뭄.
		for (String word : words) {
			TrieNode p = root;

			for (char c : word.toCharArray()) {
				if (p.children[c - 'a'] == null) {       //각 노드의 인덱스를, words에서 나오는 문자열들을 구성하는 알파벳이 가리키는 숫자들로 구성. 그리고 해당 노드의 word는 그 알파벳이 포함되었던 단어로 구성. 
					p.children[c - 'a'] = new TrieNode();//world라고 치면, w노드의 children인 o노드의 children인 r노드의 children인 l노드의 children인 d노드 식으로 구성됨.
				}										 //나중엔 이 depth가 얼만큼 그냐가 곧 이 단어의 길이 이므로, depth가 가장 큰 단어를 반환하게 됨.
				p = p.children[c - 'a'];
			}
			p.isWord = true;
			p.word = word;
		}
		return root;
	}


	public static void main(String[] args) {
		//String[] words = { "a", "banana", "app", "appl", "ap", "appld", "apple","appla" };
		// String[] words =
		// {"b","br","bre","brea","break","breakf","breakfa","breakfas","breakfast","l","lu","lun","lunc","lunch","d","di","din","dinn","dinne","dinner"};
		String[] words = {"a","ab","ac","aa"};
		
		System.out.println(longestWord(words));
	}
}

class TrieNode {
	TrieNode[] children = new TrieNode[26];
	boolean isWord;
	String word;
}