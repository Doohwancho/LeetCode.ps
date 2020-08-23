package augustChallenge;

public class StreamOfCharacters {
	
	
	//<Trial01>
	
	//넣는건 잘 넣은듯 싶은데,
	
	//찾을때가 거시기허네
	
	//바로 이전에 나온 캐릭터로 하면 거시기 할 줄 알았는데 거시기 혀
	
	//아따 거시기 허네
	
	class StreamChecker {
	    
	    class Trie{
	        Trie[] children = new Trie[26];
	        boolean end = false;
	    }

	    Trie root;
	    Trie prev;
	    Character prevC;
	    
	    public StreamChecker(String[] words) {
	        root = new Trie();
	        
	        for(String word : words){
	            Trie tmp = root;
	            for(int i = 0; i < word.length(); i++){
	                char c = word.charAt(i);
	                if(tmp.children[c-'a'] == null){ //이걸 안하면 기존꺼 덮어쓰기 때문에 기존에 이어놓은게 날아가버림
	                    tmp.children[c-'a'] = new Trie();    
	                }
	                tmp = tmp.children[word.charAt(i)-'a'];
	            }
	            tmp.end = true;
	        }
	    }
	    
	    public boolean query(char letter) {
	        if(prev == null){
	            prev = root;
	            if(prev.children[letter-'a'] != null){
	                prev = prev.children[letter-'a'];
	                prevC = letter;
	                return prev.end;
	            } else {
	                return false;
	            }
	        } else {
	            if(prev.children[letter-'a'] != null){
	                prev = prev.children[letter-'a'];
	                prevC = letter;
	                return prev.end;
	            } else {
	                if(prevC == letter){
	                    return root.children[letter-'a'] != null && root.children[letter-'a'].end;
	                } else {
	                    prev = null;    
	                    return root.children[letter-'a'] != null && root.children[letter-'a'].end;
	                }
	            }
	        }
	    }
	}
	
	
	//<문제풀이1 by Self_learner>
	
	//얜 단어를 까꾸로 넣음
	
	//stringbuilder에 순서대로 붙이고
	
	//맨 뒤에애 부터 보면서 query()의 파라미터로 온 캐릭터가 끝단인지만 보면 됨
	
	//만약 까꾸로 안넣고 순서대로 넣었으면, abcde해서 e 안되면 de보고, 또 안되면 cde보고, 또 안되면 bcde보고 
	
	//이걸 root부터 계속 돌려야 되서 n^2나옴 개 비효율적
	
	//Runtime: 202 ms
	//Memory Usage: 138.6 MB
	
	
	class StreamChecker {
	    
	    class TrieNode {
	        boolean isWord;
	        TrieNode[] next = new TrieNode[26];
	    }

	    TrieNode root = new TrieNode();
	    StringBuilder sb = new StringBuilder();

	    public StreamChecker(String[] words) {
	        createTrie(words);
	    }

	    public boolean query(char letter) {
	        sb.append(letter);
	        TrieNode node = root;
	        for (int i = sb.length() - 1; i >= 0 && node != null; i--) {
	            char c = sb.charAt(i);
	            node = node.next[c - 'a'];
	            if (node != null && node.isWord) {
	                return true;
	            }
	        }
	        return false;
	    }

	    private void createTrie(String[] words) {
	        for (String s : words) {
	            TrieNode node = root;
	            int len = s.length();
	            for (int i = len - 1; i >= 0; i--) {
	                char c = s.charAt(i);
	                if (node.next[c - 'a'] == null) {
	                    node.next[c - 'a'] = new TrieNode();
	                }
	                node = node.next[c - 'a'];
	            }
	            node.isWord = true;
	        }
	    }
	}
}
