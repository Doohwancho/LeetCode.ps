package augustChallenge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddAndSearchWordDataStructureDesign {
	
	//<Trial01>
	
	//Input:
	//["WordDictionary","addWord","addWord","search","search","search","search","search","search"]
	//[[],["a"],["a"],["."],["a"],["aa"],["a"],[".a"],["a."]]
	//Output: [null,null,null,true,true,true,true,true,true]
	//Expected: [null,null,null,true,true,false,true,false,false]

	//이게 왜 틀려?
	
	//"aa"가 왜 false야? addWord("a") 두번했으면 "aa"가 되야하는거 아냐?
	
	//아 .append("a")를 두번하면
	
	//같은곳에 붙는게 아니라 서로 다른 낱말 취급해라 이건가?
	
	class WordDictionary {

	    StringBuilder sb;
	    
	    /** Initialize your data structure here. */
	    public WordDictionary() {
	        sb = new StringBuilder();
	    }
	    
	    /** Adds a word into the data structure. */
	    public void addWord(String word) {
	        sb.append(word);
	    }
	    
	    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
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
	    
	    /** Initialize your data structure here. */
	    public WordDictionary() {
	        sb = new StringBuilder(" ");
	    }
	    
	    /** Adds a word into the data structure. */
	    public void addWord(String word) {
	        sb.append(word);
	        sb.append(" ");
	    }
	    
	    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
	    public boolean search(String word) {
	        StringBuilder tmp = new StringBuilder();
	        
	        tmp.append("[\\s]+(");
	        
	        for(char c : word.toCharArray()){
	        	tmp.append(c);
	        	/*
	            if(c == '.'){
	                tmp.append("[a-z]");
	            } else {
	                tmp.append(c);
	            }
	            */
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
	    
	    /** Initialize your data structure here. */
	    public WordDictionary() {
	        map = new HashMap<>();
	    }
	    
	    /** Adds a word into the data structure. */
	    public void addWord(String word) {
	        Character key = word.charAt(0);
	        if(!map.containsKey(key)){
	            map.put(key, new StringBuilder(" "));
	        }
	        map.get(key).append(word+" ");
	    }
	    
	    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
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
	            for( Map.Entry<Character, StringBuilder> ele : map.entrySet() ){
	                StringBuilder tmp = new StringBuilder();
	            
	                tmp.append("[\\s]+(");

	                for(char c : word.toCharArray()){
	                    tmp.append(c);
	                }
	                tmp.append(")[\\s]+");

	                Pattern pattern=Pattern.compile(tmp.toString());
	                Matcher match=pattern.matcher(ele.getValue());
	                if(match.find()) return true;
	            }
	        }
	        return false;
	    }
	}


}
