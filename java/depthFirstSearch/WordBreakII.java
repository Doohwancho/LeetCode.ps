package julyChallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordBreakII {
	
	
	//<Trial01 - Time Limit Exceeded>
	
	//쳇
	
	//stringbuilder써야지
	
    static List<String> rst;
    
    private static void dfs(String s, Map<Character, List<String>> map, int idx, String acc){
        if(idx == s.length()){
            rst.add(acc.trim());
            return;
        } 
        else if(idx > s.length()) return;
        
        if(map.containsKey(s.charAt(idx))){
            List<String> tmp = map.get(s.charAt(idx));
            for(String str : tmp){
                if(idx+str.length() <= s.length() && s.substring(idx, idx+str.length()).equals(str)){
                    dfs(s, map, idx+str.length(), acc+str+" ");
                }
            }
        } else{
            return;
        }
        
    }
    
    public static List<String> wordBreak(String s, List<String> wordDict) {
        rst = new ArrayList<>();
        Map<Character, List<String>> map = new HashMap<>();
        for(String str : wordDict){
            if(!map.containsKey(str.charAt(0))){
                map.put(str.charAt(0), new ArrayList<>());
            }
            map.get(str.charAt(0)).add(str);
        }
        dfs(s, map, 0, "");
        return rst;
    }
    
	
	//<Trial02 - Time Limit Exceeded>
	
	//stringbuilder로 바꿔도 안되네?
	
	//근본적으로 틀렸단 얘기네?
	
    static List<String> rst;
    
    private static void dfs(String s, Map<Character, List<String>> map, int idx, StringBuilder acc){
        if(idx == s.length()){
            rst.add(acc.toString().trim());
            return;
        } 
        else if(idx > s.length()) return;
        
        if(map.containsKey(s.charAt(idx))){
            List<String> tmp = map.get(s.charAt(idx));
            for(String str : tmp){
                if(idx+str.length() <= s.length() && s.substring(idx, idx+str.length()).equals(str)){
                	StringBuilder sb = new StringBuilder(acc);
                    dfs(s, map, idx+str.length(), sb.append(str).append(" "));
                }
            }
        } else{
            return;
        }
        
    }
    
    public static List<String> wordBreak(String s, List<String> wordDict) {
        rst = new ArrayList<>();
        Map<Character, List<String>> map = new HashMap<>();
        for(String str : wordDict){
            if(!map.containsKey(str.charAt(0))){
                map.put(str.charAt(0), new ArrayList<>());
            }
            map.get(str.charAt(0)).add(str);
        }
        dfs(s, map, 0, new StringBuilder());
        return rst;
    }
    
    
    //<문제풀이1 by Shradha1994>
    
    //dfs
    
    //어씨 똑같은 dfs인데 얜 되고 난 안돼?
    
    //아 얜 맵 안쓰고 그냥 wordDict를 loop했네?
    
    //근데 wordDict가 쥰내 커지면 우짤낀데
    
    //우짤끼냐고 ㅠㅠㅠㅠ 
    
    //Runtime: 3 ms
    //Memory Usage: 40.4 MB
    
    public List<String> wordBreak(String s, List<String> wordDict) {
		if (s.length() > 100) return new ArrayList(); //이건 s가 "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa .... "같은 비정상적인애 패스하기위함.

		List<String> result = new ArrayList<String> ();
		wordBreakUtil(s, wordDict, result, new StringBuilder());
		return result;
	}
    public void wordBreakUtil(String s, List<String> wordDict, List<String> result, StringBuilder subList) {
        // add " " between 2 words in subList
		if (subList.length() != 0) {
			subList.append(" ");
		}
		// iterate over all the words in wordDict
        for (String word: wordDict) {
			
			if (s.startsWith(word)) {
				StringBuilder sb = new StringBuilder(subList);
			    // append current match in sb
				sb.append(word); 
			    // if this is last word to be matched
				if (s.equals(word)) {
					result.add(new String(sb));
				} else {
					wordBreakUtil(s.substring(word.length()), wordDict, result, sb);
				}
            }
		}
	}
}
