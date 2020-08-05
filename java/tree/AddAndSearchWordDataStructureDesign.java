package augustChallenge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddAndSearchWordDataStructureDesign {
	
	/*
	//<Trial01>
	
	//Input:
//	["WordDictionary","addWord","addWord","search","search","search","search","search","search"]
//	[[],["a"],["a"],["."],["a"],["aa"],["a"],[".a"],["a."]]
	//Output: [null,null,null,true,true,true,true,true,true]
	//Expected: [null,null,null,true,true,false,true,false,false]

	//이게 왜 틀려?
	
	//"aa"가 왜 false야? addWord("a") 두번했으면 "aa"가 되야하는거 아냐?
	
	//아 .append("a")를 두번하면
	
	//같은곳에 붙는게 아니라 서로 다른 낱말 취급해라 이건가?
	
	class WordDictionary {

	    StringBuilder sb;
	    
	    public WordDictionary() {
	        sb = new StringBuilder();
	    }
	    
	    public void addWord(String word) {
	        sb.append(word);
	    }
	    
	    public boolean search(String word) {
	        StringBuilder tmp = new StringBuilder();
	        tmp.append(".*(");
	        
	        for(char c : word.toCharArray()){
	            if(c == '.'){
	                tmp.append("[a-z]");
	            } else {
	                tmp.append(c);
	            }
	        }
	        tmp.append(").*");
	        String pattern = tmp.toString();
	            
	        return sb.toString().matches(pattern);
	    }
	}
	
	

	//<Trial02 - Time Limit Exceeded>
	
	//일단 TLE까진 왔다.
	
	//일단 단어끼리의 구분을 스페이스바로 해놈.
	
	//정규표현식에선 \s이 스페이스바이나, 자바에선 \s하면 에러남. 그딴거 없다고. \t(탭), \n(엔터)등은 있는데 스페이스바가 없네 어씨?
	
	//sb.toString().matches("\s가 들어간 regex pattern"); 하면 에러남.
	
	//그래서 아래방식으로 바꿈.
	
	//Pattern pattern=Pattern.compile(tmp.toString());
    //Matcher match=pattern.matcher(sb.toString());
    //match.find();
	
	//Pattern.compile();로 \\s를 컴파일 한 뒤 하면 에러가 안남. 
	
	//지랄났네 지랄났어.
	
	//+ : 최소 1개 혹은 그 이상 n개
	
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;
	
	class WordDictionary {

	    StringBuilder sb;
	    
	    public WordDictionary() {
	        sb = new StringBuilder(" ");
	    }
	    
	    public void addWord(String word) {
	        sb.append(word);
	        sb.append(" ");
	    }
	    
	    public boolean search(String word) {
	        StringBuilder tmp = new StringBuilder();
	        
	        tmp.append("[\\s]+(");
	        
	        for(char c : word.toCharArray()){
	        	tmp.append(c);
	        	
//	            if(c == '.'){
//	                tmp.append("[a-z]");
//	            } else {
//	                tmp.append(c);
//	            }
	            
	        }
	        tmp.append(")[\\s]+");
	        Pattern pattern=Pattern.compile(tmp.toString());
		    Matcher match=pattern.matcher(sb);
	        return match.find();
	    }
	}
					
	//<Trial03 - TLE>
	
	//map에 가장 앞글자 기준으로 넣어도 안되네?
	
	//regex속도를 빠르게 하란건가?
	
	//그건 불가능하지 않나?
	
	//regex를 쓰지 말란건가? 
	
	//그럼 .은 어떻게 처리해 슈벌
	
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	class WordDictionary {

	    Map<Character, StringBuilder> map;
	    StringBuilder sb;
	    
	    public WordDictionary() {
	        map = new HashMap<>();
	    }
	    
	    public void addWord(String word) {
	        Character key = word.charAt(0);
	        if(!map.containsKey(key)){
	            map.put(key, new StringBuilder(" "));
	        }
	        map.get(key).append(word+" ");
	    }
	    
	    public boolean search(String word) {
	        Character key = word.charAt(0);
	        
	        if(map.containsKey(key)){
	            StringBuilder tmp = new StringBuilder();
	            
	            tmp.append("[\\s]+(");

	            for(char c : word.toCharArray()){
	                tmp.append(c);
	            }
	            tmp.append(")[\\s]+");

	            Pattern pattern=Pattern.compile(tmp.toString());
	            Matcher match=pattern.matcher(map.get(key));
	            return match.find();
	            
	        } else if(key == '.'){
                StringBuilder tmp = new StringBuilder();
                tmp.append("[\\s]+(");
                for(char c : word.toCharArray()){
                    tmp.append(c);
                }
                tmp.append(")[\\s]+");
                Pattern pattern=Pattern.compile(tmp.toString());
                
	            for( Map.Entry<Character, StringBuilder> ele : map.entrySet() ){
	                Matcher match=pattern.matcher(ele.getValue());
	                if(match.find()) return true;
	            }
	        }
	        return false;
	    }
	}
	*/


	//<문제풀이1 by LHearen>
	
	//tree
	
	//Runtime: 65 ms
	//Memory Usage: 77.9 MB
	class WordDictionary {
	    
	    static class Trie{
	        Trie[] alphabet = new Trie[26];
	        boolean isWord = false;
	    }
	    
	    Trie tr;
	
	    public WordDictionary() {
	        tr = new Trie();
	    }
	    
	    public void addWord(String word) {
	        Trie root = tr;
	        for(char c : word.toCharArray()){
	            int key = c-'a';
	            if(root.alphabet[key] == null){
	                root.alphabet[key] = new Trie();
	            }
	            root = root.alphabet[key];
	        }
	        root.isWord = true;
	    }
	
	    public boolean search(String word) {
	        return wordSearch(word, tr, 0);
	    }
	    
	    public boolean wordSearch(String word, Trie root, int i){
	        if(root == null) return false;
	        if(i == word.length()) return root.isWord;
	        int key = word.charAt(i)-'a';
	        if(key == -51){
	            for(int j = 0; j < 26; j++){
	                if(wordSearch(word, root.alphabet[j], i+1)){
	                    return true;
	                };
	            }
	            return false;
	        }
	        return root.alphabet[key] != null && wordSearch(word, root.alphabet[key], i+1);
	    }
	}
	
	
	//<문제풀이2 by ElementNotFoundException>
	
	//아 얘도 Hashmap썼는데 쥰내 창의적이게 key를 word.length()로 해놨구나
	
	//TLE뜰때 input이 막
	
	//"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
	//"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
	//"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
	
	//이따구로 나오거든.
	
	//문제풀이1이 섹시하긴 한데 2도 위트있네
	
	//Runtime: 36 ms
	//Memory Usage: 50.6 MB
	
	public class WordDictionary {
		private HashMap<Integer, HashSet<String>> map = new HashMap<>();

		public void addWord(String word) {
		    int L = word.length();
		    if(map.containsKey(L)) {
		        map.get(L).add(word);
		    }
		    else {
		        HashSet<String> set = new HashSet<>();
		        set.add(word);
		        map.put(L, set);
		    }
		}

		public boolean search(String word) {
		    int L = word.length();
		    if(!map.containsKey(L)) return false;
		    HashSet<String> set = map.get(L);
		    for(String str : set) {
		        int i = 0, LL = word.length();
		        while(i<LL) {
		            if(word.charAt(i)!='.' && str.charAt(i)!=word.charAt(i)) break; //.이면 걍 건너뜀
		            ++i;
		        }
		        if(i==LL && (word.charAt(i-1)=='.' || str.charAt(i-1)==word.charAt(i-1))) return true;
		    }
		    return false;
		}
		}
}
