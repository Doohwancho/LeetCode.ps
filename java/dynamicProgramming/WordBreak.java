package september;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordBreak {

	// <Trial01 - time limit exceeded>

	// input :
	// "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
	// aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
	// ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]

	// dfs

	// 인풋 더럽다!!!!! 정상적인건 잘 되는디

	private boolean dfs(Map<Character, List<String>> map, String s, int i) {
		if (i == s.length())
			return true;
		if (i > s.length())
			return false;

		List<String> tmp = map.getOrDefault(s.charAt(i), null);
		if (tmp != null) {
			for (String str : tmp) {
				if ((i + str.length() <= s.length()) && str.equals(s.substring(i, i + str.length()))
						&& dfs(map, s, i + str.length())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean wordBreak(String s, List<String> wordDict) {
		Map<Character, List<String>> map = new HashMap<>();
		for (String word : wordDict) {
			Character key = word.charAt(0);
			List<String> tmp = map.getOrDefault(key, new ArrayList<>());
			tmp.add(word);
			map.put(key, tmp);
		}
		return dfs(map, s, 0);
	}
	
	
	
	//<문제풀이1 by jeantimex> 
	
	//dp
	
	/*

	|T| | | | | | | | |
	0 1 2 3 4 5 6 7 8

	i = 1
	j = o sub = l

	i = 2
	j = 0 sub = le
	j = 1 sub = e

	i = 3
	j = 0 sub = lee
	j = 1 sub = ee
	j = 2 sub = e

	i = 4
	j = 0 sub = leet && T[0] and then break, (j == 0)
	|T | | | |T| | | | |
	0 1 2 3 4 5 6 7 8

	i = 5
	j = 0 sub = leetc
	j = 1 sub = eetc
	j = 2 sub = etc
	j = 3 sub = tc
	j = 4 sub = c

	i = 6
	j = 0 sub = leetco
	j = 1 sub = eetco
	j = 2 sub = etco
	j = 3 sub = tco
	j = 4 sub = co
	j = 5 sub = o

	i = 7
	j = 0 sub = leetcod
	j = 1 sub = eetcod
	j = 2 sub = etcod
	j = 3 sub = tcod
	j = 4 sub = cod
	j = 5 sub = od
	j = 6 sub = d

	i = 8
	j = 0 sub = leetcode
	j = 1 sub = eetcode
	j = 2 sub = etcode
	j = 3 sub = tcode
	j = 4 sub = code && T[4] and then break (dp[j-1] == true)

	|T| | | |T| | | | T|
	0 1 2 3 4 5 6 7 8
	*/
	
	//Runtime: 10 ms
	//Memory Usage: 39.4 MB

	public boolean wordBreak(String s, List<String> wordDict) {
		if (s == null || s.length() == 0)
			return false;

		int n = s.length();

		// dp[i] represents whether s[0...i] can be formed by dict
		boolean[] dp = new boolean[n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				String sub = s.substring(j, i + 1);

				if (wordDict.contains(sub) && (j == 0 || dp[j - 1])) {
					dp[i] = true;
					break; 
				}
			}
		}

		return dp[n - 1];
	}
}
