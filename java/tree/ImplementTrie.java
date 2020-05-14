package mayChallenge;

public class ImplementTrie {

	
	
	//<문제풀이1>
	
	//binary search tree
	
	//이맛이제~
	
	//Runtime: 33 ms
	//Memory Usage: 46.9 MB
	
	class Trie {

	    String val; 
	    Trie left;
	    Trie right;
	    
	    /** Initialize your data structure here. */
	    public Trie() {
	        val = null;
	        left = null;
	        right = null;
	    }
	    
	    
	    /** Inserts a word into the trie. */
	    public void insert(String word) {
	        if(val == null){
	            this.val = new String(word); 
	        }
	        else if(val.compareTo(word) < 0){
	            if(this.left == null){
	                left = new Trie();
	            }
	            this.left.insert(word);
	        } else {
	            if(this.right == null){
	                right = new Trie();
	            }
	            this.right.insert(word);
	        }
	    }
	    
	    /** Returns if the word is in the trie. */
	    public boolean search(String word) {
	        if(val == null){
	            return false;
	        }
	        if(val.equals(word)){
	            return true;
	        }
	        else if(val.compareTo(word) < 0){
	            return this.left != null ? this.left.search(word) : false;
	        } else {
	            return this.right != null ? this.right.search(word) : false;
	        }
	    }
	    
	    /** Returns if there is any word in the trie that starts with the given prefix. */
	    public boolean startsWith(String prefix) {

	        if(this.val == null){
	            return false;
	        }
	        
	        int compare = val.compareTo(prefix);
	        
	        if(compare == 0){ 
	            return true;
	        }
	        else if(compare > 0){ 
	            for(int i = 0; i < prefix.length() && i < val.length(); i++){
	                if(val.charAt(i) != prefix.charAt(i)){
	                    break;
	                }
	                if(i == prefix.length()-1){
	                    return true;
	                }
	            }
	            return this.right != null ? this.right.startsWith(prefix) : false;
	        } else { 
	            return this.left != null ? this.left.startsWith(prefix) : false;
	        }
	    }
	    
	}	
}
